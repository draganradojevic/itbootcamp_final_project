package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage extends BasePage{

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/header/div/div[3]/a[3]/span")
    private WebElement loginBtn;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/header/div/div[3]/a[4]/span")
    private WebElement signupBtn;

    @FindBy(className = "btnLogout")
    private WebElement logoutBtn;

    @FindBy(className = "btnLocaleActivation")
    private WebElement localeBtn;

    @FindBy(className = "btnEN")
    private WebElement localeEN;

    @FindBy(className = "btnES")
    private WebElement localeES;

    @FindBy(className = "btnFR")
    private WebElement localeFR;

    @FindBy(className = "btnCN")
    private WebElement localeCN;

    @FindBy(className = "btnUA")
    private WebElement localeUA;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[1]/h1")
    private WebElement landingMessage;


    public LandingPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public WebElement getLoginBtn() {
        return loginBtn;
    }

    public WebElement getLogoutBtn() {
        return logoutBtn;
    }

    public WebElement getLandingMessage() {
        return landingMessage;
    }

    public WebElement getLocaleBtn() {
        return localeBtn;
    }

    public void enterLoginPage() {
        loginBtn.click();
    }

    public void enterSignupPage() {
        signupBtn.click();
    }




    public void chooseLocale(Locales locales) {
        switch (locales) {
            case EN:
                localeEN.click();
                break;
            case ES:
                localeES.click();
                break;
            case FR:
                localeFR.click();
                break;
            case CN:
                localeCN.click();
                break;
            case UA:
                localeUA.click();
                break;
        }
    }

    public void selectLocale(Locales locale) {
        localeBtn.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btnEN")));

        chooseLocale(locale);
    }
}
