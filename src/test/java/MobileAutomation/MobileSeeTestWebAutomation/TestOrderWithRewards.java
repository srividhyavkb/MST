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
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TestOrderWithRewards extends Base {
	
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
	@Test(priority=12)
	public void TC12_IOS_SAFARI_PlaceOrderWithRewardsTestCase() throws Exception{
		Logger log = LogManager.getLogger("TestOrderWithRewards");
		log.info("******TC12_IOS_SAFARI : Starting to validate user is able to  place order with Rewards. For sign in user********");
		FunctionalComponents aplyrwds = new FunctionalComponents(IOSdriver,log);
		aplyrwds.validateQASite();
		aplyrwds.closePopupRewards();
		aplyrwds.clickMenuButton();
		aplyrwds.selectLogin();
		aplyrwds.enterUserName();
		aplyrwds.enterPassword();
		aplyrwds.clickSignin();
		aplyrwds.clickMenuButton();
		aplyrwds.selectLocationsOption();
		aplyrwds.enterRestaurantLocationForLoggedInOrder();;
		aplyrwds.clickSearchButtonForLoggedInOrder();
		aplyrwds.getRestaurantName();
		aplyrwds.orderNow();
		aplyrwds.AddChilisFavouriteToCart();
		aplyrwds.clickViewCart();
		aplyrwds.changeQuantityIOS();
		aplyrwds.selectSilverWare();
		aplyrwds.addReward();
		aplyrwds.clickCheckOut();
		aplyrwds.addRewardItem();
		aplyrwds.guestClickViewCart();
		aplyrwds.changeQuantityIOS();
		aplyrwds.addReward();
		aplyrwds.clickCheckOut();
		aplyrwds.clickOnCarryOut();
		aplyrwds.selectPickupLaterTodayIOS();
		aplyrwds.selectPickupTimeIOS();
		aplyrwds.isRewardApplied();
		aplyrwds.continueToPayment();
		aplyrwds.enterCardNo();
		aplyrwds.enterCVV();
		aplyrwds.selectExpirationMonthIOS();
		aplyrwds.selectExpirationYearIOS();
		aplyrwds.enterNameOnCard();
		aplyrwds.enterBillingZipCode();
		aplyrwds.giveTip();
		aplyrwds.checkRoundOff();
		String priceBeforePlacingOrder = aplyrwds.OrderTotal();
		aplyrwds.placeOrder();
		System.out.println(aplyrwds.getSuccessMessageforLoggedInOrder());
		String priceAfterPlacingOrder = aplyrwds.returnOrderPrice();
		Assert.assertEquals(priceBeforePlacingOrder,priceAfterPlacingOrder,"Incorrect price displayed");

}


@AfterMethod
public void  tearDown() {
	System.out.println("Report URL: " + IOSdriver.getCapabilities().getCapability("reportUrl"));
	IOSdriver.quit();
}
}
