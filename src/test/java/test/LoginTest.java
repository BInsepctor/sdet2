package test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;

import static factory.WebDriverFactory.createDriver;
import static helpers.DataGenerator.generateData;
import static helpers.PropertyProvider.getProperty;
import static helpers.Waiters.waitForHomePage;
import static org.testng.Assert.assertTrue;

public class LoginTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    private final String loginUrl = getProperty("web.url.login");
    private String correctUserName = getProperty("correct.username");
    private String correctPassword = getProperty("correct.password");
    private String unCorrectUserName = generateData();
    private String unCorrectPassword = generateData();
    private String description = generateData();

    @BeforeClass
    public void setUp() {
        String browserName = getProperty("browser.name");
        driver = createDriver(browserName);
        driver.get(loginUrl);
        loginPage = new LoginPage(driver);
    }

    @Test(description = "Test - 1: Success authentication")
    public void successAuthTest() {
        loginPage.setUserName(correctUserName)
                .setPassword(correctPassword)
                .setDescription(description)
                .clickSumbitButton();
        waitForHomePage(driver, getProperty("web.url.home"));
        Assert.assertEquals(driver.getCurrentUrl(), getProperty("web.url.home"));
    }

    @Test(description = "Test - 2: Failed authentication with incorrect username")
    public void unSuccessAuthTest() {
        loginPage.setUserName(unCorrectUserName)
                .setPassword(correctPassword)
                .setDescription(description)
                .clickSumbitButton();
        Assert.assertNotEquals(driver.getCurrentUrl(), getProperty("web.url.home"));
    }

    @Test(description = "Test - 3: Failed authentication with incorrect password")
    public void unSuccessAuthTestPaww() {
        loginPage.setUserName(correctUserName)
                .setPassword(unCorrectPassword)
                .setDescription(description)
                .clickSumbitButton();
        Assert.assertNotEquals(driver.getCurrentUrl(), getProperty("web.url.home"));
    }

    @Test(description = "Test - 4: Failed authentication with incorrect username & password")
    public void unSuccessAuthTestPaw() {
        loginPage.setUserName(unCorrectUserName)
                .setPassword(unCorrectPassword)
                .setDescription(description)
                .clickSumbitButton();
        Assert.assertNotEquals(driver.getCurrentUrl(), getProperty("web.url.home"));
    }

    @Test(description = "Test - 5: Login button disabled when fields are empty")
    public void checkData() {
        loginPage.setUserName(unCorrectUserName)
                .setPassword(unCorrectPassword);
        assertTrue(loginPage.getLoginButton().getAttribute("disabled") != null,
                "Login button should be disabled when password is missing");
    }

    @AfterMethod
    public void resetPage() {
        driver.get(loginUrl);
        driver.navigate().refresh();
        loginPage.clearForm();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}