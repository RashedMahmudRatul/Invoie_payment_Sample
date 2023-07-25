package Utility;

import java.io.FileInputStream;
import java.util.Properties;

public class BaseCredentials {

    public static String invoiceLink() throws Exception {

        Properties prop = new Properties();
        FileInputStream file;
        file = new FileInputStream("./src/test/resources/Partner_Payment_Key_Token.properties");
        prop.load(file);
        String InvoiceLink = prop.getProperty("InvoiceLink");
        return InvoiceLink;
    }
    public static String partnerEnv() throws Exception {

        Properties prop = new Properties();
        FileInputStream file;
        file = new FileInputStream("./src/test/resources/Partner_Payment_Key_Token.properties");
        prop.load(file);
        String Environment = prop.getProperty("Environment");
        return Environment;
    }
    public static String partnerOrigin() throws Exception {

        Properties prop = new Properties();
        FileInputStream file;
        file = new FileInputStream("./src/test/resources/Partner_Payment_Key_Token.properties");
        prop.load(file);
        String Environment = prop.getProperty("Origin");
        return Environment;
    }
    public static String invoiceCurrency() throws Exception {

        Properties prop = new Properties();
        FileInputStream file;
        file = new FileInputStream("./src/test/resources/Partner_Payment_Key_Token.properties");
        prop.load(file);
        String Currency = prop.getProperty("Currency");
        return Currency;
    }

    public static String invoiceAmount() throws Exception {

        Properties prop = new Properties();
        FileInputStream file;
        file = new FileInputStream("./src/test/resources/Partner_Payment_Key_Token.properties");
        prop.load(file);
        String Amount = prop.getProperty("Amount");
        return Amount;
    }

    public static String pgwKey() throws Exception {
        String Key;
        Properties prop = new Properties();
        FileInputStream file;
        file = new FileInputStream("./src/test/resources/Partner_Payment_Key_Token.properties");
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
        Properties prop = new Properties();
        FileInputStream file;
        file = new FileInputStream("./src/test/resources/Partner_Payment_Key_Token.properties");
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

}
