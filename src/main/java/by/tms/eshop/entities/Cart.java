package by.tms.eshop.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Cart {

  private Set<Product> productInCart;

  public Cart() {
    this.productInCart = new HashSet<>();
  }

  public Set<Product> getUsersCart() {
    return productInCart;
  }

  public void addProductToCart(Product product1) {
    if (isExistProductInCart(product1)) {
      productInCart.stream()
          .filter(product -> product.getId() == product1.getId())
          .forEach(product -> {
            product.setQuantity(product.getQuantity() + 1);
            product.setPrice(product.getQuantity() * product1.getPrice());
          });
    } else {
      productInCart.add(Product.builder()
          .id(product1.getId())
          .brand(product1.getBrand())
          .model(product1.getModel())
          .quantity(1)
          .price(product1.getPrice())
          .imagePath(product1.getImagePath())
          .build());
    }
  }

  public int getUserCartTotalPrice() {
    if (Optional.ofNullable(productInCart).isPresent()) {
      return productInCart.stream()
          .mapToInt(Product::getPrice)
          .sum();
    }
    return 0;
  }

  public void delUnnecessaryProduct(Product product1) {
    productInCart.removeIf(product -> {
      if (product.getId() == product1.getId() && product.getQuantity() <= 1) {
        return true;
      } else {
        if (product.getId() == product1.getId()) {
          product.setQuantity(product.getQuantity() - 1);
          product.setPrice(product.getPrice() - product1.getPrice());
          return false;
        }
      }
      return false;
    });
  }

  public void flushUserCart() {
    productInCart.clear();
  }

  private boolean isExistProductInCart(Product product1) {
    return productInCart.stream().anyMatch(product -> product.getId() == product1.getId());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cart cart = (Cart) o;
    return productInCart.equals(cart.productInCart);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productInCart);
  }
}
