package MobileAutomation.MobileSeeTestWebAutomation;

import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
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
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TestChilisAndroid extends Base {

	public AndroidDriver<AndroidElement> driver = null;
	@BeforeMethod
	public void initialize() throws Exception {
		DesiredCapabilities dc = sendAndroidBrowserCapabilities();
		driver = new AndroidDriver<>(new URL(prop.getProperty("CloudDeviceURL")), dc);
		getDriver(driver);
		driver.rotate(ScreenOrientation.PORTRAIT);
		prop = returnProperty();
		editProperties(prop, "Android_Browser", "true");
		editProperties(prop, "IOS_Browser", "false");
		editProperties(prop, "Android_App", "false");
		editProperties(prop, "IOS_App", "false");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	
	
	@Test(priority=1)
	public void TC01_ANDROID_CHROME_LoginLogoutTestCase() throws Exception {
		Logger log = LogManager.getLogger("TestLoginLogout");
		log.info("******TC01_ANDROID_CHROME : Starting to Validate Login and Logout functionality of Chilis WebApp******");
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

	@Test(priority=2)
	public void TC02_ANDROID_CHROME_RewardsValidationTestCase() throws InterruptedException {
		Logger log = LogManager.getLogger("TestRewards");
		log.info("******TC02_ANDROID_CHROME : Starting to validate Rewards details page for logged in user******");
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

	@Test(priority=3)
	public void TC03_ANDROID_CHROME_UpdateMyAccountTestCase() throws InterruptedException {
		Logger log = LogManager.getLogger("TestUpdateAccount");
		log.info("******TC03_ANDROID_CHROME : Starting to validate user is able to update my Account******");
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

	@Test(priority=4)
	public void TC04_ANDROID_CHROME_ValidateFavouriteItemsTestCase() throws InterruptedException {
		Logger log = LogManager.getLogger("TestFavouriteItems");
		log.info("******TC04_ANDROID_CHROME : Starting to verify Favorites items is displaying on menu page******");
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

	@Test(priority=5)
	public void TC05_ANDROID_CHROME_ValidateLocationSearchTestCase() throws Exception {
		Logger log = LogManager.getLogger("TestLocationSearch");
		log.info("******TC05_ANDROID_CHROME : Starting to validate location search fields and links for guest user******");
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

	@Test(priority=6)
	public void TC06_ANDROID_CHROME_CurbsideASAPorder_MCAUser_TestCase() throws Exception {

		Logger log = LogManager.getLogger("TestMcaCurbside-ASAPOrder");
		log.info("******TC06_ANDROID_CHROME : Starting to validate user is able to place Curbside-ASAP order for MCA user*******");
		FunctionalComponents curb = new FunctionalComponents(driver, log);
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
		Assert.assertEquals(priceBeforePlacingOrder, priceAfterPlacingOrder, "Incorrect price displayed");
	}

	@Test(priority=7)
	public void TC07_ANDROID_CHROME_AddMyVisitTestCase() throws Exception {
		Logger log = LogManager.getLogger("TestAddMyVisit");
		log.info("******TC07_ANDROID_CHROME : Starting to verify user is able to submit Add my visit form for login member******");
		FunctionalComponents func6 = new FunctionalComponents(driver, log);
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

	@Test(priority=8)
	public void TC08_ANDROID_CHROME_LoggedInUserOrderTestCase() throws InterruptedException {
		Logger log = LogManager.getLogger("TestLoggedInOrder");
		log.info("******TC08_ANDROID_CHROME : Starting to validate user is able order placement with existing log in user******");
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

	@Test(priority=9)
	public void TC09_ANDROID_CHROME_CarryOutLaterTodayOrder_MCAUser_TestCase() throws InterruptedException {
		Logger log = LogManager.getLogger("TestMCACarryoutLaterToday");
		log.info("******TC09_ANDROID_CHROME : Starting to validate user is able to place Carry out-Later Today order with MCA users******");
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
	
	@Test(priority=10)
	public void TC10_ANDROID_CHROME_CurbsideOrder_GuestUser_TestCase() throws InterruptedException {
		Logger log = LogManager.getLogger("TestGuestCurbsideOrder");
		log.info("******TC10_ANDROID_CHROME : Starting to validate user is able to place Curbside order. For Guest user******");
		FunctionalComponents ob = new FunctionalComponents(driver, log);
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
		ob.guestClickViewCart();
		ob.clickCheckOut();
		ob.selectCurbside();
		ob.selectPickupAsap();
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
		ob.selectExpirationYear();
		ob.enterNameOnCard();
		ob.enterBillingZipCode();
		String priceBeforePlacingOrder = ob.OrderTotal();
		ob.placeOrder();
		System.out.println(ob.getSuccessMessageforLoggedInOrder());
		String priceAfterPlacingOrder = ob.returnOrderPrice();
		Assert.assertEquals(priceBeforePlacingOrder, priceAfterPlacingOrder, "Incorrect price displayed");
	}
	
	@Test(priority=11)
	public void TC11_ANDROID_CHROME_CarryOutASAPorder_MCAUser_TestCase() throws InterruptedException
	{
		Logger log = LogManager.getLogger("TestMCACarryoutASAP");
		log.info("******TC11_ANDROID_CHROME : Starting to validate user is able to place Carry out ASAP order with MCA users******");
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
		ob.selectPickupAsap();
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
	
	@Test(priority=12)
	public void TC12_ANDROID_CHROME_PlaceOrderWithRewardsTestCase() throws Exception{
		Logger log = LogManager.getLogger("TestOrderWithRewards");
		log.info("******TC12_ANDROID_CHROME : Starting to validate user is able to  place order with Rewards. For sign in user********");
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
		aplyrwds.clickSearchButtonForLoggedInOrder();
		aplyrwds.getRestaurantName();
		aplyrwds.orderNow();
		aplyrwds.AddChilisFavouriteToCart();
		aplyrwds.clickViewCart();
		aplyrwds.changeQuantity();
		aplyrwds.selectSilverWare();
		aplyrwds.addReward();
		aplyrwds.clickCheckOut();
		aplyrwds.addRewardItem();
		aplyrwds.guestClickViewCart();
		aplyrwds.changeQuantity();
		aplyrwds.addReward();
		aplyrwds.clickCheckOut();
		aplyrwds.clickOnCarryOut();
		aplyrwds.selectPickupLaterToday();
		aplyrwds.selectPickupTime();
		aplyrwds.isRewardApplied();
		aplyrwds.continueToPayment();
		aplyrwds.enterCardNo();
		aplyrwds.enterCVV();
		aplyrwds.selectExpirationMonth();
		aplyrwds.selectExpirationYear();
		aplyrwds.enterNameOnCard();
		aplyrwds.enterBillingZipCode();
		aplyrwds.giveTip();
		aplyrwds.checkRoundOff();
		String priceBeforePlacingOrder = aplyrwds.OrderTotal();
		aplyrwds.placeOrder();
		System.out.println(aplyrwds.getSuccessMessageforLoggedInOrder());
		String priceAfterPlacingOrder = aplyrwds.returnOrderPrice();
		Assert.assertEquals(priceBeforePlacingOrder,priceAfterPlacingOrder,"Incorrect price displayed");

}
	@Test(priority=13)
	public void TC13_ANDROID_CHROME_CurbsideASAPorder_MCAUser_TestCase() throws Exception{
		
		Logger log = LogManager.getLogger("TestMcaCurbsideASAPOrder");
		log.info("******TC13_ANDROID_CHROME : Starting to validate user is able to place Curbside-ASAP order for MCA user*******");
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


	@AfterMethod
	public void tearDown() {
		System.out.println("Report URL: " + driver.getCapabilities().getCapability("reportUrl"));
		driver.quit();
		
	}

}
