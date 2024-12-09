package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.NoSuchElementException;

/**
 * Class with checkout page
 */
public class PlexsupplyCheckoutPage extends BasePage {

    private final By removeItemBtn = By.cssSelector(".product > .delete");
    private final By submitItemRemoveBtn = By.cssSelector(".action-accept");
    private final By emptyCartMessage = By.className("cart-empty");
    private final By shippngTotal = By.cssSelector("span[data-th=\"Shipping\"]");
    private final By countrySelect  = By.name("country_id");
    private final By stateSelect  = By.name("region_id");

    /**
     * Page constructor
     *
     * @param driver
     */
    public PlexsupplyCheckoutPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Remove single order item on checkout page. Method is flaky!!
     *
     * @return
     */
    public PlexsupplyCheckoutPage removeSingleOrderItem() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

        WebElement removeSingleItemBtn = wait.until(driver -> driver.findElement(removeItemBtn));

        // I know -  Thread.sleep is not a good practice but checkout page load not immediately
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(removeItemBtn)).perform();
        wait.until(driver -> true);
        removeSingleItemBtn.click();
        WebElement submitRemoveElement = driver.findElement(submitItemRemoveBtn);
        submitRemoveElement.click();

        return this;
    }

    /**
     * Set country in select input
     *
     * @param countryCode
     * @return
     */
    public PlexsupplyCheckoutPage setCountrySelectValue(String countryCode)
    {
        WebElement selectElement = driver.findElement(countrySelect);
        Select select = new Select(selectElement);
        select.selectByValue(countryCode);

        return this;
    }

    /**
     * Set state/region value
     *
     * @param regionCode
     * @return
     */
    public PlexsupplyCheckoutPage setStateSelectValue(String regionCode)
    {
        WebElement selectElement = driver.findElement(stateSelect);
        Select select = new Select(selectElement);
        select.selectByValue(regionCode);
        // this wait need for changing shipping price because shipping price calculated not immediately
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this;
    }


    /**
     * Get total shipping cost
     *
     * @return
     */
    public double getShippingCost()
    {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(shippngTotal, "$"));
        WebElement filerFromPriceElement = driver.findElement(shippngTotal);

        return Double.parseDouble((filerFromPriceElement.getText()).replace("$", ""));
    }
}
