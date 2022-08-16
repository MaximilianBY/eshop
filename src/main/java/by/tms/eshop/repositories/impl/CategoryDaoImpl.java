package by.tms.eshop.repositories.impl;

import by.tms.eshop.entities.Category;
import by.tms.eshop.repositories.CategoryDao;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Transactional
@Repository
public class CategoryDaoImpl implements CategoryDao {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Set<Category> getAllCategories() {
    return new HashSet<>(entityManager.createQuery("select c from Category c")
        .getResultList());
  }

  @Override
  public Category findById(int id) {
//    Query query = entityManager.createQuery("select c from Category c where c.id=:id");
//    query.setParameter("id", id);
    return entityManager.getReference(Category.class, id);
  }
}
