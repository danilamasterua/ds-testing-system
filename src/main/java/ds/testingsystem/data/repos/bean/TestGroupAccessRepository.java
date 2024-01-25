package ds.testingsystem.data.repos.bean;

import ds.testingsystem.data.model.EdGroup;
import ds.testingsystem.data.model.Test;
import ds.testingsystem.data.model.bean.TestGroupAccess;
import ds.testingsystem.data.repos.template.Repository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class TestGroupAccessRepository extends Repository<TestGroupAccess, Long> {
    public TestGroupAccessRepository(Class<TestGroupAccess> clazz) {super(clazz);}
}
