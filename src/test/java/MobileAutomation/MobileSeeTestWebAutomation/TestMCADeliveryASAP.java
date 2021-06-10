package MobileAutomation.MobileSeeTestWebAutomation;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Resources.Base;
import Resources.FunctionalComponents;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TestMCADeliveryASAP extends Base{
	
	public AndroidDriver<AndroidElement> driver = null;
	@BeforeMethod
	public void initialize() throws Exception {
		DesiredCapabilities dc = sendAndroidBrowserCapabilities();
		driver = new AndroidDriver<>(new URL(prop.getProperty("CloudDeviceURL")), dc);
		getDriver(driver);
		driver.rotate(ScreenOrientation.PORTRAIT);
		prop = returnProperty();
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void testMcaDeliveryAsap() throws Exception {
		
		Logger log = LogManager.getLogger("TestMcaDelivery-Asap");
		log.info("******STARTING TEST MCA DELIVERY-ASAP******");
		FunctionalComponents testfc = new FunctionalComponents(driver,log);
		testfc.validateQASite();
		testfc.closePopupRewards();
		testfc.clickMenuButton();
		testfc.selectLogin();
		testfc.enterUserName();
		testfc.enterPassword();
		testfc.clickSignin();
		testfc.clickMenuButton();
		testfc.selectLocationsOption();
		testfc.enterRestaurantLocationForLoggedInOrder();
		testfc.clickSearchButtonForLoggedInOrder();
		testfc.getRestaurantName();
		testfc.orderNow();
		testfc.AddChilisFavouriteToCart();
		testfc.clickViewCart();
		testfc.selectSilverWare();
		testfc.clickCheckOut();
		testfc.selectDelivery();
		testfc.enterDeliveryLocation();
		testfc.enterApartmentNo();
		testfc.enterDeliveryInstrBox();
		testfc.selectDeliveryDateASAP();
		testfc.continueToPayment();
		testfc.enterCardNo();
		testfc.selectExpirationMonth();
		testfc.selectExpirationYear();
		testfc.enterNameOnCard();
		testfc.enterBillingZipCode();
		testfc.enterCVV();
		testfc.giveTip();
		testfc.checkRoundOff();
		String priceBeforePlacingOrder =testfc.OrderTotal();
		testfc.placeOrder();
		System.out.println(testfc.getSuccessMessageforLoggedInOrder());
		String priceAfterPlacingOrder =testfc.returnOrderPrice();
		Assert.assertEquals(priceBeforePlacingOrder,priceAfterPlacingOrder,"Incorrect price displayed");
	}
	
	@AfterMethod
	public void tearDown() {
		System.out.println("Report URL: " + driver.getCapabilities().getCapability("reportUrl"));
		driver.quit();
		
	}

}
