package mailbox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Scanner;

public class MailCheck {
    WebDriver driver;
    String url;
    String email;
    String password;

    public MailCheck() {
        Scanner scanner = new Scanner(System.in);

        url = "https://mail.google.com/mail/";
        email = "avgautomationenjoyer@gmail.com";
        password = "avgautomationenjoyer123";
    }

    @BeforeClass
    public void initialize() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    @AfterClass
    public void terminate() {
    }

    @Test
    public void inputUsername() throws InterruptedException {
        driver.findElement(By.id("identifierId")).sendKeys(email);
        Thread.sleep(5000);
        driver.findElement(By.id("identifierNext")).click();
    }
}
