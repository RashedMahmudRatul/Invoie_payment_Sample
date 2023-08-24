package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Features",glue = "StepDefinations",
        tags="@inv_non_us",
        plugin ={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","rerun:target/failedrerun.txt"},
        monochrome = true)

public class User_Invoice_Payment_Runner {





}

/*::::::::::::::::::::::::::::::::::::Note for tags::::::::::::::::::::::::::::::::::
@inv_non_us ----------- This tags runs Non-US features
@inv_us     ----------- This tags runs  US features
***** Please change the origin in "Partner_Payment_Key_Token.properties" file as per US and Non-US tag


*/
