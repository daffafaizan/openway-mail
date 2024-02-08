package mailbox;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.time.Duration;

public class MailTest extends AbstractTests {

    // Tests
    @Test(priority = 1)
    public void dismiss() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        WebElement span = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Dismiss']")));
        WebElement button = span.findElement(By.xpath("./ancestor::button"));
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }

    @Test(priority = 2)
    public void inputUsername() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("identifierId"))).sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'Next')]"))).click();
    }

    @Test(priority = 3)
    public void inputPassword() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        wait.until(ExpectedConditions.elementToBeClickable(By.name("Passwd"))).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'Next')]"))).click();
    }

    @Test(priority = 4)
    public void tryAnotherWay() throws InterruptedException {
        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        WebElement span = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Try another way']")));
        WebElement button = span.findElement(By.xpath("./ancestor::button"));
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }

    @Test(priority = 5)
    public void selectBackUpCodes() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'Enter one of your 8-digit backup codes')]"))).click();
    }

    @Test(priority = 6)
    public void inputBackUpCode() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("backupCodePin"))).sendKeys(backupCode);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'Next')]"))).click();
    }

    @Test(priority = 7)
    public void searchUnreadMail() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search mail']"))).sendKeys("is:unread");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search mail']"))).sendKeys(Keys.RETURN);
    }

    @Test(priority = 8)
    public void retrieveTitle() {
        try {
            titles = driver.findElements(By.xpath("//div[@class='y6']/span/span"));
            System.out.println("\n===============================================");
            System.out.println("Latest unread email is titled: " + titles.getFirst().getAttribute("innerHTML"));
            System.out.println("===============================================");
        } catch (NoSuchElementException e) {
            System.out.print("\nNo unread emails");
            throw new SkipException("");
        }
    }
}
