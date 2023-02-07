package tests;

import pages.HomePage;
import pages.LandingPage;
import pages.ProfilePage;
import pages.SignupPage;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.FakerClass;

public class ProfileTests extends BaseTest {

    private SignupPage signupPage;
    private HomePage homePage;
    private ProfilePage profilePage;

    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        signupPage = new SignupPage(driver, driverWait);
        homePage = new HomePage(driver, driverWait);
        profilePage = new ProfilePage(driver, driverWait);
    }

    @AfterMethod
    public void afterMethod() {
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.className("btnLogout")));
        landingPage.getLogoutBtn().sendKeys(Keys.ENTER);
    }

    @Test
    public void editProfileTest() {
        landingPage.enterSignupPage();

//        String signupName = faker.name().firstName();
//        String signupEmail = faker.internet().emailAddress();
//        String signupPassword = faker.internet().password();
//        String signupConfirmPassword = signupPassword;

        String signupName = FakerClass.getFakeName();
        String signupEmail = FakerClass.getFakeEmail();
        String signupPassword = FakerClass.getFakePassword();
        signupPage.signup(signupName, signupEmail, signupPassword, signupPassword);

        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div[4]/div/div/div[3]/button")));
        WebElement closeBtn = driver.findElement(By.xpath("//*[@id=\"app\"]/div[4]/div/div/div[3]/button"));
        closeBtn.click();

        homePage.enterMyProfilePage();

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[2]/span/form")));


        String editName = FakerClass.getFakeName();
        String editPhone = FakerClass.getFakePhone();
        String editCity = "Chicago";
        String editCountry = FakerClass.getFakeCountry();
        String editTwitter = "https://www.twitter.com/" + editName.toLowerCase();
        String editGithub = "https://www.github.com/" + editName.toLowerCase();



        profilePage.editProfile(editName, editPhone, editCity, editCountry, editTwitter, editGithub);

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div")));

        Assert.assertTrue(profilePage.getSavedSuccessfullyMessage().getText().contains("Profile saved successfuly"));

        Assert.assertEquals(profilePage.getName().getAttribute("value"), editName);
        Assert.assertEquals(profilePage.getPhone().getAttribute("value"), editPhone);
        Assert.assertEquals(profilePage.getCity().getAttribute("value"), editCity);
        Assert.assertEquals(profilePage.getCountry().getAttribute("value"), editCountry);
        Assert.assertEquals(profilePage.getUrlTwitter().getAttribute("value"), editTwitter);
        Assert.assertEquals(profilePage.getUrlGithub().getAttribute("value"), editGithub);

    }
}
