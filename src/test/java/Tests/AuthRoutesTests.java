package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthRoutesTests extends BaseTest{

    @Test
    public void visitHomeWhenNotLoggedInTest() {
        driver.navigate().to("https://vue-demo.daniel-avellaneda.com/home");

        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    public void visitProfileWhenNotLoggedInTest() {
        driver.navigate().to("https://vue-demo.daniel-avellaneda.com/profile");

        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    public void visitAdminCitiesWhenNotLoggedInTest() {
        driver.navigate().to("https://vue-demo.daniel-avellaneda.com/admin/cities");

        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @Test
    public void visitAdminUsersWhenNotLoggedInTest() {
        driver.navigate().to("https://vue-demo.daniel-avellaneda.com/admin/users");

        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

}
