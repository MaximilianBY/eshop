package by.tms.eshop.services;

import by.tms.eshop.dto.CategoryDto;
import by.tms.eshop.entities.Category;
import java.util.Set;
import org.springframework.web.servlet.ModelAndView;

public interface CategoryService {

  Set<CategoryDto> getAllCategoriesDto();

  ModelAndView openCategoryPage();
}
