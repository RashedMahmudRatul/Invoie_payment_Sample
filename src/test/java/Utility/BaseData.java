package Utility;

import de.taimos.totp.TOTP;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

public class BaseData {


//    Apexx Card number
    public static String cardNumber() {
        String number = "5200000000001005";
//        String number = "4456530000001005";
        return number;
    }

    public static String cardMonth() {
        String month = "11";
        return month;
    }

    public static String cardYear() {
        String year = "31";
        return year;
    }

    public static String cardCvv() {
        String cvv = "003";
        return cvv;
    }

//    Stripe Card Number
    public static String stripeCardNumber() {
        String number = "5555555555554444";
        return number;
    }

    public static String stripeCardExpiry() {
        String date = "1130";
        return date;
    }

}
