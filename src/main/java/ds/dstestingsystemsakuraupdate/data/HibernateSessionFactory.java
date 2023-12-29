package ds.dstestingsystemsakuraupdate.data;


import lombok.Getter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
    private static final Logger logger = LogManager.getLogger(HibernateSessionFactory.class);
    @Getter
    private static final SessionFactory sessionFactory;
    static {
        try {
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable e){
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
