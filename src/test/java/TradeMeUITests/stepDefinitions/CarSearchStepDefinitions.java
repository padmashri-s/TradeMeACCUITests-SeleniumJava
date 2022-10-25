package TradeMeUITests.stepDefinitions;

import TradeMeUITests.common.ConfigFileReader;
import TradeMeUITests.common.FileReaderManager;
import TradeMeUITests.common.driverFactory.DriverManager;
import TradeMeUITests.pageObjects.CarSearchPage;
import TradeMeUITests.testDataObjects.CarSearchDetails;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
    public static int expectedNumberOfCars;

    public CarSearchStepDefinitions()
    {
        driver = DriverManager.getDriver();

        configFileReader = FileReaderManager.getInstance().getConfigReader();
        environment = configFileReader.getEnvironment().toString().toLowerCase();
        driver = DriverManager.getDriver();
        carSearchPage = new CarSearchPage(driver);
    }
    @Then(": Verify the number of cars returned in the search list for (.*)$")
    public void verifyNumberOfCarsReturnedInTheSearchList(String carmake) throws IOException, InvalidFormatException {
        //CarSearchDetails carSearchDetails = new CarSearchDetails(rowNumber); Can be pulled from spread sheet
        switch (carmake)
        {
            case "Ferrari":
                expectedNumberOfCars = Integer.parseInt(configFileReader.getConfigValue("totalNoOfFerrari"));
                break;
            case "BMW":
                expectedNumberOfCars = Integer.parseInt(configFileReader.getConfigValue("totalNoOfBMW"));
                break;
            case "Mazda":
                expectedNumberOfCars = Integer.parseInt(configFileReader.getConfigValue("totalNoOfMazda"));
                break;
            case "Honda":
                expectedNumberOfCars = Integer.parseInt(configFileReader.getConfigValue("totalNoOfHonda"));
                break;
            default:
                break;
        }
        int actualItems = carSearchPage.totalNumberofCarsDisplayed();
        Assert.assertEquals(actualItems, expectedNumberOfCars);
    }

     @When(": User selects the car make from Car Details Excel Sheet for (.*)$")
    public void userSelectsTheCarMakeForRowNumber(String carmake) throws IOException, InvalidFormatException {
        //CarSearchDetails carSearchDetails = new CarSearchDetails(rowNumber); Can be retrieved from SpreadSheer
        carSearchPage.setCarMake(carmake);
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
        int expectedItems = Integer.parseInt(configFileReader.getConfigValue("carMakesAvailable"));
        int actualItems = carSearchPage.verifyNumberofCarMakes();
        Assert.assertEquals(actualItems, expectedItems);
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
        carSearchPage.GoToHomePage(configFileReader.getApplicationUrl());
    }

    @Then(": User should see Motors link")
    public void verifyMotorsLinkIsAvailable() {
        boolean motorsLinkDisplayed = carSearchPage.verifymotorsLinkDisplayed();
        Assert.assertTrue(motorsLinkDisplayed);

    }
}
