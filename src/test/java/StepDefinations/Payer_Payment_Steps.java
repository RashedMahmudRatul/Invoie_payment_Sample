package StepDefinations;

import Pages.User_Invoice_Payment_Page;
import Utility.Driver_Setup;
import Utility.Partner_Payment_Api;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import static Utility.BaseCredentials.invoiceLink;
import static Utility.BaseCredentials.partnerEnv;
import static Utility.Driver_Setup.getDriver;
import static Utility.Utils.dateTime;
import static Utility.Utils.setEnvVariable;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Payer_Payment_Steps {
    public WebDriver driver;
    private Scenario scenario;
    String invoiceLink, invoiceLinkBD;
    String currentWindowHandle;
    User_Invoice_Payment_Page userPaymentPage;


    public Payer_Payment_Steps() throws IOException {
        this.driver = getDriver();
        userPaymentPage = new User_Invoice_Payment_Page(driver);
    }

    public void waitload() {
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    @After(order = 1)
    public void takeScraenshotOnFailure(Scenario scenario) throws IOException {

        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

    }

    public void switchToSecondTab() {
        currentWindowHandle = driver.getWindowHandle();
        // Get all available window handles
        Set<String> windowHandles = driver.getWindowHandles();
        System.out.println(currentWindowHandle);
        for (String windowHandle : windowHandles) {
            if (!Objects.equals(windowHandle, currentWindowHandle)) {
                driver.switchTo().window(windowHandle);
            }
        }
    }

    @Before
    public void before(Scenario scenario) {
        this.scenario = scenario;
    }

    public void writeInReport(String reportPrint) {
        String consoleOutput = reportPrint;
        String escapedOutput = StringEscapeUtils.escapeHtml4(consoleOutput);
        // Add the string in red color to the report using Markup
        String redColorMarkup = "<span style='color: red; font-style: italic; font-weight: bold;'>" + "Note ::: " + escapedOutput + "</span>";
        Markup customColor = MarkupHelper.createLabel(redColorMarkup, ExtentColor.TRANSPARENT);
        scenario.log(customColor.getMarkup());
    }

    /**
     * This method pass the basic browser auth credentials
     */
    private static String basicAuth() throws Exception {
        if (partnerEnv().trim().equals("tst")) {
            return "https://username:passwrod@";
        } else if (partnerEnv().trim().equals("dev")) {
            return "https://username:passwrod@";
        } else {
            System.out.println("Please check Environment name in config.properties file");
            return "";
        }
    }

    @Given("a valid invoice url for {string} users")
    public void a_valid_invoice_url_for_US_users(String country) throws Exception {
        Partner_Payment_Api.createInvoice(country);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Given("a invoice url of amount {string} USD for {string} users")
    public void a_invoice_url_of_amount_min_usd_for_us_users(String amount, String country) throws Exception {
        Partner_Payment_Api.createInvoice(amount, country);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @When("user clicks on the invoice url")
    public void user_clicks_on_the_invoice_url() throws Exception {
        invoiceLink = Partner_Payment_Api.invoiceLink.substring(8);
        setEnvVariable(invoiceLink);
        driver.get(basicAuth() + invoiceLink);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @Then("an invoice should appear necessary information")
    public void an_invoice_should_appear_necessary_information() throws Exception {
        Assert.assertTrue("All desired label not visible!!!", userPaymentPage.invoiceLabelCheck());
        Assert.assertTrue("Wrong Invoice Amount appeared!!!", userPaymentPage.invoiceAmountCheck());
        Assert.assertTrue("Wrong Payer information appeared!!!", userPaymentPage.payerInformationCheck());
    }

    @Given("a valid invoice for US users")
    public void a_valid_invoice_for_us_users() throws Exception {
        driver.get(basicAuth() + invoiceLink());

    }

    @Given("^a valid invoice url for Non-US users$")
    public void a_valid_invoice_for_non_us_users() throws Exception {
        driver.get(basicAuth() + invoiceLink());
    }


    @Given("user clicks on a US invoice link that has already been paid")
    public void a_valid_us_invoice_link_that_has_already_been_paid() throws Exception {
        driver.get(basicAuth() + invoiceLink());
    }

    @Given("^user clicks on a Non-US invoice link that has already been paid$")
    public void a_valid_non_us_invoice_link_that_has_already_been_paid() throws Exception {
        driver.get(basicAuth() + invoiceLink());
    }

    @And("user expends payment method dropdown")
    public void user_expends_payment_method_dropdown() throws InterruptedException {
        userPaymentPage.paymentMethodDropdownClick();
        Thread.sleep(1000);
    }

    @Then("card and crypto should be available for payment")
    public void card_and_crypto_should_be_available_for_payment() {
        Assert.assertTrue("Error in Payment Method dropdown!!!", userPaymentPage.avlblmethodCheck());
    }

    @And("user selects crypto as payment method")
    public void user_selects_crypto_as_payment_method() {
        userPaymentPage.selectCrypto();
        waitload();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Then("accepted crypto currency list should appear")
    public void accepted_crypto_currency_list_should_appear() throws InterruptedException {
        Assert.assertTrue("Error in crypto currency list dropdown!!!", userPaymentPage.cryptoListCheck());
    }

    @Then("converted amount should be change as per selected currency")
    public void converted_amount_should_be_change_as_per_selected_currency() throws InterruptedException {
        Assert.assertTrue("Error in checking crypto currency conversion!!!", userPaymentPage.convCryptoAmtCheck());

    }

    @And("user clicks on the links on Terms and Conditions")
    public void user_clicks_on_the_links_on_Terms_and_conditions() throws InterruptedException {
        userPaymentPage.termsConditionClcik();
        Thread.sleep(3000);
    }

    @And("user clicks on the links on Privacy Policy")
    public void user_clicks_on_the_links_on_privacy_policy() throws InterruptedException {
        userPaymentPage.privacyPolicyClcik();
        Thread.sleep(3000);
    }

    @And("user clicks on the links on Membership Agreement")
    public void user_clicks_on_the_links_on_membership_agreement() throws InterruptedException {
        userPaymentPage.membershipAgreementClick();
        Thread.sleep(3000);
    }


    @Then("user should see coinX terms page in a new tab")
    public void user_should_see_coinx_terms_page_in_a_new_tab() throws InterruptedException {
        switchToSecondTab();
        Assert.assertTrue("CoinX terms page not appeared!!!", driver.getCurrentUrl().endsWith("/page"));
        driver.close();
        driver.switchTo().window(currentWindowHandle);
    }

    @Then("user should see terms page in a new tab")
    public void user_should_see_terms_page_in_a_new_tab() throws InterruptedException {
        switchToSecondTab();
        Assert.assertTrue("CoinX terms page not appeared!!!", driver.getCurrentUrl().endsWith("/terms"));
        driver.close();
        driver.switchTo().window(currentWindowHandle);
    }

    @Then("user should see us privacy page in a new tab")
    public void user_should_see_us_privacy_page_in_a_new_tab() {
        switchToSecondTab();
        Assert.assertTrue("CoinX terms page not appeared!!!", driver.getCurrentUrl().endsWith("/us-privacy"));
        driver.close();
        driver.switchTo().window(currentWindowHandle);
    }

    @Then("user should see privacy page in a new tab")
    public void user_should_see_privacy_page_in_a_new_tab() {
        switchToSecondTab();
        Assert.assertTrue("CoinX terms page not appeared!!!", driver.getCurrentUrl().endsWith("/privacy"));
        driver.close();
        driver.switchTo().window(currentWindowHandle);
    }

    @Then("user should see us membership agreement page in a new tab")
    public void user_should_see_us_membership_agreement_page_in_a_new_tab() {
        switchToSecondTab();
        Assert.assertTrue("CoinX terms page not appeared!!!", driver.getCurrentUrl().endsWith("/membership"));
        driver.close();
        driver.switchTo().window(currentWindowHandle);
    }

    @Then("user should see membership agreement page in a new tab")
    public void user_should_see_membership_agreement_page_in_a_new_tab() {
        switchToSecondTab();
        Assert.assertTrue("CoinX terms page not appeared!!!", driver.getCurrentUrl().endsWith("/membership"));
        driver.close();
        driver.switchTo().window(currentWindowHandle);
    }

    @And("user selects BTC currency")
    public void user_selects_BTC_currency() throws InterruptedException {
        userPaymentPage.selectBtc();
    }

    @And("clicks on agreement checkbox and clicks on CONFIRM button")
    public void clicks_on_agreement_checkbox_and_clicks_on_confirm_button() throws InterruptedException {
        userPaymentPage.agreementCheckBoxClick();
        userPaymentPage.confirmBtnClick();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @And("user selects card as payment method")
    public void user_selects_card_as_payment_method() throws InterruptedException {
        userPaymentPage.selectCard();
        Thread.sleep(500);
    }

    @Then("a payment success message should appear")
    public void a_payment_success_message_should_appear() {
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        userPaymentPage.paymentSuccessPageCheck();
    }

    @Then("user should see an already paid error message")
    public void user_should_see_an_already_paid_error_message() {
        Assert.assertTrue("Invoice payment has already been requested! doesn't appaered", userPaymentPage.paymentRequestedMsgCheck());
    }

    @When("a {string} invoice link that has been expired")
    public void user_clicks_on_the_invoice_link_that_has_been_expired(String country) throws Exception {
        Partner_Payment_Api.createInvoice(country, dateTime());
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Thread.sleep(12000);
    }

    @Then("user should see an expired error message")
    public void user_should_see_an_expired_error_message() {
        Assert.assertTrue("Invoice expire message not appeared as expected!!!", userPaymentPage.invoiceExpireMsgCheck());

    }


    @And("user selects ETH crypto currency")
    public void user_selects_eth_crypto_currency() throws InterruptedException {
        userPaymentPage.selectEth();

    }

    @Then("an error message with minimum crypto amount should appear")
    public void an_error_message_with_minimum_crypto_amount_should_appear() throws InterruptedException {
        Assert.assertTrue("ETH minimum error message not appeared", userPaymentPage.minEthMsgCheck());
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @When("user selects ETH,LTC,USDT crypto currency")
    public void user_selects_different_crypto_currency() {
        System.out.println();
    }

    @Then("an error message with maximum crypto amount should appear")
    public void an_error_message_with_maximum_crypto_amount_should_appear() throws InterruptedException {
        Assert.assertTrue("Maximum crypto amount error message doesn't appeared as expected!!!", userPaymentPage.maxCryptoAmtCheck());
    }

    @Then("an error message for maximum BTC should appear")
    public void an_error_message_for_maximum_btc_should_appear() {
        Assert.assertTrue("Maximum BTC amount error message doesn't appeared as expected!!!", userPaymentPage.maxBtcMsgCheck());
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @Then("a payment error message should appear")
    public void a_payment_error_message_should_appear() {
        Assert.assertTrue("Expected error message not appeared!!!", userPaymentPage.paymentFailedPageCheck());
    }


}
