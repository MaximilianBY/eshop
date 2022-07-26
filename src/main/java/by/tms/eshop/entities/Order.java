package by.tms.eshop.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor //создаем конструктор со всеми аргументами и полями
@NoArgsConstructor //конструктор без аргументов и полей
@SuperBuilder //добавляем возможность работать с полями родительского класса
@Data //создаем геттеры, сеттеры, переопределяем toString, equals, hashCode по стандарту
@Entity //определяем класс как сущность для дальнейшей работы с Spring, Hibernate, JPA
@Table(name = "orders")
//определяем приндлежность сущности к таблице в БД, откуда будем брать данные
public class Order extends BaseEntity {

  @ManyToOne(optional = false)
  //определяем связь многие к одному, указываем на невозможность существования нулевой связи
  @JoinColumn(name = "USER_ID", nullable = false)
  //указываем из какого столбца брать данные для данного поля и какому объекту он принадлежит
  private User user;
  @Column(name = "ORDER_DATE", nullable = false)
  //указываем из какого столбца брать данные для данного поля
  private LocalDate orderDate;
  @Column(name = "ORDER_PRICE", nullable = false)
  //указываем из какого столбца брать данные для данного поля
  private int orderPrice;
}
