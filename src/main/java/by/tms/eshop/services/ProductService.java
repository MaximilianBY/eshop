package by.tms.eshop.services;

import by.tms.eshop.entities.Product;
import java.util.Set;
import org.springframework.web.servlet.ModelAndView;

public interface ProductService {

  Set<Product> getAllProductsByCategory(int categoryID) throws Exception;

  Product getProductByID(int productID) throws Exception;

  Set<Product> findProductByRequestFromSearch(String[] searchArray) throws Exception;
  void updateProductQuantity(Product product);

  ModelAndView getProductData(int id) throws Exception;

  ModelAndView findProductsFromRequest(String inputString) throws Exception;
  ModelAndView openDevicesPage(int categoryId);
}
