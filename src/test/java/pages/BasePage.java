package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

    public BasePage(WebDriver driver, WebDriverWait driverWait) {
        this.driver = driver;
        this.webDriverWait = driverWait;
        PageFactory.initElements(this.driver, this);
    }

    public String checkAttribute(WebElement webElement, String text) {
        return webElement.getAttribute(text);
    }

}
