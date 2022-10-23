package TradeMeUITests.common;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerFactory {

    private static boolean root = false;

    public static Logger getLogger(Class classname) {
        if(root)
            return Logger.getLogger(classname);

        String localDir = System.getProperty("user.dir");

        PropertyConfigurator.configure(localDir+ "/target/test-classes/configs/log4j.properties");
        root = true;
        return Logger.getLogger(classname);
    }
}
