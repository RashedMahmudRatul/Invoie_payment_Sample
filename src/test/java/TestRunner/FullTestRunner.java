package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Features",glue = "StepDefinations",

        tags = "@tts",
        plugin ={"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","rerun:target/failedrerun.txt"},
        monochrome = true)

public class FullTestRunner {
}

/*::::::::::::::::::::::::::::::::::::Note for tags::::::::::::::::::::::::::::::::::

++++++++++++++++++++++++++++++++++++++++++++++++New Members Scenarios+++++++++++++++++++++++++++++++++++++++++++++++++++
1. @new_member_dc_us
 ---> popup-topup, virtual card purchase and activate using debit/credit card; m2m beneficiary creation; phy card activation scenarios for US members.
2. @new_member_dc_non_us
 ---> popup-topup, virtual card purchase and activate using debit/credit card; m2m beneficiary creation; phy card activation scenarios for US members.
3. @new_member_acc
 ---> popup-topup using debit/credit card; virtual card purchase and activate using e-Wallet; m2m beneficiary creation; phy card activation scenarios for US members.

++++++++++++++++++++++++++++++++++++++++++++++++ Accounts ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
1. @all_wallet_features_us        ---> All wallet features for US members will run by this tag
2. @all_wallet_features_non_us    ---> All wallet features for Non-US members will run by this tag

++++++++++++++++++++++++++++++++++++++++++++++++ Cards +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
1. @all_card_features   ---> All card (both physical and virtual) features will run by this tag
2. @cs_card_topup_us    ---> All card (both physical and virtual) topup feature for Club Swan US members only.
3. @cs_card_topup_non_us    ---> All card (both physical and virtual) topup feature for Club Swan Non-US members only.

+++++++++++++++++++++++++++++++++++++++++++++++ Others +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
1. @concierge   --->  Concierge feature will run by this tag
2. @inst_reward_feature   ---> Instant Reward feature will run by this tag
3. @settings   ---> All settings feature will run by this tag
4. @shopping   ---> All shopping feature will run by this tag
5. @logout     ---> this will do logout

*/
