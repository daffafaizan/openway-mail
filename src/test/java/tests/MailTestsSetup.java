package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;
import pages.MailInboxPages;
import pages.MailSignInPages;

import java.util.Collections;

public class MailTestsSetup {
    protected WebDriver driver;
    protected MailSignInPages signInPages;
    protected MailInboxPages inboxPages;
    protected String url;
    protected String email;
    protected String password;
    protected String[] backupCodes;
    protected boolean tooManyFailedAttempts;

    // Constructor
    public MailTestsSetup() {
        this.url = "https://mail.google.com/mail/";
        this.email = "avgautomationenjoyer@gmail.com";
        this.password = "automationenjoyer123";

        String codes = "5906 9176\n" +
                "0081 7086\n" +
                "6148 5427\n" +
                "0384 2605\n" +
                "6451 7225\n" +
                "9793 2710\n" +
                "4514 8290\n" +
                "7006 3851\n" +
                "5835 2483\n" +
                "1324 8023";
        this.backupCodes = codes.split("\n");
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
