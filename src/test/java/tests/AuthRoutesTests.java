package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthRoutesTests extends BaseTest{

    @Test
    public void visitHomeWhenNotLoggedInTest() {
        driver.navigate().to(BASE_URL + "/home");

        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    public void visitProfileWhenNotLoggedInTest() {
        driver.navigate().to(BASE_URL + "/profile");

        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    public void visitAdminCitiesWhenNotLoggedInTest() {
        driver.navigate().to(BASE_URL + "/admin/cities");

        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    public void visitAdminUsersWhenNotLoggedInTest() {
        driver.navigate().to(BASE_URL + "/admin/users");

        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

}
