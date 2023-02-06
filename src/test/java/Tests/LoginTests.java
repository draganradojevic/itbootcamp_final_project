package Tests;

import Pages.HomePage;
import Pages.LandingPage;
import Pages.LoginPage;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest{

    private Faker faker;
    private LandingPage landingPage;
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        faker = new Faker();
        landingPage = new LandingPage(driver, driverWait);
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
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));

        String emailType = loginPage.getLoginEmail().getAttribute("type");
        String passwordType = loginPage.getLoginPassword().getAttribute("type");

        Assert.assertEquals(emailType, "email");
        Assert.assertEquals(passwordType, "password");
    }

    @Test
    public void nonexistingUserTest() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));

        loginPage.login(faker.internet().emailAddress(), faker.internet().password());

        WebElement error = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]/ul/li"));
        String errorMsg = error.getText();

        Assert.assertEquals(errorMsg, "User does not exists");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    public void inputWrongPasswordTest() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));

        String password = faker.internet().password();
        loginPage.login(VALID_EMAIL, password);

        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]")));

        Assert.assertTrue(loginPage.getMessage().contains("Wrong password"));
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    public void validLoginTest() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));

        loginPage.login(VALID_EMAIL, VALID_PASSWORD);

        driverWait.until(ExpectedConditions.urlToBe("https://vue-demo.daniel-avellaneda.com/home"));
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/home"));
    }

    @Test
    public void logoutTest() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));

        loginPage.login(VALID_EMAIL, VALID_PASSWORD);

        Assert.assertTrue(homePage.getLogoutBtn().isDisplayed());

        homePage.getLogoutBtn().click();

        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));

        driver.get("https://vue-demo.daniel-avellaneda.com/home");

        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }
}
