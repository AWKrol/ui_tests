package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import components.CatalogCourseComponent;
import factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import pages.CourseDetailPage;
import pages.CoursesPage;
import pages.MainPage;

public class GuiceModule extends AbstractModule {

  private final WebDriver driver = new WebDriverFactory().getDriver();

  @Provides
  public WebDriver getDriver() {
    return this.driver;
  }

  @Provides
  @Singleton

  public MainPage getMainPage() {
    return new MainPage(driver);
  }

  @Provides
  @Singleton
  public CoursesPage getCoursesPage() {
    return new CoursesPage(driver);
  }

  @Provides
  @Singleton
  public CourseDetailPage getCourseDetailPage() {
    return new CourseDetailPage(driver);
  }

  @Provides
  @Singleton
  public CatalogCourseComponent getCatalogCourseComponent() {
    return new CatalogCourseComponent(driver);
  }

}
