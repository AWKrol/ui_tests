package ui;

import com.google.inject.Guice;
import com.google.inject.Injector;
import modules.GuiceModule;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public abstract class AbsBaseTest{

  private Injector injector;

  @BeforeMethod
  public void init() {
    injector = Guice.createInjector(new GuiceModule());
    injector.injectMembers(this);
    WebDriver driver = injector.getProvider(WebDriver.class).get();
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15L));
  }

  @AfterMethod
  public void close() {

    WebDriver driver = injector.getProvider(WebDriver.class).get();

    if(driver != null) {
      driver.close();
      driver.quit();
    }
  }

}
