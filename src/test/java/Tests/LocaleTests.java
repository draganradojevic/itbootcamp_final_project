package Tests;

import Pages.HomePage;
import org.testng.annotations.Test;

public class LocaleTests extends BaseTest{

    private HomePage homePage;



    @Test
    public void setLocaleToESTest() {
        landingPage.selectLocale("ES");
    }

}
