package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.MailInboxPages;
import pages.MailSignInPages;

import java.util.Collections;

public class MailTests extends MailTestsSetup {

    @Test
    public void TC001_VerifyValidTitle() {
        String expectedTitle = "Sign in";
        String actualTitle = signInPages.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }
    @Test(dependsOnMethods = {"TC001_VerifyValidTitle"})
    public void TC001_VerifyEmailInputDisplayed() {
        Boolean isDisplayed = signInPages.emailInputIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(dependsOnMethods = {"TC001_VerifyValidTitle"})
    public void TC001_VerifyNextButtonDisplayed() {
        Boolean isDisplayed = signInPages.nextButtonIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(dependsOnMethods = {"TC001_VerifyValidTitle"})
    public void TC001_VerifyEmptyEmailInputError() {
        signInPages.clickNext();
        Boolean isDisplayed = signInPages.emailInputErrorIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(dependsOnMethods = {"TC001_VerifyValidTitle"})
    public void TC001_InputValidEmail() {
        signInPages.enterEmail(email);
        signInPages.clickNext();

        String expectedTitle = "Welcome";
        String actualTitle = signInPages.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }
    @Test
    public void TC002_VerifyValidTitle() {
        String expectedTitle = "Welcome";
        String actualTitle = signInPages.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }
    @Test(dependsOnMethods = {"TC002_VerifyValidTitle"})
    public void TC001_VerifyPasswordInputDisplayed() {
        Boolean isDisplayed = signInPages.passwordInputIsPresent();
        Assert.assertTrue(isDisplayed);
    }

    @Test(dependsOnMethods = {"TC002_VerifyValidTitle"})
    public void TC002_VerifyNextButtonDisplayed() {
        Boolean isDisplayed = signInPages.nextButtonIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(dependsOnMethods = {"TC002_VerifyValidTitle"})
    public void TC002_InputPassword() {
        signInPages.enterPassword(password);
        signInPages.clickNext();

        String expectedTitle = "2-Step Verification";
        String actualTitle = signInPages.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }
    @Test
    public void TC003_2FA() {
        signInPages.clickTryAnotherWay();
        signInPages.clickSelectBackupCode();
        signInPages.enterBackupCode(backupCode);
        signInPages.clickNext();

        String expectedUrl = "https://mail.google.com/mail/u/0/#inbox";
        String actualUrl = inboxPages.getUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test
    public void TC004_RetrieveTitle() {
        inboxPages.enterQuery("is:unread");
        inboxPages.getLatestUnreadTitle();
    }
}
