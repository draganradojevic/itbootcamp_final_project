package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignupTests extends BaseTest{

    @BeforeMethod
    @Override
    public void beforeMethod() {
        super.beforeMethod();
        landingPage.enterSignupPage();
    }

    @Test
    public void visitSignupPageTest() {
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

        WebElement error = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[3]/div/div/div/div/div[1]/ul/li"));
        String errorMessage = error.getText();

        Assert.assertEquals(errorMessage, "E-mail already exists");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/signup"));
    }

    @Test
    public void validSignupTest() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div/main/div/div[2]/div/div/div[2]/span/form/div/div[5]/button")));


        String name = "Dragan Radojevic";
        String email = "dragan5@mail.com";
        String password = "12344321";
        String confirmPassword = "12344321";

        signupPage.signup(name, email, password, confirmPassword);

        driverWait.until(ExpectedConditions.urlContains("/home"));

        driverWait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"app\"]/div[4]/div/div/div[1]"), "Verify your account"));
        Assert.assertTrue(homePage.getDialogMessage().contains("Verify your account"));
    }






}
