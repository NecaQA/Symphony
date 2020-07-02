package pages;

import data.DataFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    /*
     * Page locators
     */
    private static String signInButtonLocator = "//*[@id='header']//nav/div[@class='header_user_info']/a";

    /*
     * Page methods
     */
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open(){
        driver.get(DataFile.homePageUrl);
        assert isPageLoaded(DataFile.homePageUrl, By.xpath(signInButtonLocator)) : "Page isn't loaded properly.";
        return this;
    }

    public AuthenticationPage signIn(){
        WebElement signInButton = driver.findElement(By.xpath(signInButtonLocator));
        signInButton.click();
        waitTime(1);
        return new AuthenticationPage(driver);
    }
}
