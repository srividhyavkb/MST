package MobileAutomation.MobileSeeTestWebAutomation;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import Resources.Base;
import Resources.FunctionalComponents;

public class TestCurbSideASAPMCA extends Base{
	
	Properties property=new Properties();
	@Test
	public void testCurbsideAsapOrder() throws Exception{
		
		Logger log = LogManager.getLogger("TestMcaCurbside-ASAPOrder");
		log.info("******STARTING TEST MCA CURBSIDE-ASAP Order*******");
		FunctionalComponents curb = new FunctionalComponents(driver,log);
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
		curb.selectPickupAsap();
		curb.continueToPayment();
		curb.enterCardNo();
		curb.selectExpirationMonth();
		curb.selectExpirationYear();
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
	
	

}