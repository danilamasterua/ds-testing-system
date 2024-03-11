package ds.testingsystem.web.commands.test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import ds.testingsystem.data.model.*;
import ds.testingsystem.data.model.bean.enums.QuestionType;
import ds.testingsystem.data.repos.TestRepository;
import ds.testingsystem.web.commands.Command;
import ds.testingsystem.web.commands.ErrorCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SaveOrUpdateCommand extends Command {
    TestRepository repository = new TestRepository(Test.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("400 --- This command does not support GET method");
        return new ErrorCommand("400", "This command does not support GET method").execute(req, resp);
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse rep, JsonObject obj) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("currentUser");
        Test test = getTestObject(session);
        test.setName(obj.get("name").getAsString());
        test.setDescription(obj.get("description").getAsString());
        test.updateModules(getModulesFromJson(obj));
        test.setOwner(user);
        if(test.getId()==null) {
            repository.add(test);
        } else {
            repository.update(test);
        }
        session.setAttribute("currentCreatingTest", test);
        return "true";
    }

    private List<TestModule> getModulesFromJson(JsonObject obj){
        List<TestModule> moduleSet = new ArrayList<>();
        JsonArray modules = obj.get("modules").getAsJsonArray();
        for (var module:modules){
            TestModule newModule = new TestModule();
            newModule.setName(module.getAsJsonObject().get("name").getAsString());
            newModule.setCountOfQuestion(0);
            Set<Question> questionSet = new HashSet<>();
            JsonArray questions = module.getAsJsonObject().get("questions").getAsJsonArray();
            for(var question:questions){
                Question newQuestion = new Question();
                newQuestion.setDescription(question.getAsJsonObject().get("description").getAsString());
                newQuestion.setQuestionType(QuestionType.valueOf(question.getAsJsonObject().get("type").getAsString()));
                Set<AnswerVariant> answerVariants = new HashSet<>();
                JsonArray vars = question.getAsJsonObject().get("vars").getAsJsonArray();
                for(var variant:vars){
                    AnswerVariant newVar = new AnswerVariant();
                    newVar.setDescription(variant.getAsJsonObject().get("description").getAsString());
                    newVar.setRight(variant.getAsJsonObject().get("isRight").getAsBoolean());
                    answerVariants.add(newVar);
                }
                newQuestion.setAnswerVariants(answerVariants);
                questionSet.add(newQuestion);
            }
            newModule.setQuestions(questionSet);
            moduleSet.add(newModule);
        }
        return moduleSet;
    }

    private Test getTestObject(HttpSession session){
        Test test = (Test) session.getAttribute("currentCreatingTest");
        if(test == null){
            test = new Test();
        }
        return test;
    }
}
