package TradeMeUITests.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract  class Page {

    public WebDriver driver;
    public Wait wait;
    FileReaderManager fileReaderManager  = FileReaderManager.getInstance();

    public Page(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(60));
    }

    public abstract String getPageTitle();

    public abstract WebElement getElement(By locator);

    public abstract WebElement getClickableElement(By locator);

    public abstract void waitForElementClickable(By locator);

    public abstract void actionByScriptExecutor(String locator, String action);

    public <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) {
        try {
            return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
