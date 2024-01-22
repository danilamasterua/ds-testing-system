package ds.testingsystem.data.repos;

import ds.testingsystem.data.model.Question;
import ds.testingsystem.data.repos.template.Repository;

public class QuestionRepository extends Repository<Question, Long> {
    public QuestionRepository(Class<Question> clazz) {super(clazz);}
}
