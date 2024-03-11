package ds.testingsystem.data.repos;

import ds.testingsystem.data.model.TestModule;

public class TestModuleRepository extends Repository<TestModule, Long> {
    public TestModuleRepository(Class<TestModule> clazz) {super(clazz);}
}
