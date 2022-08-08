package by.tms.eshop.repositories.impl;

import by.tms.eshop.entities.OrderDetails;
import by.tms.eshop.entities.User;
import by.tms.eshop.repositories.OrderDetailsDao;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Transactional
@Repository
public class OrderDetailsDaoImpl implements OrderDetailsDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void saveOrderDetails(OrderDetails orderDetails) {
    entityManager.persist(orderDetails);
  }

  @Override
  public Set<OrderDetails> getOrderDetails(User user) {
    Query getOrders = entityManager.createQuery(
        "select u from OrderDetails u where u.orderDetailsId.order.user.id=:userId");
    getOrders.setParameter("userId", user.getId());
    return new HashSet<>(getOrders.getResultList());
  }
}
