package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage{

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/header/div/div[3]/button[2]/span")
    private WebElement logoutBtn;

    @FindBy(xpath = "//*[@id=\"app\"]/div[4]/div/div/div[1]")
    private WebElement dialogMessage;


    @FindBy(className = "btnAdmin")
    private WebElement adminBtn;

    @FindBy(className = "btnAdminCities")
    private WebElement citiesBtn;

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



    public void enterAdminCitiesPage() {
        adminBtn.click();
        citiesBtn.click();
    }

    public void enterMyProfilePage() {
        myProfileBtn.click();
    }

}
