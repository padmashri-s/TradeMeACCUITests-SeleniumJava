package TradeMeUITests.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import TradeMeUITests.common.DriverType;
import TradeMeUITests.common.FileReaderManager;
import TradeMeUITests.common.driverFactory.DriverFactory;
import TradeMeUITests.common.driverFactory.DriverManager;

import java.io.IOException;

public class Hooks {

    WebDriver driver;

    @Before(order=1)
    public void before() throws IOException, InvalidFormatException {

        var driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
        if(driverType == DriverType.CHROME){
            driver = DriverFactory.createChromeDriverInstance();
            DriverManager.setDriver(driver);
        }

        else if (driverType == DriverType.FIREFOX){
            driver = DriverFactory.createFirefoxDriverInstance();
            DriverManager.setDriver(driver);
        }
        else{
            System.out.println("No browser provided in testng.xml file");
        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @AfterStep
    public void addScreenshot(Scenario scenario){
        //if(scenario.isFailed()) {
        final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());
        //}
    }
}

