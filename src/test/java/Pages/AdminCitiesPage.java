package Pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.Key;
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

    @FindBy(xpath = "\"//div[contains(text(),'Saved successfully')]\"")
    private WebElement saveMessage;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr")
    private List<WebElement> citiesList;

//    @FindBy(xpath = "//*[@id=\"edit\"]")
    @FindBy(id = "edit")
    private WebElement editCityBtn;

    @FindBy(id = "name")
    private WebElement editName;

    @FindBy(xpath = "//*[@id='app']/div[5]/div/div/div[3]/button[2]")
    private WebElement saveEdit;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div/div[1]")
    private WebElement editSuccessfullySavedMessage;

    @FindBy(id = "search")
    private WebElement searchField;

//    @FindBy(xpath = "//tr/td[2]")
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr/td[2]")
    private By cityName;

    @FindBy(id = "delete")
    private WebElement deleteBtn;

    @FindBy(xpath = "//*[@id=\"app\"]/div[10]/div/div/div[2]/button[2]")
//    @FindBy(className = "text--lighten3")
    private WebElement deleteBtnConfirm;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div")
//    @FindBy(xpath = "//div[contains(text(),'Deleted successfully')]")
    private WebElement deleteSuccessfulMessage;




    public AdminCitiesPage(WebDriver driver, WebDriverWait driverWait) {
        super(driver, driverWait);
    }






    public WebElement getLogoutBtn() {
        return logoutBtn;
    }

    public WebElement getNewItemBtn() {
        return newItemBtn;
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

    public WebElement getEditName() {
        return editName;
    }

    public WebElement getSaveEdit() {
        return saveEdit;
    }


    public WebElement getSearchField() {
        return searchField;
    }

    public WebElement getDeleteBtn() {
        return deleteBtn;
    }

    public WebElement getDeleteBtnConfirm() {
        return deleteBtnConfirm;
    }

    public WebElement getSavedSuccessfullyMessage() {
        return savedSuccessfullyMessage;
    }

    public WebElement getSaveMessage() {
        return saveMessage;
    }

    public WebElement getEditSuccessfullySavedMessage() {
        return editSuccessfullySavedMessage;
    }

    public WebElement getDeleteSuccessfulMessage() {
        return deleteSuccessfulMessage;
    }

        public WebElement getCityName() {
        return driver.findElement(cityName);
    }



    public void createCity(String inputCityName) {
        newItemName.sendKeys(inputCityName);
    }

    public String getNewItemName() {
        Faker faker = new Faker();
        String city = faker.address().cityName();
        return city;
    }

    public String getNewItemNameEdited() {
        return getNewItemName() + "- edited";
    }

    public void search(String inputCity) {
        searchField.click();
        searchField.sendKeys(inputCity);
    }

    public void delete() {
        deleteBtn.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div[10]/div/div")));
        deleteBtnConfirm.click();
    }

}
