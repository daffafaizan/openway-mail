package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MailSignInPages {

    WebDriver driver;
    WebDriverWait wait;

    // Input Elements
    @FindBy(id = "identifierId")
    WebElement emailInput;
    @FindBy(name = "Passwd")
    WebElement passwordInput;
    @FindBy(id = "backupCodePin")
    WebElement backupCodeInput;

    // Button Elements
    @FindBy(xpath = "//button[text()='Forgot email?']")
    WebElement forgotEmailButton;
    @FindBys({@FindBy(xpath = "//span[text()='Forgot password?']"), @FindBy(xpath = "./ancestor::button")})
    WebElement forgotPasswordButton;
    @FindBys({@FindBy(xpath = "//span[text()='Create account']"), @FindBy(xpath = "./ancestor::button")})
    WebElement createAccountButton;
    @FindBy(xpath = "//*[contains(text(), 'Next')]")
    WebElement nextButton;
    @FindBys({@FindBy(xpath = "//span[text()='Try another way']"), @FindBy(xpath = "./ancestor::button")})
    WebElement tryAnotherWayButton;
    @FindBys({@FindBy(xpath = "//span[text()='Resend it']"), @FindBy(xpath = "./ancestor::button")})
    WebElement resendItButton;
    @FindBy(xpath = "//*[contains(text(), 'Enter one of your 8-digit backup codes')]")
    WebElement selectBackupCodeButton;

    // Text Elements
    @FindBy(xpath = "//h1[@id='headingText']/span[text()='Sign in']")
    WebElement signInTitle;
    @FindBy(xpath = "//h1[@id='headingText']/span[text()='Welcome']")
    WebElement welcomeTitle;
    @FindBy(xpath = "//h1[@id='headingText']/span[text()='2-Step Verification']")
    WebElement twoFactorAuthenticationTitle;
    @FindBy(xpath = "//span[text()='Too many failed attempts']")
    WebElement tooManyFailedAttemptsTitle;
    @FindBy(xpath = "//span[text()='Choose how you want to sign in:']")
    WebElement chooseSignInMethodText;
    @FindBy(xpath = "//div[text()='Enter one of your 8-digit backup codes']")
    WebElement enterBackupCodeText;
    @FindBy(xpath = "//div[text()='Enter an email or phone number']")
    WebElement emailInputError;
    @FindBy(xpath = "//span[text()='Enter a password']")
    WebElement passwordInputError;
    @FindBy(xpath = "//div[text()='Enter a code']")
    WebElement backupCodeInputError;

    public MailSignInPages(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofMillis(10000));
        PageFactory.initElements(driver, this);
    }

    // Entering input
    public void waitEnterInput(WebElement element, String input) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(input);
    }
    public void enterEmail(String email) {
        waitEnterInput(emailInput, email);
    }
    public void enterPassword(String password) {
        waitEnterInput(passwordInput, password);
    }
    public void enterBackupCode(String backupCode) {
        waitEnterInput(backupCodeInput, backupCode);
    }
    public void clearBackupCodeField() {
        backupCodeInput.clear();
    }

    // Clicking
    public void waitClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }
    public void clickTryAnotherWay() {
        waitClick(tryAnotherWayButton);
    }
    public void clickSelectBackupCode() {
        waitClick(selectBackupCodeButton);
    }
    public void clickNext() {
        waitClick(nextButton);
    }

    // Grabbing text
    public String textVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }
    public String getSignInTitle() {
        return textVisible(signInTitle);
    }
    public String getWelcomeTitle() {
        return textVisible(welcomeTitle);
    }
    public String get2FATitle() {
        return textVisible(twoFactorAuthenticationTitle);
    }
    public String getChooseSignInMethodText() {
        return textVisible(chooseSignInMethodText);
    }
    public String getEnterBackupCodeText() {
        return textVisible(enterBackupCodeText);
    }

    // Checking if element is present
    public boolean isPresent(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }
    public boolean tooManyFailedAttemptsTitleIsPresent() {
        return isPresent(tooManyFailedAttemptsTitle);
    }
    public boolean forgotEmailButtonIsPresent() {
        return isPresent(forgotEmailButton);
    }
    public boolean forgotPasswordButtonIsPresent() {
        return isPresent(forgotPasswordButton);
    }
    public boolean createAccountButtonIsPresent() {
        return isPresent(createAccountButton);
    }
    public boolean nextButtonIsPresent() {
        return isPresent(nextButton);
    }
    public boolean emailInputIsPresent() {
        return isPresent(emailInput);
    }
    public boolean passwordInputIsPresent() {
        return isPresent(passwordInput);
    }
    public boolean resendItButtonIsPresent() {
        return isPresent(resendItButton);
    }
    public boolean tryAnotherWayButtonIsPresent() {
        return isPresent(tryAnotherWayButton);
    }
    public boolean selectBackupCodeIsPresent() {
        return isPresent(selectBackupCodeButton);
    }
    public boolean backupCodeInputIsPresent() {
        return isPresent(backupCodeInput);
    }
    public boolean emailInputErrorIsPresent() {
        return isPresent(emailInputError);
    }
    public boolean passwordInputErrorIsPresent() {
        return isPresent(passwordInputError);
    }
    public boolean backupCodeInputErrorIsPresent() {
        return isPresent(backupCodeInputError);
    }

    // Checking if element is clickable
    public boolean isClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element)).isDisplayed();
    }
    public boolean forgotEmailButtonIsClickable() {
        return isClickable(forgotEmailButton);
    }
    public boolean forgotPasswordButtonIsClickable() {
        return isClickable(forgotPasswordButton);
    }
    public boolean createAccountButtonIsClickable() {
        return isClickable(createAccountButton);
    }
    public boolean nextButtonIsClickable() {
        return isClickable(nextButton);
    }
    public boolean emailInputIsClickable() {
        return isClickable(emailInput);
    }
    public boolean passwordInputIsClickable() {
        return isClickable(passwordInput);
    }
    public boolean resendItButtonIsClickable() {
        return isClickable(resendItButton);
    }
    public boolean tryAnotherWayButtonIsClickable() {
        return isClickable(tryAnotherWayButton);
    }
    public boolean selectBackupCodeIsClickable() {
        return isClickable(selectBackupCodeButton);
    }
    public boolean backupCodeInputIsClickable() {
        return isClickable(backupCodeInput);
    }
}
