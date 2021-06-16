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
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TestCurbSideASAPMCA extends Base{
	
	public IOSDriver<IOSElement> IOSdriver = null;
	@BeforeMethod
	public void initialize() throws Exception {
		DesiredCapabilities dc = sendIOSBrowserCapabilities();
		IOSdriver = new IOSDriver<>(new URL(prop.getProperty("CloudDeviceURL")), dc);
		getDriver(IOSdriver);
		IOSdriver.rotate(ScreenOrientation.PORTRAIT);
		prop = returnProperty();
		editProperties(prop, "Android_Browser", "false");
		editProperties(prop, "IOS_Browser", "true");
		editProperties(prop, "Android_App", "false");
		editProperties(prop, "IOS_App", "false");
		IOSdriver.get(prop.getProperty("url"));
		IOSdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		IOSdriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
	}
	
	
	@Test
	public void TC13_IOS_SAFARI_CurbsideASAPOrder_MCAUser_TestCase() throws Exception{
		
		Logger log = LogManager.getLogger("TestMcaCurbside-ASAPOrder");
		log.info("******TC13_IOS_SAFARI : Validate user is able to place Curbside-ASAP order for MCA user in IOS SAFARI Browser*******");
		FunctionalComponents curb = new FunctionalComponents(IOSdriver,log);
		curb.validateQASite();
		curb.closePopupRewards();
		curb.clickMenuButton();
		curb.selectLogin();
		curb.enterUserName();
		curb.enterPassword();
		curb.clickSignin();
		curb.clickMenuButton();
		curb.selectLocationsOption();
		curb.enterRestaurantLocationForLoggedInOrder();
		curb.clickSearchButtonForLoggedInOrder();
		curb.getRestaurantName();
		curb.orderNow();
		curb.AddChilisFavouriteToCart();
		curb.clickViewCart();
		curb.selectSilverWare();
		curb.clickCheckOut();
		curb.selectCurbside();
		curb.selectPickupAsapIOS();
		curb.continueToPayment();
		curb.enterCardNo();
		curb.selectExpirationMonthIOS();
		curb.selectExpirationYearIOS();
		curb.enterNameOnCard();
		curb.enterBillingZipCode();
		curb.enterCVV();
		curb.giveTip();
		curb.checkRoundOff();
		String priceBeforePlacingOrder = curb.OrderTotal();
		curb.placeOrder();
		System.out.println(curb.getSuccessMessageforLoggedInOrder());
		String priceAfterPlacingOrder = curb.returnOrderPrice();
		Assert.assertEquals(priceBeforePlacingOrder,priceAfterPlacingOrder,"Incorrect price displayed");
	}
	
	@AfterMethod
	public void  tearDown() {
		System.out.println("Report URL: " + IOSdriver.getCapabilities().getCapability("reportUrl"));
		IOSdriver.quit();
	}
	
	

}