package Tests;

import Pages.HomePage;
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

    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        faker = new Faker();
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
        adminCitiesPage.getLogoutBtn().click();
    }

    @Test
    public void visitAdminCitiesPageTest() {
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/admin/cities"));
        Assert.assertTrue(homePage.getLogoutBtn().isDisplayed());
    }

    @Test
    public void createNewCityTest() {
        adminCitiesPage.createNewCity(faker.address().cityName());

        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div/div[1]")));

        System.out.println(adminCitiesPage.getSavedSuccessfullyMessage());

        Assert.assertTrue(adminCitiesPage.getSavedSuccessfullyMessage().getText().contains("Saved successfully"));
    }




}
