package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.lang.reflect.Field;

import static constants.Constant.TimeoutVariables.IMPLICIT_WAIT;

/**
 * Parent class for all pages
 */
public class BasePage {
    protected WebDriver driver;
    protected By shoppingCartBtn = By.className("showcart");
    protected By submitCookieBtn = By.id("btn-cookie-allow");
    protected By checkoutMiniCartBtn = By.id("top-cart-btn-checkout");

    /**
     * Construct method
     *
     * @param driver
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Open page by url
     *
     * @param url
     */
    public void openPage(String url) {
        driver.get(url);
    }

    /**
     * Wait page element loading
     *
     * @param element
     * @return element
     */
    public WebElement waitElementIsVisible(WebElement element) {
        new WebDriverWait(driver, IMPLICIT_WAIT).until(ExpectedConditions.visibilityOf(element));

        return element;
    }

    /**
     * Expand mini shopping cart on any page
     */
    public void expandShoppingCart() {
        WebElement showCartBtnElement = driver.findElement(shoppingCartBtn);
        showCartBtnElement.click();
        WebElement checkoutBtnElement = driver.findElement(checkoutMiniCartBtn);
        waitElementIsVisible(checkoutBtnElement);
    }

    /**
     * Open checkout page from already expanded mini cart
     * Function separated with expandShoppingCart because previous function can be part of assertions
     */
    public void goToCheckoutFromMiniCart() {
        WebElement checkoutBtnElement = driver.findElement(checkoutMiniCartBtn);
        if (checkoutBtnElement.isDisplayed()) {
            checkoutBtnElement.click();
        }
    }

    /**
     * Open one of categories from header or navigation menu
     * (in real test must be used specially created for test purpose category)
     *
     * @param linkTitle
     */
    public void openTitledLink(String linkTitle) {
        WebElement target = driver.findElement(By.linkText(linkTitle));
        waitElementIsVisible(target);
        target.click();
    }


    /**
     * Universal getter for page By elements
     *
     * @param fieldName
     * @return
     */
    public By getElementByFieldName(String fieldName) {
        try {
            Field field = this.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);

            return (By) field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("There no field in that page: " + fieldName, e);
        }
    }

    /**
     * Open one of categories from header or navigation menu
     * (in real test must be used specially created for test purpose category)
     */
    public void submitCookie() {
        WebElement cookieButtonElement = driver.findElement(submitCookieBtn);
        waitElementIsVisible(cookieButtonElement);
        cookieButtonElement.click();
    }
}
