package Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasePage{

    @FindBy(id = "name")
    private WebElement name;

    @FindBy(id = "phone")
    private WebElement phone;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "country")
    private WebElement country;

    @FindBy(id = "urlTwitter")
    private WebElement urlTwitter;

    @FindBy(id = "urlGitHub")
    private WebElement urlGithub;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[2]/span/form/div/div/div[8]/button")
    private WebElement saveBtn;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]")
    private WebElement savedSuccessfullyMessage;


    public ProfilePage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }

    public WebElement getSaveBtn() {
        return saveBtn;
    }

    public WebElement getSavedSuccessfullyMessage() {
        return savedSuccessfullyMessage;
    }

    public void editProfile(String nameInput, String phoneInput, String cityInput, String countryInput, String twitterInput, String githubInput) {
        name.sendKeys(nameInput);
        phone.sendKeys(phoneInput);

        city.sendKeys(Keys.CONTROL + "a");
        city.sendKeys(Keys.DELETE);
        city.sendKeys(cityInput);
        city.sendKeys(Keys.ENTER);

        country.sendKeys(countryInput);
        urlTwitter.sendKeys(twitterInput);
        urlGithub.sendKeys(githubInput);
        saveBtn.click();
    }
}
