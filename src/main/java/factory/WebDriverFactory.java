package factory;

import exceptions.WebDriverNotSupportedException;
import factory.impl.ChromeSettings;
import factory.impl.IWebDriverSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.ActionsListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import java.util.Locale;

public class WebDriverFactory implements IDriverFactory {
  private final String BROWSER_NAME = System.getProperty("browser.name");
  private final String browserVersion = System.getProperty("browser.version", "123.0");

  @Override
  public WebDriver getDriver() throws WebDriverNotSupportedException {
    switch (BROWSER_NAME.toLowerCase(Locale.ROOT)) {
      case "chrome": {
        WebDriverManager.chromedriver().browserVersion(browserVersion);
        IWebDriverSettings<ChromeOptions> browserSettings = new ChromeSettings();

        return new EventFiringDecorator<>(new ActionsListener())
          .decorate(new ChromeDriver(browserSettings.getSettings()));
      }
      default:
        throw new WebDriverNotSupportedException(BROWSER_NAME);
    }
    //throw new WebDriverNotSupportedException(BROWSER_NAME);
  }

  //    @Override
  //    public WebDriver getDriver() throws WebDriverNotSupportedException {
  //        switch (BROWSER_NAME.toLowerCase(Locale.ROOT)) {
  //            case "chrome": {
  //                WebDriverManager.chromedriver().browserVersion(browserVersion);
  //                IWebDriverSettings<ChromeOptions> browserSettings = new ChromeSettings();
  //                return new EventFiringWebDriver(
  //                        (new ChromeDriver(browserSettings.getSettings()))).register(new WebDriverListener());
  //            }
  //            default:
  //                throw new WebDriverNotSupportedException(BROWSER_NAME);
  //        }
  ////        throw new WebDriverNotSupportedException(BROWSER_NAME);
  //    }

}
