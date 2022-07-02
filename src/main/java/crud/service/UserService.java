package crud.service;

import crud.model.User;
import java.util.List;

public interface UserService {

    void addUser(User user);

    List<User> showUsers();

    User getUserFromId(Long id);

    void updateUser(User user, Long id);

    void removeUser(Long id);
}
