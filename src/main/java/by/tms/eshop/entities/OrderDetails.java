package by.tms.eshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "order_details") //определяем приндлежность сущности к таблице в БД, откуда будем брать данные
public class OrderDetails {

  @EmbeddedId //обозначаем составное первичный ключ встраиваемого класса
  @JsonIgnore //указываем, что данное поле игнорируется сериализацией и десереализацией
  private OrderDetailsId orderDetailsId;

  @Column(name = "PRODUCT_QUANTITY", nullable = false)
  private Integer quantity;
}
