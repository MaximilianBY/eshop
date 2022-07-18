package by.tms.eshop.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
//не создаем для родительского класса отдельную таблицу, наследники преобразуются в независимые таблицы
@NoArgsConstructor //конструктор без аргументов и полей
@AllArgsConstructor //конструктор со всеми аргументами и полями
@SuperBuilder //определяем супер конструктор
@Data //создаем геттеры, сеттеры, переопределяем toString, equals, hashCode по стандарту
public abstract class BaseEntity {

  @Id //определяем поле как первичный ключ
  @Column(name = "ID", nullable = false) //указываем из какой колонки в БД брать данные
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  //определяем стратегию генерации первичного ключа с помощью идентификаторов в столбце БД
  protected Integer id;
}
