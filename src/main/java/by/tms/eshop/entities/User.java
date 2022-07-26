package by.tms.eshop.entities;

import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
//@NoArgsConstructor
//@ToString
@SuperBuilder
@Entity
@Table(name = "users")
public class User extends BaseEntity {

  @NotEmpty(message = "Логин не может быть пустым")
  @Column(name = "LOGIN", nullable = false)
  private String login;
  @NotEmpty(message = "Имя не может быть пустым")
  @Column(name = "NAME", nullable = false)
  private String name;
  @Column(name = "SURNAME", nullable = false)
  private String surname;
  @Column(name = "BIRTHDAY")
  private String birthday;
  @NotEmpty(message = "Email не может быть пустым")
  @Email(message = "Почтовый ящик поврежден")
  @Column(name = "EMAIL", nullable = false)
  private String email;
  @Column(name = "PHONE_NUMBER")
  private String phoneNumber;
  @Size(min = 6, max = 24, message = "Длина пароля не может быть меньше 6 символов и превышать 24 символа")
  @Column(name = "PASSWORD", nullable = false)
  private String password;
  @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
  private Set<Order> order;


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    User user = (User) o;
    return getLogin().equals(user.getLogin()) && getName().equals(user.getName())
        && getSurname().equals(user.getSurname()) && getBirthday().equals(user.getBirthday())
        && getEmail().equals(user.getEmail()) && getPhoneNumber().equals(user.getPhoneNumber())
        && getPassword().equals(user.getPassword());
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), getLogin(), getName(), getSurname(), getBirthday(),
        getEmail(), getPhoneNumber(), getPassword());
  }
}
