package WorkrigAutoamation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage extends BasePage {
    
    @FindBy(xpath = "//input[@id='form-username']")
    private WebElement usernameInput;
    
    @FindBy(xpath = "//input[@id='form-password']")
    private WebElement passwordInput;
    
    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;
    
    @FindBy(css = ".btn.btn-info")
    private WebElement logoutButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToLoginPage() {
        driver.get(properties.getProperty("base.url"));
    }

    public void login() {
        waitForElementVisible(usernameInput);
        usernameInput.sendKeys(properties.getProperty("username"));
        passwordInput.sendKeys(properties.getProperty("password"));
        loginButton.click();
    }

    public void verifyLoginSuccess() {
        waitForElementVisible(logoutButton);
        Assert.assertTrue(logoutButton.isDisplayed(), "Login was not successful");
    }

    public void logout() {
        waitForElementClickable(logoutButton);
        logoutButton.click();
        driver.switchTo().alert().accept();
    }
} 