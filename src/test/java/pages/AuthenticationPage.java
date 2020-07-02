package pages;

import org.openqa.selenium.WebDriver;
import data.DataFile;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthenticationPage extends BasePage {
    /*
    * Locators
     */
    @FindBy(id = "email_create")
    private WebElement emailAddressInputLocator;

    @FindBy(id = "SubmitCreate")
    private WebElement createAccountButtonLocator;

    @FindBy(id = "create_account_error")
    private WebElement invalidEmailAddressAlertLocator;

    /*
     * Page methods
     */
    public AuthenticationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    /**
     * Checks if Authentication page has successfully loaded
     * @return {boolean}
     */
    public boolean isAuthenticationPageLoaded(){
        return this.isPageLoaded(DataFile.authPageUrl, createAccountButtonLocator);
    }

    /**
     * Checks if Email Input For Create an Account section is Present
     * @return
     */
    public boolean isEmailInputForCreateAccountPresent(){
        return isElementPresent(3, emailAddressInputLocator);
    }

    public boolean isCreateAccountButtonPresent(){
        return isElementPresent(createAccountButtonLocator);
    }

    public void typeUserEmail(String userEmail){
        typeText(userEmail, emailAddressInputLocator);
    }

    public CreateAccountPage createAccount(String userEmail){
        assert isEmailInputForCreateAccountPresent() : "Email Filed is not present.";
        assert isCreateAccountButtonPresent() : "Create account button is not present.";
        typeUserEmail(userEmail);
        waitTime(3);
        createAccountButtonLocator.click();
        waitTime(3);
        return new CreateAccountPage(driver);
    }

}
