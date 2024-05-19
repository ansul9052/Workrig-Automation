package WorkrigAutoamation;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class Workrig_Autoamtion {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");

       // WebDriverManager.chromedriver().setup();
    	System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test()
    public void loginAndLogoutTest() throws InterruptedException {
    	driver.get("https://quloi.myworkrig.com/");
        
        WebElement username = driver.findElement(By.xpath("//input[@id='form-username']"));
        WebElement password = driver.findElement(By.xpath("//input[@id='form-password']"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        username.sendKeys("anshul.gaur");
        password.sendKeys("vd8m5791");
        
        Actions actions = new Actions(driver);
        actions.moveToElement(loginButton).click().perform();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-info"))).click();
        
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
