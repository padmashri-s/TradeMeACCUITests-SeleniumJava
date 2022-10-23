#Download this project to your local repository#

# Description #
1) The test automation is based on TestNG framework and Selenium Web Driver framework for Java.
2) Behaviour Driven Development framework using Cucumber
3) IDE used is IntelliJ

# Pre-requisites #
1) IntelliJ/Eclipse needs to be installed
2) TestNG plugin needs to be installed

# Running the application #
## Update your test data ##
1) you will need to update the test environment and browser details in [configuration file](src/test/resources/configs/Configuration.properties)
2) Update Test Data for each functionality in individual sheets in [TestData.xlsx](src/test/resources/configs/TestData.xlsx)

## To Run ##
### For regression tests along with report and screenshots: (recommended for regression tests)
1. Navigate to open the **"CarSearchRunner"** file located at [CarSearchRunner.java](src/test/java/TradeMeUITests/CarSearchRunner.java)
2. Select the file, right click and select the option "Run"
3. Once run you can verify your test reports
   or
4. Navigate to open the **"CarSearchTesting.xml"** file located at [CarSearchTesting.xml](target/CarSearchTesting.xml)
5. Select the file, right click and select the option "Run"
6. Once run you can verify your test reports

### For regression tests without reports and screenshots:
1. Navigate to open the **"CarSearch.feature"** file located at [CarSearch.feature](src/test/resources/features/CarSearch.feature)

## Verify your Test reports with screenshots ##
1. Navigate to open the report located in [CarSearchRunnerReport.html](target/cucumberReports)
2. Select the file, right click and select the option "Open In" -> Browser -> Chrome
