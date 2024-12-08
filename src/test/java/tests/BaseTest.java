package tests;

import common.CommonActions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

/**
 * Parent class for all tests
 */
public abstract class BaseTest {

    protected WebDriver driver;

    /**
     * Get webdriver default or declared in CLI
     */
    @BeforeTest
    public void setUpTest() {
        driver = CommonActions.createDriver();
    }

    /**
     * Clear browser cookies and close test window after test execution
     */
    @AfterTest
    public void clearCookiesAndLocalStorage() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        driver.manage().deleteAllCookies();
        javascriptExecutor.executeScript("window.sessionStorage.clear()");
        driver.close();
        driver.quit();
    }
}
