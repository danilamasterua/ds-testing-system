package ds.testingsystem.data.repos.bean;

import ds.testingsystem.data.model.bean.UserAnswer;
import ds.testingsystem.data.repos.template.Repository;

public class UserAnswerRepository extends Repository<UserAnswer, Long> {
    public UserAnswerRepository(Class<UserAnswer> clazz) {super(clazz);}
}
