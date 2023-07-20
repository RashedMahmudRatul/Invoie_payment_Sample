package Utility;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.IOException;

public class Utils {
    public static String setEnvVariable(String variable) throws IOException, ConfigurationException {
        PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/Partner_Payment_Key_Token.properties");
        config.setProperty("InvoiceLink", variable);
        config.save();
        return variable;
    }
}
