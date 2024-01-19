package ds.testingsystem.data.repos;

import ds.testingsystem.data.model.User;
import ds.testingsystem.data.repos.template.Repository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserRepository extends Repository<User, Long> {
    public UserRepository(Class<User> clazz) {
        super(clazz);
    }
    public User getUserByLoginOrEmail(String pointer){
        try (Session session = getSessionFactory().openSession()){
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> cq = criteriaBuilder.createQuery(getClazz());
            Root<User> root = cq.from(getClazz());
            Predicate cr1 = criteriaBuilder.equal(root.get("login"), pointer);
            Predicate cr2 = criteriaBuilder.equal(root.get("email"), pointer);
            cq.select(root).where(criteriaBuilder.or(cr1, cr2));
            Query<User> query = session.createQuery(cq);
            return query.getSingleResult();
        } catch (Exception e){
            getLogger().error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
