package MobileAutomation.MobileSeeTestWebAutomation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import Resources.Base;
import Resources.FunctionalComponents;

public class TestMCACurbSideCustomization extends Base{
	
	@Test
	public void testCurbsideAsapOrder() throws Exception{
		
		Logger log = LogManager.getLogger("TestMcaCurbside-ASAPOrder");
		log.info("******STARTING TEST MCA CURBSIDE CUSTOMIZATION*******");
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
		curb.clickSearchButton();
		curb.getRestaurantName();
		curb.orderNow();
		curb.clickMenuCatagory();
		curb.clickItemOrder();
		curb.customizeOrder();
		curb.clickAddItem();
		curb.clickViewCart();
		curb.validateCustomization();
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
