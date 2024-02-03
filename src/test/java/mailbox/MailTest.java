package mailbox;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.io.File;
import java.time.Duration;
import java.util.Collections;

public class MailTest {
    WebDriver driver;
    Robot robot;
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
        options.addArguments("window-size=800,800");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");

        // Other
        options.addArguments("disable-infobars");
        options.addExtensions(new File("/Users/daffafaizan/Documents/Programming/Projects/openway-mail/src/test/java/mailbox/Buster-Captcha-Solver-for-Humans.crx"));

        return options;
    }

    // Support
    public Point mouseLocation(int x, int y) {
        int newX = driver.manage().window().getPosition().x + x;
        int newY = driver.manage().window().getPosition().y + y;
        return new Point(newX, newY);
    }

    @BeforeClass
    public void initialize() throws AWTException {
        robot = new Robot();
        driver = new ChromeDriver(options());
        driver.get(url);
    }

    @Test(priority = 1)
    public void inputUsername() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.id("identifierId")).sendKeys(email);
        Thread.sleep(1000);
        driver.findElement(By.id("identifierNext")).click();
    }

    @Test(priority = 2)
    public void clickRecaptcha(){
        // https://stackoverflow.com/a/55264777
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name,'a-')]")));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.recaptcha-checkbox")));
        element.click();
    }

    @Test(priority = 3)
    public void solveRecaptcha() throws InterruptedException {
        Thread.sleep(5000);
        robot.mouseMove(driver.manage().window().getPosition().x + 390, driver.manage().window().getPosition().y + 760);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(23);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }
}
