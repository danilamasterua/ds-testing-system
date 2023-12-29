package ds.dstestingsystemsakuraupdate.data.repos.template;

import ds.dstestingsystemsakuraupdate.data.model.User;

import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();
    User getUserById(Long id);
    User getUserByLoginOrEmail(String pointer);
    User addUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);
}
