package TradeMeUITests.testDataObjects;
import TradeMeUITests.common.ExcelReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CarSearchDetails {
    ExcelReader externalData;
    List<Map<String, String>> carSearchDetails;
    int rownumber = 1;

    public CarSearchDetails(int rowNumber) throws IOException, InvalidFormatException
    {
        rownumber = rowNumber;
        String localDir = System.getProperty("user.dir");
        externalData = new ExcelReader();
        carSearchDetails = externalData.getData(localDir + "/src/test/resources/configs/TestData.xlsx", "CarDetails");
    }
    public String GetCarMakes() {
        return carSearchDetails.get(rownumber).get("CarMake");
    }

}
