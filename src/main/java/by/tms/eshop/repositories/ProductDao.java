package by.tms.eshop.repositories;

import by.tms.eshop.entities.Product;
import java.util.Set;

public interface ProductDao {

  Set<Product> getAllProductsFromDb();

  Set<Product> getAllProductsByCategoryFromDb(int categoryId);

  Product getProductByIdFromDb(int productId);

  Set<Product> findProductsByRequestFromSearchDb(String[] searchArray);

  void updateProductQuantityInDb(Product product);
}
