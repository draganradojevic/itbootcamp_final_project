package tests;

import pages.HomePage;
import pages.SignupPage;
import org.openqa.selenium.By;
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
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div/main/div/div[2]/div/div/div[1]/h1")));

        Assert.assertTrue(driver.getCurrentUrl().endsWith("/signup"));
    }

    @Test
    public void signupFieldInputTypeTest() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div/main/div/div[2]/div/div/div[2]/span/form/div/div[5]/button")));

        Assert.assertEquals(signupPage.getSignupEmail().getAttribute("type"), "email");
        Assert.assertEquals(signupPage.getSignupPassword().getAttribute("type"), "password");
        Assert.assertEquals(signupPage.getSignupConfirmPassword().getAttribute("type"), "password");
    }

    @Test
    public void userAlreadyExistsTest() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div/main/div/div[2]/div/div/div[2]/span/form/div/div[5]/button")));

        String name = "Test Test";
        String email = "admin@admin.com";
        String password = "123654";
        String confirmPassword = "123654";

        signupPage.signup(name, email, password, confirmPassword);

        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[3]/div/div/div/div/div[1]")));

        Assert.assertTrue(signupPage.getMessage().contains("E-mail already exists"));
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/signup"));
    }

    @Test
    public void validSignupTest() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div/main/div/div[2]/div/div/div[2]/span/form/div/div[5]/button")));

        String name = FakerClass.getFakeName();
        String email = FakerClass.getFakeEmail();
        String password = FakerClass.getFakePassword();

        signupPage.signup(name, email, password, password);

        driverWait.until(ExpectedConditions.urlContains("/home"));

        driverWait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"app\"]/div[4]/div/div/div[1]"), "Verify your account"));
        Assert.assertTrue(homePage.getDialogMessage().contains("IMPORTANT: Verify your account"));
    }
}
