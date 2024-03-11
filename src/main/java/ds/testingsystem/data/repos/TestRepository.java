package ds.testingsystem.data.repos;

import ds.testingsystem.data.model.Test;
import ds.testingsystem.data.model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class TestRepository extends Repository<Test, Long> {
    public TestRepository(Class<Test> clazz){super(clazz);}
    public List<Test> getTestListByOwner(User owner){
        try {
            Session session = getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Test> cq = criteriaBuilder.createQuery(getClazz());
            Root<Test> root = cq.from(getClazz());
            Predicate criteria = criteriaBuilder.equal(root.get("owner"), owner);
            cq.select(root).where(criteria);
            Query<Test> query = session.createQuery(cq);
            List<Test> tests = query.list();
            transaction.commit();
            return tests;
        } catch (Exception e){
            getLogger().error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
