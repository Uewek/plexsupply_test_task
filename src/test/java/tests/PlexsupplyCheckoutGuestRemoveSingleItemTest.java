package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PlexsupplyCategoryPage;
import pages.PlexsupplyCheckoutPage;
import pages.PlexsupplyHomePage;
import static constants.Constant.PlexSupplyUrls.PLEXSUPPLY_HOME_PAGE_URL;
import static constants.Constant.Categories;

/**
 * Class with automation of test case P-004
 */
public class PlexsupplyCheckoutGuestRemoveSingleItemTest extends BaseTest {

    /**
     * Automation of test case P-004
     *
     * @throws InterruptedException
     */
    @Test
    public void plexSupplyGuestRemoveSingleItemOnCheckout() throws InterruptedException {
        PlexsupplyHomePage homePage = new PlexsupplyHomePage(driver);
        homePage.openPage(PLEXSUPPLY_HOME_PAGE_URL);
        homePage.openTitledLink(Categories.PROMO);
        PlexsupplyCategoryPage categoryPage = new PlexsupplyCategoryPage(driver);
        categoryPage.checkCategoryTitle("Promo");
        categoryPage.addFirstProductToShoppingCart();
        categoryPage.expandShoppingCart();
        categoryPage.goToCheckoutFromMiniCart();
        PlexsupplyCheckoutPage checkoutPage = new PlexsupplyCheckoutPage(driver);
        checkoutPage.removeSingleOrderItem();
        By emptyCartMessage = checkoutPage.getElementByFieldName("emptyCartMessage");
        Assert.assertTrue(driver.findElement(emptyCartMessage).isDisplayed());
    }
}
