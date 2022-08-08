package by.tms.eshop.repositories;

import by.tms.eshop.dto.UserDto;
import by.tms.eshop.entities.User;

public interface UserDao {

  int getUserIdByLogin(String login);

  User findUserByLoginAndPassword(String login, String password);

  User isExistUser(String login, String password);

  void saveNewUser(User user);
  User getUserByLogin(User entity);
}
