package Tests;

import Pages.AdminCitiesPage;
import Pages.HomePage;
import Pages.LandingPage;
import Pages.LoginPage;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdminCitiesTests extends BaseTest {

    private Faker faker;
    private LandingPage landingPage;
    private LoginPage loginPage;
    private HomePage homePage;
    private AdminCitiesPage adminCitiesPage;
    private String city;
    private String editedCity;

    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        faker = new Faker();
        landingPage = new LandingPage(driver, driverWait);
        loginPage = new LoginPage(driver, driverWait);
        homePage = new HomePage(driver, driverWait);
        adminCitiesPage = new AdminCitiesPage(driver, driverWait);
        city = faker.address().cityName();
        editedCity = city + " - edited";
    }

    @BeforeMethod
    @Override
    public void beforeMethod() {
        super.beforeMethod();
        landingPage.enterLoginPage();
        loginPage.login("admin@admin.com", "12345");
        homePage.enterAdminCitiesPage();
    }

    @AfterMethod
    public void afterMethod() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("btnLogout")));
        adminCitiesPage.getLogoutBtn().click();
    }

    @Test
    public void visitAdminCitiesPageTest() {
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/admin/cities"));
        Assert.assertTrue(homePage.getLogoutBtn().isDisplayed());
    }

    @Test
    public void createNewCityTest() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div/div[1]")));
        adminCitiesPage.createCity(city);

        Assert.assertTrue(adminCitiesPage.getSavedSuccessfullyMessage().getText().contains("Saved successfully"));
    }

    @Test
    public void editCityTest() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div/div[1]")));
        adminCitiesPage.createCity(city);

        adminCitiesPage.editCity(city);
        Assert.assertTrue(adminCitiesPage.getSavedSuccessfullyMessage().getText().contains("Saved successfully"));
    }

    @Test
    public void citySearchTest() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div/div[1]")));
        adminCitiesPage.createCity(city);
        adminCitiesPage.editCity(city);

        driverWait.until(ExpectedConditions.visibilityOf(adminCitiesPage.getSearchField()));
        adminCitiesPage.searchCity(editedCity);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr/td[2]")));

        Assert.assertEquals(adminCitiesPage.getNameOfCity(), editedCity);
    }

    @Test
    public void deleteCityTest() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div/div[1]")));

        adminCitiesPage.createCity(city);
        adminCitiesPage.editCity(city);
        adminCitiesPage.searchCity(editedCity);

//        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr/td[2]")));
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr/td[2]")));

        adminCitiesPage.deleteCity(editedCity);

        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div/div[1]")));

        WebElement deleteMsg = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div/div[1]"));
        String deleteMsgTxt = deleteMsg.getText();

        Assert.assertTrue(deleteMsgTxt.contains("Deleted successfully"));
    }
}
