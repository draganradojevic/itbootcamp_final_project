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
    private String city;

    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        faker = new Faker();
        city = faker.address().cityName();
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
        adminCitiesPage.createNewCity(city);

        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div/div[1]")));

        System.out.println(adminCitiesPage.getSavedSuccessfullyMessage());

        Assert.assertTrue(adminCitiesPage.getSavedSuccessfullyMessage().getText().contains("Saved successfully"));
    }

    @Test
    public void editCityTest() {

        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table")));

        for (WebElement city : adminCitiesPage.getCitiesList()) {

            if (city.getText().contains(this.city)) {
                WebElement editButton = city.findElement(By.id("edit"));

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                editButton.click();

                adminCitiesPage.getEditInput().sendKeys(" - edited");
                adminCitiesPage.getSaveBtn().click();
            }
        }

        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[2]/div/div/div/div/div[1]")));


        Assert.assertTrue(adminCitiesPage.getEditSuccessfullySavedMessage().getText().contains("Saved successfully "));
    }


    @Test
    public void searchCityTest() {
        adminCitiesPage.search(this.city);


    }


    @Test
    public void deleteCityTest() {



        Assert.assertTrue(adminCitiesPage.getDeleteSuccessfulMessage().getText().contains("Deleted successfully"));
    }





}
