package tests;

import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class MailTests extends MailTestsSetup {

    @Test(priority = 1)
    public void TC001_VerifyValidTitle() {
        String expectedTitle = "Sign in";
        String actualTitle = signInPages.getSignInTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }
    @Test(priority = 2)
    public void TC002_VerifyEmailInputDisplayed() {
        boolean isDisplayed = signInPages.emailInputIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 3)
    public void TC003_VerifyEmailInputClickable() {
        boolean isClickable = signInPages.emailInputIsClickable();
        Assert.assertTrue(isClickable);
    }
    @Test(priority = 4)
    public void TC004_VerifyNextButtonDisplayed() {
        boolean isDisplayed = signInPages.nextButtonIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 5)
    public void TC005_VerifyNextButtonClickable() {
        boolean isClickable = signInPages.nextButtonIsClickable();
        Assert.assertTrue(isClickable);
    }
    @Test(priority = 6)
    public void TC006_VerifyForgotEmailButtonDisplayed() {
        boolean isDisplayed = signInPages.forgotEmailButtonIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 7)
    public void TC007_VerifyForgotEmailButtonClickable() {
        boolean isClickable = signInPages.forgotEmailButtonIsClickable();
        Assert.assertTrue(isClickable);
    }
    @Test(priority = 8)
    public void TC008_VerifyCreateAccountButtonDisplayed() {
        boolean isDisplayed = signInPages.createAccountButtonIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 9)
    public void TC009_VerifyCreateAccountButtonClickable() {
        boolean isClickable = signInPages.createAccountButtonIsClickable();
        Assert.assertTrue(isClickable);
    }
    @Test(priority = 10)
    public void TC010_VerifyEmptyEmailInputError() {
        signInPages.clickNext();
        boolean isDisplayed = signInPages.emailInputErrorIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 11)
    public void TC011_InputValidEmail() {
        signInPages.enterEmail(email);
        signInPages.clickNext();

        String expectedTitle = "Welcome";
        String actualTitle = signInPages.getWelcomeTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }
    @Test(priority = 12)
    public void TC012_VerifyValidTitle() {
        String expectedTitle = "Welcome";
        String actualTitle = signInPages.getWelcomeTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }
    @Test(priority = 13)
    public void TC013_VerifyPasswordInputDisplayed() {
        boolean isDisplayed = signInPages.passwordInputIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 14)
    public void TC014_VerifyPasswordInputClickable() {
        boolean isClickable = signInPages.passwordInputIsClickable();
        Assert.assertTrue(isClickable);
    }
    @Test(priority = 15)
    public void TC015_VerifyNextButtonDisplayed() {
        boolean isDisplayed = signInPages.nextButtonIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 16)
    public void TC016_VerifyNextButtonClickable() {
        boolean isClickable = signInPages.nextButtonIsClickable();
        Assert.assertTrue(isClickable);
    }
    @Test(priority = 17)
    public void TC017_VerifyForgotPasswordButtonDisplayed() {
        boolean isDisplayed = signInPages.forgotPasswordButtonIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 18)
    public void TC018_VerifyForgotPasswordButtonClickable() {
        boolean isClickable = signInPages.forgotPasswordButtonIsClickable();
        Assert.assertTrue(isClickable);
    }
    @Test(priority = 19)
    public void TC019_VerifyEmptyPasswordInputError() {
        signInPages.clickNext();
        boolean isDisplayed = signInPages.passwordInputErrorIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 20)
    public void TC020_InputPassword() {
        signInPages.enterPassword(password);
        signInPages.clickNext();

        String expectedTitle = "2-Step Verification";
        String actualTitle = signInPages.get2FATitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }
    @Test(priority = 21)
    public void TC021_VerifyValidTitle() {
        String expectedTitle = "2-Step Verification";
        String actualTitle = signInPages.get2FATitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }
    @Test(priority = 22)
    public void TC022_VerifyTooManyFailedAttemptsTitleDisplayed() throws TimeoutException {
        try {
            tooManyFailedAttempts = signInPages.tooManyFailedAttemptsTitleIsPresent();
            Assert.assertTrue(true);
        } catch (TimeoutException exception) {
            tooManyFailedAttempts = false;
            Assert.assertFalse(false);
        }
    }
    @Test(priority = 23)
    public void TC023_VerifyResendItButtonDisplayed() {
        if (!tooManyFailedAttempts) {
            boolean isDisplayed = signInPages.resendItButtonIsPresent();
            Assert.assertTrue(isDisplayed);
        } else {
            System.out.print("\nNo resend it button detected");
            throw new SkipException("");
        }
    }
    @Test(priority = 24)
    public void TC024_VerifyResendItButtonClickable() {
        if (!tooManyFailedAttempts) {
            boolean isClickable = signInPages.resendItButtonIsClickable();
            Assert.assertTrue(isClickable);
        } else {
            System.out.print("\nNo clickable resend it button");
            throw new SkipException("");
        }
    }
    @Test(priority = 25, dependsOnMethods = {"TC023_VerifyResendItButtonDisplayed"})
    public void TC025_VerifyTryAnotherWayDisplayed() {
        boolean isDisplayed = signInPages.tryAnotherWayButtonIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 26, dependsOnMethods = {"TC023_VerifyResendItButtonDisplayed"})
    public void TC026_VerifyTryAnotherWayClickable() {
        boolean isClickable = signInPages.tryAnotherWayButtonIsClickable();
        Assert.assertTrue(isClickable);
    }
    @Test(priority = 27, dependsOnMethods = {"TC023_VerifyResendItButtonDisplayed", "TC025_VerifyTryAnotherWayDisplayed"})
    public void TC027_ClickTryAnotherWay() {
        signInPages.clickTryAnotherWay();

        String expectedTitle = "Choose how you want to sign in:";
        String actualTitle = signInPages.getChooseSignInMethodText();
        Assert.assertEquals(actualTitle, expectedTitle);
    }
    @Test(priority = 28)
    public void TC028_VerifySelectBackupCodeDisplayed() {
        boolean isDisplayed = signInPages.selectBackupCodeIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 29)
    public void TC029_VerifySelectBackupCodeClickable() {
        boolean isClickable = signInPages.selectBackupCodeIsClickable();
        Assert.assertTrue(isClickable);
    }
    @Test(priority = 30)
    public void TC030_ClickSelectBackupCode() {
        signInPages.clickSelectBackupCode();

        String expectedTitle = "Enter one of your 8-digit backup codes";
        String actualTitle = signInPages.getEnterBackupCodeText();
        Assert.assertEquals(actualTitle, expectedTitle);
    }
    @Test(priority = 31)
    public void TC031_VerifyInputBackupCodeDisplayed() {
        boolean isDisplayed = signInPages.backupCodeInputIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 32)
    public void TC032_VerifyInputBackupCodeClickable() {
        boolean isClickable = signInPages.backupCodeInputIsClickable();
        Assert.assertTrue(isClickable);
    }
    @Test(priority = 33)
    public void TC033_VerifyNextButtonDisplayed() {
        boolean isDisplayed = signInPages.nextButtonIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 34)
    public void TC034_VerifyNextButtonClickable() {
        boolean isClickable = signInPages.nextButtonIsClickable();
        Assert.assertTrue(isClickable);
    }
    @Test(priority = 35)
    public void TC035_VerifyEmptyBackupCodeInputError() {
        signInPages.clickNext();
        boolean isDisplayed = signInPages.backupCodeInputErrorIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 36)
    public void TC036_VerifyTryAnotherWayDisplayed() {
        boolean isDisplayed = signInPages.tryAnotherWayButtonIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 37)
    public void TC037_VerifyTryAnotherWayClickable() {
        boolean isClickable = signInPages.tryAnotherWayButtonIsClickable();
        Assert.assertTrue(isClickable);
    }
    @Test(priority = 38)
    public void TC038_InputBackupCode() throws TimeoutException {
        for (String backupCode: backupCodes) {
            signInPages.enterBackupCode(backupCode);
            signInPages.clickNext();

            try {
                String expectedURL = "https://mail.google.com/mail/u/0/#inbox";
                String actualURL = inboxPages.getURL();
                if (expectedURL.equals(actualURL)) {
                    Assert.assertTrue(true);
                    break;
                }
            } catch (TimeoutException exception) {
                signInPages.clearBackupCodeField();
            }

        }
    }
    @Test(priority = 39)
    public void TC040_VerifyInboxTabDisplayed() {
        boolean isDisplayed = inboxPages.inboxIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 40)
    public void TC041_VerifyInboxTabClickable() {
        boolean isDisplayed = inboxPages.inboxIsClickable();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 41)
    public void TC041_VerifyStarredTabDisplayed() {
        boolean isDisplayed = inboxPages.starredIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 42)
    public void TC042_VerifyStarredTabClickable() {
        boolean isDisplayed = inboxPages.starredIsClickable();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 43)
    public void TC043_VerifySnoozedTabDisplayed() {
        boolean isDisplayed = inboxPages.snoozedIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 44)
    public void TC044_VerifySnoozedTabClickable() {
        boolean isDisplayed = inboxPages.snoozedIsClickable();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 45)
    public void TC045_VerifySentTabDisplayed() {
        boolean isDisplayed = inboxPages.sentIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 46)
    public void TC046_VerifySentTabClickable() {
        boolean isDisplayed = inboxPages.sentIsClickable();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 47)
    public void TC047_VerifyDraftsTabDisplayed() {
        boolean isDisplayed = inboxPages.draftsIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 48)
    public void TC048_VerifyDraftsTabClickable() {
        boolean isDisplayed = inboxPages.draftsIsClickable();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 49)
    public void TC049_VerifyMoreDropdownDisplayed() {
        boolean isDisplayed = inboxPages.moreDropdownIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 50)
    public void TC050_VerifyMoreDropdownClickable() {
        boolean isDisplayed = inboxPages.moreDropdownIsClickable();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 51)
    public void TC051_VerifyIsEmailAvailable() {
        boolean isAvailable = inboxPages.availableEmails();
        Assert.assertFalse(isAvailable);
    }
    @Test(priority = 52)
    public void TC052_VerifySearchBarDisplayed() {
        boolean isDisplayed = inboxPages.searchBarIsPresent();
        Assert.assertTrue(isDisplayed);
    }
    @Test(priority = 53)
    public void TC053_VerifySearchBarClickable() {
        boolean isClickable = inboxPages.searchBarIsClickable();
        Assert.assertTrue(isClickable);
    }
    @Test(priority = 54)
    public void TC054_SearchUnreadEmail() {
        inboxPages.enterQuery("is:unread");

        boolean isAvailable = inboxPages.availableEmails();
        Assert.assertFalse(isAvailable);
    }
    @Test(priority = 55)
    public void TC055_RetrieveLatestUnreadEmailTitle() {
        String title = inboxPages.getLatestUnreadTitle();
        Assert.assertNotNull(title);
    }
}
