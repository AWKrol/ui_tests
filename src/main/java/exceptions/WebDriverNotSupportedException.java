package exceptions;

public class WebDriverNotSupportedException extends RuntimeException {

  public WebDriverNotSupportedException(String browserName) {
    super(String.format("browser %s not supported", browserName));
  }
}
