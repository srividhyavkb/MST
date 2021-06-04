package MobileAutomation.MobileSeeTestWebAutomation;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Resources.Base;
import Resources.FunctionalComponents;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TestFavouriteItems extends Base {
	@Test
	public void testFavouriteItems() throws InterruptedException {
		Logger log = LogManager.getLogger("TestFavouriteItems");
		log.info("******STARTING TEST CHILIS FAVOURITE ITEMS******");
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

}