package by.tms.eshop.services.impl;

import static by.tms.eshop.PagesPathEnum.CATEGORY_PAGE;
import static by.tms.eshop.RequestParamsEnum.CATEGORY;

import by.tms.eshop.entities.Category;
import by.tms.eshop.repositories.CategoryDao;
import by.tms.eshop.services.CategoryService;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryDao categoryDao;

  public CategoryServiceImpl(CategoryDao categoryDao) {
    this.categoryDao = categoryDao;
  }

  @Override
  public Set<Category> getAllCategories() {
    return categoryDao.getAllCategories();
  }

  @Override
  public ModelAndView openCategoryPage() {
    ModelMap modelMap = new ModelMap();
    Set<Category> categorySet = getAllCategories();
    modelMap.addAttribute(CATEGORY.getValue(), categorySet);
    return new ModelAndView(CATEGORY_PAGE.getPath(), modelMap);
  }
}
