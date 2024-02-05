package mailbox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;

import java.awt.*;
import java.io.File;
import java.time.Duration;
import java.util.Collections;
import java.util.Random;

public class AbstractTests {
    protected WebDriver driver;
    protected Robot robot;
    protected String url;
    protected String email;
    protected String password;
    protected String title;
    protected Boolean captcha;

    // Constructor
    public AbstractTests() {
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
        options.addExtensions(new File(System.getProperty("user.dir") + "/src/test/java/extensions/Buster-Captcha-Solver-for-Humans.crx"));

        return options;
    }

    // Support
    public Duration randomDuration(int start, int finish) {
        Random random = new Random();
        int duration = random.nextInt(start, finish);
        return Duration.ofMillis(duration);
    }

    public void loadPageSleep() throws InterruptedException {
        Thread.sleep(randomDuration(2500, 3500));
    }

    public void redirectPageSleep() throws InterruptedException {
        Thread.sleep(randomDuration(6000, 8000));
    }

    public void betweenElementSleep() throws InterruptedException {
        Thread.sleep(randomDuration(2000, 3000));
    }

    @BeforeSuite
    public void initialize() throws AWTException {
        robot = new Robot();
        driver = new ChromeDriver(options());
        driver.get(url);
    }

}
