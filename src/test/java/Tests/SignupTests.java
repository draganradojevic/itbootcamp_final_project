package Tests;

import Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignupTests extends BaseTest{

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
        homePage.enterSignupPage();
    }

    @Test
    public void visitSignupPageTest() {
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/signup"));
    }

    @Test
    public void signupFieldInputTypeTest() {
        Assert.assertEquals(signupPage.getSignupEmail().getAttribute("type"), "email");
        Assert.assertEquals(signupPage.getSignupPassword().getAttribute("type"), "password");
        Assert.assertEquals(signupPage.getSignupConfirmPassword().getAttribute("type"), "password");
    }





}
