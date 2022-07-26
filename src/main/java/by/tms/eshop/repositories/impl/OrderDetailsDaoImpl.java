package by.tms.eshop.repositories.impl;

import by.tms.eshop.entities.OrderDetails;
import by.tms.eshop.entities.User;
import by.tms.eshop.repositories.OrderDetailsDao;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class OrderDetailsDaoImpl implements OrderDetailsDao {

  private final SessionFactory sessionFactory;

  public OrderDetailsDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public void saveOrderDetails(OrderDetails orderDetails) {
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    session.save(orderDetails);
    session.getTransaction().commit();
  }

  @Override
  public Set<OrderDetails> getOrderDetailsFromDb(User user) {
    Session session = sessionFactory.getCurrentSession();
    Query<OrderDetails> getOrders = session.createQuery(
        "select u from OrderDetails u where u.orderDetailsId.order.user.id=:userId");
    getOrders.setParameter("userId", user.getId());
//    log.info("order details is: " + getOrders.getResultList().stream().filter(orderDetails -> orderDetails.getOrderDetailsId().getOrder().getOrderPrice()));
    return new HashSet<>(getOrders.getResultList());
  }
}
