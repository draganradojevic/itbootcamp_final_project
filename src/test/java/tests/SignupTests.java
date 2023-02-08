package tests;

import pages.HomePage;
import pages.SignupPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.FakerClass;

public class SignupTests extends BaseTest{

    private SignupPage signupPage;
    private HomePage homePage;

    @BeforeMethod
    @Override
    public void beforeMethod() {
        super.beforeMethod();
        signupPage = new SignupPage(driver, driverWait);
        homePage = new HomePage(driver, driverWait);
        driverWait.until(ExpectedConditions.elementToBeClickable(landingPage.getSignupBtn()));
        landingPage.enterSignupPage();
    }

    @Test
    public void signupPageVisitTest() {
        signupPage.waitForSignupNameField();

        Assert.assertTrue(driver.getCurrentUrl().endsWith("/signup"));
    }

    @Test
    public void signupFieldInputTypeTest() {
        signupPage.waitForSignupNameField();

        Assert.assertEquals(signupPage.checkAttribute(signupPage.getSignupEmail(),"type"), "email");
        Assert.assertEquals(signupPage.checkAttribute(signupPage.getSignupPassword(), "type"), "password");
        Assert.assertEquals(signupPage.checkAttribute(signupPage.getSignupConfirmPassword(), "type"), "password");
    }

    @Test
    public void userAlreadyExistsTest() {
        signupPage.waitForSignupNameField();

        String name = "Test Test";
        String email = VALID_EMAIL;
        String password = "123654";
        String confirmPassword = "123654";

        signupPage.signup(name, email, password, confirmPassword);

        signupPage.waitForErrorMessage();

        Assert.assertTrue(signupPage.getMessage().contains("E-mail already exists"));
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/signup"));
    }

    @Test
    public void validSignupTest() {
        signupPage.waitForSignupNameField();

        String name = FakerClass.getFakeName();
        String email = FakerClass.getFakeEmail();
        String password = FakerClass.getFakePassword();

        signupPage.signup(name, email, password, password);

        homePage.waitForURLToContainHome();
        homePage.waitForDialogMessage();

        Assert.assertTrue(homePage.getDialogMessage().contains("IMPORTANT: Verify your account"));
    }
}
