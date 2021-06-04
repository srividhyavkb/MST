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


public class TestAddMyVisit extends Base {

@Test
public void testAddMyVisit() throws Exception
{
Logger log = LogManager.getLogger("TestAddMyVisit");
log.info("******STARTING TEST ADD MY VISIT******");
FunctionalComponents func6= new FunctionalComponents(driver,log);
func6.validateQASite();
func6.closePopupRewards();
func6.clickMenuButton();
func6.selectLogin();
func6.enterUserName();
func6.enterPassword();
func6.clickSignin();
func6.clickAddVisit();
func6.enterRestaurantLocation();
func6.selectChilisLocation();
func6.selectVisitMonth();
func6.selectVisitDay();
func6.selectVisitYear();
func6.enterCheckNumber();
func6.enterCheckTotal();
func6.clickSubmit();
System.out.println(func6.retrieveSuccessMessage());
}


}
