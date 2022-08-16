package by.tms.eshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
  private int id;
  private String login;
  private String name;
  private String surname;
  private String birthday;
  private String email;
  private String phoneNumber;
  private String password;
}
