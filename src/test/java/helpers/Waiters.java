package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static helpers.PropertyProvider.getProperty;
import static helpers.PropertyProvider.getInstance;

public class Waiters {
    private static WebDriverWait wait;
    private static ThreadLocal<Waiters> instance = new ThreadLocal<>();

    public Waiters(WebDriver driver) {
        int timeout = Integer.parseInt(getProperty("explicit.timeout"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public static void waitUntilVisible(WebDriver driver, final WebElement element) {
        getInstance(driver).wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForHomePage(WebDriver driver, String expectedUrl) {
        getInstance(driver).wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    private static Waiters getInstance(WebDriver driver) {
        if (instance.get() == null) {
            instance.set(new Waiters(driver));
        }
        return instance.get();
    }
}
