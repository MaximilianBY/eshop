package by.tms.eshop.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor //создаем конструктор со всеми аргументами и полями
@NoArgsConstructor //конструктор без аргументов и полей
@Data //создаем геттеры, сеттеры, переопределяем toString, equals, hashCode по стандарту
@Builder //определяем билдер для класса
@Embeddable //обозначаем класс встраиваемым
public class OrderDetailsId implements Serializable {

  @JsonBackReference
  //указываем что поле является частью двусторонней связи, а также что является обратной ссылкой
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  //определяем связь многие к одному, указываем на невозможность существования нулевой связи, определяем тип загрузки
  @JoinColumn(name = "order_id", nullable = false)
  //указываем из какого столбца брать данные для данного поля и какому объекту он принадлежит
  private Order order;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  //определяем связь многие к одному, указываем на невозможность существования нулевой связи, определяем тип загрузки
  @JoinColumn(name = "product_id", nullable = false)
  //указываем из какого столбца брать данные для данного поля и какому объекту он принадлежит
  private Product product;
}
