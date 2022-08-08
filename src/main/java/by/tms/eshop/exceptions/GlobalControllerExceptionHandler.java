package by.tms.eshop.exceptions;

import static by.tms.eshop.PagesPathConstants.ERROR_PAGE;
import static by.tms.eshop.RequestParamsEnum.ERROR_PARAM;

import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ExceptionHandler(AuthorizationException.class)
  public ModelAndView handleAuthException(Exception ex) {
    ModelMap modelMap = new ModelMap();
    modelMap.addAttribute(ERROR_PARAM.getValue(), ex.getMessage());
    ModelAndView model = new ModelAndView();
    model.setViewName(ERROR_PAGE);
    model.addAllObjects(modelMap);
    return model;
  }
}
