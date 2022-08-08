package by.tms.eshop.controllers;

import static by.tms.eshop.EshopConstants.PRODUCT_ID;
import static by.tms.eshop.EshopConstants.SHOPPING_CART;
import static by.tms.eshop.EshopConstants.USER;
import static by.tms.eshop.PagesPathConstants.SIGN_IN_PAGE;

import by.tms.eshop.entities.Cart;
import by.tms.eshop.entities.User;
import by.tms.eshop.services.CartService;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@SessionAttributes({SHOPPING_CART})
@RequestMapping("/cart")
public class CartController {

  private final CartService cartService;

  public CartController(CartService cartService) {
    this.cartService = cartService;
  }

  @GetMapping("/open")
  public ModelAndView openCartPage(@ModelAttribute(SHOPPING_CART) Cart cart) {
    return cartService.openCartPage(cart);
  }

  @GetMapping("/add")
  public ModelAndView addProductToCart(@RequestParam(PRODUCT_ID) String id,
      @ModelAttribute(SHOPPING_CART) Cart cart) throws Exception {
    int productId = Integer.parseInt(id);
    return cartService.addProductToCart(productId, cart);
  }

  @GetMapping("/delete")
  public ModelAndView delProductFromCart(@RequestParam(PRODUCT_ID) String id,
      @SessionAttribute(SHOPPING_CART) Cart cart) throws Exception {
    int productId = Integer.parseInt(id);
    return cartService.deleteProductFromCart(productId, cart);
  }

  @GetMapping("/reset")
  public ModelAndView clearUserCart(@SessionAttribute(SHOPPING_CART) Cart cart) {
    return cartService.clearUserCart(cart);
  }

  @GetMapping("/confirmOrder")
  public ModelAndView confirmOrder(@SessionAttribute(SHOPPING_CART) Cart cart, HttpSession session) throws Exception {
    if (Optional.ofNullable(session.getAttribute(USER)).isPresent()){
      log.info("info user" + " " + ((User) session.getAttribute(USER)).getLogin());
    }
    if (Optional.ofNullable(session.getAttribute(USER)).isEmpty()){
      log.info("inside abort confirm order");
      return new ModelAndView(SIGN_IN_PAGE);
    }
    return cartService.confirmOrder((User) session.getAttribute(USER), cart);
  }

  @ModelAttribute(SHOPPING_CART)
  public Cart shoppingCart() {
    return new Cart();
  }
}
