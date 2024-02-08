package tests;

import org.testng.annotations.Test;
import pages.SignInPages;

public class SignInTests extends BaseTest {
    SignInPages signInPages;

    public SignInTests() {
        this.signInPages = new SignInPages(driver);
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
    }
}
