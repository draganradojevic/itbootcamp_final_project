package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthRoutesTests extends BaseTest{

    @Test
    public void visitHomeWhenNotLoggedInTest() {
        driver.get("https://vue-demo.daniel-avellaneda.com/home");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

}
