package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;
import pages.MailInboxPages;
import pages.MailSignInPages;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MailTestsSetup {
    protected WebDriver driver;
    protected MailSignInPages signInPages;
    protected MailInboxPages inboxPages;
    protected String url;
    protected String email;
    protected String password;
    protected List<String> backupCodes;
    protected boolean tooManyFailedAttempts;

    // Constructor
    public MailTestsSetup() {
        this.url = "https://mail.google.com/mail/";
        this.email = "avgautomationenjoyer@gmail.com";
        this.password = "automationenjoyer123";
        this.backupCodes = Arrays.asList("54376727", "91277883", "74250944", "63538810");
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

        return options;
    }

    @BeforeSuite
    public void initialize() {
        this.driver = new ChromeDriver(options());
        this.signInPages = new MailSignInPages(driver);
        this.inboxPages = new MailInboxPages(driver);
        driver.get(url);
    }
}
