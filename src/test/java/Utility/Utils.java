package Utility;


import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static String setEnvVariable(String variable) throws IOException, ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/Partner_Payment_Key_Token.properties");
        config.setProperty("InvoiceLink", variable);
        config.save();
        return variable;
    }
    public static LocalDateTime dateTime(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        return currentDateTime;
    }
}
