package by.tms.eshop.dto.converters;

import by.tms.eshop.dto.UserDto;
import by.tms.eshop.entities.User;
import by.tms.eshop.repositories.UserDao;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

  private final UserDao userDao;

  public UserConverter(UserDao userDao) {
    this.userDao = userDao;
  }

  public UserDto toDto(User user) {
    return UserDto.builder()
        .id(user.getId())
        .login(user.getLogin())
        .name(user.getName())
        .surname(user.getSurname())
        .birthday(user.getBirthday())
        .email(user.getEmail())
        .phoneNumber(user.getPhoneNumber())
        .build();
  }

  public User fromDto(UserDto userDto) {
    return User.builder()
        .login(userDto.getLogin())
        .password(userDto.getPassword())
        .name(userDto.getName())
        .surname(userDto.getSurname())
        .birthday(userDto.getBirthday())
        .email(userDto.getEmail())
        .phoneNumber(userDto.getPhoneNumber())
        .build();
  }
}
