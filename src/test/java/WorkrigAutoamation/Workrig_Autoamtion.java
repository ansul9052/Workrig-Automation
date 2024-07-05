package WorkrigAutoamation;

import java.time.Duration;
import java.util.logging.Logger;
import java.util.logging.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Workrig_Autoamtion {
    WebDriver driver;
    WebDriverWait wait;
    Logger logger = Logger.getLogger(Workrig_Autoamtion.class.getName());

    @BeforeClass
    public void setUp() {
        ChromeOptions option = new ChromeOptions();
//        option.addArguments("--headless");
        driver = new ChromeDriver(option);
        option.addArguments("--disable-notifications");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void NaukriProfileUpdate() {
        try {
            driver.navigate().to("https://www.naukri.com/");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='login_Layer']")));

            driver.findElement(By.xpath("//a[@id='login_Layer']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter your active Email ID / Username']")));
            driver.findElement(By.xpath("//input[@placeholder='Enter your active Email ID / Username']")).sendKeys("anshulgaur66@gmail.com");
            driver.findElement(By.xpath("//input[@placeholder='Enter your password']")).sendKeys("Imgre@t12");
            driver.findElement(By.xpath("//input[@placeholder='Enter your password']")).sendKeys(Keys.ENTER);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='View profile']")));
            driver.findElement(By.xpath("//a[normalize-space()='View profile']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='widgetHead']//span[@class='edit icon'][normalize-space()='editOneTheme']")));

            WebElement scroll = driver.findElement(By.cssSelector("div[class='prefill typ-14Medium'] div"));
            WebElement edit = driver.findElement(By.xpath("//div[@class='widgetHead']//span[@class='edit icon'][normalize-space()='editOneTheme']"));

            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", scroll);
            

            edit.click();

            WebElement editArea = driver.findElement(By.xpath("//textarea[@id='resumeHeadlineTxt']"));
            editArea.sendKeys(Keys.BACK_SPACE);
            editArea.sendKeys(".");
            driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='msg']")));
            String confirm = driver.findElement(By.xpath("//p[@class='msg']")).getText();
            System.out.println(confirm);
        } catch(Exception e) {
            logger.log(Level.SEVERE, "Issue in code", e);
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
