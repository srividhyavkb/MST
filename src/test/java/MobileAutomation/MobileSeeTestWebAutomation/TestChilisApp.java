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
import Resources.FunctionalComponents;
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
		seetest.click("NATIVE","xpath=//*[text()='OK']", 0,1);
		seetest.click("NATIVE","xpath=//*[text()='CANCEL']", 0,1);
		getDriver(driver);	
		prop = returnProperty();
	}
	
	@Test
	public void launchApp() throws InterruptedException
	{
		Logger log = LogManager.getLogger("TestChilisApp");
		log.info("******Starting to test chilis app******");
		FunctionalComponents obj = new FunctionalComponents(driver, log);
		obj.androidAppClosePopUp();
		obj.appClickLoginButton();
		obj.appEnterUserName();
		obj.appEnterPassword();
		obj.appClickSignIn();
		obj.appClickMoreButton();
		obj.appClickLogoutButton();
		
	}
	
	@AfterMethod
	public void tearDown() {
		System.out.println("Report URL: " + driver.getCapabilities().getCapability("reportUrl"));
		driver.quit();
		
	}

}
