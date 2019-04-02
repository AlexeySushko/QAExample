package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author Alexey Sushko 16/09/2018
 */

public class StartPage {

    private WebDriver driver;

    @FindBy(id="search-departure-station")
    private WebElement departure;

    @FindBy(css = ".locations-container__location")
    private WebElement choiseAirportDeparture;

    @FindBy(id = "search-arrival-station")
    private WebElement arrival;

    @FindBy(css = ".locations-container__location")
    private WebElement choiseAirportArrival;

    @FindBy(id = "search-departure-date")
    private WebElement useData;

    @FindBy(id = "search-departure-station")
    private WebElement FirstPageOrigin;

    @FindBy(id = "search-arrival-station")
    private WebElement FirstPageDestination;

    @FindBy(xpath = "//button[@tabindex='2']")
    private WebElement buttonSearch;

    public StartPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
        driver.manage().timeouts().pageLoadTimeout(70, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(70, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
    }

    public void inputDeparture(String text) {
        departure.clear();
        departure.sendKeys(text);
    }

    public void choiseAirportDeparture(){
        choiseAirportDeparture.click();
    }

    public void inputArrival(String text) {
        arrival.clear();
        arrival.sendKeys(text);
    }

    public void choiseAirportArrival(){
        choiseAirportArrival.click();
    }

    public String dateStartPage() {
        return useData.getText();
    }

    public String firstPageOrigin() {
        return getTrimValue(FirstPageOrigin);
    }

    public String firstPageDestination() {
        return getTrimValue(FirstPageDestination);
    }

    public String getTrimValue(WebElement el)
    {
        return el. getAttribute("value").trim();
    }

    public void buttonSearchClick() {
        buttonSearch.click();
    }

    public BookingSelectFlightPage goToAboutPage()
    {
        return new BookingSelectFlightPage(driver);
    }
}
