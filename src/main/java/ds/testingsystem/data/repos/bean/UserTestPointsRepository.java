package ds.testingsystem.data.repos.bean;

import ds.testingsystem.data.model.bean.UserTestPoints;
import ds.testingsystem.data.repos.template.Repository;

public class UserTestPointsRepository extends Repository<UserTestPoints, Long> {
    public UserTestPointsRepository(Class<UserTestPoints> clazz) {super(clazz);}
}
