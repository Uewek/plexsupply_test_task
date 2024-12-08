package pages;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.openqa.selenium.interactions.Actions;


import java.util.List;

/**
 * Class with category or search result page
 */
public class PlexsupplyCategoryPage extends BasePage{


    private By categoryTitle = By.xpath("//*[@id=\"page-title-heading\"]/span");
    private By closeCartMessageButton = By.cssSelector(".close-message");
    private By addToCartBtn = By.cssSelector("button[title='Add to Cart']");
    private By priceSlider = By.cssSelector(".smile-es-range-slider > .ui-slider");
    private By submitPriceBtn = By.cssSelector("a[data-role=\"apply-range\"]");
    private By productQty = By.cssSelector("p.toolbar-amount > span.toolbar-number");
    private By displayedProductCard = By.cssSelector("main li.item.product.product-item");
    private By productCardFinalPrice = By.xpath("span[data-price-type=\"finalPrice\"]");
    private By filterFromPrice = By.cssSelector(".smile-es-range-slider div[data-role=\"from-label\"]\n");
    private By filterToPrice = By.cssSelector(".smile-es-range-slider div[data-role=\"to-label\"]\n");


    /**
     * Construct method
     *
     * @param driver
     */
    public PlexsupplyCategoryPage(WebDriver driver)
    {
        super(driver);
    }

    /**
     * Page dependent assertion. Check
     *
     * @param title
     */
    public PlexsupplyCategoryPage checkCategoryTitle(String title)
    {
        WebElement categoryTitleElement = driver.findElement(categoryTitle);
        String realTitle = categoryTitleElement.getText();
        Assert.assertTrue(realTitle.contains(title));

        return this;
    }

    /**
     * Add first product from category
     *
     */
    public PlexsupplyCategoryPage addFirstProductToShoppingCart()
    {
        WebElement firstAddToCartBtn = driver.findElements(addToCartBtn).get(0);
        firstAddToCartBtn.click();
        // PopUp displayed not always!
        List<WebElement> closeElements = driver.findElements(closeCartMessageButton);
        if (!closeElements.isEmpty()) {
            closeElements.get(0).click();
        }

        return  this;
    }

    /**
     * Set price filter on category or search result page
     *
     * @param minPricePercent
     * @param maxPricePercent
     * @return
     */
    public PlexsupplyCategoryPage setPriceFilter(int minPricePercent, int maxPricePercent)
    {
        WebElement priceSliderElement = driver.findElement(priceSlider);
        int sliderWidth =  priceSliderElement.getSize().getWidth();
        int minCoef = (sliderWidth/100)*minPricePercent;
        int maxCoef = (sliderWidth/100)*maxPricePercent;
        int center = (sliderWidth/2);
        int minPxPoint = center-minCoef;
        int maxPxPoint = center+(center - maxCoef);

        Actions actions = new Actions(driver);
        actions.moveToElement(priceSliderElement, -minPxPoint, 0).click().perform();
        actions.moveToElement(priceSliderElement, maxPxPoint, 0).click().perform();
        WebElement submitPriceBtnElement = driver.findElement(submitPriceBtn);
        submitPriceBtnElement.click();

        return this;
    }

    /**
     * Use filter on category or search result page
     *
     * @param filterName
     * @return
     */
    public PlexsupplyCategoryPage useFilter(String filterName)
    {
        String selector = "//span[text()='" + filterName +"']";
        WebElement navFilterElement = driver.findElement(By.xpath(selector));
        navFilterElement.click();

        return this;

    }

    /**
     * Get qty of products in category from displayed qty number
     *
     * @return
     */
    public int getCategoryProductsQty()
    {
        WebElement qtyNumber = driver.findElement(productQty);
        return Integer.parseInt(qtyNumber.getText());
    }

    /**
     * Get qty of products in category by displayed product cards
     *
     * @return
     */
    public int getDisplayedCategoryProducts()
    {
        List<WebElement> productCards = driver.findElements(displayedProductCard);
        return productCards.size();
    }

    /**
     * Check results of search by price action
     *
     * @return this
     */
    public PlexsupplyCategoryPage checkResultsOfPriceSearch() {
        WebElement filerFromPriceElement = driver.findElement(filterFromPrice);
        double fromPrice = Double.parseDouble((filerFromPriceElement.getText()).replace("$", ""));
        WebElement filerToPriceElement = driver.findElement(filterToPrice);
        double toPrice = Double.parseDouble((filerToPriceElement.getText()).replace("$", ""));
        List<WebElement> priceElements = driver.findElements(productCardFinalPrice);

        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replace(" ", "");
            int currentTruePrice = Integer.parseInt(priceText);
            Assert.assertTrue(currentTruePrice >= fromPrice && currentTruePrice <= toPrice);
        }

        return this;
    }
}
