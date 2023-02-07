package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.LandingPage;

import java.time.Duration;

public abstract class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait driverWait;
    protected LandingPage landingPage;

    protected final String BASE_URL = "https://vue-demo.daniel-avellaneda.com";
    protected final String VALID_EMAIL = "admin@admin.com";
    protected final String VALID_PASSWORD = "12345";

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Desktop\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        landingPage = new LandingPage(driver, driverWait);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(BASE_URL);
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
