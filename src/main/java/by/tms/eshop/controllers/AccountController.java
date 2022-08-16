package by.tms.eshop.controllers;

import static by.tms.eshop.EshopConstants.USER;
import static by.tms.eshop.PagesPathConstants.SIGN_IN_PAGE;

import by.tms.eshop.entities.User;
import by.tms.eshop.services.OrderService;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {

  private final OrderService orderService;

  public AccountController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping
  public ModelAndView openAccountPage(HttpSession session) throws Exception {
    if (Optional.ofNullable(session.getAttribute(USER)).isEmpty()){
      log.info("inside abort confirm order");
      return new ModelAndView(SIGN_IN_PAGE);
    }
    return orderService.getUserOrders((User) session.getAttribute(USER));
  }
}