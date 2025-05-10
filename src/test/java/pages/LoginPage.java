package pages;

import helpers.Waiters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static helpers.Waiters.waitUntilVisible;

public class LoginPage {
    protected final WebDriver driver;
    protected final Waiters wait;

    @FindBy(xpath = "//input[@id = 'username']")
    WebElement userName;
    @FindBy(xpath = "//input[@id = 'password']")
    WebElement password;
    @FindBy(id = "formly_1_input_username_0")
    WebElement description;
    @FindBy(css = "button[ng-click='Auth.login()']")
    WebElement loginButton;
    @FindBy(css = "[ng-if='Auth.error'].alert-danger")
    private WebElement errorMessage;

    public LoginPage(final WebDriver driver) {
        this.driver = driver;
        this.wait = new Waiters(driver);
        PageFactory.initElements(driver,this);
    }

    public LoginPage setUserName (String userName) {
        waitUntilVisible(driver, this.userName);
        set(this.userName, userName);
        return this;
    }

    public LoginPage setPassword (String password) {
        set(this.password, password);
        return this;
    }

    public LoginPage setDescription (String description) {
        waitUntilVisible(driver, this.password);
        set(this.description, description);
        return this;
    }

    public LoginPage clickSumbitButton() {
        loginButton.click();
        return this;
    }
    public WebElement getLoginButton(){
        return loginButton;
    }

    public LoginPage clearForm() {
        userName.clear();
        password.clear();
        return this;
    }

    protected void set(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

}
