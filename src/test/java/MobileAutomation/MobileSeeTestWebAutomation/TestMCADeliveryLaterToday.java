package MobileAutomation.MobileSeeTestWebAutomation;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import Resources.Base;
import Resources.FunctionalComponents;

public class TestMCADeliveryLaterToday extends Base{
	
	Properties property=new Properties();
	
	@Test
	public void testMcaDeliveryAsap() throws Exception {
		
		Logger log = LogManager.getLogger("TestMcaDelivery-Asap");
		log.info("******STARTING TEST MCA DELIVERY-ASAP******");
		FunctionalComponents testfc = new FunctionalComponents(driver,log);
		testfc.validateQASite();
		testfc.closePopupRewards();
		testfc.clickMenuButton();
		testfc.selectLogin();
		testfc.enterUserName();
		testfc.enterPassword();
		testfc.clickSignin();
		testfc.clickMenuButton();
		testfc.selectLocationsOption();
		testfc.enterRestaurantLocationForLoggedInOrder();
		testfc.clickSearchButton();
		testfc.getRestaurantName();
		testfc.orderNow();
		testfc.AddChilisFavouriteToCart();
		testfc.clickViewCart();
		testfc.selectSilverWare();
		testfc.clickCheckOut();
		testfc.selectDelivery();
		testfc.enterDeliveryLocation();
		testfc.enterApartmentNo();
		testfc.clickDeliveryInstrBox();
		testfc.selectDeliveryLaterToday();
		testfc.selectDeliveryTime();     
		testfc.continueToPayment();
		testfc.enterCardNo();
		testfc.selectExpirationMonth();
		testfc.selectExpirationYear();
		testfc.enterNameOnCard();
		testfc.enterBillingZipCode();
		testfc.enterCVV();
		testfc.giveTip();
		testfc.checkRoundOff();
		String priceBeforePlacingOrder =testfc.OrderTotal();
		testfc.placeOrder();
		System.out.println(testfc.getSuccessMessageforLoggedInOrder());
		String priceAfterPlacingOrder =testfc.returnOrderPrice();
		Assert.assertEquals(priceBeforePlacingOrder,priceAfterPlacingOrder,"Incorrect price displayed");
	}

}
