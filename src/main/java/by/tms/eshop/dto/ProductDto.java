package by.tms.eshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

  private int id;
  private String brand;
  private String model;
  private String description;
  private int price;
  private int quantity;
  private int categoryId;
  private String imagePath;
}
