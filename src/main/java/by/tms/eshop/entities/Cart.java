package by.tms.eshop.entities;

import by.tms.eshop.dto.ProductDto;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Cart {

  private Set<ProductDto> productInCart;

  public Cart() {
    this.productInCart = new HashSet<>();
  }

  public Set<ProductDto> getUsersCart() {
    return productInCart;
  }

  public void addProductToCart(ProductDto addedProduct) {
    if (isExistProductInCart(addedProduct)) {
      productInCart.stream()
          .filter(product -> product.getId() == addedProduct.getId())
          .forEach(product -> {
            product.setQuantity(product.getQuantity() + 1);
            product.setPrice(product.getQuantity() * addedProduct.getPrice());
          });
    } else {
      productInCart.add(ProductDto.builder()
          .id(addedProduct.getId())
          .brand(addedProduct.getBrand())
          .model(addedProduct.getModel())
          .quantity(1)
          .price(addedProduct.getPrice())
          .imagePath(addedProduct.getImagePath())
          .build());
    }
  }

  public int getUserCartTotalPrice() {
    if (Optional.ofNullable(productInCart).isPresent()) {
      return productInCart.stream()
          .mapToInt(ProductDto::getPrice)
          .sum();
    }
    return 0;
  }

  public void delUnnecessaryProduct(ProductDto productToRemove) {
    productInCart.removeIf(product -> {
      if (product.getId() == productToRemove.getId() && product.getQuantity() <= 1) {
        return true;
      } else {
        if (product.getId() == productToRemove.getId()) {
          product.setQuantity(product.getQuantity() - 1);
          product.setPrice(product.getPrice() - productToRemove.getPrice());
          return false;
        }
      }
      return false;
    });
  }

  public void flushUserCart() {
    productInCart.clear();
  }

  private boolean isExistProductInCart(ProductDto productToCheck) {
    return productInCart.stream().anyMatch(product -> product.getId() == productToCheck.getId());
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
