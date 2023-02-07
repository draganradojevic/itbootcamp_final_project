package tests;

import pages.HomePage;
import pages.ProfilePage;
import pages.SignupPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

        String signupName = FakerClass.getFakeName();
        String signupEmail = FakerClass.getFakeEmail();
        String signupPassword = FakerClass.getFakePassword();
        signupPage.signup(signupName, signupEmail, signupPassword, signupPassword);

        homePage.waitForDialogMessage();
        homePage.getCloseBtn().click();

        homePage.enterMyProfilePage();

        profilePage.waitForEditProfileForm();

        String editName = FakerClass.getFakeName();
        String editPhone = FakerClass.getFakePhone();
        String editCity = "Chicago";
        String editCountry = FakerClass.getFakeCountry();
        String editTwitter = "https://www.twitter.com/" + editName.toLowerCase();
        String editGithub = "https://www.github.com/" + editName.toLowerCase();

        profilePage.editProfile(editName, editPhone, editCity, editCountry, editTwitter, editGithub);

        profilePage.waitForSavedSuccessfullyMessage();

        Assert.assertTrue(profilePage.getSavedSuccessfullyMessage().getText().contains("Profile saved successfuly"));

        Assert.assertEquals(profilePage.getNameValue(), editName);
        Assert.assertEquals(profilePage.getPhoneValue(), editPhone);
        Assert.assertEquals(profilePage.getCityValue(), editCity);
        Assert.assertEquals(profilePage.getCountryValue(), editCountry);
        Assert.assertEquals(profilePage.getTwitterURLValue(), editTwitter);
        Assert.assertEquals(profilePage.getGithubURLValue(), editGithub);
    }
}
