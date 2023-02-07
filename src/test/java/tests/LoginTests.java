package tests;

import pages.HomePage;
import pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.FakerClass;

public class LoginTests extends BaseTest{

    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        loginPage = new LoginPage(driver, driverWait);
        homePage = new HomePage(driver, driverWait);
    }

    @BeforeMethod
    @Override
    public void beforeMethod() {
        super.beforeMethod();
        landingPage.enterLoginPage();
    }

    @Test
    public void loginPageURLTest() {
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    public void inputTypesTest() {
        loginPage.waitForEmailField();

        String emailType = loginPage.checkAttribute(loginPage.getLoginEmail(), "type");
        String passwordType = loginPage.checkAttribute(loginPage.getLoginPassword(), "type");

        Assert.assertEquals(emailType, "email");
        Assert.assertEquals(passwordType, "password");
    }

    @Test
    public void nonexistingUserTest() {
        loginPage.waitForEmailField();

        loginPage.login(FakerClass.getFakeEmail(), FakerClass.getFakePassword());

        Assert.assertEquals(loginPage.getErrorMsg(),"User does not exists");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    public void inputWrongPasswordTest() {
        loginPage.waitForEmailField();

        loginPage.login(VALID_EMAIL, FakerClass.getFakePassword());

        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]")));

        Assert.assertTrue(loginPage.getMessage().contains("Wrong password"));
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    public void validLoginTest() {
        loginPage.waitForEmailField();

        loginPage.login(VALID_EMAIL, VALID_PASSWORD);

        driverWait.until(ExpectedConditions.urlToBe("https://vue-demo.daniel-avellaneda.com/home"));
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/home"));
    }

    @Test
    public void logoutTest() {
        loginPage.waitForEmailField();

        loginPage.login(VALID_EMAIL, VALID_PASSWORD);

        Assert.assertTrue(homePage.getLogoutBtn().isDisplayed());

        homePage.getLogoutBtn().click();

        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));

        driver.get("https://vue-demo.daniel-avellaneda.com/home");

        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }
}
