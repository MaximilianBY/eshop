package by.tms.eshop.services.impl;

import static by.tms.eshop.PagesPathConstants.REGISTRATION_PAGE;
import static by.tms.eshop.PagesPathConstants.SIGN_IN_PAGE;

import by.tms.eshop.dto.UserDto;
import by.tms.eshop.dto.converters.UserConverter;
import by.tms.eshop.entities.User;
import by.tms.eshop.repositories.UserDao;
import by.tms.eshop.services.CategoryService;
import by.tms.eshop.services.UserService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

  private final UserDao userDao;
  private final CategoryService categoryService;
  private final UserConverter userConverter;

  public UserServiceImpl(UserDao userDao, CategoryService categoryService,
      UserConverter userConverter) {
    this.userDao = userDao;
    this.categoryService = categoryService;
    this.userConverter = userConverter;
  }

  @Override
  public ModelAndView authenticate(User user) {
    log.info("inside check user");
    if (checkUserEntry(user)) {
      User userFromDb = userDao.findUserByLoginAndPassword(user.getLogin(), user.getPassword());
      if (Optional.ofNullable(userFromDb).isPresent()) {
        log.info("inside user checked, loading category");
        return categoryService.openCategoryPage();
      }
    }
    log.info("user not exist");
    return new ModelAndView(SIGN_IN_PAGE);
  }

  @Override
  public ModelAndView registration(User user) {
    log.info("inside registration user");
    ModelAndView modelAndView = new ModelAndView();
    log.info(user.getLogin() + " " + user.getName());
    if (checkUserEntry(user)) {
      User newUser = userDao.findUserByLoginAndPassword(user.getLogin(), user.getPassword());
      if (Optional.ofNullable(newUser).isEmpty()) {
        userDao.saveNewUser(user);
        modelAndView.setViewName(SIGN_IN_PAGE);
      } else {
        modelAndView.setViewName(REGISTRATION_PAGE);
      }
    }
    return modelAndView;
  }

  @Override
  public User getUserData(User entity) {
    User user = userDao.getUserByLogin(entity);
    return user;
  }

  @Override
  public int getUserIDByLogin(String login) throws Exception {
    return 0;
  }

  @Override
  public User isExistUser(String login, String password) {
    return userDao.isExistUser(login, password);
  }

  @Override
  public boolean checkUserEntry(User user) {
    return Optional.ofNullable(user).isPresent() && Optional.ofNullable(user.getLogin()).isPresent()
        && Optional.ofNullable(user.getPassword()).isPresent();
  }
}
