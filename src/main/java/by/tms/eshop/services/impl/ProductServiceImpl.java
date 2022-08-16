package by.tms.eshop.services.impl;


import static by.tms.eshop.PagesPathConstants.DEVICES_PAGE;
import static by.tms.eshop.PagesPathConstants.PRODUCT_PAGE;
import static by.tms.eshop.PagesPathConstants.SEARCH_PAGE;
import static by.tms.eshop.RequestParamsEnum.DEVICES;
import static by.tms.eshop.RequestParamsEnum.PRODUCT;

import by.tms.eshop.dto.ProductDto;
import by.tms.eshop.dto.converters.ProductConverter;
import by.tms.eshop.entities.Product;
import by.tms.eshop.repositories.ProductDao;
import by.tms.eshop.services.ProductService;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
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
  public Set<ProductDto> getAllProductsByCategory(int categoryID) throws Exception {
    Optional<Set<ProductDto>> products = Optional.ofNullable(
        productDao.getProductsByCategory(categoryID));
    return products.orElse(null);
  }

  @Override
  public ProductDto getProductByID(int productID) throws Exception {
    Optional<ProductDto> product = Optional.ofNullable(productDao.getProductById(productID));
    return product.orElse(null);
  }

  @Override
  public Set<ProductDto> findProductByRequestFromSearch(String[] searchArray) throws Exception {
    Optional<Set<ProductDto>> products = Optional.ofNullable(
        productDao.searchProducts(searchArray));
    return products.orElse(null);
  }

  @Override
  public void updateProductQuantity(ProductDto product) {
    productDao.updateProductQuantity(product);
  }

  @Override
  public ModelAndView openDevicesPage(int categoryId) {
    ModelMap modelMap = new ModelMap();
    Set<ProductDto> products = productDao.getProductsByCategory(categoryId);
    if (Optional.ofNullable(products).isPresent()) {
      modelMap.addAttribute(DEVICES.getValue(), products);
    }
    return new ModelAndView(DEVICES_PAGE, modelMap);
  }

  @Override
  public ModelAndView getProductData(int id) throws Exception {
    ModelMap modelMap = new ModelMap();
    ProductDto product = productDao.getProductById(id);
    if (Optional.ofNullable(product).isPresent()) {
      modelMap.addAttribute(PRODUCT.getValue(), product);
    }
    return new ModelAndView(PRODUCT_PAGE, modelMap);
  }

  @Override
  public ModelAndView findProductsFromRequest(String inputString) throws Exception {
    ModelMap modelMap = new ModelMap();
    if (Optional.ofNullable(inputString).isPresent()) {
      String[] searchArr = inputString.split("\\W");
      modelMap.addAttribute(DEVICES.getValue(),
          productDao.searchProducts(searchArr));
    }
    return new ModelAndView(SEARCH_PAGE, modelMap);
  }

  @Override
  public Set<ProductDto> getAllProducts() {
    return productDao.getAllProducts();
  }
}
