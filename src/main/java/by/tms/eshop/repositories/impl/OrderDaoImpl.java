package by.tms.eshop.repositories.impl;

import by.tms.eshop.entities.Order;
import by.tms.eshop.entities.User;
import by.tms.eshop.repositories.OrderDao;
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
public class OrderDaoImpl implements OrderDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void saveOrder(Order order) {
    entityManager.persist(order);
  }

  @Override
  public Set<Order> getUserOrders(User user) {
    Query getOrders = entityManager.createQuery("select u from Order u where u.user.id=:userId");
    getOrders.setParameter("userId", user.getId());
    log.info("user order info: " + getOrders.getResultList());
    return new HashSet<>(getOrders.getResultList());
  }
}
