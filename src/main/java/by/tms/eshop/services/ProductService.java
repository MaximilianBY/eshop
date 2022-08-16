package by.tms.eshop.services;

import by.tms.eshop.dto.ProductDto;
import by.tms.eshop.entities.Product;
import java.util.Set;
import org.springframework.web.servlet.ModelAndView;

public interface ProductService {

  Set<ProductDto> getAllProductsByCategory(int categoryID) throws Exception;

  ProductDto getProductByID(int productID) throws Exception;

  Set<ProductDto> findProductByRequestFromSearch(String[] searchArray) throws Exception;
  void updateProductQuantity(ProductDto product);

  ModelAndView getProductData(int id) throws Exception;

  ModelAndView findProductsFromRequest(String inputString) throws Exception;
  ModelAndView openDevicesPage(int categoryId);
  Set<ProductDto> getAllProducts();
}
