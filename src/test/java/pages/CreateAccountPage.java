package pages;

import data.DataFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CreateAccountPage extends BasePage {

    /*
     * Page locators
     */

    @FindBy(id = "account-creation_form")
    private WebElement createAccountFormLocator;

    @FindBy(id="id_gender1")
    private WebElement titleMisterRadionButtonLocator;

    @FindBy(id="id_gender2")
    private WebElement titleMrsRadionButtonLocator;

    @FindBy(id="customer_firstname")
    private WebElement personalInfoFirstNameFieldLocator;

    @FindBy(id="customer_lastname")
    private WebElement personalInfoLastNameFieldLocator;

    @FindBy(id="email")
    private WebElement emailFieldLocator;

    @FindBy(id="passwd")
    private WebElement passwordFieldLocator;

    @FindBy(id="days")
    private WebElement dobDaysFieldLocator;

    @FindBy(id="months")
    private WebElement dobMonthsFieldLocator;

    @FindBy(id="years")
    private WebElement dobYearsFieldLocator;

    @FindBy(id="newsletter")
    private WebElement newsletterCheckboxLocator;

    @FindBy(id="optin")
    private WebElement optinCheckboxLocator;

    @FindBy(id="firstname")
    private WebElement addressFirstNameFieldLocator;

    @FindBy(id="lastname")
    private WebElement addressLastNameFieldLocator;

    @FindBy(id="company")
    private WebElement companyFieldLocator;

    @FindBy(id="address1")
    private WebElement address1FieldLocator;

    @FindBy(id="address2")
    private WebElement address2FieldLocator;

    @FindBy(id="city")
    private WebElement cityFieldLocator;

    @FindBy(id="id_state")
    private WebElement stateDropdownLocator;

    @FindBy(id="postcode")
    private WebElement postcodeFieldLocator;

    @FindBy(id="id_country")
    private WebElement countryDropdownLocator;

    @FindBy(id="other")
    private WebElement additionalInfoFieldLocator;

    @FindBy(id="phone")
    private WebElement phoneFieldLocator;

    @FindBy(id="phone_mobile")
    private WebElement mobileFieldLocator;

    @FindBy(id="alias")
    private WebElement aliasFieldLocator;

    @FindBy(id="submitAccount")
    private WebElement submitButtonLocator;



    /*
     * Page methods
     */


    public CreateAccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Checks if Create Account page has successfully loaded
     * @return {boolean}
     */
    public boolean isCreateAccountPageLoaded() throws Exception{
        Thread.sleep(3000);
        return this.isPageLoaded(DataFile.createAccountPageUrl, createAccountFormLocator);
    }

    /*
     * Personal info Section
     */

    public void clickMrRadioButton(){
        titleMisterRadionButtonLocator.click();
    }

    public void clickMrsRadioButton(){
        titleMrsRadionButtonLocator.click();
    }

    public void enterFirstName(String firstName){
        personalInfoFirstNameFieldLocator.click();
        personalInfoFirstNameFieldLocator.clear();
        typeText(firstName, personalInfoFirstNameFieldLocator);
    }

    public void enterLastName(String lastName){
        personalInfoLastNameFieldLocator.click();
        personalInfoLastNameFieldLocator.clear();
        typeText(lastName, personalInfoLastNameFieldLocator);
    }

    public boolean isEmailFieldPopulated(String userEmail){
        String emailFieldText = emailFieldLocator.getText();
        if((emailFieldText != null) || (emailFieldText != "")){
            return true;
        } return false;
    }

    public void enterEmail(String userEmail){
        emailFieldLocator.click();
        emailFieldLocator.clear();
        typeText(userEmail, emailFieldLocator);
    }

    public void enterPassword(String password){
        passwordFieldLocator.click();
        passwordFieldLocator.clear();
        typeText(password, passwordFieldLocator);
    }

    public void enterDayOfBirth(String day){
        dobDaysFieldLocator.click();
        List<WebElement> days = dobDaysFieldLocator.findElements(By.tagName(DataFile.option));
        for(WebElement eachDay : days){
            String dayValue = eachDay.getText().trim();
            if(dayValue.equals(day)){
                eachDay.click();
                break;
            }
        }
    }

    public void enterMonthOfBirth(String month){
        dobMonthsFieldLocator.click();
        List<WebElement> months = dobMonthsFieldLocator.findElements(By.tagName(DataFile.option));
        for(WebElement eachMonth : months){
            String monthValue = eachMonth.getText().trim();
            if(monthValue.equals(month)){
                eachMonth.click();
                break;
            }
        }
    }

    public void enterYearOfBirth(String year){
        dobYearsFieldLocator.click();
        List<WebElement> years = dobYearsFieldLocator.findElements(By.tagName(DataFile.option));
        for(WebElement eachYear : years){
            String yearValue = eachYear.getText().trim();
            if(yearValue.equals(year)){
                eachYear.click();
                break;
            }
        }
    }

    public void enterDateOfBirth(String day, String month, String year){
        enterDayOfBirth(day);
        enterMonthOfBirth(month);
        enterYearOfBirth(year);
    }

    public void checkNewsletterCheckbox(){
        newsletterCheckboxLocator.click();
    }

    public void checkOptInCheckbox(){
        optinCheckboxLocator.click();
    }

    /*
     * Your Address Section
     */

    public void enterFirstNameInAddressSection(String firstName) {
        addressFirstNameFieldLocator.click();
        addressFirstNameFieldLocator.clear();
        typeText(firstName, addressFirstNameFieldLocator);
    }

    public void enterLastNameInAddressSection(String lastName) {
        addressLastNameFieldLocator.click();
        addressLastNameFieldLocator.clear();
        typeText(lastName, addressLastNameFieldLocator);
    }
    public void enterCompany(String company) {
        companyFieldLocator.click();
        companyFieldLocator.clear();
        typeText(company, companyFieldLocator);
    }

    public void enterAddress(String address) {
        address1FieldLocator.click();
        address1FieldLocator.clear();
        typeText(address, address1FieldLocator);
    }

    public void enterCity(String city) {
        cityFieldLocator.click();
        cityFieldLocator.clear();
        typeText(city, cityFieldLocator);
    }

    public void selectState(String state){
        stateDropdownLocator.click();
        List<WebElement> stateObjects = stateDropdownLocator.findElements(By.tagName(DataFile.option));
        for(WebElement stateObject : stateObjects){
            String stateName = stateObject.getText();
            if(stateName.equals(state)){
                stateObject.click();
            }
        }
    }

    public void enterZip(String zip) {
        postcodeFieldLocator.click();
        postcodeFieldLocator.clear();
        typeText(zip, postcodeFieldLocator);
    }

    public void enterPhoneNumber(String phoneNumber) {
        phoneFieldLocator.click();
        phoneFieldLocator.clear();
        typeText(phoneNumber, phoneFieldLocator);
    }

    public List<String> getRequiredUserInfo(){
        List<String> userInfo = new ArrayList<String>();
        userInfo.add(DataFile.sFirstName);
        userInfo.add(DataFile.sLastName);
        userInfo.add(DataFile.sUserPass);
        userInfo.add(DataFile.sDayOfBirth);
        userInfo.add(DataFile.sMonthOfBirth);
        userInfo.add(DataFile.sYearOfBirth);
        userInfo.add(DataFile.sAddress);
        userInfo.add(DataFile.sCityName);
        userInfo.add(DataFile.sState);
        userInfo.add(DataFile.sZip);
        userInfo.add(DataFile.sMobileNumber);
        return userInfo;
    }

    public void fillInRequiredInfoOnly(){
        List<String> userInfo = this.getRequiredUserInfo();
        enterFirstName(userInfo.get(0));
        enterLastName(userInfo.get(1));
        enterPassword(userInfo.get(2));
        enterDateOfBirth(userInfo.get(3), userInfo.get(4), userInfo.get(5));
        enterFirstNameInAddressSection(userInfo.get(0));
        enterLastNameInAddressSection(userInfo.get(1));
        enterAddress(userInfo.get(6));
        enterCity(userInfo.get(7));
        selectState(userInfo.get(8));
        enterZip(userInfo.get(9));
        enterPhoneNumber(userInfo.get(10));
    }

    public MyAccountPage createAnAccount(){
        this.fillInRequiredInfoOnly();
        clickRegisterAccount();
        waitTime(2);
        return new MyAccountPage(driver);
    }

    public MyAccountPage clickRegisterAccount(){
        this.submitButtonLocator.click();
        return new MyAccountPage(driver);
    }
}
