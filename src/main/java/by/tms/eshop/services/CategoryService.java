package by.tms.eshop.services;

import by.tms.eshop.entities.Category;
import java.util.Set;
import org.springframework.web.servlet.ModelAndView;

public interface CategoryService {
  Set<Category> getAllCategories();

  ModelAndView openCategoryPage();
}
