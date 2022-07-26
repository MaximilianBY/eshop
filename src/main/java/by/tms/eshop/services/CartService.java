package by.tms.eshop.services;


import static by.tms.eshop.EshopConstants.SHOPPING_CART;
import static by.tms.eshop.PagesPathEnum.CART_PAGE;
import static by.tms.eshop.RequestParamsEnum.PRODUCT;
import static by.tms.eshop.RequestParamsEnum.TOTAL_PRICE;
import static by.tms.eshop.RequestParamsEnum.USER_CART;

import by.tms.eshop.entities.Cart;
import by.tms.eshop.entities.Product;
import by.tms.eshop.entities.User;

import by.tms.eshop.repositories.ProductDao;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@Service
public class CartService {

  private final ProductDao productDao;
  private final OrderService orderService;

  private final UserService userService;

  public CartService(ProductDao productDao, OrderService orderService, UserService userService) {
    this.productDao = productDao;
    this.orderService = orderService;
    this.userService = userService;
  }

  public ModelAndView openCartPage(Cart cart){
    ModelMap modelMap = new ModelMap();
    if (Optional.ofNullable(cart).isPresent()){
      modelMap.addAttribute(USER_CART.getValue(), cart.getUsersCart());
    }
    return new ModelAndView(CART_PAGE.getPath(), modelMap);
  }

  public ModelAndView addProductToCart(int productID, Cart cart) throws Exception {
    ModelMap modelMap = new ModelMap();

    Product product = productDao.getProductByIdFromDb(productID);
    cart.addProductToCart(product);

    modelMap.addAttribute(PRODUCT.getValue(), product);
    modelMap.addAttribute(USER_CART.getValue(), cart.getUsersCart());
    modelMap.addAttribute(TOTAL_PRICE.getValue(), cart.getUserCartTotalPrice());
    return new ModelAndView(CART_PAGE.getPath(), modelMap);
  }

  public ModelAndView deleteProductFromCart(int productID, Cart cart) throws Exception {
    ModelMap modelMap = new ModelMap();

    Product product = productDao.getProductByIdFromDb(productID);
    cart.delUnnecessaryProduct(product);

    modelMap.addAttribute(USER_CART.getValue(), cart.getUsersCart());
    modelMap.addAttribute(TOTAL_PRICE.getValue(), cart.getUserCartTotalPrice());
    return new ModelAndView(CART_PAGE.getPath(), modelMap);
  }

  public ModelAndView clearUserCart(Cart cart) {
    ModelMap modelMap = new ModelMap();

    cart.flushUserCart();

    modelMap.addAttribute(USER_CART.getValue(), cart.getUsersCart());
    modelMap.addAttribute(TOTAL_PRICE.getValue(), cart.getUserCartTotalPrice());
    return new ModelAndView(CART_PAGE.getPath(), modelMap);
  }

  public ModelAndView confirmOrder(User entity, Cart cart) throws Exception {
    ModelMap modelMap = new ModelMap();

    User user = userService.getUserData(entity);
    orderService.createOrder(user, cart);
    modelMap.addAttribute(USER_CART.getValue(), cart.getUsersCart());
    modelMap.addAttribute(TOTAL_PRICE.getValue(), cart.getUserCartTotalPrice());
    return new ModelAndView(CART_PAGE.getPath(), modelMap);
  }
}
