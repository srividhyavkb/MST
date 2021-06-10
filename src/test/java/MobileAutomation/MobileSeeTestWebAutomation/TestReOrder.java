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
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TestReOrder extends Base {
	
	
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
	@Test
	public void testReOrder() throws Exception {
		Logger log = LogManager.getLogger("TestReorder");
		log.info("******STARTING TEST RE-ORDER******");
		FunctionalComponents reorder = new FunctionalComponents(driver, log);
		reorder.validateQASite();
		reorder.closePopupRewards();
		reorder.clickMenuButton();
		reorder.selectLogin();
		reorder.enterUserName();
		reorder.enterPassword();
		reorder.clickSignin();
		reorder.clickMenuButton();
		reorder.selectReorderOption();
		reorder.clickReorderforanOrder();
		reorder.enterRestaurantLocationForLoggedInOrder();
		reorder.clickSearchButtonForLoggedInOrder();
		reorder.orderNow();
		reorder.AddChilisFavouriteToCart();
		reorder.clickViewCart();
		reorder.changeQuantity();
		reorder.selectSilverWare();
		reorder.clickCheckOut();
		reorder.continueToPayment();
		reorder.enterCardNo();
		reorder.enterCVV();
		reorder.selectExpirationMonth();
		reorder.selectExpirationYear();
		reorder.enterNameOnCard();
		reorder.enterBillingZipCode();
		reorder.giveTip();
		reorder.checkRoundOff();
		String priceBeforePlacingOrder = reorder.OrderTotal();
		reorder.placeOrder();
		System.out.println(reorder.getSuccessMessageforLoggedInOrder());
		String priceAfterPlacingOrder = reorder.returnOrderPrice();
		Assert.assertEquals(priceBeforePlacingOrder, priceAfterPlacingOrder, "Incorrect price displayed");
	}

	@AfterMethod
	public void tearDown() {
		System.out.println("Report URL: " + driver.getCapabilities().getCapability("reportUrl"));
		driver.quit();
		
	}
}