package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.PlexsupplyHomePage;
import pages.PlexsupplyCategoryPage;

import static constants.Constant.PlexSupplyUrls.PLEXSUPPLY_HOME_PAGE_URL;
import static constants.Constant.Filters;
import static constants.Constant.Categories;

/**
 * This class contain test with category filtration. Automation of P-005
 */
public class PlexsupplyCategoryFiltrationTest extends BaseTest {

    /**
     * Open subcategory from nav menu, use price filter, use another filters,
     * and compare declared products qty and number of displayed products
     *
     * @throws InterruptedException
     */
    @Test
    public void fitersCategory() throws InterruptedException {
        PlexsupplyHomePage homePage = new PlexsupplyHomePage(driver);
        homePage.openPage(PLEXSUPPLY_HOME_PAGE_URL);
        homePage.hoverOnNavItem(Categories.HEALTH);
        homePage.hoverOnNavItem(Categories.EAR_CARE);
        homePage.hoverOnNavItem(Categories.EARPLUGS);
        homePage.openTitledLink(Categories.EARPLUGS);
        PlexsupplyCategoryPage categoryPage = new PlexsupplyCategoryPage(driver);
        categoryPage.checkCategoryTitle(Categories.EARPLUGS);
        categoryPage.setPriceFilter(5, 80);
        categoryPage.useFilter(Filters.E_A_R);
        categoryPage.submitCookie();
        categoryPage.useFilter(Filters.BOX);
        categoryPage.checkResultsOfPriceSearch();
        int declaredProductsQty = categoryPage.getCategoryProductsQty();
        int displayedProductsQty = categoryPage.getDisplayedCategoryProducts();
        Assert.assertEquals(displayedProductsQty, declaredProductsQty);
    }
}
