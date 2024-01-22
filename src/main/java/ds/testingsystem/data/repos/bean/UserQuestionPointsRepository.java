package ds.testingsystem.data.repos.bean;

import ds.testingsystem.data.model.bean.UserQuestionPoints;
import ds.testingsystem.data.repos.template.Repository;

public class UserQuestionPointsRepository extends Repository<UserQuestionPoints, Long> {
    public UserQuestionPointsRepository(Class<UserQuestionPoints> clazz) {super(clazz);}
}
