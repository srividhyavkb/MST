package MobileAutomation.MobileSeeTestWebAutomation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import Resources.Base;
import Resources.FunctionalComponents;

public class TestGuestCarryOutCustomization extends Base {
	
	@Test
	public void testGuestCOFCust() throws InterruptedException
	{
		Logger log = LogManager.getLogger("TestGuestCarryOutFutureCustomized");
		log.info("******STARTING TEST GUEST CARRY OUT FUTURE CUSTOMIZED******");
		FunctionalComponents ob = new FunctionalComponents(driver,log);
		ob.validateQASite();
		ob.closePopupRewards();
		ob.clickMenuButton();
		ob.selectLocationsOption();
		ob.enterRestaurantLocation();
		ob.clickSearchButton();
		ob.clickOrderNow();
		ob.clickMenuCatagory();
		ob.clickItemOrder();
		ob.customizeOrder();
		ob.clickAddItem();
		ob.noClickViewCart();
		ob.validateCustomization();
		ob.clickCheckOut();
		ob.clickOnCarryOut();
		ob.selectPickupForFuture();
		ob.selectPickupTime();
		ob.enterFirstName();
		ob.enterLastName();
		ob.enterContactNumber();
		ob.enterEMail();
		ob.placeOrder();
	}

}
