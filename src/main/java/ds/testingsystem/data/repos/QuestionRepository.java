package ds.testingsystem.data.repos;

import ds.testingsystem.data.model.Question;

public class QuestionRepository extends Repository<Question, Long> {
    public QuestionRepository(Class<Question> clazz) {super(clazz);}
}
