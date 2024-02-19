package ds.testingsystem.web.commands.test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import ds.testingsystem.data.model.AnswerVariant;
import ds.testingsystem.data.model.Question;
import ds.testingsystem.data.model.Test;
import ds.testingsystem.data.model.TestModule;
import ds.testingsystem.data.model.bean.enums.QuestionType;
import ds.testingsystem.web.commands.Command;
import ds.testingsystem.web.commands.InvalidCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SaveOrUpdateCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("400 --- This command does not support GET method");
        return new InvalidCommand("400", "This command does not support GET method").execute(req, resp);
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse rep, JsonObject obj) throws IOException, ServletException {
        Test test = new Test();
        test.setName(obj.get("name").getAsString());
        test.setDescription(obj.get("description").getAsString());
        test.setModules(getModulesFromJson(obj));
        System.out.println(test);
        return "true";
    }

    private Set<TestModule> getModulesFromJson(JsonObject obj){
        Set<TestModule> moduleSet = new HashSet<>();
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
}
