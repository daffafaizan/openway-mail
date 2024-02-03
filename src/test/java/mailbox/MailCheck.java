package mailbox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class MailCheck {
    WebDriver driver;
    String url;

    public MailCheck() {
        url = "https://daffafaizan.com";
    }

    @BeforeClass
    public void initialize() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    @AfterClass
    public void terminate() {
        driver.close();
    }
}
