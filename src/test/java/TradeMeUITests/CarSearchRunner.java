package TradeMeUITests;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions (features = {"classpath:features/CarSearch.feature"},glue = {"classpath:TradeMeUITests.stepDefinitions"},
plugin = {"pretty", "html:target/cucumber-reports/CarSearchRunnerReport.html","json:target/cucumber-reports/CarSearchRunnerReport.json"},
publish = true)

public class CarSearchRunner extends AbstractTestNGCucumberTests{
}
