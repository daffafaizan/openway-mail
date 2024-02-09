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
    @FindBy(xpath = "//*[contains(text(), 'Enter one of your 8-digit backup codes')]")
    WebElement selectBackupCodeButton;

    // Text Elements
    @FindBy(xpath = "//h1[@id='headingText']/span")
    WebElement title;
    @FindBy(xpath = "//span[text()='Enter an email or phone number']")
    WebElement emailInputError;

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
        return title.getText();
    }

    // Checking if element is present
    public Boolean nextButtonIsPresent() {
        return nextButton.isDisplayed();
    }
    public Boolean emailInputIsPresent() {
        return emailInput.isDisplayed();
    }
    public Boolean passwordInputIsPresent() {
        return passwordInput.isDisplayed();
    }
    public Boolean emailInputErrorIsPresent() {
        return emailInputError.isDisplayed();
    }

}
