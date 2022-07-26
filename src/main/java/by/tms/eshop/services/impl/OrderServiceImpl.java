package by.tms.eshop.services.impl;

import static by.tms.eshop.PagesPathEnum.ACCOUNT_PAGE;
import static by.tms.eshop.RequestParamsEnum.ORDER_DETAILS;
import static by.tms.eshop.RequestParamsEnum.TOTAL_PRICE;
import static by.tms.eshop.RequestParamsEnum.USER_INFO;

import by.tms.eshop.entities.Cart;
import by.tms.eshop.entities.Order;
import by.tms.eshop.entities.OrderDetails;
import by.tms.eshop.entities.OrderDetailsId;
import by.tms.eshop.entities.Product;
import by.tms.eshop.entities.User;
import by.tms.eshop.repositories.OrderDao;
import by.tms.eshop.repositories.OrderDetailsDao;
import by.tms.eshop.services.OrderService;
import by.tms.eshop.services.ProductService;
import by.tms.eshop.services.UserService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

  private final OrderDao orderDao;
  private final ProductService productService;
  private final OrderDetailsDao orderDetailsDao;
  private final UserService userService;

  public OrderServiceImpl(OrderDao orderDao, ProductService productService,
      OrderDetailsDao orderDetailsDao, UserService userService) {
    this.orderDao = orderDao;
    this.productService = productService;
    this.orderDetailsDao = orderDetailsDao;
    this.userService = userService;
  }

  @Override
  public int getOrderTotalPrice(List<Order> sumPriceOfProducts) {
    if (Optional.ofNullable(sumPriceOfProducts).isPresent()) {
      return sumPriceOfProducts.stream()
          .mapToInt(Order::getOrderPrice)
          .sum();
    }
    return 0;
  }

  /**
   * Создаем заказ, в качестве аргументов передаем пользователя из сессии и корзину из сессии.
   * Создаем объект Order, в него сохраняем пользователя, а так же дату оформления заказа и общую
   * стоимость заказа, далее передаем этот объект в OrderDao и сохраняем его в БД.
   * <p>
   * В цикл передаем объект Cart. Берем продукт из списка, и передаем в ProductService где изменим
   * кол-во продуктов в БД отняв те, которые заказали. Создаем объект OrderDetailsId, сохраняем в
   * него order и продукт, далее создаем объект OrderDetails и сохраняем в него объект
   * OrderDetailsId и количество текущего продукта.
   * <p>
   * Вызываем OrderDetailsDao и сохраняем объект в БД.
   * <p>
   * Очищаем полностью корзину.
   */
  @Override
  public void createOrder(User user, Cart cart) throws Exception {
    Order order = Order.builder()
        .user(user)
        .orderDate(LocalDate.now())
        .orderPrice(cart.getUserCartTotalPrice())
        .build();
    orderDao.saveOrderInDb(order);
    for (Product product : cart.getUsersCart()) {
      productService.updateProductQuantity(product);

      OrderDetailsId orderDetailsId = OrderDetailsId.builder()
          .order(order)
          .product(product)
          .build();

      OrderDetails orderDetails = OrderDetails.builder()
          .orderDetailsId(orderDetailsId)
          .quantity(product.getQuantity())
          .build();

      orderDetailsDao.saveOrderDetails(orderDetails);

    }
    cart.flushUserCart();
  }

  @Override
  public ModelAndView getUserOrders(User entity) throws Exception {
    ModelMap modelMap = new ModelMap();
    User user = userService.getUserData(entity);
    modelMap.addAttribute(USER_INFO.getValue(), user);
    Set<Order> userOrders = orderDao.getUserOrdersFromDb(user);
    Set<OrderDetails> orderDetails = orderDetailsDao.getOrderDetailsFromDb(user);
    if (Optional.ofNullable(orderDetails).isPresent()) {
//      modelMap.addAttribute(ORDER_STORY.getValue(), userOrders);
      modelMap.addAttribute(ORDER_DETAILS.getValue(), orderDetails);
      int totalOrderPrice = userOrders.stream().mapToInt(Order::getOrderPrice).sum();
      modelMap.addAttribute(TOTAL_PRICE.getValue(), totalOrderPrice);
    }
    return new ModelAndView(ACCOUNT_PAGE.getPath(), modelMap);
  }
}
