package Pages;

import Utility.BaseData;
import Utility.Driver_Setup;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class Payment_Gateway_Page {
    public WebDriver driver;
    @FindBy(xpath = "//iframe[contains(@name,'__privateStripeFrame')]")
    WebElement stripeFrame;
    @FindBy(xpath = "//*[@id=\"card_number\"]")
    WebElement cardNo;
    @FindBy(xpath = "//*[@id=\"expiry_month\"]")
    WebElement expMonth;
    @FindBy(xpath = "//*[@id=\"expiry_year\"]")
    WebElement expYear;
    @FindBy(xpath = "//*[@id=\"cvv\"]")
    WebElement cvv;
    @FindBy(xpath = "//*[@id=\"hostedPaymentsubmitBtn\"]")
    WebElement payBtn;
    @FindBy(xpath = "//input[@name='cardnumber']")
    WebElement cardNumber;
    @FindBy(xpath = "//input[@name='exp-date']")
    WebElement expDate;
    @FindBy(xpath = "//input[@name='cvc']")
    WebElement cvc;
    @FindBy(xpath = "//span[text()='Pay']")
    WebElement stripePayBtn;
    @FindBy(xpath = "//h3[text()='Payment Information']")
    WebElement paymentInfoLebel;

    public Payment_Gateway_Page(WebDriver driver) {
        this.driver = Driver_Setup.driver;
        PageFactory.initElements(driver, this);
    }

    public void enterApxxCardNum() {
        cardNo.sendKeys(BaseData.cardNumber());
    }

    public void enterApxxMonth() {
        expMonth.sendKeys(BaseData.cardMonth());
    }

    public void enterApxxCardYear() {
        expYear.sendKeys(BaseData.cardYear());
    }

    public void enterApexxCvv() {
        cvv.sendKeys(BaseData.cardCvv());
    }

    public void clickApxxPay() {
        payBtn.click();
    }

    public void enterStripeCardNum() {
        cardNumber.sendKeys(BaseData.stripeCardNumber());
    }

    public void wrongCardNumberStripe() {
        cardNumber.sendKeys("4456530000001096");
    }

    public void enterStripeExpdate() {

        expDate.sendKeys(BaseData.stripeCardExpiry());
    }

    public void enterStripeCvc() {
        cvc.sendKeys(BaseData.cardCvv());
    }

    public void stripePayClick() throws InterruptedException {
        paymentInfoLebel.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        stripePayBtn.click();
    }

}
