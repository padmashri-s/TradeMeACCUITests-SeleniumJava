package TradeMeUITests.common;

public class JsonDataReader {
    private String filePath;

    public JsonDataReader(String filename){
        filePath = FileReaderManager.getInstance().getConfigReader().getTestDataResourcePath()+filename;
    }

}
