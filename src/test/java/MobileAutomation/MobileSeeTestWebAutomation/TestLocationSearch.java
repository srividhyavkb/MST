package MobileAutomation.MobileSeeTestWebAutomation;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Resources.Base;
import Resources.FunctionalComponents;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TestLocationSearch extends Base {
@Test
public void testLocationFeature() throws Exception
{
Logger log = LogManager.getLogger("TestLocationSearch");
log.info("******STARTING TEST LOCATION SEARCH******");
FunctionalComponents func2 = new FunctionalComponents(driver,log);
func2.validateQASite();
func2.closePopupRewards();
func2.clickMenuButton();
func2.selectLocationsOption();
func2.enterRestaurantLocationforSearch();
func2.clickSearchButton();
String result = func2.getRestaurantName();
System.out.println(result);
}


}