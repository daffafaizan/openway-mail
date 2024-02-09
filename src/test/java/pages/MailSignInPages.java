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
    @FindBy(xpath = "//*[contains(text(), 'Next')]")
    WebElement nextButton;
    @FindBys({@FindBy(xpath = "//span[text()='Try another way']"), @FindBy(xpath = "./ancestor::button")})
    WebElement tryAnotherWayButton;
    @FindBys({@FindBy(xpath = "//span[text()='Resend it']"), @FindBy(xpath = "./ancestor::button")})
    WebElement resendItButton;
    @FindBy(xpath = "//*[contains(text(), 'Enter one of your 8-digit backup codes')]")
    WebElement selectBackupCodeButton;

    // Text Elements
    @FindBy(xpath = "//h1[@id='headingText']/span")
    WebElement title;
    @FindBy(xpath = "//div[text()='Enter an email or phone number']")
    WebElement emailInputError;
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

    // Clicking
    public void waitClick(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }
    public void clickTryAnotherWay() {
        wait.until(ExpectedConditions.elementToBeClickable(resendItButton));
        waitClick(tryAnotherWayButton);
    }
    public void clickSelectBackupCode() {
        waitClick(selectBackupCodeButton);
    }
    public void clickNext() {
        waitClick(nextButton);
    }

    // Grabbing text
    public String getTitle() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        return title.getText();
    }
    public String getURL() {
        return driver.getCurrentUrl();
    }

    // Checking if element is present
    public boolean isPresent(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
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
    public boolean tryAnotherWayIsPresent() {
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
    public boolean backupCodeInputErrorIsPresent() {
        return isPresent(backupCodeInputError);
    }

}
