package mailbox;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.awt.event.InputEvent;
import java.time.Duration;

public class MailTest extends AbstractTests {

    // Tests
    @Test(priority = 1)
    public void inputUsername() throws InterruptedException {
        driver.findElement(By.id("identifierId")).sendKeys(email);
        driver.findElement(By.id("identifierNext")).click();
    }

    @Test(priority = 2)
    public void clickRecaptcha(){
        // https://stackoverflow.com/a/55264777
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name,'a-')]")));
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.recaptcha-checkbox"))).click();
            driver.switchTo().defaultContent();
            captcha = true;
        } catch (TimeoutException e) {
            captcha = false;
            System.out.print("\nNo captcha detected");
            throw new SkipException("");
        }

    }

    @Test(priority = 3)
    public void solveRecaptcha() throws InterruptedException {
        if (captcha) {
            robot.mouseMove(driver.manage().window().getPosition().x + 390, driver.manage().window().getPosition().y + 760);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(25);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } else {
            System.out.print("\nNo captcha detected");
            throw new SkipException("");
        }
    }

    @Test(priority = 4)
    public void continueRecaptcha() throws InterruptedException {
        if (captcha) {
            driver.findElement(By.xpath("//*[contains(text(), 'Next')]")).click();
        } else {
            System.out.print("\nNo captcha detected");
            throw new SkipException("");
        }
    }

    @Test(priority = 5)
    public void inputPassword() throws InterruptedException{
        driver.findElement(By.name("Passwd")).sendKeys(password);
        driver.findElement(By.xpath("//*[contains(text(), 'Next')]")).click();
    }

    @Test(priority = 6)
    public void searchUnreadMail() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search mail']"))).sendKeys("is:unread");
        driver.findElement(By.xpath("//input[@placeholder='Search mail']")).sendKeys(Keys.RETURN);
    }

    @Test(priority = 7)
    public void retrieveTitle() throws InterruptedException {
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
