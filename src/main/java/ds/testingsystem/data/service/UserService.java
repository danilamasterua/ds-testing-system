package ds.testingsystem.data.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ds.testingsystem.data.model.User;
import ds.testingsystem.data.repos.UserRepository;

public class UserService {
    private static final Logger log = LogManager.getLogger(UserService.class);
    private static final UserRepository repo = new UserRepository(User.class);
    public static User getUser(String pointer){
        try{
            return repo.getUserByLoginOrEmail(pointer);
        } catch(Exception e){
            log.error(e.getMessage());
            return null;
        }
    }
}
