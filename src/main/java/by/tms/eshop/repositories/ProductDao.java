package by.tms.eshop.repositories;

import by.tms.eshop.dto.ProductDto;
import by.tms.eshop.entities.Product;
import java.util.Set;

public interface ProductDao {

  Set<ProductDto> getAllProducts();

  Set<ProductDto> getProductsByCategory(int categoryId);

  ProductDto getProductById(int productId);

  Set<ProductDto> searchProducts(String[] searchArray);

  void updateProductQuantity(ProductDto product);
}
