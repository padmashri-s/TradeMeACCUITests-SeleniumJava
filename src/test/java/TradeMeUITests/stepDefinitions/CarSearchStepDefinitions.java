package TradeMeUITests.stepDefinitions;
import TradeMeUITests.common.BasePage;
import TradeMeUITests.common.ConfigFileReader;
import TradeMeUITests.common.FileReaderManager;
import TradeMeUITests.common.Page;
import TradeMeUITests.common.driverFactory.DriverManager;
import TradeMeUITests.pageObjects.CarSearchPage;
import TradeMeUITests.pageObjects.*;
import TradeMeUITests.testDataObjects.CarSearchDetails;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.IOException;
import java.time.Duration;

public class CarSearchStepDefinitions {

    public static WebDriver driver;
    CarSearchPage carSearchPage;
    CarSearchDetails carSearchDetails;
    ConfigFileReader configFileReader;
    String environment;


    public CarSearchStepDefinitions()
    {
        driver = DriverManager.getDriver();

        configFileReader = FileReaderManager.getInstance().getConfigReader();
        environment = configFileReader.getEnvironment().toString().toLowerCase();
    }
    @Then(": Verify the number of cars returned in the search list")
    public void verifyNumberOfCarsReturnedInTheSearchList() {
        carSearchPage.displayTotalNumberofCars();

    }

    @Then(": Cars are listed in the search list for (.*)$")
    public void carsListedInTheSearchListForRowNumber(int rowNumber) throws IOException, InvalidFormatException {
        CarSearchDetails carSearchDetails = new CarSearchDetails(rowNumber);
        Assert.assertTrue(carSearchPage.checkForCarDetailsPresent(carSearchDetails.GetCarMakes()));
    }

    @When(": User selects the car make from Car Details Excel Sheet for (.*)$")
    public void userSelectsTheCarMakeForRowNumber(int rownumber) throws IOException, InvalidFormatException {
        CarSearchDetails carSearchDetails = new CarSearchDetails(rownumber);
        carSearchPage.setCarMake(carSearchDetails.GetCarMakes());

    }

    @And(": Click on Search button")
    public void clickOnSearchButton() {
        carSearchPage.clickSearchButton();
    }

    @Given(": User at the car search tab")
    public void userAtTheCarSearchTab() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        var expectedUrl = configFileReader.getConfigValue(environment + "MotorSearchPageUrl");
        wait.until(ExpectedConditions.urlContains(expectedUrl));
        Assert.assertTrue(carSearchPage.checkCurrentUrlIs(expectedUrl).equalsIgnoreCase(expectedUrl));
    }

    @Then(": Verify the number of available car makes")
    public void verifyNumberOfAvailableCarMakes() {
        carSearchPage.verifyNumberofCarMakes();
    }

    @When(": User selects the Make dropdown")
    public void userSelectsTheMakeDropdown() {
        carSearchPage.selectCarMake();
    }

    @Then(": User should see the Make dropdown")
    public void verifyMakeDropdownIsAvailable() {
        boolean makeDropdownDisplayed = carSearchPage.verifyMakeDropDownDisplayed();
        Assert.assertTrue(makeDropdownDisplayed);
    }

    @Then(": User landed at the car search tab")
    public void userLandedAtTheCarSearchTab() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        var expectedUrl = configFileReader.getConfigValue(environment + "MotorSearchPageUrl");
        wait.until(ExpectedConditions.urlContains(expectedUrl));
        Assert.assertTrue(carSearchPage.checkCurrentUrlIs(expectedUrl).equalsIgnoreCase(expectedUrl));
    }

    @When(": User selects the Motors tab")
    public void clickMotorTab() {
      carSearchPage.clickMotorSearchTab();
    }

    @Given(": User at the home page")
    public void homePage() {
        driver = DriverManager.getDriver();
        carSearchPage = new CarSearchPage(driver);
        carSearchPage.GoToHomePage(configFileReader.getApplicationUrl());
    }

    @Then(": User should see Motors link")
    public void verifyMotorsLinkIsAvailable() {
        boolean motorsLinkDisplayed = carSearchPage.verifymotorsLinkDisplayed();
        Assert.assertTrue(motorsLinkDisplayed);

    }
}
