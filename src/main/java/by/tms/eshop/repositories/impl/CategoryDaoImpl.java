package by.tms.eshop.repositories.impl;

import by.tms.eshop.entities.Category;
import by.tms.eshop.repositories.CategoryDao;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class CategoryDaoImpl implements CategoryDao {

  private final SessionFactory sessionFactory;

  public CategoryDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public Set<Category> getAllCategories() {
    Session session = sessionFactory.getCurrentSession();
    Set<Category> categorySet = new HashSet<>(session.createQuery("select u from Category u").list());
    log.info(categorySet.toString());
    return categorySet;
  }
}
