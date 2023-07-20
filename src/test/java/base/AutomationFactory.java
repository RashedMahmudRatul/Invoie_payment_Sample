package base;


import org.openqa.selenium.WebDriver;


/**
 * @author Muhemmet UYAR
 * 8/30/2021
 */
public class AutomationFactory {

    private WebDriver webDriver;

    private final        String        operatingSystem       = System.getProperty ("os.name").toUpperCase ();
    private final        String        systemArchitecture    = System.getProperty ("os.arch").toUpperCase ();
    private final        boolean       useRemoteWebDriver    = Boolean.getBoolean ("remoteDriver");
    private final        boolean       headlessMode          = Boolean.getBoolean ("headless");





    public void quitDriver () {
        if (null != webDriver) {
            webDriver.quit();
            webDriver = null;
        }
    }


}
