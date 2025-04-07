package WorkrigAutoamation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Properties properties;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.properties = loadProperties();
        PageFactory.initElements(driver, this);
    }

    protected Properties loadProperties() {
        Properties props = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            props.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }

    protected void waitForElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
} 