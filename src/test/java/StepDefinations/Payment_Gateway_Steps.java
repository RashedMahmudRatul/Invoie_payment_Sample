package StepDefinations;

import Pages.Payment_Gateway_Page;
import Utility.Driver_Setup;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class Payment_Gateway_Steps {
    public WebDriver driver;
    Payment_Gateway_Page paymentpage;
    public Payment_Gateway_Steps() throws IOException {
        this.driver = Driver_Setup.getDriver();
        paymentpage = new Payment_Gateway_Page(driver);
    }
    public void waitload(){
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
    @And("enter card details in Stripe and clicks on pay")
    public void enter_card_details_in_payment_information() throws InterruptedException {
            System.out.println("Stripe Appeared");
            Thread.sleep(1000);
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@name,'__privateStripeFrame')]")));
            paymentpage.enterStripeCardNum();
            paymentpage.enterStripeExpdate();
            paymentpage.enterStripeCvc();
            Thread.sleep(500);
            String windowHandle = driver.getWindowHandle(); // save the original window handle
            driver.switchTo().window(windowHandle);
            Thread.sleep(1500);
            paymentpage.stripePayClick();
            Thread.sleep(2000);
            waitload();
    }

    @And("enter invalid card details in Stripe and clicks on pay")
    public void enter_invalid_card_details_in_payment_information() throws InterruptedException {
            System.out.println("Stripe Appeared");
            Thread.sleep(1000);
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@name,'__privateStripeFrame')]")));
            paymentpage.wrongCardNumberStripe();
            paymentpage.enterStripeExpdate();
            paymentpage.enterStripeCvc();
            Thread.sleep(500);
            String windowHandle = driver.getWindowHandle(); // save the original window handle
            driver.switchTo().window(windowHandle);
            Thread.sleep(1500);
            paymentpage.stripePayClick();
            Thread.sleep(2000);
            waitload();
    }

    @And("enter card details in Apexx and clicks on pay")
    public void enter_card_details_in_apexx_and_clicks_on_pay() throws InterruptedException {
            waitload();
            System.out.println("Apex Appeared");
            driver.switchTo().frame("apex-frame");
            paymentpage.enterApxxCardNum();
            paymentpage.enterApxxMonth();
            paymentpage.enterApxxCardYear();
            paymentpage.enterApexxCvv();
            Thread.sleep(1500);
            paymentpage.clickApxxPay();
            String windowHandle = driver.getWindowHandle(); //save the original window handle
            driver.switchTo().window(windowHandle);
            waitload();

    }

}