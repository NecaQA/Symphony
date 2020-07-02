package tests;

import data.DataFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import pages.HomePage;

public class OpenAutomationPracticeUrl extends BaseTest{

    @Test(description = "Verification that Automation Practice URL can be successfully opened.")
    public void test_OpenAutomationPracticeUrl(){
        //Instantiating Chromedriver and maximizing the window
        System.setProperty(DataFile.chromeDriver, DataFile.chromeDriverPath);
        WebDriver driver = new ChromeDriver();
        //Navigating to automationpractice.com URL
        try{
            driver.manage().window().maximize();
            HomePage homePage = new HomePage(driver).open();
        } finally {
            driver.quit();
        }
    }
}
