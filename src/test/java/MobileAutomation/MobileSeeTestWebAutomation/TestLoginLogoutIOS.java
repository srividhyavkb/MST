package MobileAutomation.MobileSeeTestWebAutomation;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Resources.BaseIOS;
import Resources.FunctionalComponentsIOS;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TestLoginLogoutIOS extends BaseIOS {
	@Test
	public void testLoginFeature() throws InterruptedException {
		Logger log = LogManager.getLogger("TestLoginLogoutIOS");
		log.info("******STARTING TEST LOGIN LOGOUT IN IOS DEVICE******");
		FunctionalComponentsIOS login = new FunctionalComponentsIOS(driver, log);
		login.validateQASite();
		login.closePopupRewards();
		login.clickMenuButton();
		login.selectLogin();
		login.enterUserName();
		login.enterPassword();
		login.clickSignin();
		login.clickMenuButton();
		login.logout();
		System.out.println(login.validateLogout());
	}

}