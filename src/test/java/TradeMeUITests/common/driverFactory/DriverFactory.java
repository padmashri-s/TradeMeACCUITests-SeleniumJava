package TradeMeUITests.common.driverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

public class DriverFactory {
    public static WebDriver driver;

    synchronized public static WebDriver createChromeDriverInstance() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, false);
        chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, false);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        return driver;
    }

    synchronized public static WebDriver createFirefoxDriverInstance() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, false);
        firefoxOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, false);
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().window().fullscreen();
        return driver;
    }
}
