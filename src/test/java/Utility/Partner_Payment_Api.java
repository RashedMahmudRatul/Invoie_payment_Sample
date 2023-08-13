package Utility;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.Response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static Utility.BaseCredentials.*;
import static io.restassured.RestAssured.given;

public class Partner_Payment_Api {
    public static String  invoiceLink;
    static String  endPoint =  "EndPoint_here";
    static Response response = new RestAssuredResponseImpl();

    /**
     * This method returns auth headers as per set in Partner_Payment_Key_Token.properties file.
     */
    private static Map requestHeader() throws Exception {
        String apiKey = BaseCredentials.pgwKey();
        String token = BaseCredentials.pgwToken();
        Map<String,Object> requestHeader = new HashMap<>();
        requestHeader.put("X-AU-Key", apiKey);
        requestHeader.put("X-AU-Token", token);
        requestHeader.put("Content-Type", ContentType.JSON);
        requestHeader.put("Accept", ContentType.JSON);
        return requestHeader;
    }

    /**
     * This method will return the baseURL as per environment that set in Partner_Payment_Key_Token.properties file.
     */
    private static String baseUrl() throws Exception {
        if (partnerEnv().trim().equals("test")) {
            return "test_api_link";
        } else if (partnerEnv().trim().equals("dev")) {
            return  "dev_api_link";
        } else {
            System.out.println("Please check Environment name in config.properties file");
            return "";
        }

    }
    public static void createInvoice(String country) throws Exception {
        RestAssured.baseURI  = baseUrl();
        String requestBody = "{\r\n"
                + "    \"firstName\": \"" + "Richard" + "\",\r\n"
                + "    \"lastName\": \"" + "Millan" + "\",\r\n"
                + "    \"postCode\": \"" + "12536" + "\",\r\n"
                + "    \"address\": \"" + "Lake Bridge road" + "\",\r\n"
                + "    \"countryCode\": \"" + "Bangladesh" + "\",\r\n"
                + "    \"city\": \"" + "Dhaka" + "\",\r\n"
                + "    \"emailAddress\": \"" + "user@mailinator.com" + "\",\r\n"
                + "    \"mobileNumber\": \"" + "+17574498317" + "\",\r\n"
                + "    \"currency\": \"" + "USD" + "\",\r\n"
                + "    \"amount\": \"" + "100" + "\",\r\n"
                + "    \"expiryDateTime\": \"" + "2023-07-29T00:00:00.000Z" + "\",\r\n"
                + "    \"webhookUrl\": \"" + "www.google.com" + "\",\r\n"
                + "    \"purpose\": \"" + "This is a test purpose." + "\"\r\n"
                + "}";
        response = given().headers(requestHeader())
                .body(requestBody)
                .when().post(endPoint)
                .then().extract().response();

        System.out.println("Response Body: "+response.getBody().asString());
        System.out.println("Status Code: "+ response.getStatusCode());
        invoiceLink = response.getBody().path("data.url");
        System.out.println("Invoice Link: "+invoiceLink);
    }

    public static void createInvoice(String amount ,String country) throws Exception {
        RestAssured.baseURI  = baseUrl();
        String requestBody = "{\r\n"
                + "    \"firstName\": \"" + "Richard" + "\",\r\n"
                + "    \"lastName\": \"" + "Millan" + "\",\r\n"
                + "    \"postCode\": \"" + "12536" + "\",\r\n"
                + "    \"address\": \"" + "Lake Bridge road" + "\",\r\n"
                + "    \"countryCode\": \"" + "UAE" + "\",\r\n"
                + "    \"city\": \"" + "City" + "\",\r\n"
                + "    \"emailAddress\": \"" + "user@mailinator.com" + "\",\r\n"
                + "    \"mobileNumber\": \"" + "+17574498317" + "\",\r\n"
                + "    \"currency\": \"" + "USD" + "\",\r\n"
                + "    \"amount\": \"" + "100" + "\",\r\n"
                + "    \"expiryDateTime\": \"" + "2023-07-29T00:00:00.000Z" + "\",\r\n"
                + "    \"webhookUrl\": \"" + "www.google.com" + "\",\r\n"
                + "    \"purpose\": \"" + "This is a test purpose." + "\"\r\n"
                + "}";
        response = given().headers(requestHeader())
                .body(requestBody)
                .when().post(endPoint)
                .then().extract().response();

        System.out.println("Response Body: "+response.getBody().asString());
        System.out.println("Status Code: "+ response.getStatusCode());
        invoiceLink = response.getBody().path("data.url");
        System.out.println("Invoice Link: "+invoiceLink);
    }
    public static void createInvoice(String country , LocalDateTime expiredTime) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String dateTime = expiredTime.minusHours(6).plusSeconds(10). format(formatter);

        RestAssured.baseURI  = baseUrl();
        String requestBody = "{\r\n"
                + "    \"firstName\": \"" + "Richard" + "\",\r\n"
                + "    \"lastName\": \"" + "Millan" + "\",\r\n"
                + "    \"postCode\": \"" + "12536" + "\",\r\n"
                + "    \"address\": \"" + "Lake Bridge road" + "\",\r\n"
                + "    \"countryCode\": \"" + "Bangladesh" + "\",\r\n"
                + "    \"city\": \"" + "Texas" + "\",\r\n"
                + "    \"emailAddress\": \"" + "user@mailinator.com" + "\",\r\n"
                + "    \"mobileNumber\": \"" + "+17574498317" + "\",\r\n"
                + "    \"currency\": \"" + "USD" + "\",\r\n"
                + "    \"amount\": \"" + "100" + "\",\r\n"
                + "    \"expiryDateTime\": \"" + dateTime + "\",\r\n"
                + "    \"webhookUrl\": \"" + "www.gogle.com" + "\",\r\n"
                + "    \"purpose\": \"" + "This is a test purpose." + "\"\r\n"
                + "}";
        response = given().headers(requestHeader())
                .body(requestBody)
                .when().post(endPoint)
                .then().extract().response();

        System.out.println("Response Body: "+response.getBody().asString());
        System.out.println("Status Code: "+ response.getStatusCode());
        invoiceLink = response.getBody().path("data.url");
        System.out.println("Invoice Link: "+invoiceLink);
    }

}
