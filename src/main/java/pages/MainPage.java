package pages;

import annotations.UrlPrefix;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

@UrlPrefix("/")
public class MainPage extends AnyPageAbs<MainPage> {

  private String selectCourseTypeName;

  public String getSelectCourseTypeName() {
    return selectCourseTypeName;
  }

  @Inject
  public MainPage(WebDriver driver) {
    super(driver);
  }

  public CoursesPage clickMoreCourses() {
    click(moreCourses);

    return new CoursesPage(driver);
  }

  @FindBy(xpath = "//section/a/button")
  private WebElement moreCourses;

  @FindBy(xpath = "//nav/div[2]")
  private WebElement coursesType;

  public CoursesPage selectRandomCourseCategory() throws InterruptedException {
    String valueClassInitial = coursesType.getAttribute("class");

    actions.moveToElement(coursesType).build().perform();
    String valueClassFinal = coursesType.getAttribute("class");

    for (int i = 0; i < 8; i++) {
      if (!valueClassInitial.equals(valueClassFinal)){
        List<WebElement> listCourses = driver.findElements(By.xpath("//p[text()='Все курсы']/following-sibling::div/a"));
        WebElement selectCourseType = faker.options().nextElement(listCourses);
        selectCourseTypeName = selectCourseType.getText().split(" \\(")[0];
        selectCourseType.click();

        return new CoursesPage(driver);
      }
      Thread.sleep(200);
    }

    return null;
  }

}
