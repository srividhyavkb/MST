package MobileAutomation.MobileSeeTestWebAutomation;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Resources.Base;
import Resources.FunctionalComponents;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TestGuestCurbSide extends Base {
	
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
	public void testGuestCurbSideASAP() throws InterruptedException {
		Logger log = LogManager.getLogger("TestGuestCurbsideOrder");
		log.info("******STARTING TEST GUEST CURBSIDE ORDER******");
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
	
	@AfterMethod
	public void tearDown() {
		System.out.println("Report URL: " + driver.getCapabilities().getCapability("reportUrl"));
		driver.quit();
		
	}
}