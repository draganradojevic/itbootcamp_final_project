package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    @FindBy(id = "email")
    private WebElement loginEmail;

    @FindBy(id = "password")
    private WebElement loginPassword;

    @FindBy(xpath = "//*[@id=\"app\"]/div/main/div/div[2]/div/div/div[3]/span/form/div/div[3]/button")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]/ul/li")
    private WebElement errorMsg;

//    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div")
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]")
    private WebElement message;

    public LoginPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public WebElement getLoginEmail() {
        return loginEmail;
    }

    public WebElement getLoginPassword() {
        return loginPassword;
    }

    public String getErrorMsg() {
        return errorMsg.getText();
    }

    public String getMessage() {
        return message.getText();
    }

    public void login(String emailInput, String passwordInput) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        loginEmail.sendKeys(emailInput);

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        loginPassword.sendKeys(passwordInput);

        loginButton.click();
    }

    public void waitForEmailField() {
        webDriverWait.until(ExpectedConditions.visibilityOf(loginEmail));
    }

}
