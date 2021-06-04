package MobileAutomation.MobileSeeTestWebAutomation;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Resources.Base;
import Resources.BaseIOS;
import Resources.FunctionalComponents;
import Resources.FunctionalComponentsIOS;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TestLoggedInOrderIOS extends BaseIOS {
	@Test
	public void testLoggedInOrderFeature() throws InterruptedException {
		Logger log = LogManager.getLogger("TestLoggedInOrderIOS");
		log.info("******STARTING TEST LOGGED IN ORDER IN IOS DEVICE******");
		FunctionalComponentsIOS ob = new FunctionalComponentsIOS(driver, log);
		ob.validateQASite();
		ob.closePopupRewards();
		ob.clickMenuButton();
		ob.selectLogin();
		ob.enterUserName();
		ob.enterPassword();
		ob.clickSignin();
		ob.clickMenuButton();
		ob.selectLocationsOption();
		ob.enterRestaurantLocationForLoggedInOrder(prop.getProperty("location"));
		ob.clickSearchButtonForLoggedInOrder();
		ob.orderNow();
		ob.AddChilisFavouriteToCart();
		ob.clickViewCart();
		ob.selectSilverWare();
		ob.clickCheckOut();
		ob.continueToPayment();
		ob.enterCVV();
		ob.giveTip();
		ob.checkRoundOff();
		String priceBeforePlacingOrder = ob.OrderTotal();
		ob.placeOrder();
		System.out.println(ob.getSuccessMessageforLoggedInOrder());
		String priceAfterPlacingOrder = ob.returnOrderPrice();
		Assert.assertEquals(priceBeforePlacingOrder, priceAfterPlacingOrder, "Incorrect price displayed");
	}
}