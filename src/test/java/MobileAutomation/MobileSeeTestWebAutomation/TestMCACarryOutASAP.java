package MobileAutomation.MobileSeeTestWebAutomation;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import Resources.Base;
import Resources.FunctionalComponents;

public class TestMCACarryOutASAP extends Base {
	
	@Test
	public void testMCACarryoutASAP() throws InterruptedException
	{
		Logger log = LogManager.getLogger("TestMCACarryoutASAP");
		log.info("******STARTING TEST MCA CARRY OUT ASAP******");
		FunctionalComponents ob = new FunctionalComponents(driver,log);
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
		ob.clickSearchButton();
		ob.clickOrderNow();
		ob.AddChilisFavouriteToCart();
		ob.clickViewCart();
		ob.clickCheckOut();
		ob.clickOnCarryOut();
		ob.selectPickupAsap();
		ob.continueToPayment();
		ob.enterCardNo();
		ob.enterCVV();
		ob.selectExpirationMonth();
		ob.selectExpirationMonth();
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
