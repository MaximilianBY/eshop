package by.tms.eshop;

public enum RequestParamsEnum {
  USER_INFO("user"),
  CATEGORY("categories"),
  DEVICES("devices"),
  PRODUCT("product"),
  USER_CART("user_cart"),
  ORDER_STORY("order_story"),
  ORDER_DETAILS("order_details"),
  TOTAL_PRICE("total_price"),
  ERROR_PARAM("error");

  private final String value;

  RequestParamsEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
