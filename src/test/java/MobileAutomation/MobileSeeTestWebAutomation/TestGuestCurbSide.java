package MobileAutomation.MobileSeeTestWebAutomation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import Resources.Base;
import Resources.FunctionalComponents;

public class TestGuestCurbSide extends Base {
	@Test
	public void testGuestCurbSideASAP() throws InterruptedException {
		Logger log = LogManager.getLogger("TestGuestCurbsideOrder");
		log.info("******STARTING TEST GUEST CURBSIDE ORDER******");
		FunctionalComponents ob = new FunctionalComponents(driver, log);
		ob.validateQASite();
		ob.closePopupRewards();
		ob.clickMenuButton();
		ob.selectLocationsOption();
		ob.enterRestaurantLocation();
		ob.clickSearchButton();
		ob.clickOrderNow();
		ob.clickMenuCatagory();
		ob.clickItemOrder();
		ob.clickAddItem();
		ob.noClickViewCart();
		ob.clickCheckOut();
		ob.selectCurbside();
		ob.selectPickupDate();
		ob.selectPickupTime();
		ob.enterFirstName();
		ob.enterLastName();
		ob.enterContactNumber();
		ob.enterEMail();
		ob.enterVehicleMake();
		ob.enterVehicleModel();
		ob.enterVehicleColor();
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