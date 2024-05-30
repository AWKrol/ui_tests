package pages;

import annotations.PageValidation;
import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;

@PageValidation("//h1")
public class CourseDetailPage extends AnyPageAbs<CourseDetailPage> {

  @Inject
  public CourseDetailPage(WebDriver driver) {
    super(driver);
  }

  //@FindBy(xpath = "//h1")
  //private WebElement courseHeader;
  //
  //public void assertHeaderCourse(String courseName) {
  //Assert.assertEquals(courseHeader.getText(), courseName);
  //}

}

