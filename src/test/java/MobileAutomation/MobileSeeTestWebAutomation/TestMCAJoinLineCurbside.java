package MobileAutomation.MobileSeeTestWebAutomation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import Resources.Base;
import Resources.FunctionalComponents;

import Resources.FunctionalComponents;

public class TestMCAJoinLineCurbside {

	
	@Test
	public void testMCAJoinLineCurbsideASAP() throws InterruptedException
	{
		Logger log = LogManager.getLogger("TestMCAJoinLineCurbsideASAP");
		log.info("******STARTING TEST MCA JOIN LINE CURBSIDE ASAP******");
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
		ob.enterRestaurantLocation();
		ob.clickSearchButton();
		ob.initiateJoinLine();
		ob.clickMinimumPartySize();
		ob.enterNameJoinLine();
		ob.enterContactNumberJoinLine();
		ob.clickJoinLine();
		ob.clickMenuCatagory();
		ob.clickItemOrder();
		ob.clickAddItem();
		ob.noClickViewCart();
		ob.clickCheckOut();
		ob.selectCurbside();
		ob.selectPickupDate();
		ob.continueToPayment();
		ob.enterCardNo();
		ob.enterCVV();
		ob.selectExpirationMonth();
		ob.selectExpirationYear();
		ob.enterNameOnCard();
		ob.enterBillingZipCode();
		String priceBeforePlacingOrder = ob.OrderTotal();
		ob.placeOrder();
		System.out.println(ob.getSuccessMessageforLoggedInOrder());
		String priceAfterPlacingOrder = ob.returnOrderPrice();
		Assert.assertEquals(priceBeforePlacingOrder, priceAfterPlacingOrder, "Incorrect price displayed");
	}
}
