package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationPage extends BasePage {

    /*
     * Page locators
     */

    @FindBy(xpath = "//*[@id='center_column']/div/p")
    private WebElement orderCompleteLocator;

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isOrderComplete(){
        return isElementPresent(3, orderCompleteLocator);
    }
}
