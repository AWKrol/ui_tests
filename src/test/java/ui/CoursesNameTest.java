package ui;

import com.google.inject.Inject;
import components.CatalogCourseComponent;
import org.testng.annotations.Test;
import pages.CoursesPage;
import pages.MainPage;

public class CoursesNameTest extends AbsBaseTest{

  @Inject
  private MainPage mainPage;

  @Inject
  private CoursesPage coursesPage;

  @Inject
  private CatalogCourseComponent catalogCourseComponent;

  @Test
  public void coursesName() {
    String courseName = "C++ Developer. Basic";
    mainPage
      .open()
      .clickMoreCourses()
        .pageValidation("Каталог");

    catalogCourseComponent
      .clickCourseByName(courseName)
        .pageValidation(courseName);
  }

}
