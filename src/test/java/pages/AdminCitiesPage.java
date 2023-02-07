package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    @FindBy(id = "edit")
    private WebElement editCityBtn;

    @FindBy(id = "name")
    private WebElement editName;

    @FindBy(id = "search")
    private WebElement searchField;

    //    @FindBy(xpath = "/html/body/div/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr[1]/td[2]")
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr/td[2]")
    private WebElement nameOfCity;

    @FindBy(id = "delete")
    private WebElement deleteBtn;

//    @FindBy(xpath = "//*[@id=\"app\"]/div[6]/div/div/div[2]/button[2]")
//    @FindBy(xpath = "//*[@id=\"app\"]/div[7]/div/div/div[2]/button[2]")
//    @FindBy(className = "text--lighten3")
    @FindBy(css = "#app > div.v-dialog__content.v-dialog__content--active > div > div > div.v-card__actions > button.v-btn.v-btn--text.theme--light.v-size--default.red--text.text--lighten3")
    private WebElement deleteBtnConfirm;


    public AdminCitiesPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }


    public WebElement getLogoutBtn() {
        return logoutBtn;
    }

    public WebElement getSavedSuccessfullyMessage() {
        return savedSuccessfullyMessage;
    }

    public WebElement getEditName() {
        return editName;
    }

    public WebElement getSearchField() {
        return searchField;
    }

    public String getNameOfCity() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr/td[2]")));
        return nameOfCity.getText();
    }

    public void createCity(String inputCityName) {
        newItemBtn.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        newItemName.sendKeys(inputCityName);

        saveBtn.click();
    }


    public void editCity(String inputCityName) {
        editCityBtn.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[5]/div/div")));
        editName.click();
        editName.sendKeys(getEditName().getText() + " - edited");
        saveBtn.click();
    }

    public void searchCity(String inputCityName) {
        searchField.click();
        searchField.sendKeys(Keys.CONTROL + "a" + Keys.DELETE);

        searchField.sendKeys(inputCityName);
    }

    public void deleteCity(String inputCityName) {
        deleteBtn.click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[6]/div/div")));
        deleteBtnConfirm.click();
    }

}
