package by.tms.eshop.services.impl;

import static by.tms.eshop.PagesPathConstants.HOME_PAGE;
import static by.tms.eshop.RequestParamsEnum.CATEGORY;

import by.tms.eshop.dto.CategoryDto;
import by.tms.eshop.dto.converters.CategoryConverter;
import by.tms.eshop.repositories.CategoryDao;
import by.tms.eshop.services.CategoryService;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryDao categoryDao;
  private final CategoryConverter categoryConverter;

  public CategoryServiceImpl(CategoryDao categoryDao, CategoryConverter categoryConverter) {
    this.categoryDao = categoryDao;
    this.categoryConverter = categoryConverter;
  }

  @Override
  public Set<CategoryDto> getAllCategoriesDto() {
    return categoryDao.getAllCategories().stream().map(categoryConverter::toDto).collect(Collectors.toSet());
  }

  @Override
  public ModelAndView openCategoryPage() {
    ModelMap modelMap = new ModelMap();
    Set<CategoryDto> categorySet = getAllCategoriesDto();
    modelMap.addAttribute(CATEGORY.getValue(), categorySet);
    return new ModelAndView(HOME_PAGE, modelMap);
  }
}
