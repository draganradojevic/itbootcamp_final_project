package Tests;

import Pages.HomePage;
import Pages.LoginPage;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

    @Test
    public void nonexistingUserTest() {
        Faker faker = new Faker();
        loginPage.login(faker.internet().emailAddress(), faker.internet().password());

        WebElement error = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]/ul/li"));
        String errorMsg = error.getText();

        Assert.assertEquals(errorMsg, "User does not exists");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }


}
