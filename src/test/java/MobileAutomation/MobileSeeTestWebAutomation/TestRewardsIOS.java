package MobileAutomation.MobileSeeTestWebAutomation;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Resources.BaseIOS;
import Resources.FunctionalComponents;
import Resources.FunctionalComponentsIOS;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TestRewardsIOS extends BaseIOS {
	@Test
	public void testRewardsFeature() throws InterruptedException {
		Logger log = LogManager.getLogger("TestRewardsIOS");
		log.info("******STARTING TEST REWARDS IN IOS DEVICE******");
		FunctionalComponentsIOS func3 = new FunctionalComponentsIOS(driver, log);
		func3.validateQASite();
		func3.closePopupRewards();
		func3.clickMenuButton();
		func3.selectLogin();
		func3.enterPassword();
		func3.clickSignin();
		int noOfRewardsDisplayed = func3.noOfRewards();
		int ActualrewardCount = func3.ActualRewardsCount();
		System.out.println(noOfRewardsDisplayed + " : " + ActualrewardCount);
		Assert.assertEquals(noOfRewardsDisplayed, ActualrewardCount, "Discrepancy observed in Rewards Count");
	}

}