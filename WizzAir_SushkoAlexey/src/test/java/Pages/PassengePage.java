package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Alexey Sushko 16/09/2018
 */

public class PassengePage {

    private WebDriver driver;

    @FindBy(id = "passenger-first-name-0")
    public WebElement firstName;

    @FindBy(id = "passenger-last-name-0")
    public WebElement lastName;

    @FindBy(xpath = "//label[@for='passenger-gender-0-male']")
    public WebElement buttonMale;

    @FindBy(css = ".rf-switch__slogan__wrap :nth-of-type(1)")
    public WebElement buttonLuggage;

    @FindBy(id = "passengers-continue-btn")
    public WebElement buttonFinalContinue;

    public PassengePage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public LoginPage goToAboutPage()
    {
        return new LoginPage(driver);
    }


    public void enterFirstName(String textFirstName) {
        firstName.sendKeys(textFirstName);
    }

    public void enterLastName(String textLastName) {
        lastName.sendKeys(textLastName);
    }

    public void clickButtonMale() {
        buttonMale.click();
    }

    public void selectLuggage() {
        buttonLuggage.click();
    }

    public void clickFinalContinueButton() {
        buttonFinalContinue.click();
    }
}
