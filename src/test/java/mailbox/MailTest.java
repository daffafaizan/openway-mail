package mailbox;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.Collections;

public class MailTest {
    WebDriver driver;
    String url;
    String email;
    String password;

    // Constructor
    public MailTest() {
        url = "https://mail.google.com/mail/";
        email = "avgautomationenjoyer@gmail.com";
        password = "automationenjoyer123";
    }

    // Config
    public ChromeOptions options() {
        // https://github.com/ultrafunkamsterdam/undetected-chromedriver/issues/144#issuecomment-1068581533
        ChromeOptions options = new ChromeOptions();

        // Fixing 255 Error crashes
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        // Options to trick bot detection
        // Removing webdriver property
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", null);

        // Changing the user agent / browser fingerprint
        options.addArguments("window-size=1920,1080");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");

        // Other
        options.addArguments("disable-infobars");
        options.addExtensions(new File("/Users/daffafaizan/Documents/Programming/Projects/openway-mail/src/test/java/mailbox/Buster-Captcha-Solver-for-Humans.crx"));

        return options;
    }

    @BeforeClass
    public void initialize() {
        driver = new ChromeDriver(options());
        driver.get(url);
    }

    @AfterClass
    public void terminate() {
    }

    @Test(priority = 1)
    public void inputUsername() throws InterruptedException {
        Thread.sleep(10000);
        driver.findElement(By.id("identifierId")).sendKeys(email);
        Thread.sleep(5000);
        driver.findElement(By.id("identifierNext")).click();
    }

    @Test(priority = 2)
    public void clickRecaptcha(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name,'a-')]")));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.recaptcha-checkbox")));
        element.click();
    }
}
