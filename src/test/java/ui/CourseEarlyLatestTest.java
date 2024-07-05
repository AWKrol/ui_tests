package ui;


import com.google.inject.Inject;
import components.CatalogCourseComponent;
import org.testng.annotations.Test;
import pages.CoursesPage;
import pages.MainPage;
import java.io.IOException;

public class CourseEarlyLatestTest extends AbsBaseTest {

  @Inject
  private MainPage mainPage;

  @Inject
  private CoursesPage coursesPage;

  @Inject
  private CatalogCourseComponent catalogCourseComponent;

  @Test
  public void courseEarlyLatest() throws IOException {
    mainPage.open()
      .clickMoreCourses()
        .pageValidation("Каталог");

    catalogCourseComponent.assertEarlyLateCourse();
  }

}
