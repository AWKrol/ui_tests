package factory.impl;

import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class ChromeSettings implements IWebDriverSettings<ChromeOptions> {

  @Override
  public ChromeOptions getSettings() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--ignore-certificate-errors");
    chromeOptions.addArguments("--start-maximized");
    chromeOptions.addArguments("--no-first-run");
    chromeOptions.addArguments("--homepage=about:blank");
    chromeOptions.setPageLoadTimeout(Duration.ofSeconds(30));

    return chromeOptions;
  }

}
