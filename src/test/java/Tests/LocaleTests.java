package Tests;

import Pages.LandingPage;
import Pages.Locales;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LocaleTests extends BaseTest{

    private LandingPage landingPage;

    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        landingPage = new LandingPage(driver, driverWait);
    }

    @Test
    public void setLocaleToESTest() {

        landingPage.selectLocale(Locales.ES);

        Assert.assertTrue(landingPage.getLandingMessage().getText().contains("Página de aterrizaje"));
    }

    @Test
    public void setLocaleToENTest() {
        landingPage.selectLocale(Locales.EN);

        Assert.assertTrue(landingPage.getLandingMessage().getText().contains("Landing"));
    }

    @Test
    public void setLocaleToFRTest() {
        landingPage.selectLocale(Locales.FR);

        Assert.assertTrue(landingPage.getLandingMessage().getText().contains("Page d'atterrissage"));
    }

}
