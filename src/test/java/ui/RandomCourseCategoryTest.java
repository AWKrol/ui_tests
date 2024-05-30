package ui;

import com.google.inject.Inject;
import org.testng.annotations.Test;
import pages.CoursesPage;
import pages.MainPage;

public class RandomCourseCategoryTest extends AbsBaseTest {

  @Inject
  MainPage mainPage;

  @Inject
  private CoursesPage coursesPage;

  @Test
  public void randomCourseCategory() throws InterruptedException {

    mainPage.open()
      .cookieOk(mainPage)
      .selectRandomCourseCategory()
      .pageValidation("Каталог")
        .assertCheckboxCoursesType(mainPage);
  }

}
