package WorkrigAutoamation;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Workrig_Autoamtion  {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void loginAndCheckInOut() throws InterruptedException {
        driver.get("https://quloi.myworkrig.com/");

        WebElement username = driver.findElement(By.xpath("//input[@id='form-username']"));
        WebElement password = driver.findElement(By.xpath("//input[@id='form-password']"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));


        username.sendKeys("anshul.gaur");
        password.sendKeys("vd8m5791");
        loginButton.click();

        WebElement checkInOutButton = driver.findElement(By.cssSelector(".btn.btn-info"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkInOutButton);
        Thread.sleep(1000); 
        
        checkInOutButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-info")));
      //  checkInOutButton.click();

        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
