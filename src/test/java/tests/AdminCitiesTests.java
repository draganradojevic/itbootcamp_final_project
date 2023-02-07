package tests;

import pages.AdminCitiesPage;
import pages.HomePage;
import pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.FakerClass;

public class AdminCitiesTests extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;
    private AdminCitiesPage adminCitiesPage;
    private String city;
    private String editedCity;

    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        loginPage = new LoginPage(driver, driverWait);
        homePage = new HomePage(driver, driverWait);
        adminCitiesPage = new AdminCitiesPage(driver, driverWait);
        city = FakerClass.getFakeCity();
        editedCity = city + " - edited";
    }

    @BeforeMethod
    @Override
    public void beforeMethod() {
        super.beforeMethod();
        landingPage.enterLoginPage();
        loginPage.login(VALID_EMAIL, VALID_PASSWORD);
        homePage.enterAdminCitiesPage();
    }

    @AfterMethod
    public void afterMethod() {
        adminCitiesPage.waitForLogoutButtonPresence();
        adminCitiesPage.getLogoutBtn().click();
    }

    @Test
    public void visitAdminCitiesPageTest() {
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/admin/cities"));
        Assert.assertTrue(homePage.getLogoutBtn().isDisplayed());
    }

    @Test
    public void createNewCityTest() {
        adminCitiesPage.waitForContainerToBeVisible();

        adminCitiesPage.createCity(city);

        Assert.assertTrue(adminCitiesPage.getSavedSuccessfullyMessage().contains("Saved successfully"));
    }

    @Test
    public void editCityTest() {
        adminCitiesPage.waitForContainerToBeVisible();

        adminCitiesPage.createCity(city);

        adminCitiesPage.editCity(city);
        Assert.assertTrue(adminCitiesPage.getSavedSuccessfullyMessage().contains("Saved successfully"));
    }

    @Test
    public void citySearchTest() {
        adminCitiesPage.waitForContainerToBeVisible();

        adminCitiesPage.createCity(city);
        adminCitiesPage.editCity(city);

        adminCitiesPage.waitForSearchField();

        adminCitiesPage.searchCity(editedCity);

        adminCitiesPage.waitForCityName();

        Assert.assertEquals(adminCitiesPage.getNameOfCity(), editedCity);
    }

    @Test
    public void deleteCityTest() {
        adminCitiesPage.waitForContainerToBeVisible();

        adminCitiesPage.createCity(city);
        adminCitiesPage.editCity(city);
        adminCitiesPage.searchCity(editedCity);

        adminCitiesPage.waitForCityName();

        adminCitiesPage.deleteCity(editedCity);

        adminCitiesPage.waitForDeletedSuccessfullyMessage();

        Assert.assertTrue(adminCitiesPage.getDeleteSuccessfullyMessage().contains("Deleted successfully"));
    }
}
