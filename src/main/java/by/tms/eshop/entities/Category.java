package by.tms.eshop.entities;

import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

@AllArgsConstructor //создаем конструктор со всеми аргументами и полями
@RequiredArgsConstructor //создаем конструктор с обязательными полями или отмеченными NonNull
@SuperBuilder //добавляем возможность работать с полями родительского класса
@Getter //создаем геттеры для всех полей
@Setter //создаем сеттеры для всех полей
@ToString //переопределяем вывод данных полей в виде строк в консоль
@Entity //определяем класс как сущность для дальнейшей работы с Spring, Hibernate, JPA
@Table(name = "categories")
//определяем приндлежность сущности к таблице в БД, откуда будем брать данные
public class Category extends BaseEntity {

  @Column(name = "NAME", nullable = false)
  //указываем из какого столбца брать данные для данного поля
  private String name;
  @Column(name = "IMAGE_PATH", nullable = false)
  //указываем из какого столбца брать данные для данного поля
  private String imagePath;
  @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
  //определяем связь один ко многим, задаем двунаправленную связь между категориями и продуктами, указываем весь набор каскадных операций, а так же указываем,
  // что при удалении будут удаленны все ссылки из БД продукта на категорию при удалении соответствующей категории
  @ToString.Exclude //исключаем вывод поля в текстовом виде в консоль
  private Set<Product> productSet;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Category category = (Category) o;
    return id != null && Objects.equals(id, category.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
