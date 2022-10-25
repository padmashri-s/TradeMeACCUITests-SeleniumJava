package TradeMeUITests.pageObjects;
import TradeMeUITests.common.BasePage;
import TradeMeUITests.common.LoggerFactory;
import org.openqa.selenium.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CarSearchPage extends BasePage{
    By motorSearchLink = By.xpath("/html/body/tm-root/div[1]/main/div/tm-dynamic-homepage/tm-homepage-search-header/nav/div[2]/ul/li[3]/a");

    By carMakeDropDown = By.xpath("//select[@name=\"selectedMake\"]");
    By searchButton = By.xpath("//button[@type=\"submit\"]");
    By carMakeHeading = By.xpath("/html/body/tm-root/div[1]/main/div/tm-motors-search-results/tm-search-header/div/div/tm-search-header-heading/h1");
    By noCarsReturunedHeading = By.xpath("/html/body/tm-root/div[1]/main/div/tm-motors-search-results/div/div/div[1]/div/tm-search-header-result-count/h3");
    private final Logger log = LoggerFactory.getLogger(BasePage.class);
    public CarSearchPage(WebDriver driver)
    {
        super(driver);
    }

    public void clickMotorSearchTab()
    {
        getClickableElement(motorSearchLink).click();
    }
    public int totalCars;
    public void selectCarMake()
    {
       // getClickableElement(this.carMakeDropDown).click();
        Select makeDropDown = new Select(getElement(this.carMakeDropDown));
        List<WebElement> optionsCount = makeDropDown.getOptions();
        System.out.println(optionsCount);
    }

    public void clickSearchButton()
    {
        getClickableElement(this.searchButton).click();
    }
    public void GoToHomePage(String url) {
        log.info("Launching home page");
        driver.get(url);
        log.info("Home page title is "+ driver.getTitle());
    }
    public boolean verifymotorsLinkDisplayed() {
        return (getElement(this.motorSearchLink).isDisplayed());
    }
    public boolean verifyMakeDropDownDisplayed() {
        return (getElement(this.carMakeDropDown).isDisplayed());
    }

    public String checkCurrentUrlIs(String motorPageUrl) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains(motorPageUrl));
        return driver.getCurrentUrl();
    }
    public int verifyNumberofCarMakes() {
        Select carMakeDropDown = new Select(getElement(this.carMakeDropDown));
        List<WebElement> optionsCount = carMakeDropDown.getOptions();
        return optionsCount.size();
    }

    public void setCarMake(String carMake)
    {
        Select makeDropDown = new Select(getElement(this.carMakeDropDown));
        makeDropDown.selectByVisibleText(carMake);
    }
    public boolean checkForCarDetailsPresent(String carMake)
    {
        return getElement(this.carMakeHeading).getText().contains(carMake);
    }

    public int totalNumberofCarsDisplayed()
    {
      String totalNumberofCars = getElement(this.noCarsReturunedHeading).getText().toString();
      var totalCars = Integer.parseInt(totalNumberofCars.replaceAll("[^\\\\0-9+]", ""));

      return totalCars;
    }
}
