package by.tms.eshop.dto.converters;

import by.tms.eshop.dto.ProductDto;
import by.tms.eshop.entities.Product;
import by.tms.eshop.repositories.CategoryDao;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

  private final CategoryDao categoryDao;

  public ProductConverter(CategoryDao categoryDao) {
    this.categoryDao = categoryDao;
  }

  public ProductDto toDto(Product product) {
    return ProductDto.builder()
        .id(product.getId())
        .brand(product.getBrand())
        .model(product.getModel())
        .description(product.getDescription())
        .price(product.getPrice())
        .quantity(product.getQuantity())
        .categoryId(product.getCategory().getId())
        .imagePath(product.getImagePath())
        .build();
  }

  public Product createFromDto(ProductDto productDto) {
    return Product.builder()
        .brand(productDto.getBrand())
        .model(productDto.getModel())
        .description(productDto.getDescription())
        .price(productDto.getPrice())
        .quantity(productDto.getQuantity())
        .category(categoryDao.findById(productDto.getCategoryId()))
        .imagePath(productDto.getImagePath())
        .build();
  }
  public Product readFromDto(ProductDto productDto) {
    return Product.builder()
        .id(productDto.getId())
        .brand(productDto.getBrand())
        .model(productDto.getModel())
        .description(productDto.getDescription())
        .price(productDto.getPrice())
        .quantity(productDto.getQuantity())
        .category(categoryDao.findById(productDto.getCategoryId()))
        .imagePath(productDto.getImagePath())
        .build();
  }

}
