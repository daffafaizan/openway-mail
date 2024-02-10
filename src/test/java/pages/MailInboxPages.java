package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MailInboxPages {

    WebDriver driver;
    WebDriverWait wait;

    // Input Elements
    @FindBy(xpath = "//input[@placeholder='Search mail']")
    WebElement searchBar;

    // Link and Other Elements
    @FindBy(linkText = "Inbox")
    WebElement inboxLink;
    @FindBy(linkText = "Starred")
    WebElement starredLink;
    @FindBy(linkText = "Snoozed")
    WebElement snoozedLink;
    @FindBy(linkText = "Sent")
    WebElement sentLink;
    @FindBy(linkText = "Drafts")
    WebElement draftsLink;
    @FindBy(xpath = "//span[@aria-label='More labels']")
    WebElement moreDropdown;
    @FindBy(xpath = "//tbody/tr")
    List<WebElement> emails;
    @FindBy(xpath = "//tbody/tr/td[4]/div[2]/span/span")
    List<WebElement> unreadEmailTitleElements;

    public MailInboxPages(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofMillis(6000));
        PageFactory.initElements(driver, this);
    }

    // Entering input
    public void enterQuery(String query) {
        wait.until(ExpectedConditions.elementToBeClickable(searchBar)).sendKeys(query, Keys.RETURN);
    }

    // Grabbing text
    public String getLatestUnreadTitle() {
        String title = unreadEmailTitleElements.getFirst().getAttribute("innerHTML");
        System.out.println("\n===============================================");
        System.out.println("Latest unread email is titled: " + title);
        System.out.println("===============================================");
        return title;
    }
    public String getURL() {
        wait.until(ExpectedConditions.visibilityOf(searchBar)).isDisplayed();
        return driver.getCurrentUrl();
    }

    // Misc
    public boolean availableEmails() {
        return emails.isEmpty();
    }

    // Checking if element is present
    public boolean isPresent(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }
    public boolean searchBarIsPresent() {
        return isPresent(searchBar);
    }
    public boolean inboxIsPresent() {
        return isPresent(inboxLink);
    }
    public boolean starredIsPresent() {
        return isPresent(starredLink);
    }
    public boolean snoozedIsPresent() {
        return isPresent(snoozedLink);
    }
    public boolean sentIsPresent() {
        return isPresent(sentLink);
    }
    public boolean draftsIsPresent() {
        return isPresent(draftsLink);
    }
    public boolean moreDropdownIsPresent() {
        return isPresent(moreDropdown);
    }

    // Checking if element is clickable
    public boolean isClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element)).isDisplayed();
    }
    public boolean searchBarIsClickable() {
        return isClickable(searchBar);
    }
    public boolean inboxIsClickable() {
        return isClickable(inboxLink);
    }
    public boolean starredIsClickable() {
        return isClickable(starredLink);
    }
    public boolean snoozedIsClickable() {
        return isClickable(snoozedLink);
    }
    public boolean sentIsClickable() {
        return isClickable(sentLink);
    }
    public boolean draftsIsClickable() {
        return isClickable(draftsLink);
    }
    public boolean moreDropdownIsClickable() {
        return isClickable(moreDropdown);
    }
}
