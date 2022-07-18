package by.tms.eshop.controllers;

import static by.tms.eshop.EshopConstants.PRODUCT_ID;

import by.tms.eshop.entities.Cart;
import by.tms.eshop.entities.User;
import by.tms.eshop.services.CartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/cart")
public class CartController {

  private final CartService cartService;

  public CartController(CartService cartService) {
    this.cartService = cartService;
  }

  @GetMapping("/open")
  public ModelAndView openCartPage(@SessionAttribute("cart") Cart cart) {
    return cartService.openCartPage(cart);
  }

  @GetMapping("/add")
  public ModelAndView addProductToCart(@RequestParam(PRODUCT_ID) String id,
      @SessionAttribute("cart") Cart cart) throws Exception {
    int productId = Integer.parseInt(id);
    return cartService.addProductToCart(productId, cart);
  }

  @GetMapping("/delete")
  public ModelAndView delProductFromCart(@RequestParam(PRODUCT_ID) String id,
      @SessionAttribute("cart") Cart cart) throws Exception {
    int productId = Integer.parseInt(id);
    return cartService.deleteProductFromCart(productId, cart);
  }

  @GetMapping("/reset")
  public ModelAndView clearUserCart(@SessionAttribute("cart") Cart cart) {
    return cartService.clearUserCart(cart);
  }

  @GetMapping("/confirmOrder")
  public ModelAndView confirmOrder(@SessionAttribute("user") User user,
      @SessionAttribute("cart") Cart cart) throws Exception {
    return cartService.confirmOrder(user, cart);
  }
}
