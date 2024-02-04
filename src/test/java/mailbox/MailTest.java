package mailbox;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

    @Test(priority = 2, enabled = false)
    public void clickRecaptcha(){
        // https://stackoverflow.com/a/55264777
        WebDriverWait wait = new WebDriverWait(driver, randomDuration(4500, 5500));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name,'a-')]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.recaptcha-checkbox"))).click();
        driver.switchTo().defaultContent();
    }

    @Test(priority = 3, enabled = false)
    public void solveRecaptcha() throws InterruptedException {
        loadPageSleep();
        robot.mouseMove(driver.manage().window().getPosition().x + 390, driver.manage().window().getPosition().y + 760);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(25);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    @Test(priority = 4, enabled = false)
    public void continueRecaptcha() throws InterruptedException {
        loadPageSleep();
        driver.findElement(By.xpath("//*[contains(text(), 'Next')]")).click();
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
        loadPageSleep();
        driver.findElement(By.xpath("//input[contains(@placeholder, 'Search mail')]")).sendKeys("is:unread");
        driver.findElement(By.xpath("//input[contains(@placeholder, 'Search mail')]")).sendKeys(Keys.RETURN);
    }}
