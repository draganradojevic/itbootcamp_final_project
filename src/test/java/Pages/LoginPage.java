package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    @FindBy(id = "email")
    private WebElement loginEmail;

    @FindBy(id = "password")
    private WebElement loginPassword;

    @FindBy(xpath = "//*[@id=\"app\"]/div/main/div/div[2]/div/div/div[3]/span/form/div/div[3]/button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public WebElement getLoginEmail() {
        return loginEmail;
    }

    public WebElement getLoginPassword() {
        return loginPassword;
    }

    public void login(String emailInput, String passwordInput) {
        loginEmail.sendKeys(emailInput);
        loginPassword.sendKeys(passwordInput);
        loginButton.click();
    }



}
