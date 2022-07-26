package by.tms.eshop.services;

import by.tms.eshop.entities.User;
import org.springframework.web.servlet.ModelAndView;

public interface UserService {

  ModelAndView authenticate(User user) throws Exception;

  ModelAndView registration(User user) throws Exception;

  User getUserData(User entity);

  int getUserIDByLogin(String login) throws Exception;

  User isExistUser(String login, String password);

  boolean checkUserEntry(User user);
}
