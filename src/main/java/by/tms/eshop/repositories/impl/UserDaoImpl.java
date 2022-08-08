package by.tms.eshop.repositories.impl;

import by.tms.eshop.dto.UserDto;
import by.tms.eshop.entities.User;
import by.tms.eshop.repositories.UserDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Transactional
@Repository
public class UserDaoImpl implements UserDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public int getUserIdByLogin(String login) {
    Query query = entityManager.createQuery("select u.id from User u where u.login=:login");
    query.setParameter("login", login);
    return (int) query.getSingleResult();
  }

  @Override
  public User findUserByLoginAndPassword(String login, String password) {
    Query query = entityManager.createQuery(
        "select u from User u where u.login=:login and u.password=:password");
    query.setParameter("login", login);
    query.setParameter("password", password);
    return (User) query.getSingleResult();
  }

  @Override
  public User isExistUser(String login, String password) {
    Query isExist = entityManager.createNativeQuery(
        "SELECT IF (SELECT * FROM ESHOP.USERS.LOGIN = ? AND ESHOP.USERS.PASSWORD = ?)");
    isExist.setParameter(1, login);
    isExist.setParameter(2, password);
    User user;
    user = (User) isExist.getSingleResult();
    return user;
  }

  @Override
  public void saveNewUser(User user) {
    entityManager.persist(user);
  }

  @Override
  public User getUserByLogin(User entity) {
    Query query = entityManager.createQuery("select u from User u where u.login=:login");
    query.setParameter("login", entity.getLogin());
    return (User) query.getSingleResult();
  }
}
