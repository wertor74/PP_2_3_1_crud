package crud.dao;

import crud.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final EntityManagerFactory entityManager;

    public UserDaoImp(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory;
    }

    @Override
    public void addUser(User user) {
        entityManager.createEntityManager().persist(user);
    }

    @Override
    @SuppressWarnings("uncheked")
    public List<User> showUsers() {
        TypedQuery<User> query= entityManager.createEntityManager().createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    @Override
    public User getUserFromId (Long id) {
        TypedQuery<User> query = entityManager.createEntityManager().createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                .setParameter("id", id);
        return query.getSingleResult();
    }
}
