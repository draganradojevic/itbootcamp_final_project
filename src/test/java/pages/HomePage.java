package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage{

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/header/div/div[3]/button[2]/span")
    private WebElement logoutBtn;

    @FindBy(xpath = "//*[@id=\"app\"]/div[4]/div/div")
    private WebElement dialogMessage;

    @FindBy(className = "btnAdmin")
    private WebElement adminBtn;

    @FindBy(className = "btnAdminCities")
    private WebElement citiesBtn;

    @FindBy(className = "btnClose")
    private WebElement closeBtn;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/header/div/div[3]/a[3]")
    private WebElement myProfileBtn;



    public HomePage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }


    public WebElement getLogoutBtn() {
        return logoutBtn;
    }

    public String getDialogMessage() {
        return dialogMessage.getText();
    }

    public WebElement getCloseBtn() {
        return closeBtn;
    }

    public void enterAdminCitiesPage() {
        adminBtn.click();
        citiesBtn.click();
    }

    public void enterMyProfilePage() {
        myProfileBtn.click();
    }


    public void waitForURLToContainHome() {
        webDriverWait.until(ExpectedConditions.urlContains("/home"));
    }

    public void waitForDialogMessage() {
        webDriverWait.until(ExpectedConditions.textToBePresentInElement(dialogMessage, "Verify your account"));

    }

}
