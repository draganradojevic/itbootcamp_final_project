package Tests;

import Pages.HomePage;
import org.testng.annotations.Test;

public class LocaleTests extends BaseTest{

    @Test
    public void setLocaleToESTest() {
        landingPage.selectLocale("ES");
    }

}
