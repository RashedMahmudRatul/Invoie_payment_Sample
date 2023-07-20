package StepDefinations;

import Pages.Partner_Payment_Page;
import Utility.Hooks;
import Utility.Partner_Payment_Api;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;

import static Utility.BaseCredentials.invoiceLink;
import static Utility.BaseCredentials.partnerEnv;
import static Utility.Utils.setEnvVariable;
import static io.restassured.RestAssured.given;

import org.apache.commons.text.StringEscapeUtils;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Partner_Payment_Step {
    public WebDriver driver;
    private Scenario scenario;
    String invoiceLinkUS, invoiceLinkBD;
    String currentWindowHandle;
    Partner_Payment_Page partnerPaymentPage;


    public Partner_Payment_Step() {
        this.driver = Hooks.getDriver();
        partnerPaymentPage = new Partner_Payment_Page(driver);
    }

    public void waitload() {
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
    public void switchToSecondTab(){
        currentWindowHandle = driver.getWindowHandle();
        // Get all available window handles
        Set<String> windowHandles = driver.getWindowHandles();
        System.out.println(currentWindowHandle);
        for (String windowHandle : windowHandles){
            if (windowHandle!=currentWindowHandle){
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
            return "https://sandbox:test!easy@";
        } else if (partnerEnv().trim().equals("dev")) {
            return "https://devs:super!power@";
        } else {
            System.out.println("Please check Environment name in config.properties file");
            return "";
        }
    }

    @Given("a valid invoice url for {string} users")
    public void a_valid_invoice_url_for_US_users(String country) throws Exception {
        Partner_Payment_Api.createInvoice(country);
        System.out.println("3");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @When("user clicks on the invoice url")
    public void user_clicks_on_the_invoice_url() throws Exception {

//        String url = "https://sandbox.hodl.clubswan.com/external/invoice-payment/c09f7745-4c57-4345-9ce3-51352c6f0de5";
//        invoiceLinkUS = url.substring(8);
//        setEnvVariable(invoiceLinkUS);
//        driver.get(basicAuth() + invoiceLinkUS);
//        System.out.println("1: " + invoiceLinkUS);

        invoiceLinkUS = Partner_Payment_Api.invoiceLink.substring(8);
        setEnvVariable(invoiceLinkUS);
        driver.get(basicAuth() + invoiceLinkUS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @Then("an invoice should appear necessary information")
    public void an_invoice_should_appear_necessary_information() throws Exception {
        Assert.assertTrue("All desired label not visible!!!", partnerPaymentPage.invoiceLabelCheck());
        Assert.assertTrue("Wrong Invoice Amount appeared!!!", partnerPaymentPage.invoiceAmountCheck());
        Assert.assertTrue("Wrong Payer information appeared!!!", partnerPaymentPage.payerInformationCheck());
    }

    @Given("a valid invoice for US users")
    public void a_valid_invoice_for_us_users() throws Exception {
        driver.get(basicAuth() + invoiceLink());

    }

    @Given ("user clicks on a US invoice link that has already been paid")
    public void a_valid_us_invoice_link_that_has_already_been_paid() throws Exception {
        driver.get(basicAuth() + invoiceLink());
    }
    @When("user expends payment method dropdown")
    public void user_expends_payment_method_dropdown() throws InterruptedException {
        partnerPaymentPage.paymentMethodDropdownClick();
        Thread.sleep(1000);
    }

    @Then("card and crypto should be available for payment")
    public void card_and_crypto_should_be_available_for_payment() {
        Assert.assertTrue("Error in Payment Method dropdown!!!", partnerPaymentPage.avlblmethodCheck());
    }

    @And("user selects crypto as payment method")
    public void user_selects_crypto_as_payment_method() {
        partnerPaymentPage.selectCrypto();
        waitload();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Then("accepted crypto currency list should appear")
    public void accepted_crypto_currency_list_should_appear() throws InterruptedException {
        Assert.assertTrue("Error in crypto currency list dropdown!!!", partnerPaymentPage.cryptoListCheck());
    }

    @Then("converted amount should be change as per selected currency")
    public void converted_amount_should_be_change_as_per_selected_currency() throws InterruptedException {
        Assert.assertTrue("Error in checking crypto currency conversion!!!", partnerPaymentPage.convCryptoAmtCheck());

    }

    @And("user clicks on the links on Terms and Conditions")
    public void user_clicks_on_the_links_on_Terms_and_conditions() throws InterruptedException {
        partnerPaymentPage.termsConditionClcik();
        Thread.sleep(3000);
    }

    @And("user clicks on the links on Privacy Policy")
    public void user_clicks_on_the_links_on_privacy_policy() throws InterruptedException {
        partnerPaymentPage.privacyPolicyClcik();
        Thread.sleep(3000);
    }

    @And ("user clicks on the links on Membership Agreement")
    public void user_clicks_on_the_links_on_membership_agreement() throws InterruptedException {
        partnerPaymentPage.membershipAgreementClick();
        Thread.sleep(3000);
    }


    @Then("user should see coinX terms page in a new tab")
    public void user_should_see_coinx_terms_page_in_a_new_tab() throws InterruptedException {
        switchToSecondTab();
        Assert.assertTrue("CoinX terms page not appeared!!!",driver.getCurrentUrl().endsWith("/coinx-terms"));
        driver.close();
        driver.switchTo().window(currentWindowHandle);
    }

    @Then ("user should see us privacy page in a new tab")
    public void user_should_see_us_privacy_page_in_a_new_tab(){
        switchToSecondTab();
        Assert.assertTrue("CoinX terms page not appeared!!!",driver.getCurrentUrl().endsWith("/us-privacy"));
        driver.close();
        driver.switchTo().window(currentWindowHandle);
    }

    @Then ("user should see us membership agreement page in a new tab")
    public void user_should_see_us_membership_agreement_page_in_a_new_tab(){
        switchToSecondTab();
        Assert.assertTrue("CoinX terms page not appeared!!!",driver.getCurrentUrl().endsWith("/membershipUs"));
        driver.close();
        driver.switchTo().window(currentWindowHandle);
    }

    @And ("user selects BTC currency")
    public void user_selects_BTC_currency() throws InterruptedException {
        partnerPaymentPage.selectBtc();
    }

    @And ("clicks on agreement checkbox and clicks on CONFIRM button")
    public void clicks_on_agreement_checkbox_and_clicks_on_confirm_button() throws InterruptedException {
        partnerPaymentPage.agreementCheckBoxClick();
        partnerPaymentPage.confirmBtnClick();
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
    }

    @And ("user selects card as payment method")
    public void user_selects_card_as_payment_method() throws InterruptedException {
        partnerPaymentPage.selectCard();
        Thread.sleep(500);
    }

    @Then ("a payment success message should appear")
    public void a_payment_success_message_should_appear(){
        driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
        partnerPaymentPage.paymentSuccessPageCheck();
    }

    @Then ("user should see an already paid error message")
    public void user_should_see_an_already_paid_error_message(){
        fgdfg
    }



}
