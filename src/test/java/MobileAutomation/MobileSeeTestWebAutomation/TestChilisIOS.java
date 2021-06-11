package MobileAutomation.MobileSeeTestWebAutomation;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Resources.Base;
import Resources.FunctionalComponents;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TestChilisIOS extends Base
{
	public IOSDriver<IOSElement> IOSdriver = null;
	@BeforeMethod
	public void initialize() throws Exception {
		DesiredCapabilities dc = sendIOSBrowserCapabilities();
		IOSdriver = new IOSDriver<>(new URL(prop.getProperty("CloudDeviceURL")), dc);
		getDriver(IOSdriver);
		IOSdriver.rotate(ScreenOrientation.PORTRAIT);
		prop = returnProperty();
		editProperties(prop, "Android_Browser", "false");
		editProperties(prop, "IOS_Browser", "true");
		editProperties(prop, "Android_App", "false");
		editProperties(prop, "IOS_App", "false");
		IOSdriver.get(prop.getProperty("url"));
		IOSdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		IOSdriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
	}
	
	@Test(enabled = true)
	public void testLoginFeature() throws Exception {
		Logger log = LogManager.getLogger("TestLoginLogout");
		log.info("******TC10_IOS : Starting to Validate Login and Logout functionality of Chilis WebApp******");
		FunctionalComponents func = new FunctionalComponents(IOSdriver, log);
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

	@Test(enabled = false)
	public void testRewardsFeature() throws InterruptedException {
		Logger log = LogManager.getLogger("TestRewards");
		log.info("******TC11_IOS : Starting to validate Rewards details page for logged in user******");
		FunctionalComponents func3 = new FunctionalComponents(IOSdriver, log);
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
		func3.getRewardTitleIOS();
	}

	@Test(enabled = false)
	public void testUpdateAccountFeature() throws InterruptedException {
		Logger log = LogManager.getLogger("TestUpdateAccount");
		log.info("******TC12_IOS : Starting to validate user is able to update my Account******");
		FunctionalComponents func4 = new FunctionalComponents(IOSdriver, log);
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

	@Test(enabled = false)
	public void testFavouriteItems() throws InterruptedException {
		Logger log = LogManager.getLogger("TestFavouriteItems");
		log.info("******TC13_IOS : Starting to verify Favorites items is displaying on menu page******");
		FunctionalComponents func5 = new FunctionalComponents(IOSdriver, log);
		func5.validateQASite();
		func5.closePopupRewards();
		func5.clickMenuButton();
		func5.selectLogin();
		func5.enterUserName();
		func5.enterPassword();
		func5.clickSignin();
		func5.clickMenuButton();
		func5.selectMenuOption();
		func5.getAllChilisFavouriteItemsIOS();
	}

	@Test(enabled = false)
	public void testLocationFeature() throws Exception {
		Logger log = LogManager.getLogger("TestLocationSearch");
		log.info("******TC14_IOS : Starting to validate location search fields and links for guest user******");
		FunctionalComponents func2 = new FunctionalComponents(IOSdriver, log);
		func2.validateQASite();
		func2.closePopupRewards();
		func2.clickMenuButton();
		func2.selectLocationsOption();
		func2.enterRestaurantLocationforSearch();
		func2.clickSearchButton();
		String result = func2.getRestaurantName();
		System.out.println(result);
	}

	@Test(enabled = false)
	public void testCurbsideAsapOrder() throws Exception {

		Logger log = LogManager.getLogger("TestMcaCurbside-ASAPOrder");
		log.info("******TC15_IOS : Starting to validate user is able to place Curbside-ASAP order for MCA user*******");
		FunctionalComponents curb = new FunctionalComponents(IOSdriver, log);
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
		curb.AddChilisFavouriteToCartIOS();
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

	@Test(enabled = false)
	public void testAddMyVisit() throws Exception {
		Logger log = LogManager.getLogger("TestAddMyVisit");
		log.info("******TC16_IOS : Starting to verify user is able to submit Add my visit form for login member******");
		FunctionalComponents func6 = new FunctionalComponents(IOSdriver, log);
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

	@Test(enabled = false)
	public void testLoggedInOrderFeature() throws InterruptedException {
		Logger log = LogManager.getLogger("TestLoggedInOrder");
		log.info("******TC17_IOS : Starting to validate user is able order placement with existing log in user******");
		FunctionalComponents ob = new FunctionalComponents(IOSdriver, log);
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
		ob.AddChilisFavouriteToCartIOS();
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

	@Test(enabled = false)
	public void testMCACarryoutLaterToday() throws InterruptedException {
		Logger log = LogManager.getLogger("TestMCACarryoutLaterToday");
		log.info("******TC18_IOS : Starting to validate user is able to place Carry out-Later Today order with MCA users******");
		FunctionalComponents ob = new FunctionalComponents(IOSdriver, log);
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
		ob.AddChilisFavouriteToCartIOS();
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

	@AfterMethod
	public void  tearDown() {
		System.out.println("Report URL: " + IOSdriver.getCapabilities().getCapability("reportUrl"));
		IOSdriver.quit();
	}

}
