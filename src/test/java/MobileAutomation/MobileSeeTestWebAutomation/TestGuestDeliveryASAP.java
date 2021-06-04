package MobileAutomation.MobileSeeTestWebAutomation;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import Resources.Base;
import Resources.FunctionalComponents;

public class TestGuestDeliveryASAP extends Base {
@Test
public void testGuestDeliveryASAP() throws InterruptedException
{
	Logger log = LogManager.getLogger("TestGuestDeliveryASAP");
	log.info("******STARTING TEST GUEST DELIVERY ASAP******");
	FunctionalComponents ob = new FunctionalComponents(driver,log);
	ob.validateQASite();
	ob.closePopupRewards();
	ob.clickMenuButton();
	ob.selectLocationsOption();
	ob.enterRestaurantLocationforSearch();
	ob.clickSearchButton();
	ob.orderNow();
	ob.clickMenuCatagory();
	ob.clickItemOrder();
	ob.clickAddItem();
	ob.noClickViewCart();
	ob.clickCheckOutforGuestUser();
	ob.selectDelivery();
	ob.enterDeliveryLocation();
	ob.enterApartmentNo();
	ob.selectDeliveryDateASAP();
	ob.enterFirstName();
	ob.enterLastName();
	ob.enterContactNumber();
	ob.enterEMail();
	ob.continueToPayment();
	ob.enterCardNo();
	ob.enterCVV();
	ob.selectExpirationMonth();
	ob.selectExpirationMonth();
	ob.enterNameOnCard();
	ob.enterBillingZipCode();
	ob.placeOrder();
}
}