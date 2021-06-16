package MobileAutomation.MobileSeeTestWebAutomation;

import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.experitest.appium.SeeTestClient;

import Resources.Base;
import Resources.Elements;
import Resources.FunctionalComponents;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TestChilisIOSApp extends Base {
	
	public IOSDriver<IOSElement> driver = null;
	SeeTestClient seetest;
	@BeforeMethod
	public void initialize() throws Exception {
		DesiredCapabilities dc = sendIOSChilisAppCapabilities();
		driver = new IOSDriver<>(new URL(prop.getProperty("CloudDeviceURL")), dc);
		seetest = new SeeTestClient(driver);
		driver.rotate(ScreenOrientation.PORTRAIT);
//		driver.findElement(Elements.iosDontAllowButtonXpath).click();
//		driver.findElement(Elements.appCancelButtonXpath).click();
		getDriver(driver);	
		prop = returnProperty();
		editProperties(prop, "Android_Browser", "false");
		editProperties(prop, "IOS_Browser", "false");
		editProperties(prop, "Android_App", "false");
		editProperties(prop, "IOS_App", "true");
	}
	
	@Test(enabled=false)
	public void iosLoginLogout() {
		Logger log = LogManager.getLogger("IOSAppTestLoginLogout");
		log.info("******Starting to login logout functionality in IOS Chilis App******");
		FunctionalComponents obj = new FunctionalComponents(driver, log);
		obj.appClosePopUpIOS();
		obj.appClickLoginButtonIOS();
		obj.appEnterUserNameIOS();
		obj.appEnterPasswordIOS();
		obj.appClickSignInIOS();
		obj.appClickMoreButtonIOS();
		obj.appClickLogoutButtonIOS();
	}
	
	@Test(enabled=true)
	public void iosappLocationSearch() throws InterruptedException
	{
		Logger log = LogManager.getLogger("IOSAppTestLocationSearch");
		log.info("******Starting to test Location Search Functionality of IOS Chilis App******");
		FunctionalComponents obj = new FunctionalComponents(driver, log);
		obj.appClosePopUpIOS();
		obj.appClickFindRestaurantIOS();
		obj.appEnterRestaurantLocationIOS();
		
	}
	
	@Test(enabled=false)
	public void appValidateRewards() throws InterruptedException
	{
		Logger log = LogManager.getLogger("AppTestLoginLogout");
		log.info("******Starting to validate Rewards for sign in users******");
		FunctionalComponents obj = new FunctionalComponents(driver, log);
		obj.appClosePopUpIOS();
		obj.appClickLoginButtonIOS();
		obj.appEnterUserNameIOS();
		obj.appEnterPasswordIOS();
		obj.appClickSignInIOS();
		obj.appClickRewardsButtonIOS();
		obj.appValidateRewardsCountIOS();
		obj.appViewRewardNamesIOS();
		
	}
	

	@AfterMethod
	public void tearDown() {
		System.out.println("Report URL: " + driver.getCapabilities().getCapability("reportUrl"));
		driver.quit();
		
	}
}
