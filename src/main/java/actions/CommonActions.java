package actions;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import waiters.StandardWaiters;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class CommonActions<T> {

  protected WebDriver driver;
  protected StandardWaiters waiters;
  protected Faker faker;
  protected Actions actions;

  public CommonActions(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);

    waiters = new StandardWaiters(driver);
    faker= new Faker();
    actions = new Actions(driver);
  }

  protected BiConsumer<By, Predicate<? super WebElement>> clickElementByPredicate = (By locator, Predicate<? super WebElement> predicate) -> {
    List<WebElement> elements = driver.findElements(locator).stream().filter(predicate).collect(Collectors.toList());

    if(!elements.isEmpty()) {
      elements.get(0).click();
    }
  };

  public WebElement $(By locator) {
    return driver.findElement(locator);
  }

  public void click(WebElement element) {
    if (new StandardWaiters(driver).waitForElementClickable(element)) {
      //      actions.moveToElement(element).build().perform();
      element.click();
    } else {
      Assert.fail("element not clickable");
    }
  }
}
