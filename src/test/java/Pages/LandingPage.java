package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage extends BasePage{

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/header/div/div[3]/a[3]/span")
    private WebElement loginBtn;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/header/div/div[3]/a[4]/span")
    private WebElement signupBtn;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/header/div/div[3]/button")
    private WebElement localeBtn;


    public LandingPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public void enterLoginPage() {
        loginBtn.click();
    }

    public void enterSignupPage() {
        signupBtn.click();
    }

    public void selectLocale(String localeInput) {
        loginBtn.click();
        Select selectLocale = new Select(localeBtn);
        selectLocale.selectByVisibleText(localeInput);
    }
}
