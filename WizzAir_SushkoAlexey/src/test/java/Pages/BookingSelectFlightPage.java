package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * @author Alexey Sushko 16/09/2018
 */

public class BookingSelectFlightPage {

    private WebDriver driver;

    @FindBy(css = ".cookie-policy__button")
    private WebElement buttonClose;

    @FindBy(xpath = "//div[@class='rf-fare']/div[1]/label[1]")//  //div[@class='rf-fare']/div[1]
    private List<WebElement> tariffCollection;

    @FindBy(id = "flight-select-continue-btn")
    private WebElement buttonContinue;

    @FindBy(xpath = "//time[@class='heading heading--4 date']")
    private WebElement dateBookingSelectFlightPage;

    @FindBy(css = ".flight-info__stations")
    private WebElement secondPagePlace;

    @FindBy(xpath = "//div[@class='rf-fare__price']")
    private List<WebElement> tariffs;

    @FindBy(id = "fare-selector-return")
    private WebElement flightsAreNotDisplayed;

    public BookingSelectFlightPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
//        driver.manage().timeouts().pageLoadTimeout(70, TimeUnit.SECONDS);
//        driver.manage().timeouts().setScriptTimeout(70, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
    }

    public void closeInformFrame() {

        new WebDriverWait(driver, 70).until(ExpectedConditions.elementToBeClickable(buttonClose));
        buttonClose.click();
    }

    public void selectTariffCollection(){
        if(tariffCollection.size()>0) {
            tariffCollection.get(0).click();
        }
    }

    public String dateBookingSelectFlightPage(){
        return dateBookingSelectFlightPage.getText();
    }

    public String secondPagePlace(){
        return secondPagePlace.getText();
    }

    public WebElement getTariffOnTheIndex(int index){
        return tariffs.get(index);
    }

    public void clickButtonContinue() {
        buttonContinue.click();
    }

    public PassengePage goToAboutPage()
    {
        return new PassengePage(driver);
    }



}
