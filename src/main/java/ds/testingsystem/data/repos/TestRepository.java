package ds.testingsystem.data.repos;

import ds.testingsystem.data.model.Test;
import ds.testingsystem.data.repos.template.Repository;
import lombok.AllArgsConstructor;


public class TestRepository extends Repository<Test, Long> {
    public TestRepository(Class<Test> clazz){super(clazz);}
}
