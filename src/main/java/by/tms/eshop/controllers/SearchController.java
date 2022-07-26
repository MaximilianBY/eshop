package by.tms.eshop.controllers;

import by.tms.eshop.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/search")
public class SearchController {

  private ProductService productService;

  public SearchController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public ModelAndView getResultFromSearchRequest(@RequestParam("searchField") String searchField)
      throws Exception {
    return productService.findProductsFromRequest(searchField);
  }
}
