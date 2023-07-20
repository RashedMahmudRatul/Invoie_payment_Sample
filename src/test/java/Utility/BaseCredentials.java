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

        Properties prop = new Properties();
        FileInputStream file;
        file = new FileInputStream("./src/test/resources/Partner_Payment_Key_Token.properties");
        prop.load(file);
        String Key = prop.getProperty("Key");
        return Key;
    }

    public static String pgwToken() throws Exception {

        Properties prop = new Properties();
        FileInputStream file;
        file = new FileInputStream("./src/test/resources/Partner_Payment_Key_Token.properties");
        prop.load(file);
        String Token = prop.getProperty("Token");
        return Token;
    }

}
