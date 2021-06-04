package MobileAutomation.MobileSeeTestWebAutomation;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Resources.Base;
import Resources.FunctionalComponents;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TestLoggedInOrder extends Base {
	@Test
	public void testLoggedInOrderFeature() throws InterruptedException {
		Logger log = LogManager.getLogger("TestLoggedInOrder");
		log.info("******STARTING TEST LOGGED IN ORDER******");
		FunctionalComponents ob = new FunctionalComponents(driver, log);
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
		ob.selectSilverWare();
		ob.clickCheckOut();
		ob.continueToPayment();
		ob.enterCardNo();
		ob.enterCVV();
		ob.selectExpirationMonth();
		ob.selectExpirationYear();
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
}
