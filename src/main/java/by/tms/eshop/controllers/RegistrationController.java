package by.tms.eshop.controllers;

import static by.tms.eshop.EshopConstants.USER;
import static by.tms.eshop.PagesPathConstants.REGISTRATION_PAGE;

import by.tms.eshop.dto.UserDto;
import by.tms.eshop.entities.User;
import by.tms.eshop.services.UserService;
import javax.validation.Valid;
import org.springframework.validation.Errors;
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
    return new ModelAndView(REGISTRATION_PAGE);
  }

  @PostMapping
  public ModelAndView addNewUser(@ModelAttribute @Valid User user, Errors errors) throws Exception {
    return userService.registration(user);
  }

  @ModelAttribute(USER)
  public User setUpUserForm() {
    return new User();
  }
}
