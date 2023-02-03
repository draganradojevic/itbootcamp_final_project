package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdminCitiesTests extends BaseTest {

    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
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
        landingPage.getLogoutBtn().click();
    }

    @Test
    public void visitAdminCitiesPageTest() {
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/admin/cities"));
        Assert.assertTrue(homePage.getLogoutBtn().isDisplayed());
    }

    @Test
    public void createNewCityTest() {

        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div/div[1]")));
        adminCitiesPage.getNewItemBtn().click();

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        adminCitiesPage.createCity(adminCitiesPage.getNewItemName());

        driverWait.until(ExpectedConditions.elementToBeClickable(By.className("btnSave")));
        adminCitiesPage.getSaveBtn().click();

        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div")));

        Assert.assertTrue(adminCitiesPage.getSavedSuccessfullyMessage().getText().contains("Saved successfully"));
    }

    @Test  (dependsOnMethods = "createNewCityTest")
    public void editCityTest() {

        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table")));

        adminCitiesPage.getEditCityBtn().click();

        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='app']/div[5]/div/div/div[3]/button[2]")));
        adminCitiesPage.getEditName().click();
        adminCitiesPage.getEditName().sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        adminCitiesPage.getEditName().sendKeys(adminCitiesPage.getNewItemNameEdited());

        driverWait.until(ExpectedConditions.elementToBeClickable(adminCitiesPage.getSaveBtn()));
        adminCitiesPage.getSaveBtn().click();

        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[2]/div/div/div/div/div[1]")));

        Assert.assertTrue(adminCitiesPage.getEditSuccessfullySavedMessage().getText().contains("Saved successfully"));
    }


    @Test (dependsOnMethods = "editCityTest")
    public void searchCityTest() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("search")));

        adminCitiesPage.search(adminCitiesPage.getNewItemNameEdited());

        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr/td[2]")));

        WebElement nameOfCity = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr/td[2]"));
        String cityName = nameOfCity.getText();

        Assert.assertTrue(cityName.contains(adminCitiesPage.getNewItemNameEdited()));

    }


    @Test (dependsOnMethods = "searchCityTest")
    public void deleteCityTest() {

    driverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("search")));

    adminCitiesPage.getSearchField().sendKeys(adminCitiesPage.getNewItemName());

    driverWait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr/td[2]"), 1));

    Assert.assertTrue(adminCitiesPage.getCityName().getText().contains(adminCitiesPage.getNewItemName()));
    adminCitiesPage.getDeleteBtn().click();

    driverWait.until(ExpectedConditions.visibilityOf(adminCitiesPage.getDeleteBtnConfirm()));
    adminCitiesPage.getDeleteBtnConfirm().click();

    driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Deleted successfully')]")));
    Assert.assertTrue(adminCitiesPage.getDeleteSuccessfulMessage().getText().contains("Deleted successfully"));
    }

}
