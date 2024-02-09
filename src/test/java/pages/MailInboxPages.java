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

    // Text Elements
    @FindBy(xpath = "//div[@class='y6']/span/span")
    List<WebElement> titles;

    public MailInboxPages(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        PageFactory.initElements(driver, this);
    }

    public void enterQuery(String query) {
        wait.until(ExpectedConditions.elementToBeClickable(searchBar)).sendKeys(query, Keys.RETURN);
    }

    public void retrieveLatestUnreadTitle() {
        System.out.println("\n===============================================");
        System.out.println("Latest unread email is titled: " + titles.getFirst().getAttribute("innerHTML"));
        System.out.println("===============================================");
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }
}
