package factory;

import org.openqa.selenium.WebDriver;
import static factory.Chrome.createChromeDriver;

public class WebDriverFactory {
    public static WebDriver createDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                return createChromeDriver();
            default:
                throw new IllegalStateException(String.format("Unsupported browser: %s", browserName));
        }
    }

}
