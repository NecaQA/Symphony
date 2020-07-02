package pages;

import data.DataFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartSummaryPage extends BasePage {

    /*
     * Locators for the page
     */
    @FindBy(xpath = "//*[contains(@id,'product_price')]/span[contains(@class,'percent')]")
    private WebElement reductionPercentLocator;

    @FindBy(xpath = "//*[@id='center_column']/p/a[contains(@class,'checkout')]")
    private WebElement checkoutSummaryLocator;

    @FindBy(id = "address_delivery")
    private WebElement deliveryAddressLocator;

    @FindBy(xpath = "//*[@id='center_column']/form/p/button")
    private WebElement checkoutAddressLocator;

    @FindBy(xpath = "//*[@id='form']/p/button")
    private WebElement checkoutShippingLocator;

    @FindBy(id = "cgv")
    private  WebElement termsOfServiceCheckboxLocator;

    @FindBy(xpath = "//*[@id='HOOK_PAYMENT']//p/a[contains(@class,'bankwire')]")
    private WebElement bankwireButtonLocator;

    @FindBy(xpath = "//*[@id='cart_navigation']/button")
    private WebElement confirmOrderButtonLocator;

    private static String firstNameLocator = "./li[contains(@class,'firstname')]";

    public ShoppingCartSummaryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isShoppingCartSummaryPage(){
        return isPageLoaded(DataFile.summaryUrl, signInOutButtonLocator);
    }

    public String getProductReductionPercent(){
        String reduction = reductionPercentLocator.getText().trim();
        return reduction;
    }

    public void clickCheckoutSummary(){
        checkoutSummaryLocator.click();
    }

    public String getFullName(){
        WebElement nameElement = deliveryAddressLocator.findElement(By.xpath(firstNameLocator));
        String name = nameElement.getText();
        return name;
    }

    public void clickCheckoutAddress(){
        checkoutAddressLocator.click();
    }

    public void checkTermsOfServiceCheckbox(){
        termsOfServiceCheckboxLocator.click();
    }

    public void clickCheckoutShipping(){
        checkoutShippingLocator.click();
    }

    public void clickPayByBankWireButton(){
        bankwireButtonLocator.click();
    }

    public void clickConfirmOrderButton(){
        confirmOrderButtonLocator.click();
    }

    public OrderConfirmationPage confirmOrder(){
        clickConfirmOrderButton();
        return new OrderConfirmationPage(driver);
    }


}
