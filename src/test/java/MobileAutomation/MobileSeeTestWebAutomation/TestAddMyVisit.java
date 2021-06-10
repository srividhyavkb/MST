package MobileAutomation.MobileSeeTestWebAutomation;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
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


public class TestAddMyVisit extends Base {
	
	public IOSDriver<IOSElement> IOSdriver = null;
	@BeforeMethod
	public void initialize() throws Exception {
		DesiredCapabilities dc = sendIOSBrowserCapabilities();
		IOSdriver = new IOSDriver<>(new URL(prop.getProperty("CloudDeviceURL")), dc);
		getDriver(IOSdriver);
		IOSdriver.rotate(ScreenOrientation.PORTRAIT);
		prop = returnProperty();
		IOSdriver.get(prop.getProperty("url"));
		IOSdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		IOSdriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
	}
	

@Test
public void testAddMyVisit() throws Exception {
	Logger log = LogManager.getLogger("TestAddMyVisit");
	log.info("******TC07_ANDROID : Starting to verify user is able to submit Add my visit form for login member******");
	FunctionalComponents func6 = new FunctionalComponents(IOSdriver, log);
	func6.validateQASite();
	func6.closePopupRewards();
	func6.clickMenuButton();
	func6.selectLogin();
	func6.enterUserName();
	func6.enterPassword();
	func6.clickSignin();
	func6.clickAddVisit();
	func6.enterRestaurantLocationIOS();
	func6.selectChilisLocationIOS();
	func6.selectVisitMonthIOS();
	func6.selectVisitDayIOS();
	func6.selectVisitYearIOS();
	func6.enterCheckNumber();
	func6.enterCheckTotal();
	func6.clickSubmit();
	System.out.println(func6.retrieveSuccessMessage());
}

@AfterMethod
public void  tearDown() {
	System.out.println("Report URL: " + IOSdriver.getCapabilities().getCapability("reportUrl"));
	IOSdriver.quit();
}

}


