package by.tms.eshop.repositories.impl;

import by.tms.eshop.entities.User;
import by.tms.eshop.repositories.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class UserDaoImpl implements UserDao {

  private final SessionFactory sessionFactory;

  public UserDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public int getUserIdByLogin(String login) {
    return 0;
  }

  @Override
  public User findUserByLoginAndPassword(String login, String password) {
    Session session = sessionFactory.getCurrentSession();
    Query<User> query = session.createQuery(
        "select u from User u where u.login=:login and u.password=:password");
    query.setParameter("login", login);
    query.setParameter("password", password);
    return query.uniqueResult();
  }

  @Override
  public User isExistUser(String login, String password) {
    Session session = sessionFactory.getCurrentSession();
    User user = null;
    NativeQuery isExist = session.createSQLQuery("select IF (select * FROM ESHOP.USERS.LOGIN = ? AND ESHOP.USERS.PASSWORD)");
    isExist.setParameter("login", login);
    isExist.setParameter("password", password);
    log.info(String.valueOf(isExist.uniqueResult()));
    user = (User) isExist.getSingleResult();
    return user;
  }

  @Override
  public void saveNewUser(User user) {
    Session session = sessionFactory.getCurrentSession();
    session.save(user);
  }

  @Override
  public User getUserDataFromDbByLogin(User entity) {
    Session session = sessionFactory.getCurrentSession();
    Query<User> query = session.createQuery(
        "select u from User u where u.login=:login");
    query.setParameter("login", entity.getLogin());
    return query.uniqueResult();
  }
}
