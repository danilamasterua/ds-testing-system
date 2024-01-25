package ds.testingsystem.data.repos;

import ds.testingsystem.data.model.Test;
import ds.testingsystem.data.model.User;
import ds.testingsystem.data.repos.template.Repository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class TestRepository extends Repository<Test, Long> {
    public TestRepository(Class<Test> clazz){super(clazz);}
    public List<Test> getByOwner(User owner){
        try (Session session = getSessionFactory().openSession()){
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Test> cq = criteriaBuilder.createQuery(getClazz());
            Root<Test> root = cq.from(getClazz());
            Predicate criteria = criteriaBuilder.equal(root.get("owner_id"), owner.getId());
            cq.select(root).where(criteria);
            Query<Test> query = session.createQuery(cq);
            return query.getResultList();
        } catch (Exception e){
            getLogger().error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
