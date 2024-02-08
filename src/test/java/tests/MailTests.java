package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.MailInboxPages;
import pages.MailSignInPages;

import java.io.File;
import java.util.Collections;

public class MailTests {
    protected WebDriver driver;
    protected MailSignInPages signInPages;
    protected MailInboxPages inboxPages;
    protected String url;
    protected String email;
    protected String password;
    protected String backupCode;

    // Constructor
    public MailTests() {
        this.url = "https://mail.google.com/mail/";
        this.email = "avgautomationenjoyer@gmail.com";
        this.password = "automationenjoyer123";
        this.backupCode = "40886018";
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
        this.driver = new ChromeDriver(options());
        this.signInPages = new MailSignInPages(driver);
        this.inboxPages = new MailInboxPages(driver);
        driver.get(url);
    }

    @Test(priority = 1)
    public void enterEmailTest() {
        signInPages.enterEmail(email);
        signInPages.clickNext();
    }

    @Test(priority = 2)
    public void enterPasswordTest() {
        signInPages.enterPassword(password);
        signInPages.clickNext();
    }

    @Test(priority = 3)
    public void clickTryAnotherWayTest() {
        signInPages.clickTryAnotherWay();
    }

    @Test(priority = 4)
    public void clickSelectInputBackupCodeTest() {
        signInPages.clickSelectBackupCode();
    }

    @Test(priority = 5)
    public void enterBackupCodeTest() {
        signInPages.enterBackupCode(backupCode);
        signInPages.clickNext();
    }

    @Test(priority = 6)
    public void enterQueryTest() {
        inboxPages.enterQuery("is:unread");
    }

    @Test(priority = 7)
    public void returnEnterQueryTest() {
        inboxPages.returnEnterQuery();
    }

    @Test(priority = 8)
    public void retrieveLatestUnreadTitleTest() {
        inboxPages.retrieveLatestUnreadTitle();
    }
}
