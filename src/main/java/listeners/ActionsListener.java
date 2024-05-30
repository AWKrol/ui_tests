package listeners;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.events.WebDriverListener;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ActionsListener implements WebDriverListener {

  @Override
  public void beforePerform(WebDriver driver, Collection<Sequence> actions) {
    actions.stream()
        .flatMap(action -> getOrigins(action).stream())
        .forEach(origin -> ((JavascriptExecutor) driver)
        .executeScript(
        "arguments[0].setAttribute("
          + "\"onmouseover\", \"style='border:3mm ridge rgba(500, 0, 0, .8)';\");"
          + "arguments[0].setAttribute(" + "\"onclick\", \"style=null;\");",
          origin));
  }

  private List<WebElement> getOrigins(Sequence sequence) {
    Map<String, Object> encodeSequence = sequence.encode();
    if (!encodeSequence.containsKey("actions")) {

      return Collections.emptyList();
    }

    return ((List<Map<String, Object>>) encodeSequence.get("actions")).stream()
              .filter(action -> action.get("origin") instanceof WebElement)
              .map(action -> (WebElement) action.get("origin"))
              .collect(Collectors.toList());
  }


  //    @Inject
  //    private WebDriver driver;

  //
  //    @Override
  //    public void beforeClick(WebElement element) {
  //
  ////        System.out.println(driver);
  ////        int i =1;
  ////        ((JavascriptExecutor) driver)
  ////                        .executeScript(
  ////                                "arguments[0].setAttribute("
  ////                                        + "\"onmouseover\", \"style='border:5px solid black';\");"
  //////                                        + "arguments[0].setAttribute("
  //////                                        + "\"onclick\", \"style=null;\");",
  ////                                ,
  ////                                element);
  //    }
  //
  //    @SuppressWarnings("unchecked")
  //    private List<WebElement> getOrigins(Sequence sequence) {
  //        Map<String, Object> encodedSequence = sequence.encode();
  //        if (!encodedSequence.containsKey("actions")) {
  //            return Collections.emptyList();
  //        }
  //        return ((List<Map<String, Object>>) encodedSequence.get("actions")).stream()
  //                .filter(action -> action.get("origin") instanceof WebElement)
  //                .map(action -> (WebElement) action.get("origin"))
  //                .collect(Collectors.toList());
  //    }

}
