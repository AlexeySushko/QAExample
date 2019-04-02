import Pages.BookingSelectFlightPage;
import Pages.LoginPage;
import Pages.PassengePage;
import Pages.StartPage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.Date;
import java.util.Set;

/**
 * @author Alexey Sushko 16/09/2018
 */


public class TestClass {

    private static WebDriver driver;
    static WebDriverWait wait;
    Action action;

    @BeforeClass
    public static void setUp(){

        File file = new File("F:\\Alexey\\QA_Java\\WizzAir_SushkoAlexey\\src\\drivers\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver" , file.getAbsolutePath());

//        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 3);
        driver.manage().window().maximize();
        driver.get("https://wizzair.com");
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }

    @Test
    public void CheckInToTheFlightBeforeTheAuthorizationWindowAppears(){

        StartPage startPage = new StartPage(driver);
        startPage.inputDeparture("Kiev");
        startPage.choiseAirportDeparture();
        startPage.inputArrival("Copenhagen");
        startPage.choiseAirportArrival();

        String dateStartPage = startPage.dateStartPage();
        String firstPageOrigin = startPage.firstPageOrigin();
        String firstPageDestination = startPage.firstPageDestination();

        startPage.buttonSearchClick();


        //**************************************************************************************************************
        //go to the BookingSelectFlightPage

        BookingSelectFlightPage bookingSelectFlightPage = startPage.goToAboutPage();

        //wait for new window to open
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String popupHandle = "https://wizzair.com/#/booking/select-flight/IEV/CPH/2018-09-17/null/1/0/0/0/null";
        Set<String> windowHandles = driver.getWindowHandles();
        int i = 0;
        for (String handle : windowHandles){
            if (i == 1)
            {
                System.out.println(handle.toString());
                popupHandle = handle;
                break;
            }

            i++;
        }
        driver.switchTo().window(popupHandle);

        bookingSelectFlightPage.closeInformFrame();
        bookingSelectFlightPage.selectTariffCollection();
        String dateBookingSelectFlightPage = bookingSelectFlightPage.dateBookingSelectFlightPage();

        //Verify:
        //flight date;
        Assert.assertEquals(new Date(dateStartPage), new Date(dateBookingSelectFlightPage));

        //arrival/destination points
        Assert.assertEquals(firstPageOrigin+"-"+firstPageDestination, deleteNotAlphabetSymbols(bookingSelectFlightPage.secondPagePlace()));

        //3 options with different prices are displayed including price. Without discont club price
        Assert.assertEquals(true, CompareTariff(bookingSelectFlightPage.getTariffOnTheIndex(0).getText(), bookingSelectFlightPage.getTariffOnTheIndex(1).getText(), bookingSelectFlightPage.getTariffOnTheIndex(2).getText()));

        bookingSelectFlightPage.clickButtonContinue();


        //**************************************************************************************************************
        //go to the PassengePage

        PassengePage passengePage = bookingSelectFlightPage.goToAboutPage();

        passengePage.enterFirstName("Alexey");
        passengePage.enterLastName("Sushko");

        passengePage.clickButtonMale();
        passengePage.selectLuggage();
        passengePage.clickFinalContinueButton();


        //**************************************************************************************************************
        //go to the LoginPage

        LoginPage loginPage = passengePage.goToAboutPage();
        Assert.assertEquals(true, loginPage.loginWindow.isDisplayed());
    }

    /*This method compare place on start page with place on second page*/
    public String deleteNotAlphabetSymbols(String place) {
        String s = place.replace(")", "");
        String s2 = s.replace("(", "");

        return s2.replace(" ", "");
    }

    public Boolean CompareTariff(String tariff1, String tariff2, String tariff3)
    {
        Double tar1 = Double.parseDouble(tariff1.replace("UAH", ""));
        Double tar2 = Double.parseDouble(tariff2.replace("UAH", ""));
        Double tar3 = Double.parseDouble(tariff3.replace("UAH", ""));

        if (tar1 != tar2 & tar1 != tar3 & tar2 != tar3)
        {
            return true;
        }
        return false;
    }




}
