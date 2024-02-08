package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.util.Collections;

public class BaseTest {
    protected WebDriver driver;
    protected String url;
    protected String email;
    protected String password;
    protected String backupCode;

    // Constructor
    public BaseTest() {
        this.driver = new ChromeDriver(options());
        this.url = "https://mail.google.com/mail/";
        this.email = "avgautomationenjoyer@gmail.com";
        this.password = "automationenjoyer123";
        this.backupCode = "29230737";
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
        options.addArguments("--incognito");
        options.addExtensions(new File(System.getProperty("user.dir") + "/src/test/java/extensions/Buster-Captcha-Solver-for-Humans.crx"));

        return options;
    }

    @BeforeSuite
    public void initialize() {
        driver.get(url);
    }
}
