package MobileAutomation.MobileSeeTestWebAutomation;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.experitest.appium.SeeTestClient;

import Resources.Base;
import Resources.Elements;
import Resources.FunctionalComponents;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;


public class TestChilisApp extends Base{
	
	public AndroidDriver<AndroidElement> driver = null;
	SeeTestClient seetest;
	@BeforeMethod
	public void initialize() throws Exception {
		DesiredCapabilities dc = sendAndroidChilisAppCapabilities();
		driver = new AndroidDriver<>(new URL(prop.getProperty("CloudDeviceURL")), dc);
		seetest = new SeeTestClient(driver);
		driver.rotate(ScreenOrientation.PORTRAIT);
		driver.findElement(Elements.appOkButtonXpath).click();
		driver.findElement(Elements.appCancelButtonXpath).click();
		getDriver(driver);	
		prop = returnProperty();
		editProperties(prop, "Android_Browser", "false");
		editProperties(prop, "IOS_Browser", "false");
		editProperties(prop, "Android_App", "true");
		editProperties(prop, "IOS_App", "false");
	}
	
	@Test(enabled=false)
	public void appLoginLogout() throws InterruptedException
	{
		Logger log = LogManager.getLogger("AppTestLoginLogout");
		log.info("******Starting to login logout functionality in Chilis App******");
		FunctionalComponents obj = new FunctionalComponents(driver, log);
		obj.androidAppClosePopUp();
		obj.appClickLoginButton();
		obj.appEnterUserName();
		obj.appEnterPassword();
		obj.appClickSignIn();
		obj.appClickMoreButton();
		obj.appClickLogoutButton();
		
	}
	@Test(enabled=true)
	public void appLocationSearch() throws InterruptedException
	{
		Logger log = LogManager.getLogger("AppTestLocationSearch");
		log.info("******Starting to test Location Search Functionality of Chilis App******");
		FunctionalComponents obj = new FunctionalComponents(driver, log);
		obj.androidAppClosePopUp();
		obj.appClickFindRestaurant();
		obj.appEnterRestaurantLocation();
		
		
	}
	
	@AfterMethod
	public void tearDown() {
		System.out.println("Report URL: " + driver.getCapabilities().getCapability("reportUrl"));
		driver.quit();
		
	}

}
