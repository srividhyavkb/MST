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
	@Test(enabled=false)
	public void appLocationSearch() throws InterruptedException
	{
		Logger log = LogManager.getLogger("AppTestLocationSearch");
		log.info("******Starting to test Location Search Functionality of Chilis App******");
		FunctionalComponents obj = new FunctionalComponents(driver, log);
		obj.androidAppClosePopUp();
		obj.appClickFindRestaurant();
		obj.appEnterRestaurantLocation();
		
		
	}
	
	@Test(enabled=false)
	public void appValidateRewards() throws InterruptedException
	{
		Logger log = LogManager.getLogger("AppTestLoginLogout");
		log.info("******Starting to validate Rewards for sign in users******");
		FunctionalComponents obj = new FunctionalComponents(driver, log);
		obj.androidAppClosePopUp();
		obj.appClickLoginButton();
		obj.appEnterUserName();
		obj.appEnterPassword();
		obj.appClickSignIn();
		obj.appClickRewardsButton();
		obj.appValidateRewardsCount();
		obj.appViewRewardNames();
		
	}
	
	@Test(enabled=false)
	public void appValidateChilisFavouriteItems() throws InterruptedException
	{
		Logger log = LogManager.getLogger("AppTestLoginLogout");
		log.info("******Starting to validate Chilis Favourite Items for sign in users******");
		FunctionalComponents obj = new FunctionalComponents(driver, log);
		obj.androidAppClosePopUp();
		obj.appClickLoginButton();
		obj.appEnterUserName();
		obj.appEnterPassword();
		obj.appClickSignIn();
		obj.appgetChilisFavouriteItems(driver);
		
	}
	
	@Test(enabled=true)
	public void appUpdateMyAccount()
	{
		Logger log = LogManager.getLogger("AppTestMyAccountUpdate");
		log.info("******Starting to validate user is able to update my Account******");
		FunctionalComponents obj = new FunctionalComponents(driver, log);
		obj.androidAppClosePopUp();
		obj.appClickLoginButton();
		obj.appEnterUserName();
		obj.appEnterPassword();
		obj.appClickSignIn();
		obj.appClickMoreButton();
		obj.appSelectMyAccountOption();
		obj.appgetFirstNameBeforeUpdate(driver);
		obj.appgetLastNameBeforeUpdate(driver);
		obj.appgetEmailBeforeUpdate(driver);
		obj.appgetZipCodeBeforeUpdate(driver);
		obj.appUpdateFirstName(driver);
		obj.appUpdateLastName(driver);
		obj.appUpdateEmail(driver);
		obj.appUpdateZipCode(driver);
		obj.appCheckConsent();
		obj.appClickUpdate();
		obj.appRetrieveSuccessMessage();
		obj.appgetFirstNameAfterUpdate(driver);
		obj.appgetLastNameAfterUpdate(driver);
		obj.appgetEmailAfterUpdate(driver);
		obj.appgetZipCodeAfterUpdate(driver);
		obj.appValidateUpdate(driver);
		
	}
	@AfterMethod
	public void tearDown() {
		System.out.println("Report URL: " + driver.getCapabilities().getCapability("reportUrl"));
		driver.quit();
		
	}

}
