package Tests;

import Pages.HomePage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest{

    private HomePage homePage;

    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        homePage = new HomePage(driver, driverWait);
    }

    @BeforeMethod
    @Override
    public void beforeMethod() {
        super.beforeMethod();
        homePage.enterLoginPage();
    }

    @Test
    public void loginPageURLTest() {
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    public void inputTypesTest() {
        String emailType = loginPage.getLoginEmail().getAttribute("type");
        String passwordType = loginPage.getLoginPassword().getAttribute("type");

        Assert.assertEquals(emailType, "email");
        Assert.assertEquals(passwordType, "password");
    }


}
