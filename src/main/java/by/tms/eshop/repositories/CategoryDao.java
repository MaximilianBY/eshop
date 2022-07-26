package by.tms.eshop.repositories;

import by.tms.eshop.entities.Category;
import java.util.Set;

public interface CategoryDao {
  Set<Category> getAllCategories();
}
