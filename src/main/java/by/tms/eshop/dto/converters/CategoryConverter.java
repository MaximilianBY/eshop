package by.tms.eshop.dto.converters;

import by.tms.eshop.dto.CategoryDto;
import by.tms.eshop.entities.Category;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

  private final ProductConverter productConverter;

  public CategoryConverter(ProductConverter productConverter) {
    this.productConverter = productConverter;
  }

  public CategoryDto toDto(Category category) {
    return Optional.ofNullable(category).map(c -> CategoryDto.builder()
            .id(c.getId())
            .name(c.getName())
            .imagePath(c.getImagePath())
            .productDtoSet(Optional.ofNullable(c.getProductSet())
                .map(p -> p.stream().map(productConverter::toDto)
                    .collect(Collectors.toSet())).orElse(Set.of()))
            .build())
        .orElse(null);
  }

  public Category fromDto(CategoryDto categoryDto){
    return Optional.ofNullable(categoryDto).map(cd -> Category.builder()
        .name(cd.getName())
        .imagePath(cd.getImagePath())
        .build())
        .orElse(null);
  }
}
