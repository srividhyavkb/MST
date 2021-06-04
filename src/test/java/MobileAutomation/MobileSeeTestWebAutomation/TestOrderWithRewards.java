package MobileAutomation.MobileSeeTestWebAutomation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import Resources.Base;

import Resources.FunctionalComponents;

public class TestOrderWithRewards extends Base {
	
	@Test
	public void testOrderWithRewards() throws Exception{
		Logger log = LogManager.getLogger("Test Order With Rewards");
		log.info("******STARTING TEST ORDER WITH REWARDS********");
		FunctionalComponents aplyrwds = new FunctionalComponents(driver,log);
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
		aplyrwds.clickSearchButton();
		aplyrwds.getRestaurantName();
		aplyrwds.orderNow();
		aplyrwds.AddChilisFavouriteToCart();
		aplyrwds.clickViewCart();
		aplyrwds.changeQuantity();
		aplyrwds.selectSilverWare();
		aplyrwds.addReward();
		aplyrwds.clickCheckOut();
		aplyrwds.addRewardItem();
		aplyrwds.noClickViewCart();
		aplyrwds.changeQuantity();
		aplyrwds.selectSilverWare();
		aplyrwds.addReward();
		aplyrwds.clickCheckOut();
		aplyrwds.clickOnCarryOut();
		aplyrwds.selectPickupLaterToday();
		aplyrwds.selectPickupTime();
		aplyrwds.isRewardApplied();
		aplyrwds.continueToPayment();
		aplyrwds.enterCVV();
		aplyrwds.giveTip();
		aplyrwds.checkRoundOff();
		String priceBeforePlacingOrder = aplyrwds.OrderTotal();
		aplyrwds.placeOrder();
		System.out.println(aplyrwds.getSuccessMessageforLoggedInOrder());
		String priceAfterPlacingOrder = aplyrwds.returnOrderPrice();
		Assert.assertEquals(priceBeforePlacingOrder,priceAfterPlacingOrder,"Incorrect price displayed");

}
}
