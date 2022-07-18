package by.tms.eshop.services.impl;


import static by.tms.eshop.PagesPathEnum.DEVICES_PAGE;
import static by.tms.eshop.PagesPathEnum.PRODUCT_PAGE;
import static by.tms.eshop.PagesPathEnum.SEARCH_PAGE;
import static by.tms.eshop.RequestParamsEnum.DEVICES;
import static by.tms.eshop.RequestParamsEnum.PRODUCT;

import by.tms.eshop.entities.Product;
import by.tms.eshop.repositories.ProductDao;
import by.tms.eshop.services.ProductService;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductDao productDao;

  public ProductServiceImpl(ProductDao productDao) {
    this.productDao = productDao;
  }

  @Override
  public Set<Product> getAllProductsByCategory(int categoryID) throws Exception {
    Optional<Set<Product>> products = Optional.ofNullable(
        productDao.getAllProductsByCategoryFromDb(categoryID));
    return products.orElse(null);
  }

  @Override
  public Product getProductByID(int productID) throws Exception {
    Optional<Product> product = Optional.ofNullable(productDao.getProductByIdFromDb(productID));
    return product.orElse(null);
  }

  @Override
  public Set<Product> findProductByRequestFromSearch(String[] searchArray) throws Exception {
    Optional<Set<Product>> products = Optional.ofNullable(
        productDao.findProductsByRequestFromSearchDb(searchArray));
    return products.orElse(null);
  }

  @Override
  public void updateProductQuantity(Product product) {
    productDao.updateProductQuantityInDb(product);
  }

  @Override
  public ModelAndView openDevicesPage(int categoryId) {
    ModelMap modelMap = new ModelMap();
    Set<Product> products = productDao.getAllProductsByCategoryFromDb(categoryId);
    if (Optional.ofNullable(products).isPresent()) {
      modelMap.addAttribute(DEVICES.getValue(), products);
    }
    return new ModelAndView(DEVICES_PAGE.getPath(), modelMap);
  }

  @Override
  public ModelAndView getProductData(int id) throws Exception {
    ModelMap modelMap = new ModelMap();
    Product product = productDao.getProductByIdFromDb(id);
    if (Optional.ofNullable(product).isPresent()) {
      modelMap.addAttribute(PRODUCT.getValue(), product);
    }
    return new ModelAndView(PRODUCT_PAGE.getPath(), modelMap);
  }

  @Override
  public ModelAndView findProductsFromRequest(String inputString) throws Exception {
    ModelMap modelMap = new ModelMap();
    if (Optional.ofNullable(inputString).isPresent()) {
      String[] searchArr = inputString.split("\\W");
      modelMap.addAttribute(DEVICES.getValue(),
          productDao.findProductsByRequestFromSearchDb(searchArr));
    }
    return new ModelAndView(SEARCH_PAGE.getPath(), modelMap);
  }
}
