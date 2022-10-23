package TradeMeUITests.common.driverFactory;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static ThreadLocal<WebDriver> Driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return Driver.get();
    }

    public static void setDriver(WebDriver driver) {
        Driver.set(driver);
    }
}
