package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.AuthenticationPage;
import pages.HomePage;

public class BaseTest {

    protected void openHomePage(WebDriver driver){
        HomePage homePage = new HomePage(driver).open();
    }



}
