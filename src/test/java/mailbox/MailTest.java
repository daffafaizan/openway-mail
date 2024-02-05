package mailbox;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.awt.event.InputEvent;

public class MailTest extends AbstractTests {

    // Tests
    @Test(priority = 1)
    public void inputUsername() throws InterruptedException {
        loadPageSleep();
        driver.findElement(By.id("identifierId")).sendKeys(email);
        betweenElementSleep();
        driver.findElement(By.id("identifierNext")).click();
    }

    @Test(priority = 2)
    public void clickRecaptcha(){
        // https://stackoverflow.com/a/55264777
        try {
            WebDriverWait wait = new WebDriverWait(driver, randomDuration(4500, 5500));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name,'a-')]")));
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.recaptcha-checkbox"))).click();
            driver.switchTo().defaultContent();
            captcha = true;
        } catch (TimeoutException e) {
            captcha = false;
            throw new SkipException("No captcha detected");
        }

    }

    @Test(priority = 3)
    public void solveRecaptcha() throws InterruptedException {
        loadPageSleep();
        if (captcha) {
            robot.mouseMove(driver.manage().window().getPosition().x + 390, driver.manage().window().getPosition().y + 760);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(25);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } else {
            throw new SkipException("No captcha detected");
        }
    }

    @Test(priority = 4)
    public void continueRecaptcha() throws InterruptedException {
        loadPageSleep();
        if (captcha) {
            driver.findElement(By.xpath("//*[contains(text(), 'Next')]")).click();
        } else {
            throw new SkipException("No captcha detected");
        }
    }

    @Test(priority = 5)
    public void inputPassword() throws InterruptedException{
        loadPageSleep();
        driver.findElement(By.name("Passwd")).sendKeys(password);
        betweenElementSleep();
        driver.findElement(By.xpath("//*[contains(text(), 'Next')]")).click();
    }

    @Test(priority = 6)
    public void searchUnreadMail() throws InterruptedException {
        redirectPageSleep();
        driver.findElement(By.xpath("//input[@placeholder='Search mail']")).sendKeys("is:unread");
        betweenElementSleep();
        driver.findElement(By.xpath("//input[@placeholder='Search mail']")).sendKeys(Keys.RETURN);
    }

    @Test(priority = 7)
    public void retrieveTitle() throws InterruptedException {
        betweenElementSleep();
        title = driver.findElement(By.xpath("//div[@class='y6']/span/span")).getAttribute("innerHTML");
        System.out.println("Latest unread email is titled: " + title);
    }
}
