package MobileAutomation.MobileSeeTestWebAutomation;


import java.net.URL;
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
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TestMCACarryOutLaterToday extends Base {
	
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
		IOSdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		IOSdriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
	}
	
	@Test/*( enabled=true, retryAnalyzer = Resources.RetryAnalyzer.class )*/
	public void testMCACarryoutASAP() throws InterruptedException
	{
		Logger log = LogManager.getLogger("TestMCACarryoutASAP");
		log.info("******STARTING TEST MCA CARRY OUT ASAP******");
		FunctionalComponents ob = new FunctionalComponents(IOSdriver,log);
		ob.validateQASite();
		ob.closePopupRewards();
		ob.clickMenuButton();
		ob.selectLogin();
		ob.enterUserName();
		ob.enterPassword();
		ob.clickSignin();
		ob.clickMenuButton();
		ob.selectLocationsOption();
		ob.enterRestaurantLocationForLoggedInOrder();
		ob.clickSearchButtonForLoggedInOrder();
		ob.orderNow();
		ob.AddChilisFavouriteToCart();
		ob.clickViewCart();
		ob.clickCheckOut();
		ob.clickOnCarryOut();
		ob.selectPickupLaterTodayIOS();
		ob.selectPickupTimeIOS();
		ob.continueToPayment();
		ob.enterCardNo();
		ob.enterCVV();
		ob.selectExpirationMonthIOS();
		ob.selectExpirationYearIOS();
		ob.enterNameOnCard();
		ob.enterBillingZipCode();
		ob.giveTip();
		ob.checkRoundOff();
		String priceBeforePlacingOrder = ob.OrderTotal();
		ob.placeOrder();
		System.out.println(ob.getSuccessMessageforLoggedInOrder());
		String priceAfterPlacingOrder = ob.returnOrderPrice();
		Assert.assertEquals(priceBeforePlacingOrder, priceAfterPlacingOrder, "Incorrect price displayed");
	}
	
	@AfterMethod
	public void  tearDown() {
		System.out.println("Report URL: " + IOSdriver.getCapabilities().getCapability("reportUrl"));
		IOSdriver.quit();
	}

}
