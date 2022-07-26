package by.tms.eshop.repositories.impl;

import by.tms.eshop.entities.Product;
import by.tms.eshop.repositories.ProductDao;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class ProductDaoImpl implements ProductDao {

  private final SessionFactory sessionFactory;

  public ProductDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public Set<Product> getAllProductsFromDb() {
    Session session = sessionFactory.getCurrentSession();
    Query<Product> products = session.createQuery(
        "select u from Product u where u.quantity >0");
    return new HashSet<>(products.getResultList());
  }

  @Override
  public Set<Product> getAllProductsByCategoryFromDb(int categoryId) {
    Session session = sessionFactory.getCurrentSession();
    Query<Product> products = session.createQuery(
        "select u from Product u where u.category.id=:categoryId and u.quantity >0");
    products.setParameter("categoryId", categoryId);
    log.info(products.getResultList().toString());
    return new HashSet<>(products.getResultList());
  }

  @Override
  public Product getProductByIdFromDb(int productId) {
    Session session = sessionFactory.getCurrentSession();
    Query<Product> product = session.createQuery(
        "select u from Product u where u.id=:productId");
    product.setParameter("productId", productId);
    return product.getSingleResult();
  }

  @Override
  public Set<Product> findProductsByRequestFromSearchDb(String[] searchArray) {
    Set<Product> products = new HashSet<>();

    for (Product product : getAllProductsFromDb()) {
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
  public void updateProductQuantityInDb(Product product) {
    Session session = sessionFactory.getCurrentSession();
    session.beginTransaction();
    javax.persistence.Query updateProd = session.createQuery(
        "update Product u set u.quantity=:quantity where u.id=:productId");
    int remainingQuantity =
        getProductByIdFromDb(product.getId()).getQuantity() - product.getQuantity();
    updateProd.setParameter("productId", product.getId());
    updateProd.setParameter("quantity", remainingQuantity);
    updateProd.executeUpdate();
    session.getTransaction().commit();
  }
}
