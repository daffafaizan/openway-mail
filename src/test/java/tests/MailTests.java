package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
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
        this.backupCode = "20634357";
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

    @Test
    public void TC001_VerifyTitle() {
        String expectedTitle = "Sign in";
        String actualTitle = signInPages.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void TC002_InputEmail() {
        signInPages.enterEmail(email);
        signInPages.clickNext();

        String expectedTitle = "Welcome";
        String actualTitle = signInPages.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void TC003_InputPassword() {
        signInPages.enterPassword(password);
        signInPages.clickNext();

        String expectedTitle = "2-Step Verification";
        String actualTitle = signInPages.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void TC004_2FA() {
        signInPages.clickTryAnotherWay();
        signInPages.clickSelectBackupCode();
        signInPages.enterBackupCode(backupCode);
        signInPages.clickNext();

        String expectedUrl = "https://mail.google.com/mail/u/0/#inbox";
        String actualUrl = inboxPages.getUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test
    public void TC005_RetrieveTitle() {
        inboxPages.enterQuery("is:unread");
        inboxPages.retrieveLatestUnreadTitle();
    }
}
