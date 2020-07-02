package pages;

import data.DataFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.rmi.runtime.Log;

public abstract class BasePage {
    /**
     * This class will contain all the neccessary locators and reusable methods
     */

    /*
     * Locators
     */
    @FindBy(xpath = "//*[@id='header']//nav/div[@class='header_user_info']/a")
    WebElement signInOutButtonLocator;



    protected WebDriver driver = null;

    protected BasePage(WebDriver driver) {
        if (driver == null)
            throw new AssertionError("driver instance is null.");
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Checks if specified web element is present
     * @param {WebElement} locator
     * @return {boolean} Whether element is present or not
     */
    public boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        }
        catch (final Exception e) {
            return false;
        }
    }

    public boolean isElementPresent(int timeOutInSeconds, WebElement element){
        waitUntilVisible(timeOutInSeconds, element);
        return element.isDisplayed();
    }

    /**
     * Is the given element defined in the By present in the DOM, used DEFAULT timeout.
     *
     * @param {WebElement} Locator of Parent Web Element
     * @param {By}         By Locator of Desired Element
     */
    public boolean isElementPresent(By by) {
        boolean result = false;
        try {
            WebElement webElement = driver.findElement(by);
            result = webElement.isDisplayed();
        }
        catch (Exception e) {
            result = false;
        }
        return result;
    }

    /**
     * Wait until specified web element becomes visible
     * @param {int} timeOutInSeconds - Timeout interval in seconds
     * @param {WebElement} webElement - Specified web element
     * @return
     */
    public Object waitTime(int timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        return this;
    }

    public Object waitUntilVisible(int timeOutInSeconds, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
        return this;
    }

    public Object waitUntilClickable(int timeOutInSeconds, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return this;
    }

    /**
     * Checks if a specific Page is loaded
     * @param {String} expected page URL
     * @param {WebElement} element specific to the page
     */
    public boolean isPageLoaded(String url, By by){
        String currentUrl = driver.getCurrentUrl();
        waitTime(1);
        if(currentUrl.equals(url) && isPageElementPresent(by)) {
            return true;
        } return false;
    }

    public boolean isPageLoaded(String url, WebElement element){
        String currentUrl = driver.getCurrentUrl();
        waitTime(3);
        if(currentUrl.equals(url) && isPageElementPresent(element)) {
            return true;
        } return false;
    }

    /**
     * Checks if a specific page element is present on the page
     * @param pageElement
     * @return
     */
    public boolean isPageElementPresent(WebElement pageElement){
        return isElementPresent(pageElement);
    }

    public boolean isPageElementPresent(By by){
        return isElementPresent(by);
    }

    /**
     * Reusable method for inputing text into a given field
     */
    public void typeText(String text, WebElement textField){
        assert isPageElementPresent(textField) : "Text field is not present on the page.";
        textField.click();
        textField.sendKeys(text);
    }











}
