package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage{

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/header/div/div[3]/button[2]/span")
    private WebElement logoutBtn;

    @FindBy(xpath = "//*[@id=\"app\"]/div[4]/div/div/div[1]")
    private WebElement dialogMessage;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/header/div/div[3]/button[1]")
    private WebElement adminBtn;

    @FindBy(xpath = "//*[@id=\"list-item-282\"]/div[2]")
    private WebElement cities;

    public HomePage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }


    public WebElement getLogoutBtn() {
        return logoutBtn;
    }

    public String getDialogMessage() {
        return dialogMessage.getAttribute("value");
    }

    public void logout() {
        logoutBtn.click();
    }


//
//    public void enterAdminCitiesPage() {
//        adminBtn.click();
//
//        Select adminCities = new Select();
//        webDriverWait.until(ExpectedConditions.visibilityOf(cities));
//        adminCities.selectByVisibleText("Cities");
//    }

}
