package by.tms.eshop.controllers;

import static by.tms.eshop.EshopConstants.PRODUCT_ID;

import by.tms.eshop.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/product")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping()
  public ModelAndView openProductPage(@RequestParam(PRODUCT_ID) String id) throws Exception {
    int productId = Integer.parseInt(id);
    return productService.getProductData(productId);
  }

}
