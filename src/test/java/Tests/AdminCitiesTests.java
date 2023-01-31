package Tests;

import Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdminCitiesTests extends BaseTest {

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
        landingPage.enterLoginPage();
        loginPage.login("admin@admin.com", "12345");
    }

//    @Test
//    public void visitAdminCitiesPageTest() {
//        homePage.enterAdminCitiesPage();
//
//        Assert.assertTrue(driver.getCurrentUrl().endsWith("/admin/cities"));
//        Assert.assertTrue(homePage.getLogoutBtn().isDisplayed());
//
//    }



}
