package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage extends BasePage{

    @FindBy(id = "name")
    private WebElement signupName;

    @FindBy(id = "email")
    private WebElement signupEmail;

    @FindBy(id = "password")
    private WebElement signupPassword;

    @FindBy(id = "confirmPassword")
    private WebElement signupConfirmPassword;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[2]/span/form/div/div[5]/button")
    private WebElement signupButton;


    public SignupPage(WebDriver driver, WebDriverWait webDriverWait) {
        super(driver, webDriverWait);
    }

    public WebElement getSignupEmail() {
        return signupEmail;
    }

    public WebElement getSignupPassword() {
        return signupPassword;
    }

    public WebElement getSignupConfirmPassword() {
        return signupConfirmPassword;
    }

    public void signup(String nameInput, String emailInput, String passwordInput, String confirmPasswordInput) {
        signupName.sendKeys(nameInput);
        signupEmail.sendKeys(emailInput);
        signupPassword.sendKeys(passwordInput);
        signupConfirmPassword.sendKeys(confirmPasswordInput);
        signupButton.click();
    }


}
