package by.tms.eshop.services;

import by.tms.eshop.entities.Cart;
import by.tms.eshop.entities.Order;
import by.tms.eshop.entities.User;
import java.util.List;
import java.util.Set;
import org.springframework.web.servlet.ModelAndView;

public interface OrderService {

  int getOrderTotalPrice(List<Order> sumPriceOfProducts);

  void createOrder(User user, Cart cart) throws Exception;

  ModelAndView getUserOrders(User user) throws Exception;
}
