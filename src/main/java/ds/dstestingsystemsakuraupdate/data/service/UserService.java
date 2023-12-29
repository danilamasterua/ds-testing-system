package ds.dstestingsystemsakuraupdate.data.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ds.dstestingsystemsakuraupdate.data.model.User;
import ds.dstestingsystemsakuraupdate.data.repos.UserRepositoryImpl;

public class UserService {
    private static final Logger log = LogManager.getLogger(UserService.class);
    private static final UserRepositoryImpl repo = new UserRepositoryImpl();
    public static User getUser(String pointer){
        try{
            return repo.getUserByLoginOrEmail(pointer);
        } catch(Exception e){
            log.error(e.getMessage());
            return null;
        }
    }
}
