package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Page for plexsupply.com home page
 */
public class PlexsupplyHomePage extends BasePage
{
    /**
     * Construct method
     *
     * @param driver
     */
    public PlexsupplyHomePage(WebDriver driver)
    {
        super(driver);
    }

    /**
     * Hover on item of navigation menu
     * @param title
     */
    public void hoverOnNavItem(String title)
    {
        WebElement targetNavItem = driver.findElement(By.linkText(title));
        waitElementIsVisible(targetNavItem);
        Actions actions = new Actions(driver);
        actions.moveToElement(targetNavItem).perform();
    }

}
