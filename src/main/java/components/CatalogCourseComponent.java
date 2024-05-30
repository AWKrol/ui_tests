package components;

import annotations.Component;
import com.google.inject.Inject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.CourseDetailPage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component("xpath://button[contains(text(),'Показать еще')]/preceding-sibling::div")
public class CatalogCourseComponent extends AnyComponentAbs<CatalogCourseComponent> {

  @Inject
  public CatalogCourseComponent(WebDriver driver) {
    super(driver);
  }

  //@FindBy(xpath = "//button[contains(text(),'Показать еще')]/preceding-sibling::div/a")
  //private List<WebElement> courses;
  //private final List<WebElement> courses = getComponentEntity().findElements(By.xpath("/a"));

  private List<WebElement> getListCourses() {
    return getComponentEntity().findElements(By.xpath("a"));
  }

  public CourseDetailPage clickCourseByName(String courseName) {
    getListCourses().stream()
      .map(element -> getNameCourse(element))
      .filter(element -> element.getText().equals(courseName))
      .findFirst().get().click();

    return new CourseDetailPage(driver);
  }

  public List<WebElement> getEarlyLateCourse(boolean early) {
    //DateTimeFormatter dtf = DateTimeFormatter.ofPattern( "dd MMMM, yyyy");
    String dateFormatter = "dd MMMM, yyyy";
    String dateCourse =
            getListCourses().stream()
            .map(element -> {
              //String date = element.findElement(By.xpath("div[last()]")).getText().split(" · ")[0];
              String date = getDateCourse(element).split(" · ")[0];
              return  LocalDate.parse(date, DateTimeFormatter.ofPattern(dateFormatter));
            })
            .reduce((courseOne, courseTwo) -> (courseOne.compareTo(courseTwo) <= 0) == early ? courseOne : courseTwo)
            .map(x -> x.format(DateTimeFormatter.ofPattern(dateFormatter, new Locale("ru")))).get();

    System.out.println(dateCourse);

    return getListCourses().stream()
            .filter(element -> getDateCourse(element).contains(dateCourse))
            .collect(Collectors.toList());
  }

  private WebElement getNameCourse(WebElement element) {
    return element.findElement(By.xpath("h6"));
  }

  private String getDateCourse(WebElement element) {
    return element.findElement(By.xpath("div[last()]")).getText();
  }

  private void assertEarlyLateCourseHelper(boolean early) throws IOException {
    List<WebElement> earlyCourse = getEarlyLateCourse(early);
    for(WebElement course : earlyCourse) {
      String dateCourseExpected = getDateCourse(course);
      String nameCourseExpected = getNameCourse(course).getText();
      String href = course.getAttribute("href");
      System.out.println(href);
      //Document document = Jsoup.connect(StringUtils.stripEnd(System.getProperty("base.url"), "/") + href)
      Document document = Jsoup.connect(href).get();
      String nameCourseActual = document.selectXpath("(//h1)[1]").text();
      String dateCourseActual = document.selectXpath("//section/div[last()]/div/div[1]/p").text();
      Assert.assertEquals(nameCourseActual, nameCourseExpected);
      Assert.assertTrue(dateCourseExpected.contains(dateCourseActual));
      //break;
    }
  }

  public void assertEarlyLateCourse() throws IOException {
    assertEarlyLateCourseHelper(true);
    assertEarlyLateCourseHelper(false);
  }

}
