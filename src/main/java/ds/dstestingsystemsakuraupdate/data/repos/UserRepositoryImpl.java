package ds.dstestingsystemsakuraupdate.data.repos;

import ds.dstestingsystemsakuraupdate.data.HibernateSessionFactory;
import ds.dstestingsystemsakuraupdate.data.model.User;
import ds.dstestingsystemsakuraupdate.data.repos.template.UserRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private static final Logger log = LogManager.getLogger(UserRepositoryImpl.class);
    private static final SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()){
            String hql = "FROM User";
            Query<User> query = session.createQuery(hql, User.class);
            return (List<User>) query.getResultList();
        } catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public User getUserById(Long id) {
        try (Session session = sessionFactory.openSession()){
            return session.get(User.class, id);
        } catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public User getUserByLoginOrEmail(String pointer) {
        try (Session session = sessionFactory.openSession()){
            String hql = "FROM User WHERE login= :pointer OR email= :pointer";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("pointer", pointer);
            return query.getSingleResult();
        } catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public User addUser(User user) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            return user;
        } catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public User updateUser(User user) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
            return user;
        } catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteUser(Long id) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            transaction.commit();
        } catch(Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
