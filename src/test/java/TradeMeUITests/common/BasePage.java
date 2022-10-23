package TradeMeUITests.common;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@SuppressWarnings("rawtypes")
public class BasePage extends Page {

	private final Logger log = LoggerFactory.getLogger(BasePage.class);

	By alert = By.xpath("//*[@role ='alert']");

	By progressBar = By.className("ng-progress-bar");

	public BasePage(WebDriver driver) {
		super(driver);
	}

	@Override
	public String getPageTitle() {
		return driver.getTitle();
	}

	@Override
	public WebElement getElement(By locator) {
		try{
			Thread.sleep(150); //This delay is specifically put to resolve the login popup window as sometimes they're brittle
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return driver.findElement(locator);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<WebElement> getElements(By locator) {
		try{
			Thread.sleep(100);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return driver.findElements(locator);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public WebElement getClickableElement(By locator) {
		try{
			Thread.sleep(500);
			waitForElementClickable(locator);
			return driver.findElement(locator);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void waitForElementClickable(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	@Override
	public void actionByScriptExecutor(String locator, String action) {
		((JavascriptExecutor) driver).executeScript(String.format("document.querySelector('%s').%s()", locator, action));
	}

	public boolean checkForAlertNotPresent(){
		if (getElements(this.alert).size() !=0){
			return false;
		}else{
			return true;
		}
	}

	public void waitUntilProgressBarIsNotActive() {
		String status = getProgressBarStatus();
		while(status.equals("true")){
			status = getProgressBarStatus();
		}
	}

	public String getProgressBarStatus() {
		return getElement(this.progressBar).getAttribute("active");
	}


}
