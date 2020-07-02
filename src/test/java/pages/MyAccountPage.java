package pages;

import data.DataFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class MyAccountPage extends BasePage {

    /*
     * Page locators
     */
    @FindBy(id="search_query_top")
    private WebElement searchFieldLocator;

    @FindBy(xpath = "//*[@id='searchbox']/button")
    private WebElement searchButtonLocator;

    @FindBy(xpath = "//*[@id='social_block']/ul/li[@class='twitter']")
    private WebElement twitterButtonLocator;

    @FindBy(xpath = "//*[@id='social_block']/ul/li[@class='facebook']")
    private WebElement facebookButtonLocator;

    @FindBy(xpath = "//*[@id='social_block']/ul/li[@class='youtube']")
    private WebElement youtubeButtonLocator;

    @FindBy(xpath = "//*[@id='social_block']/ul/li[@class='google-plus']")
    private WebElement googleButtonLocator;

    @FindBy(id = "reduction_percent_display")
    private WebElement reductionPricepercentLocator;

    @FindBy(id = "quantity_wanted")
    private WebElement quantityFieldLocator;

    @FindBy(id = "group_1")
    private WebElement sizeDropDownLocator;

    @FindBy(id = "color_to_pick_list")
    private WebElement colorFieldsLocator;

    @FindBy(xpath = "//*[@id='add_to_cart']/button")
    private WebElement addToCartButtonLocator;

    @FindBy(xpath = "//*[@id='layer_cart']//div[@class='layer_cart_product_info']")
    private WebElement productInfoModalLocator;

    @FindBy(xpath = "//*[@id='layer_cart']//a[contains(@class,'button')]")
    private WebElement checkoutButtonLocator;

    private static String searchResultsOptionsLocator = "//*[@id='center_column']/ul/li//a/img";
    private static String productAttributesLocator = "./span[contains(@id,'attributes')]";
    private static String productQuantityLocator = ".//span[contains(@id,'quantity')]";

    public MyAccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isMyAccountPageLoaded(){
       return isPageLoaded(DataFile.myAccountPageUrl, signInOutButtonLocator);
    }

    public boolean isSignOutButtonPresent(){
        return isElementPresent(signInOutButtonLocator);
    }

    public void searchText(String searchPhrase){
        searchFieldLocator.click();
        searchFieldLocator.clear();
        typeText(searchPhrase, searchFieldLocator);
        searchButtonLocator.click();
        assert validateSearchIsPerformed() : "Search was not performed properly.";
    }

    public boolean validateSearchIsPerformed(){
        String currentUrl = driver.getCurrentUrl();
        return (currentUrl.contains(DataFile.searchUrlPart));
    }

    public boolean isTwitterButtonPresent(){
        return isElementPresent(2, twitterButtonLocator);
    }

    public boolean isFacebookButtonPresent(){
        return isElementPresent(2, facebookButtonLocator);
    }

    public boolean isYouTubeButtonPresent(){
        return isElementPresent(2, youtubeButtonLocator);
    }

    public boolean isGoogleButtonPresent(){
        return isElementPresent(2, googleButtonLocator);
    }

    public void selectSearchResultByNumber(int numberOfTheDress){
        List<WebElement> searchResults = driver.findElements(By.xpath(searchResultsOptionsLocator));
        int index = numberOfTheDress - 1;
        WebElement selectedResult = searchResults.get(index);
        selectedResult.click();
    }

    public String getReductionPricePercent(){
        String reductionPercentValue = reductionPricepercentLocator.getText();
        return reductionPercentValue;
    }

    public void selectQuantity(int quantity){
        String sQuantity = Integer.toString(quantity);
        quantityFieldLocator.click();
        quantityFieldLocator.clear();
        typeText(sQuantity, quantityFieldLocator);
    }

    public void selectSize(String size){
        sizeDropDownLocator.click();
        List<WebElement> options = sizeDropDownLocator.findElements(By.tagName(DataFile.option));
        for(WebElement option : options){
            String optionTitle = option.getText();
            if(optionTitle.equals(size)){
                option.click();
                break;
            }
        }
    }

    public void selectColor(int colorNumber){
        List<WebElement> colorOptions = colorFieldsLocator.findElements(By.xpath("./li"));
        int index = colorNumber - 1;
        WebElement color = colorOptions.get(index);
        color.click();
    }

    public void clickAddToCartButton(){
        addToCartButtonLocator.click();
    }

    public boolean isProductSuccessfullyAddedModalOpen() throws Exception{
        Thread.sleep(3000);
        return isElementPresent(3, productInfoModalLocator);
    }

    public List<String> getProductAttributes(){
        WebElement attributesObject = productInfoModalLocator.findElement(By.xpath(productAttributesLocator));
        List<String> prodAtt = new ArrayList<String>();
        String productAttributes = attributesObject.getText();
        String[] attributes = productAttributes.split(",");
        for(String att : attributes){
           prodAtt.add(att) ;
        }
          return prodAtt;
    }

    public String getProductColor(){
        List<String> prodAtt = getProductAttributes();
        String color = prodAtt.get(0).trim();
        return color;
    }

    public String getProductSize(){
        List<String> prodAtt = getProductAttributes();
        String size = prodAtt.get(1).trim();
        return size;
    }

    public int getProductQuantity(){
        WebElement quantityObject = productInfoModalLocator.findElement(By.xpath(productQuantityLocator));
        String quantityText = quantityObject.getText();
        int quantity = Integer.parseInt(quantityText);
        return quantity;
    }

    public void clickCheckoutButton(){
        checkoutButtonLocator.click();
    }

    public ShoppingCartSummaryPage proceedToCheckout(){
        clickCheckoutButton();
        return new ShoppingCartSummaryPage(driver);
    }

}
