package by.tms.eshop.controllers;

import by.tms.eshop.entities.User;
import by.tms.eshop.services.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/account")
public class AccountController {

  private final OrderService orderService;

  public AccountController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping
  public ModelAndView openAccountPage(@SessionAttribute("user") User user) throws Exception {
    return orderService.getUserOrders(user);
  }
}