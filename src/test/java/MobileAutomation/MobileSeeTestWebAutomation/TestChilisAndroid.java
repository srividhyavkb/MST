package MobileAutomation.MobileSeeTestWebAutomation;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Resources.Base;
import Resources.FunctionalComponents;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TestChilisAndroid extends Base {
	
	@Test( enabled=true, retryAnalyzer = Resources.RetryAnalyzer.class )
	public void testLoginFeature() throws Exception {
		Logger log = LogManager.getLogger("TestLoginLogout");
		log.info("******Starting to Validate Login and Logout functionality of Chilis WebApp******");
		FunctionalComponents func = new FunctionalComponents(driver, log);
		func.validateQASite();
		func.closePopupRewards();
		func.clickMenuButton();
		func.selectLogin();
		func.enterUserName();
		func.enterPassword();
		func.clickSignin();
		func.clickMenuButton();
		func.logout();
		System.out.println(func.validateLogout());
	}

	@Test( enabled=false, retryAnalyzer = Resources.RetryAnalyzer.class )
	public void testRewardsFeature() throws InterruptedException {
		Logger log = LogManager.getLogger("TestRewards");
		log.info("******Starting to validate Rewards details page for logged in user******");
		FunctionalComponents func3 = new FunctionalComponents(driver, log);
		func3.validateQASite();
		func3.closePopupRewards();
		func3.clickMenuButton();
		func3.selectLogin();
		func3.enterUserName();
		func3.enterPassword();
		func3.clickSignin();
		int noOfRewardsDisplayed = func3.noOfRewards();
		int ActualrewardCount = func3.ActualRewardsCount();
		Assert.assertEquals(noOfRewardsDisplayed, ActualrewardCount, "Discrepancy observed in Rewards Count");
		func3.getRewardTitle();
	}

	@Test( enabled=false, retryAnalyzer = Resources.RetryAnalyzer.class )
	public void testUpdateAccountFeature() throws InterruptedException {
		Logger log = LogManager.getLogger("TestUpdateAccount");
		log.info("******Starting to validate user is able to update my Account******");
		FunctionalComponents func4 = new FunctionalComponents(driver, log);
		func4.validateQASite();
		func4.closePopupRewards();
		func4.clickMenuButton();
		func4.selectLogin();
		func4.enterUserName();
		func4.enterPassword();
		func4.clickSignin();
		func4.clickMenuButton();
		func4.selectMyAccountOption();
		func4.getFirstNameBeforeUpdate();
		func4.getLastNameBeforeUpdate();
		func4.getEmailBeforeUpdate();
		func4.getZipCodeBeforeUpdate();
		func4.updateFirstName();
		func4.updateLastName();
		func4.updateEmail();
		func4.updateZipCode();
		func4.checkConsent();
		func4.clickUpdateButton();
		func4.getSuccessMessage();
		func4.getFirstNameAfterUpdate();
		func4.getLastNameAfterUpdate();
		func4.getEmailAfterUpdate();
		func4.getZipCodeAfterUpdate();
		func4.ValidateUpdate();

	}

	@Test( enabled=false, retryAnalyzer = Resources.RetryAnalyzer.class )
	public void testFavouriteItems() throws InterruptedException {
		Logger log = LogManager.getLogger("TestFavouriteItems");
		log.info("******Starting to verify Favorites items is displaying on menu page******");
		FunctionalComponents func5 = new FunctionalComponents(driver, log);
		func5.validateQASite();
		func5.closePopupRewards();
		func5.clickMenuButton();
		func5.selectLogin();
		func5.enterUserName();
		func5.enterPassword();
		func5.clickSignin();
		func5.clickMenuButton();
		func5.selectMenuOption();
		func5.getAllChilisFavouriteItems();
	}

	@Test( enabled=false, retryAnalyzer = Resources.RetryAnalyzer.class )
	public void testLocationFeature() throws Exception {
		Logger log = LogManager.getLogger("TestLocationSearch");
		log.info("******Starting to validate location search fields and links for guest user******");
		FunctionalComponents func2 = new FunctionalComponents(driver, log);
		func2.validateQASite();
		func2.closePopupRewards();
		func2.clickMenuButton();
		func2.selectLocationsOption();
		func2.enterRestaurantLocationforSearch();
		func2.clickSearchButton();
		String result = func2.getRestaurantName();
		System.out.println(result);
	}
	
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
	
	@Test
	public void testAddMyVisit() throws Exception
	{
	Logger log = LogManager.getLogger("TestAddMyVisit");
	log.info("******STARTING TEST ADD MY VISIT******");
	FunctionalComponents func6= new FunctionalComponents(driver,log);
	func6.validateQASite();
	func6.closePopupRewards();
	func6.clickMenuButton();
	func6.selectLogin();
	func6.enterUserName();
	func6.enterPassword();
	func6.clickSignin();
	func6.clickAddVisit();
	func6.enterRestaurantLocation();
	func6.selectChilisLocation();
	func6.selectVisitMonth();
	func6.selectVisitDay();
	func6.selectVisitYear();
	func6.enterCheckNumber();
	func6.enterCheckTotal();
	func6.clickSubmit();
	System.out.println(func6.retrieveSuccessMessage());
	}
	
	@Test
	public void testLoggedInOrderFeature() throws InterruptedException {
		Logger log = LogManager.getLogger("TestLoggedInOrder");
		log.info("******STARTING TEST LOGGED IN ORDER******");
		FunctionalComponents ob = new FunctionalComponents(driver, log);
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
		ob.clickSearchButtonForLoggedInOrder();
		ob.orderNow();
		ob.AddChilisFavouriteToCart();
		ob.clickViewCart();
		ob.selectSilverWare();
		ob.clickCheckOut();
		ob.continueToPayment();
		ob.enterCardNo();
		ob.enterCVV();
		ob.selectExpirationMonth();
		ob.selectExpirationYear();
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
	
	@Test/*( enabled=true, retryAnalyzer = Resources.RetryAnalyzer.class )*/
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
		ob.clickSearchButtonForLoggedInOrder();
		ob.orderNow();
		ob.AddChilisFavouriteToCart();
		ob.clickViewCart();
		ob.clickCheckOut();
		ob.clickOnCarryOut();
		ob.selectPickupLaterToday();
		ob.selectPickupTime();
		ob.continueToPayment();
		ob.enterCardNo();
		ob.enterCVV();
		ob.selectExpirationMonth();
		ob.selectExpirationYear();
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
