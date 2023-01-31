package Tests;

import Pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

    @Test
    public void userAlreadyExistsTest() {
        String name = "Test Test";
        String email = "admin@admin.com";
        String password = "123654";
        String confirmPassword = "123654";

        signupPage.signup(name, email, password, confirmPassword);

        WebElement error = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[3]/div/div/div/div/div[1]/ul/li"));
        String errorMessage = error.getText();

        Assert.assertEquals(errorMessage, "E-mail already exists");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/signup"));
    }





}
