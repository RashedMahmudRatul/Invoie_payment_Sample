package Utility;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Hook {
    private static WebDriver driver;
    private Scenario scenario;


    @Before
    public static WebDriver getDriver() {

        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe"); //Google Chrome
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);



//                System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe"); //Fire Fox
//                driver = new FirefoxDriver();
//                driver.manage().window().maximize();
//                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


//            System.setProperty("webdriver.edge.driver", "Drivers/msedgedriver.exe"); //MicroSoftEdge
//            driver = new EdgeDriver();
//            driver.manage().window().maximize();
//            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        }


        return driver;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    @After
    public void tearDown() {
        driver.quit();
        driver.close();
    }

}
