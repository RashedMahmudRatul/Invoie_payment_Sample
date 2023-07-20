package base;


import Utility.Hooks;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



/**
 * @author MFU
 * 1/7/2021
 */
public class AutomationBase {

    private static List<AutomationFactory> automationThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<AutomationFactory> driverThread;

    private static String strApiGateway = System.getProperty("apiGateway", "stg");


    public static void instantiateWebDriverObjects() {

            driverThread = ThreadLocal.withInitial(() -> {
            AutomationFactory automationThread = new AutomationFactory();
            automationThreadPool.add(automationThread);
            return automationThread;
        });
    }



    public static WebDriver openDriver() {
        return Hooks.getDriver();
    }



    public static void closeAutomationObjects() {

//        for (AutomationFactory automationThread : automationThreadPool) {
//            automationThread.quitDriver();
//        }

        driverThread.get().quitDriver();

    }

    public static byte[] screenShot() {
        TakesScreenshot ts = (TakesScreenshot) openDriver();

        return ts.getScreenshotAs(OutputType.BYTES);
    }

    public static void setWebDriver(WebDriver webDriver) {
    }
}
