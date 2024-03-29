package by.tms.eshop.repositories;

import by.tms.eshop.entities.Order;
import by.tms.eshop.entities.User;
import java.util.Set;

public interface OrderDao {
  void saveOrder(Order order);
  Set<Order> getUserOrders(User user);
}
