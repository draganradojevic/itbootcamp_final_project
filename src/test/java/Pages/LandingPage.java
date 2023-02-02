package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage extends BasePage{

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/header/div/div[3]/a[3]/span")
    private WebElement loginBtn;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/header/div/div[3]/a[4]/span")
    private WebElement signupBtn;

//    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/header/div/div[3]/button")
//    private WebElement localeBtn;
    @FindBy(className = "btnLocaleActivation")
    private WebElement localeBtn;

    @FindBy(id = "list-item-995")
    private WebElement localeEN;

    @FindBy(id = "list-item-997")
    private WebElement localeES;

//    @FindBy(id = "list-item-999")
    @FindBy(xpath = "//*[@id=\"list-item-999\"]")
    private WebElement localeFR;

    @FindBy(id = "list-item-1001")
    private WebElement localeCN;

    @FindBy(id = "list-item-1003")
    private WebElement localeUA;




    @FindBy(xpath = "//*[@id=\"app\"]/div[3]/div")
    private WebElement localeChoices;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[1]/h1")
    private WebElement landingMessage;


    public LandingPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }


    public WebElement getLandingMessage() {
        return landingMessage;
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

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("list-item-995")));
        chooseLocale(locale);

//        driver.findElement(By.xpath("//a[text()='" + localeInput + "']")).click();

//        Select selectLocale = new Select(localeBtn);
//        selectLocale.selectByVisibleText(localeInput);
    }
}
