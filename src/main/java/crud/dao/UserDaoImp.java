package crud.dao;

import crud.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    @SuppressWarnings("uncheked")
    public List<User> showUsers() {
        TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
        return query.getResultList();
    }

    @Override
    public User getUserFromId (Long id) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                .setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void updateUser(User user, Long id) {
        entityManager.createQuery("UPDATE User u set u.name = :newName WHERE u.id =:oldId")
                .setParameter("oldId", id)
                .setParameter("newName", user.getName())
                .executeUpdate();
        entityManager.createQuery("UPDATE User u set u.fatherName =:newFatherName WHERE u.id =:oldId")
                .setParameter("oldId", user.getId())
                .setParameter("newFatherName", user.getFatherName())
                .executeUpdate();
        entityManager.createQuery("UPDATE User u set u.lastName =:newLastName WHERE u.id =:oldId")
                .setParameter("oldId", user.getId())
                .setParameter("newLastName", user.getLastName())
                .executeUpdate();
        entityManager.createQuery("UPDATE User u set u.age =:newAge WHERE u.id =:oldId")
                .setParameter("oldId", user.getId())
                .setParameter("newAge", user.getAge())
                .executeUpdate();
    }

    @Override
    public void removeUser(Long id) {
        entityManager.remove(getUserFromId(id));
    }
}