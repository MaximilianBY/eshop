package by.tms.eshop.entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

@Getter //создаем геттеры для всех полей
@Setter //создаем сеттеры для всех полей
@ToString //переопределяем вывод данных полей в виде строк в консоль
@AllArgsConstructor //создаем конструктор со всеми аргументами и полями
@RequiredArgsConstructor //создаем конструктор с обязательными полями или отмеченными NonNull
@SuperBuilder //добавляем возможность работать с полями родительского класса
@Entity //определяем класс как сущность для дальнейшей работы с Spring, Hibernate, JPA
@Table(name = "products") //определяем приндлежность сущности к таблице в БД, откуда будем брать данные
public class Product extends BaseEntity {

  @Column(name = "BRAND", nullable = false) //указываем из какого столбца брать данные для данного поля
  private String brand;
  @Column(name = "MODEL", nullable = false) //указываем из какого столбца брать данные для данного поля
  private String model;
  @Column(name = "DESCRIPTION", nullable = false) //указываем из какого столбца брать данные для данного поля
  private String description;
  @Column(name = "PRICE", nullable = false) //указываем из какого столбца брать данные для данного поля
  private int price;
  @Column(name = "QUANTITY", nullable = false) //указываем из какого столбца брать данные для данного поля
  private int quantity;
  @ManyToOne(optional = false)
  //определяем связь многие к одному, указываем на невозможность существования нулевой связи
  @JoinColumn(name = "CATEGORY_ID", nullable = false, referencedColumnName = "id") //указываем из какого столбца брать данные для данного поля и какому объекту он принадлежит
  private Category category;
  @Column(name = "IMAGE_PATH", nullable = false) //указываем из какого столбца брать данные для данного поля
  private String imagePath;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Product product = (Product) o;
    return id != null && Objects.equals(id, product.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
