package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AdminCitiesPage extends BasePage{

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/header/div/div[3]/button[2]")
    private WebElement logoutBtn;

    @FindBy(className = "btnNewItem")
    private WebElement newItemBtn;

    @FindBy(id = "name")
    private WebElement newItemName;

    @FindBy(className = "btnSave")
    private WebElement saveBtn;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div/div[1]")
    private WebElement savedSuccessfullyMessage;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr")
    private List<WebElement> citiesList;

    @FindBy(xpath = "//*[@id=\"edit\"]")
    private WebElement editCityBtn;

    @FindBy(id = "name")
    private WebElement editInput;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div/div[1]")
    private WebElement editSuccessfullySavedMessage;




    public AdminCitiesPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }






    public WebElement getLogoutBtn() {
        return logoutBtn;
    }

    public WebElement getEditCityBtn() {
        return editCityBtn;
    }

    public List<WebElement> getCitiesList() {
        return citiesList;
    }

    public WebElement getSaveBtn() {
        return saveBtn;
    }

    public WebElement getEditInput() {
        return editInput;
    }

    public WebElement getSavedSuccessfullyMessage() {
        return savedSuccessfullyMessage;
    }

    public WebElement getEditSuccessfullySavedMessage() {
        return editSuccessfullySavedMessage;
    }

    public void createNewCity(String inputCityName) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[1]/div[3]/form/div[1]/button")));
        newItemBtn.click();

        newItemName.clear();
        newItemName.sendKeys(inputCityName);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        saveBtn.click();
    }

}
