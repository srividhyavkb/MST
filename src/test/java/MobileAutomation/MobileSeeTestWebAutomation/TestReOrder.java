package MobileAutomation.MobileSeeTestWebAutomation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import Resources.Base;
import Resources.FunctionalComponents;

public class TestReOrder extends Base {
	@Test
	public void testReOrder() throws Exception {
		Logger log = LogManager.getLogger("TestReorder");
		log.info("******STARTING TEST RE-ORDER******");
		FunctionalComponents reorder = new FunctionalComponents(driver, log);
		reorder.validateQASite();
		reorder.closePopupRewards();
		reorder.clickMenuButton();
		reorder.selectLogin();
		reorder.enterUserName();
		reorder.enterPassword();
		reorder.clickSignin();
		reorder.clickMenuButton();
		reorder.selectReorderOption();
		reorder.clickReorderforanOrder();
		reorder.changeQuantity();
		reorder.selectSilverWare();
		reorder.clickCheckOut();
		reorder.continueToPayment();
		reorder.enterCVV();
		reorder.giveTip();
		reorder.checkRoundOff();
		String priceBeforePlacingOrder = reorder.OrderTotal();
		reorder.placeOrder();
		System.out.println(reorder.getSuccessMessageforLoggedInOrder());
		String priceAfterPlacingOrder = reorder.returnOrderPrice();
		Assert.assertEquals(priceBeforePlacingOrder, priceAfterPlacingOrder, "Incorrect price displayed");
	}

}