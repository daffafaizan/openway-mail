package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MailTests extends MailTestsSetup {

    @Test(priority = 1)
    public void TC001_VerifyValidTitle() {
        String expectedTitle = "Sign in";
        String actualTitle = signInPages.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }
    @Test(priority = 2)
    public void TC001_VerifyEmailInputDisplayed() {
        boolean isDisplayed = signInPages.emailInputIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 3)
    public void TC001_VerifyNextButtonDisplayed() {
        boolean isDisplayed = signInPages.nextButtonIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 4)
    public void TC001_VerifyEmptyEmailInputError() {
        signInPages.clickNext();
        boolean isDisplayed = signInPages.emailInputErrorIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 5)
    public void TC001_InputValidEmail() {
        String currentURL = signInPages.getURL();
        signInPages.enterEmail(email);
        signInPages.clickNext();
        String nextURL = signInPages.getURL();

        boolean isValid = currentURL.equals(nextURL);
        Assert.assertFalse(isValid);
    }
    @Test(priority = 6)
    public void TC002_VerifyValidTitle() {
        String expectedTitle = "Welcome";
        String actualTitle = signInPages.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }
    @Test(priority = 7)
    public void TC002_VerifyPasswordInputDisplayed() {
        boolean isDisplayed = signInPages.passwordInputIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 8)
    public void TC002_VerifyNextButtonDisplayed() {
        boolean isDisplayed = signInPages.nextButtonIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 9)
    public void TC002_InputPassword() {
        String currentURL = signInPages.getURL();
        signInPages.enterPassword(password);
        signInPages.clickNext();
        String nextURL = signInPages.getURL();

        boolean isValid = currentURL.equals(nextURL);
        Assert.assertFalse(isValid);
    }
    @Test(priority = 10)
    public void TC003_VerifyValidTitle() {
        String expectedTitle = "2-Step Verification";
        String actualTitle = signInPages.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }
    @Test(priority = 11)
    public void TC003_VerifyTryAnotherWayDisplayed() {
        boolean isDisplayed = signInPages.tryAnotherWayIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 12)
    public void TC003_ClickTryAnotherWay() {
        String currentURL = signInPages.getURL();
        signInPages.clickTryAnotherWay();
        String nextURL = signInPages.getURL();

        boolean isValid = currentURL.equals(nextURL);
        Assert.assertFalse(isValid);
    }
    @Test(priority = 13)
    public void TC004_VerifySelectBackupCodeDisplayed() {
        boolean isDisplayed = signInPages.selectBackupCodeIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 14)
    public void TC004_ClickSelectBackupCode() {
        signInPages.clickSelectBackupCode();
    }
    @Test(priority = 15)
    public void TC005_VerifyInputBackupCodeDisplayed() {
        boolean isDisplayed = signInPages.backupCodeInputIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 16)
    public void TC005_VerifyNextButtonDisplayed() {
        boolean isDisplayed = signInPages.nextButtonIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 17)
    public void TC005_VerifyEmptyBackupCodeInputError() {
        signInPages.clickNext();
        boolean isDisplayed = signInPages.backupCodeInputErrorIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 18)
    public void TC005_InputBackupCode() {
        String currentURL = signInPages.getURL();
        signInPages.enterBackupCode(backupCode);
        signInPages.clickNext();
        String nextURL = inboxPages.getUrl();

        boolean isValid = currentURL.equals(nextURL);
        Assert.assertFalse(isValid);
    }
    @Test(priority = 19)
    public void TC006_VerifySearchBarDisplayed() {
        boolean isDisplayed = inboxPages.searchBarIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 20)
    public void TC006_SearchUnreadEmail() {
        inboxPages.enterQuery("is:unread");
    }
    @Test(priority = 21)
    public void TC006_RetrieveLatestUnreadEmailTitle() {
        inboxPages.getLatestUnreadTitle();
    }
}
