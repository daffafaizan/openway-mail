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
        Boolean isDisplayed = signInPages.emailInputIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 3)
    public void TC001_VerifyNextButtonDisplayed() {
        Boolean isDisplayed = signInPages.nextButtonIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 4)
    public void TC001_VerifyEmptyEmailInputError() {
        signInPages.clickNext();
        Boolean isDisplayed = signInPages.emailInputErrorIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 5)
    public void TC001_InputValidEmail() {
        signInPages.enterEmail(email);
        signInPages.clickNext();
    }
    @Test(priority = 6)
    public void TC002_VerifyValidTitle() {
        String expectedTitle = "Welcome";
        String actualTitle = signInPages.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }
    @Test(priority = 7)
    public void TC001_VerifyPasswordInputDisplayed() {
        Boolean isDisplayed = signInPages.passwordInputIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 8)
    public void TC002_VerifyNextButtonDisplayed() {
        Boolean isDisplayed = signInPages.nextButtonIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 9)
    public void TC002_InputPassword() {
        signInPages.enterPassword(password);
        signInPages.clickNext();
    }
    @Test(priority = 10)
    public void TC003_VerifyValidTitle() {
        String expectedTitle = "2-Step Verification";
        String actualTitle = signInPages.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }
    @Test(priority = 11)
    public void TC003_VerifyTryAnotherWayDisplayed() {
        Boolean isDisplayed = signInPages.tryAnotherWayIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 12)
    public void TC003_ClickTryAnotherWay() {
        signInPages.clickTryAnotherWay();
    }
    @Test(priority = 13)
    public void TC004_VerifySelectBackupCodeDisplayed() {
        Boolean isDisplayed = signInPages.selectBackupCodeIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 14)
    public void TC004_ClickSelectBackupCode() {
        signInPages.clickSelectBackupCode();
    }
    @Test(priority = 15)
    public void TC005_VerifyInputBackupCodeDisplayed() {
        Boolean isDisplayed = signInPages.backupCodeInputIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 16)
    public void TC005_VerifyNextButtonDisplayed() {
        Boolean isDisplayed = signInPages.nextButtonIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 17)
    public void TC005_VerifyEmptyBackupCodeInputError() {
        signInPages.clickNext();
        Boolean isDisplayed = signInPages.backupCodeInputErrorIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 18)
    public void TC005_InputBackupCode() {
        signInPages.enterBackupCode(backupCode);
        signInPages.clickNext();
    }
    @Test(priority = 19)
    public void TC006_VerifySearchBarDisplayed() {
        Boolean isDisplayed = inboxPages.searchBarIsPresent();
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
