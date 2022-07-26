package by.tms.eshop.repositories;

import by.tms.eshop.entities.Order;
import by.tms.eshop.entities.OrderDetails;
import by.tms.eshop.entities.Product;
import by.tms.eshop.entities.User;
import java.util.Set;

public interface OrderDetailsDao {
  void saveOrderDetails(OrderDetails orderDetails);
  Set<OrderDetails> getOrderDetailsFromDb(User user);
}
