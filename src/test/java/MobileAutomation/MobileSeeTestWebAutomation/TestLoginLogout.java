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

public class TestLoginLogout extends Base {
	
	
	public static final boolean status()
	{
		final boolean r = true;
		return r;
	}
	
	
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

	@Test( enabled=true, retryAnalyzer = Resources.RetryAnalyzer.class )
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

	@Test( enabled=true, retryAnalyzer = Resources.RetryAnalyzer.class )
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

	@Test( enabled=true, retryAnalyzer = Resources.RetryAnalyzer.class )
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

	@Test( enabled=true, retryAnalyzer = Resources.RetryAnalyzer.class )
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
	
	 
	

}
