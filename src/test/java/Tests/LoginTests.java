package Tests;

import Pages.HomePage;
import Pages.LoginPage;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest{

    private HomePage homePage;
    private Faker faker;

    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        homePage = new HomePage(driver, driverWait);
        faker = new Faker();
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

    @Test
    public void nonexistingUserTest() {
        loginPage.login(faker.internet().emailAddress(), faker.internet().password());

        WebElement error = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]/ul/li"));
        String errorMsg = error.getText();

        Assert.assertEquals(errorMsg, "User does not exists");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    public void wrongPasswordTest() {
        String email = "admin@admin.com";
        loginPage.login(email, faker.internet().password());

        WebElement errorPw = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]/ul/li"));
        String errorMessage = errorPw.getText();

        Assert.assertEquals(errorMessage, "Wrong password");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    public void validLoginTest() {
        String email = "admin@admin.com";
        String password = "12345";
        loginPage.login(email, password);

        driverWait.until(ExpectedConditions.urlToBe("https://vue-demo.daniel-avellaneda.com/home"));
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/home"));
    }


}
