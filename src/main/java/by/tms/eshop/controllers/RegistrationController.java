package by.tms.eshop.controllers;

import static by.tms.eshop.EshopConstants.USER;
import static by.tms.eshop.PagesPathEnum.REGISTRATION_PAGE;

import by.tms.eshop.entities.User;
import by.tms.eshop.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@RestController
@SessionAttributes({USER})
@RequestMapping("/registration")
public class RegistrationController {

  private final UserService userService;

  public RegistrationController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ModelAndView openRegistrationPage() {
    return new ModelAndView(REGISTRATION_PAGE.getPath());
  }

  @PostMapping
  public ModelAndView addNewUser(@ModelAttribute("user") User user) throws Exception {
    return userService.registration(user);
  }

  @ModelAttribute(USER)
  public User setUpUserForm() {
    return new User();
  }
}
