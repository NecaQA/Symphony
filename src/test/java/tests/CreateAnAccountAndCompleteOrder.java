package tests;

import data.DataFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import pages.*;

import java.util.Random;

public class CreateAnAccountAndCompleteOrder extends BaseTest {

    @Test(description = "Verification that an account can be successfully created.")
    public void test_CreateAnAccount() throws  Exception{
        System.setProperty(DataFile.chromeDriver, DataFile.chromeDriverPath);
        WebDriver driver = new ChromeDriver();

        try{
            HomePage homePage = new HomePage(driver).open();
            AuthenticationPage authPage = homePage.signIn();
            int randomNumber = new Random().nextInt();
            String sUserEmail = DataFile.sUserName + randomNumber + DataFile.sEmailSuffix;
            CreateAccountPage createAccountPage = authPage.createAccount(sUserEmail);
            assert createAccountPage.isCreateAccountPageLoaded() : "Create An Account page is not loaded properly.";

            //Create account by providing only the required info
            MyAccountPage myAccount = createAccountPage.createAnAccount();
            assert myAccount.isMyAccountPageLoaded() : "My Account Page is not loaded";
            assert myAccount.isSignOutButtonPresent() : "SingOut Button is not present";

            //Search for dresses and verify presence of Social media buttons
            myAccount.searchText(DataFile.searchPhrase);
            assert myAccount.isTwitterButtonPresent() : "Twitter button is not present on the page.";
            assert myAccount.isFacebookButtonPresent()  : "Facebook button is not present on the page.";
            assert myAccount.isYouTubeButtonPresent() :  "YouTube button is not present on the page.";
            assert myAccount.isGoogleButtonPresent() : "Google button is not present on the page.";

            //Verify that first dress is 5% off and pick Size,Color and quantity
            myAccount.selectSearchResultByNumber(1);
            String reductionPrice = myAccount.getReductionPricePercent();
            assert reductionPrice.equals(DataFile.reduction) : "Wrong reduction value! Expected: " + DataFile.reduction+ "; Actual: " +reductionPrice;
            myAccount.selectQuantity(3);
            myAccount.selectSize(DataFile.size);
            myAccount.selectColor(3);
            myAccount.clickAddToCartButton();

            //Verify popup shows and the values for size, color and quantity are right
            assert myAccount.isProductSuccessfullyAddedModalOpen() : "Pop-up is not open!";
            assert (myAccount.getProductQuantity() == 3) : "Wrong quantity! Expected: 3; Actual: " + myAccount.getProductQuantity();
            assert (myAccount.getProductColor().equals(DataFile.color)) : "Wrong color! Expected: " + DataFile.color + "; Actual: " + myAccount.getProductColor();
            assert (myAccount.getProductSize().equals(DataFile.size)) : "Wrong size1 Expected: " +DataFile.size + "; Actual: " + myAccount.getProductSize();

            //Click 'Proceed to Chekout' and verify existence of elements on every page
            ShoppingCartSummaryPage summaryPage = myAccount.proceedToCheckout();
            assert (summaryPage.getProductReductionPercent().equals(DataFile.reduction)) : "Wrong reduction value! Expected: " + DataFile.reduction+ "; Actual: " +summaryPage.getProductReductionPercent();
            summaryPage.clickCheckoutSummary();
            assert (summaryPage.getFullName().contains(DataFile.sFirstName)) : "Wrong name! Expected " + summaryPage.getFullName() + " to contain " +DataFile.sFirstName;
            summaryPage.clickCheckoutAddress();
            summaryPage.checkTermsOfServiceCheckbox();
            summaryPage.clickCheckoutShipping();

            //Select payment method and confirm order
            summaryPage.clickPayByBankWireButton();
            OrderConfirmationPage confirmationPage = summaryPage.confirmOrder();
            assert confirmationPage.isOrderComplete() : "The order is not complete!";

        } finally {
            driver.quit();
        }
        }
}
