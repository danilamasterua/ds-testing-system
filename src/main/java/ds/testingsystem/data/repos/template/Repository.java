package ds.testingsystem.data.repos.template;

import ds.testingsystem.data.HibernateSessionFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public abstract class Repository<T, I> {
    @Getter
    final Class<T> clazz;
    @Getter
    private static final SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    @Getter
    private static final Logger logger = LogManager.getLogger(Repository.class);

    public Repository(Class<T> clazz) {
        this.clazz = clazz;
    }

    public List<T> getAll(){
        try (Session session = sessionFactory.openSession()){
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> cq = criteriaBuilder.createQuery(clazz);
            Root<T> rootEntry = cq.from(clazz);
            CriteriaQuery<T> cqAll = cq.select(rootEntry);
            TypedQuery<T> cqAllQuery = session.createQuery(cqAll);
            return cqAllQuery.getResultList();
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
    public T getById(I id){
        try (Session session = sessionFactory.openSession()){
            return session.get(clazz, id);
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
    public T add(T obj){
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(obj);
            transaction.commit();
            return obj;
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
    public T update(T obj){
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(obj);
            transaction.commit();
            return obj;
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
    public void deleteById(I id){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            T obj = getById(id);
            session.remove(obj);
            transaction.commit();
        } catch (Exception e){
            logger.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
