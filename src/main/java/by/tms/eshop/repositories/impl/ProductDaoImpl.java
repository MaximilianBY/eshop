package by.tms.eshop.repositories.impl;

import by.tms.eshop.dto.ProductDto;
import by.tms.eshop.dto.converters.ProductConverter;
import by.tms.eshop.entities.Product;
import by.tms.eshop.repositories.ProductDao;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Transactional
@Repository
public class ProductDaoImpl implements ProductDao {

  @PersistenceContext
  private EntityManager entityManager;

  private final ProductConverter productConverter;

  public ProductDaoImpl(ProductConverter productConverter) {
    this.productConverter = productConverter;
  }

  @Override
  public Set<ProductDto> getAllProducts() {
    Query products = entityManager.createQuery(
        "select u from Product u where u.quantity >0");
    Set<Product> productSet = new HashSet<>(products.getResultList());
    return productSet.stream().map(productConverter::toDto).collect(
        Collectors.toSet());
  }

  @Override
  public Set<ProductDto> getProductsByCategory(int categoryId) {
    Query products = entityManager.createQuery(
        "select u from Product u where u.category.id=:categoryId and u.quantity >0");
    products.setParameter("categoryId", categoryId);
    log.info(products.getResultList().toString());
    Set<Product> productSet = new HashSet<>(products.getResultList());
    return productSet.stream().map(productConverter::toDto).collect(
        Collectors.toSet());
  }

  @Override
  public ProductDto getProductById(int productId) {
    Query product = entityManager.createQuery(
        "select u from Product u where u.id=:productId");
    product.setParameter("productId", productId);
    return productConverter.toDto((Product) product.getSingleResult());
  }

  @Override
  public Set<ProductDto> searchProducts(String[] searchArray) {
    Set<ProductDto> products = new HashSet<>();

    for (ProductDto product : getAllProducts()) {
      int count = 0;
      for (String request : searchArray) {
        if (product.getBrand().toLowerCase().contains(request.toLowerCase()) || product.getModel()
            .toLowerCase().contains(request.toLowerCase())) {
          count++;
        }
        if (count >= searchArray.length) {
          products.add(product);
        }
      }
    }
    return products;
  }

  @Override
  public void updateProductQuantity(ProductDto entity) {
    Product product = entityManager.find(Product.class, entity.getId());
    product.setQuantity(product.getQuantity() - entity.getQuantity());
    entityManager.merge(product);
  }
}