package by.tms.eshop.repositories.impl;

import by.tms.eshop.entities.Order;
import by.tms.eshop.entities.User;
import by.tms.eshop.repositories.OrderDao;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class OrderDaoImpl implements OrderDao {

  private final SessionFactory sessionFactory;

  public OrderDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public void saveOrderInDb(Order order) {
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    session.save(order);
    session.getTransaction().commit();
  }

  @Override
  public Set<Order> getUserOrdersFromDb(User user) {
    Session session = sessionFactory.getCurrentSession();
    Query<Order> getOrders = session.createQuery("select u from Order u where u.user.id=:userId");
    getOrders.setParameter("userId", user.getId());
    log.info("user order info: " + getOrders.getResultList());
    return new HashSet<>(getOrders.getResultList());
  }
}
