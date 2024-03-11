package ds.testingsystem.data.repos;

import ds.testingsystem.data.model.EdGroup;

public class EdGroupRepository extends Repository<EdGroup, Long> {
    public EdGroupRepository(Class<EdGroup> clazz) {
        super(clazz);
    }
}
