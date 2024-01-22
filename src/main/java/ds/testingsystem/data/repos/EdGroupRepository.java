package ds.testingsystem.data.repos;

import ds.testingsystem.data.model.EdGroup;
import ds.testingsystem.data.repos.template.Repository;

public class EdGroupRepository extends Repository<EdGroup, Long> {
    public EdGroupRepository(Class<EdGroup> clazz) {
        super(clazz);
    }
}
