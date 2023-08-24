package Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseCredentials {
    static Properties prop = new Properties();
    static FileInputStream file;
    public static String invoiceLink() throws Exception {
        file = new FileInputStream("./src/test/resources/User_Payment_Key_Token.properties");
        prop.load(file);
        String invoiceLink = prop.getProperty("InvoiceLink");
        return invoiceLink;
    }
    public static String partnerEnv() throws Exception {

        Properties prop = new Properties();
        FileInputStream file;
        file = new FileInputStream("./src/test/resources/User_Payment_Key_Token.properties");
        prop.load(file);
        String environment = prop.getProperty("Environment");
        return environment;
    }
    public static String partnerOrigin() throws Exception {

        Properties prop = new Properties();
        FileInputStream file;
        file = new FileInputStream("./src/test/resources/User_Payment_Key_Token.properties");
        prop.load(file);
        String origin = prop.getProperty("Origin");
        return origin;
    }
    public static String invoiceCurrency() throws Exception {

        Properties prop = new Properties();
        FileInputStream file;
        file = new FileInputStream("./src/test/resources/User_Payment_Key_Token.properties");
        prop.load(file);
        String currency = prop.getProperty("Currency");
        return currency;
    }

    public static String invoiceAmount() throws Exception {

        Properties prop = new Properties();
        FileInputStream file;
        file = new FileInputStream("./src/test/resources/User_Payment_Key_Token.properties");
        prop.load(file);
        String Amount = prop.getProperty("Amount");
        return Amount;
    }

    public static String pgwKey() throws Exception {
        String Key;
        Properties prop = new Properties();
        FileInputStream file;
        file = new FileInputStream("./src/test/resources/User_Payment_Key_Token.properties");
        prop.load(file);
        if (partnerEnv().equals("dev") && partnerOrigin().equals("US")){
            Key = prop.getProperty("Key_us_dev");
            return Key;
        } else if (partnerEnv().equals("dev") && partnerOrigin().equals("BD")) {
            Key = prop.getProperty("Key_dev");
            return Key;
        }
        else if (partnerEnv().equals("tst") && partnerOrigin().equals("US")) {
            Key = prop.getProperty("Key_us_tst");
            return Key;
        }
        else if (partnerEnv().equals("tst") && partnerOrigin().equals("BD")) {
            Key = prop.getProperty("Key_tst");
            return Key;
        }
        else
            return "";
    }

    public static String pgwToken() throws Exception {
        String Token;
        file = new FileInputStream("./src/test/resources/User_Payment_Key_Token.properties");
        prop.load(file);

        if (partnerEnv().equals("dev") && partnerOrigin().equals("US")){
            Token = prop.getProperty("Token_us_dev");
            return Token;
        } else if (partnerEnv().equals("dev") && partnerOrigin().equals("BD")) {
            Token = prop.getProperty("Token_dev");
            return Token;
        }
        else if (partnerEnv().equals("tst") && partnerOrigin().equals("US")) {
            Token = prop.getProperty("Token_us_tst");
            return Token;
        }
        else if (partnerEnv().equals("tst") && partnerOrigin().equals("BD")) {
            Token = prop.getProperty("Token_tst");
            return Token;
        }
        else
            return "";
    }
    public static String browserName() throws IOException, FileNotFoundException {
        file = new FileInputStream("./src/test/resources/configuration.properties");
        prop.load(file);
        String bsrName = prop.getProperty("Browser");
        return bsrName;

    }

}
