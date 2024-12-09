package tests;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PlexsupplyCategoryPage;
import pages.PlexsupplyCheckoutPage;
import pages.PlexsupplyHomePage;

import static constants.Constant.PlexSupplyUrls.PLEXSUPPLY_HOME_PAGE_URL;
import static constants.Constant.PlexSupplyUrls.PLEXSUPPLY_CHECKOUT_PAGE_URL;
import static constants.Constant.Categories;

/**
 * Contain test automation for P-003
 */
public class PlexsupplyCheckoutShippingCostChangeTest extends  BaseTest
{
    /**
     * Automation of test case P-003 For stable automation that test must use logined customer not a guest!
     *
     * @throws InterruptedException
     */
    @Test
    public void checkShippingCostChangeOnCheckout() throws InterruptedException {
        PlexsupplyHomePage homePage = new PlexsupplyHomePage(driver);
        homePage.openPage(PLEXSUPPLY_HOME_PAGE_URL);
        homePage.openTitledLink(Categories.PROMO);
        PlexsupplyCategoryPage categoryPage = new PlexsupplyCategoryPage(driver);
        categoryPage.addFirstProductToShoppingCart();
        PlexsupplyCheckoutPage checkoutPage = new PlexsupplyCheckoutPage(driver);
        checkoutPage.openPage(PLEXSUPPLY_CHECKOUT_PAGE_URL);
        checkoutPage.setCountrySelectValue("US");
        checkoutPage.setStateSelectValue("1");
        double firstShippingCost = checkoutPage.getShippingCost();
        homePage.openTitledLink(Categories.COFFEE_PROMO);
        categoryPage.addFirstProductToShoppingCart();
        checkoutPage.openPage(PLEXSUPPLY_CHECKOUT_PAGE_URL);
        checkoutPage.setCountrySelectValue("US");
        checkoutPage.setStateSelectValue("1");
        double secondShippingCost = checkoutPage.getShippingCost();
        Assert.assertNotEquals(firstShippingCost,secondShippingCost);
    }
}
