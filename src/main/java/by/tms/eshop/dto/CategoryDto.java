package by.tms.eshop.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

  private int id;
  private String name;
  private String imagePath;
  private Set<ProductDto> productDtoSet;
}
