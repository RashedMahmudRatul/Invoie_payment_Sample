package Pages;

import Utility.Driver_Setup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static Utility.BaseCredentials.invoiceAmount;
import static Utility.BaseCredentials.invoiceCurrency;

public class User_Invoice_Payment_Page {
    public WebDriver driver;

    @FindBy(xpath = "//h5[text()='Invoice Summary']")
    WebElement invSummaryLabel;
    @FindBy(xpath = "//h4[text()='Pay Total']")
    WebElement payTotalLabel;
    @FindBy(xpath = "//h4[text()='Pay Total']/../h4[2]")
    WebElement invAmt;
    @FindBy(xpath = "//h4[text()='Pay Total']/../../div[2]/h5")
    WebElement detailsLabel;
    @FindBy(xpath = "//h4[text()='Pay Total']/../../div[2]/div[1]")
    WebElement name;
    @FindBy(xpath = "//h4[text()='Pay Total']/../../div[2]/div[2]")
    WebElement email;
    @FindBy(xpath = "//h4[text()='Pay Total']/../../div[2]/div[3]")
    WebElement phone;
    @FindBy(xpath = "//h4[text()='Pay Total']/../../div[2]/div[4]")
    WebElement purpose;
    @FindBy(xpath = "//label[text()='Payment Method']")
    WebElement paymthdLabel;
    @FindBy(xpath = "//label[text()='Payment Method']/../div/div")
    WebElement paymthdDropdown;
    @FindBy(xpath = "//li[text()='Card']")
    WebElement card;
    @FindBy(xpath = "//li[text()='Crypto']")
    WebElement crypto;
    @FindBy(xpath = "(//div[@role='button'])[2]")
    WebElement cryptoListDropdown;
    @FindAll({@FindBy(xpath = "//li[@role='option']")})
    List<WebElement> cryptoList;
    @FindBy(xpath = "//li[@role='option'][1]")
    WebElement btc;
    @FindBy(xpath = "//li[@role='option'][2]")
    WebElement eth;
    @FindBy(xpath = "//li[@role='option'][3]")
    WebElement ltc;
    @FindBy(xpath = "//li[@role='option'][4]")
    WebElement usdt;
    @FindBy(xpath = "(//div[@role='button'])[2]/../../../span")
    WebElement cryptoAmt;
    @FindBy(xpath = "//a[text()='Terms and Conditions']")
    WebElement termsCondition;
    @FindBy(xpath = "//a[text()='Privacy Policy']")
    WebElement privacyPolicy;
    @FindBy(xpath = "//a[text()='Membership Agreement']")
    WebElement membershipAgreement;
    @FindBy(xpath = "//input[@name='agreement']")
    WebElement agreementCheckBox;
    @FindBy(xpath = "//button[@type='button']")
    WebElement confirmBtn;
    @FindBy(xpath = "//h5[text()='Payment Status']")
    WebElement payStatusLabel;
    @FindBy(xpath = "//h5[text()='Payment Status']/../../div[2]/*[local-name() = 'svg']")
    WebElement confirmationIcon;
    @FindBy(xpath = "//h5[text()='Payment Status']/../../div[2]/div")
    WebElement successMsg;
    @FindBy(xpath = "//h4[text()='Pay Total']/../../../../div[2]/div/p")
    WebElement paymentRequestedMsg;
    @FindBy(xpath = "(//div[@role='button'])[2]/../../../div[2]")
    WebElement minMaxCryptoMsg;
    @FindBy(xpath = "//label[text()='Payment Method']/../../p")
    WebElement maxBtcMsg;
    @FindBy(xpath = "//h4[text()='Pay Total']/../../../../div[2]/div/p")
    WebElement invoiceExpireMsg;

    public User_Invoice_Payment_Page(WebDriver driver) {
        this.driver = Driver_Setup.driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * This method checks available labels in invoice UI
     */
    public boolean invoiceLabelCheck() {
        boolean invSummaryVisible = invSummaryLabel.isDisplayed();
        boolean payTotalVisible = payTotalLabel.isDisplayed();
        boolean detailsVisible = detailsLabel.isDisplayed();
        boolean paymentMethodVisible = paymthdLabel.isDisplayed();
        boolean paymthdDropdownVisible = paymthdDropdown.getText().equals("Select");
        System.out.println("Invoice Summary label visible: " + invSummaryVisible + "\nPay total label visible: " + payTotalVisible + "\nDetails label visible: " + detailsVisible + "\nPayment Method label visible: " + paymentMethodVisible + "\nDropdown visible: " + paymthdDropdownVisible);

        if (invSummaryVisible && payTotalVisible && detailsVisible && paymentMethodVisible && paymthdDropdownVisible)
            return true;
        else
            return false;

    }

    /**
     * This method checks payer information that set in request body
     */
    public boolean payerInformationCheck() {
        boolean nameCheck = name.getText().equals("Name\nRichard Millan");
        boolean emailCheck = email.getText().equals("Email\nhodl_rtl_tst_us_inv@mailinator.com");
        boolean phoneCheck = phone.getText().equals("Phone Number\n+17574498317");
        boolean purposeCheck = purpose.getText().equals("Purpose\nThis is a test purpose.");
        System.out.println("Payer Name visible: " + nameCheck + "\nPayer Email visible: " + emailCheck + "\nPhone Number visible: " + phoneCheck + "\nPurpose visible: " + purposeCheck);
        if (nameCheck && emailCheck && phoneCheck && purposeCheck)
            return true;
        else
            return false;
    }

    /**
     * This method checks invoice currency and amount as per set in properties file
     */
    public boolean invoiceAmountCheck() throws Exception {
        String currency = invAmt.getText().replaceAll("\\s", "").substring(0, 1);
        String amount = invAmt.getText().replaceAll("\\s", "").substring(1);

        if (invoiceCurrency().equals("USD")) {
            if (currency.equals("$") && amount.equals(invoiceAmount()))
                return true;
        } else if (invoiceCurrency().equals("EUR")) {
            if (currency.equals("€") && amount.equals(invoiceAmount()))
                return true;
        } else if (invoiceCurrency().equals("GBP")) {
            if (currency.equals("£") && amount.equals(invoiceAmount()))
                return true;
        } else {
            System.out.println("Given Currency and invoice currency doesn't match!!");
        }
        return false;
    }

    /**
     * This method expands payment method dropdown
     */
    public void paymentMethodDropdownClick() {
        paymthdDropdown.click();
    }

    /**
     * This method will return true if both crypto and card is available for payment
     */
    public boolean avlblmethodCheck() {
        if (card.isDisplayed() && crypto.isDisplayed())
            return true;
        else
            return false;
    }

    /**
     * This method selects card as payment method
     */
    public void selectCard() {
        card.click();
    }

    /**
     * This method selects crypto as payment method
     */
    public void selectCrypto() {
        crypto.click();
    }

    /**
     * This method counts available cryptocurrency and match with "TBTC", "TETH", "TLTC", "USDT"
     */
    public boolean cryptoListCheck() throws InterruptedException {
        cryptoListDropdown.click();
        Thread.sleep(1000);
        List<String> allCrypto = new ArrayList<>();
        String[] availableCrypto = {"TBTC", "TETH", "TLTC", "USDT"};
        for (WebElement crypto : cryptoList) {
            String cryptoName = crypto.getText();
            allCrypto.add(cryptoName);
        }
        System.out.println(allCrypto);
        if (allCrypto.size() != availableCrypto.length) {
            System.out.println("Expected crypto currencies count doesn't match");
            return false;
        } else {
            for (int i = 0; i < allCrypto.size(); i++) {
                if (!allCrypto.get(i).equals(availableCrypto[i]))
                    return false;
            }
        }
        return true;
    }

    /**
     * This method compares the crypto amount with the previous amount after changing the currency
     */
    public boolean convCryptoAmtCheck() throws InterruptedException {

        List<String> checkedCurrency = new ArrayList<>();
        String[] availableCrypto = {"TBTC", "TETH", "TLTC", "USDT"};
        cryptoListDropdown.click();
        Thread.sleep(1000);
        String tempAmount = "";
        String currentAmt = "";
        int cryptoCount = cryptoList.size();
        for (int i = 0; i < cryptoCount; i++) {
            if (i == 0) {
                btc.click();
                currentAmt = cryptoAmt.getText();
                if (currentAmt != "") {
                    tempAmount = currentAmt;
                    checkedCurrency.add("TBTC");
                    System.out.println("BTC Done");
                }
                Thread.sleep(1000);
                cryptoListDropdown.click();
                Thread.sleep(1000);
            } else if (i == 1) {
                eth.click();
                Thread.sleep(2000);
                currentAmt = cryptoAmt.getText();
                if (!tempAmount.equals(currentAmt)) {
                    checkedCurrency.add("TETH");
                    System.out.println("ETH Done");
                }
                Thread.sleep(1000);
                cryptoListDropdown.click();
                Thread.sleep(1000);
            } else if (i == 2) {
                ltc.click();
                Thread.sleep(2000);
                currentAmt = cryptoAmt.getText();
                if (!tempAmount.equals(currentAmt)) {
                    checkedCurrency.add("TLTC");
                    System.out.println("LTC Done");
                }
                Thread.sleep(1000);
                cryptoListDropdown.click();
                Thread.sleep(1000);
            } else if (i == 3) {
                usdt.click();
                Thread.sleep(2000);
                currentAmt = cryptoAmt.getText();
                if (!tempAmount.equals(currentAmt)) {
                    checkedCurrency.add("USDT");
                    System.out.println("USDT Done");
                }
            } else {
                System.out.println("Error in crypto currency list!!!");
            }
        }
        System.out.println(checkedCurrency);
        if (checkedCurrency.size() != availableCrypto.length) {
            System.out.println("Unable to check all currency conversion!!");
            return false;
        }
        return true;
    }

    /**
     * This method clicks on Terms and condition
     */
    public void termsConditionClcik() {
        termsCondition.click();
    }

    /**
     * This method clicks on Privacy Policy
     */
    public void privacyPolicyClcik() {
        privacyPolicy.click();
    }

    /**
     * This method clicks on Membership Agreement
     */
    public void membershipAgreementClick() {
        membershipAgreement.click();
    }

    /**
     * This message selects btc
     */
    public void selectBtc() throws InterruptedException {
        cryptoListDropdown.click();
        Thread.sleep(500);
        btc.click();
    }

    public void agreementCheckBoxClick() throws InterruptedException {
        agreementCheckBox.click();
        Thread.sleep(500);
    }

    public void confirmBtnClick() throws InterruptedException {
        confirmBtn.click();
    }

    /**
     * This method checks payment success message page elements
     */
    public boolean paymentSuccessPageCheck() {
        boolean successMsgs = successMsg.getText().equals("Congratulations!\n" +
                "Your payment request has been accepted.\n" +
                "You will be notified via email upon payment confirmation.");
        System.out.println("Text in success msg:" + successMsgs);
        if (payStatusLabel.isDisplayed() && confirmationIcon.isDisplayed() && successMsgs)
            return true;
        else
            return false;
    }

    public boolean paymentFailedPageCheck() {
        boolean successMsgs = successMsg.getText().equals("Your recent payment request was unsuccessful.\n" + "Please contact a member of support for further assistance.");
        System.out.println("Text in success msg: " + successMsgs);

        if (payStatusLabel.isDisplayed() && confirmationIcon.isDisplayed() && successMsgs)
            return true;
        else
            return false;
    }

    public boolean paymentRequestedMsgCheck() {
        if (paymentRequestedMsg.isDisplayed()) {
            if (paymentRequestedMsg.getText().equals("Invoice payment has already been requested!"))
                return true;
        }
        return false;
    }


    public void selectEth() throws InterruptedException {
        cryptoListDropdown.click();
        Thread.sleep(500);
        eth.click();
        Thread.sleep(2000);
    }

    public boolean minEthMsgCheck() throws InterruptedException {
        if (minMaxCryptoMsg.isDisplayed() && minMaxCryptoMsg.getText().trim().equals("minimun order amount is 0.01 ETH")) {
            return true;
        }
        return false;
    }

    public boolean maxCryptoAmtCheck() throws InterruptedException {
        List<String> checkedCurrency = new ArrayList<>();
        String[] availableCrypto = {"TETH", "TLTC", "USDT"};
        cryptoListDropdown.click();
        Thread.sleep(1000);
        int cryptoCount = cryptoList.size();

        for (int i = 0; i < cryptoCount; i++) {
            if (i == 1) {
                eth.click();
                Thread.sleep(3000);
                if (minMaxCryptoMsg.getText().trim().equals("maximum order amount is 10 ETH")) {
                    checkedCurrency.add("TETH");
                    System.out.println("ETH Done");
                }
                Thread.sleep(1000);
                cryptoListDropdown.click();
                Thread.sleep(1000);
            } else if (i == 2) {
                ltc.click();
                Thread.sleep(3000);
                if (minMaxCryptoMsg.getText().trim().equals("maximum order amount is 50 LTC")) {
                    checkedCurrency.add("TLTC");
                    System.out.println("LTC Done");
                }
                Thread.sleep(1000);
                cryptoListDropdown.click();
                Thread.sleep(1000);
            } else if (i == 3) {
                usdt.click();
                Thread.sleep(3000);
                if (minMaxCryptoMsg.getText().trim().equals("maximum order amount is 100000 USDT")) {
                    checkedCurrency.add("USDT");
                    System.out.println("USDT Done");
                }

            }

        }
        System.out.println(checkedCurrency);
        if (checkedCurrency.size() != availableCrypto.length) {
            System.out.println("Unable to check all currency conversion!!");
            return false;
        }
        return true;
    }

    public boolean maxBtcMsgCheck(){
        if (maxBtcMsg.isDisplayed() && maxBtcMsg.getText().trim().equals("maximum order amount is 5 BTC"))
            return true;
        else return false;
    }
    public boolean invoiceExpireMsgCheck(){
        if (invoiceExpireMsg.isDisplayed() && invoiceExpireMsg.getText().trim().equals("Invoice has expired"))
            return true;
        else return false;
    }


}
