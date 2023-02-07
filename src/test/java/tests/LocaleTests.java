package tests;

import pages.Locales;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LocaleTests extends BaseTest{

    @Test
    public void setLocaleToESTest() {

        landingPage.selectLocale(Locales.ES);

        Assert.assertTrue(landingPage.getLandingMessage().contains("Página de aterrizaje"));
    }

    @Test
    public void setLocaleToENTest() {
        landingPage.selectLocale(Locales.EN);

        Assert.assertTrue(landingPage.getLandingMessage().contains("Landing"));
    }

    @Test
    public void setLocaleToFRTest() {
        landingPage.selectLocale(Locales.FR);

        Assert.assertTrue(landingPage.getLandingMessage().contains("Page d'atterrissage"));
    }

    @Test
    public void setLocaleToCNTest() {
        landingPage.selectLocale(Locales.CN);

        Assert.assertTrue(landingPage.getLandingMessage().contains("首页"));
    }

    @Test
    public void setLocaleToUATest() {
        landingPage.selectLocale(Locales.UA);

        Assert.assertTrue(landingPage.getLandingMessage().contains("Лендінг"));
    }

}
