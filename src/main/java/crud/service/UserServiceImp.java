package crud.service;

import crud.dao.UserDao;
import crud.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService{

    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> showUsers() {
        return userDao.showUsers();
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserFromId(Long id) {
        return userDao.getUserFromId(id);
    }
}
