package common;

import java.io.File;

import constants.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static constants.Constant.BrowserCombinations.OS_AND_BROWSER_COMBINATIONS;
import static constants.Constant.BrowserCombinations.PLATFORM_AND_BROWSER;
import static constants.Constant.ErrorMessages.WRONG_OS_BROWSER_COMBINATION_MESSAGE;

/**
 * Class with base actions
 */
public class CommonActions {

    /**
     * Prepare and return webdriver depended on platform and browser (default Linux, Chrome)
     *
     * @return
     */
    public static WebDriver createDriver() {
        WebDriver driver = null;

        String platformAndBrowser = PLATFORM_AND_BROWSER;

        String cliPlatformAndBrowser = System.getProperty("platformAndBrowser");

        if (cliPlatformAndBrowser != null) {
            platformAndBrowser = cliPlatformAndBrowser;
            boolean isAllowed = Arrays.asList(OS_AND_BROWSER_COMBINATIONS).contains(cliPlatformAndBrowser);

            if (!isAllowed) {
                throw new RuntimeException(cliPlatformAndBrowser + WRONG_OS_BROWSER_COMBINATION_MESSAGE);
            }
        }

        switch (platformAndBrowser) {
            case "linux_chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
                driver = new ChromeDriver();
                break;
            case "windows_chrome":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "linux_firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
                driver = new FirefoxDriver();
                break;
            case "windows_firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            default:
                Assert.fail("Incorrect platform or browser: " + platformAndBrowser);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constant.TimeoutVariables.EXCLICIT_WAIT, TimeUnit.SECONDS);

        return driver;
    }
}
