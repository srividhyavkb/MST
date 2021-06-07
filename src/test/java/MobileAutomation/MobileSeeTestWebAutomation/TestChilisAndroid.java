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
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	
	
	@Test(enabled = true,priority=1)
	public void testLoginFeature() throws Exception {
		Logger log = LogManager.getLogger("TestLoginLogout");
		log.info("******TC01_ANDROID : Starting to Validate Login and Logout functionality of Chilis WebApp******");
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

	@Test(enabled = false,priority=2)
	public void testRewardsFeature() throws InterruptedException {
		Logger log = LogManager.getLogger("TestRewards");
		log.info("******TC02_ANDROID : Starting to validate Rewards details page for logged in user******");
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

	@Test(enabled = false,priority=3)
	public void testUpdateAccountFeature() throws InterruptedException {
		Logger log = LogManager.getLogger("TestUpdateAccount");
		log.info("******TC03_ANDROID : Starting to validate user is able to update my Account******");
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

	@Test(enabled = false,priority=4)
	public void testFavouriteItems() throws InterruptedException {
		Logger log = LogManager.getLogger("TestFavouriteItems");
		log.info("******TC04_ANDROID : Starting to verify Favorites items is displaying on menu page******");
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

	@Test(enabled = false,priority=5)
	public void testLocationFeature() throws Exception {
		Logger log = LogManager.getLogger("TestLocationSearch");
		log.info("******TC05_ANDROID : Starting to validate location search fields and links for guest user******");
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

	@Test(enabled = false,priority=6)
	public void testCurbsideAsapOrder() throws Exception {

		Logger log = LogManager.getLogger("TestMcaCurbside-ASAPOrder");
		log.info("******TC06_ANDROID : Starting to validate user is able to place Curbside-ASAP order for MCA user*******");
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

	@Test(enabled = false,priority=7)
	public void testAddMyVisit() throws Exception {
		Logger log = LogManager.getLogger("TestAddMyVisit");
		log.info("******TC07_ANDROID : Starting to verify user is able to submit Add my visit form for login member******");
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

	@Test(enabled = false,priority=8)
	public void testLoggedInOrderFeature() throws InterruptedException {
		Logger log = LogManager.getLogger("TestLoggedInOrder");
		log.info("******TC08_ANDROID : Starting to validate user is able order placement with existing log in user******");
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

	@Test(enabled = false,priority=9)
	public void testMCACarryoutLaterToday() throws InterruptedException {
		Logger log = LogManager.getLogger("TestMCACarryoutLaterToday");
		log.info("******TC09_ANDROID : Starting to validate user is able to place Carry out-Later Today order with MCA users******");
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
	
	@Test(enabled = false,priority=10)
	public void testGuestCurbSide() throws InterruptedException {
		Logger log = LogManager.getLogger("TestGuestCurbsideOrder");
		log.info("******TC10_ANDROID : Starting to validate user is able to place Curbside order. For Guest user******");
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

	@AfterMethod
	public void tearDown() {
		System.out.println("Report URL: " + driver.getCapabilities().getCapability("reportUrl"));
		driver.quit();
		
	}

}
