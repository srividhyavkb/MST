package Resources;

import java.io.FileInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileBrowserType;

public class Base {

	private String accessKey = "eyJhbGciOiJIUzI1NiJ9.eyJ4cC51Ijo5NDY2NzQxLCJ4cC5wIjo5NDY2NzQwLCJ4cC5tIjoxNjAxNTI2MzAxODAxLCJleHAiOjE5MTY4OTI5NDQsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.H9SHgMkl1nXr3WVmM5BPwU4nX05Qt9IVSVItBur8WE0";
	protected AndroidDriver<AndroidElement> driver = null;
	protected IOSDriver<IOSElement> IOSdriver = null;
	DesiredCapabilities dc = new DesiredCapabilities();
	DesiredCapabilities dcIOS = new DesiredCapabilities();
	String projectPath = System.getProperty("user.dir");
	protected Properties prop;
	
	public Base()
	{
		prop = returnProperty();
	}

	public AndroidDriver<AndroidElement> initializeDriver() throws Exception {

		dc.setCapability("accessKey", accessKey);
		dc.setCapability("testName", "Quick Start Android Browser Demo");
		dc.setCapability("deviceQuery",
				"@os='android' and @version='" + prop.getProperty("android_version") + "' and @category='PHONE'");
		dc.setBrowserName(MobileBrowserType.CHROMIUM);
		driver = new AndroidDriver<>(new URL("https://cloud.seetest.io/wd/hub"), dc);
		driver.rotate(ScreenOrientation.PORTRAIT);
		return driver;
	}

	public IOSDriver<IOSElement> initializeDriverIOS() throws Exception {

		dcIOS.setCapability("accessKey", accessKey);
		dcIOS.setCapability("testName", "Quick Start iOS Browser Demo");
		dcIOS.setCapability("deviceQuery", "@os='ios' and @version='14.4' and @category='PHONE'");
		dcIOS.setBrowserName(MobileBrowserType.SAFARI);
		IOSdriver = new IOSDriver<>(new URL("https://cloud.seetest.io/wd/hub"), dc);
		IOSdriver.rotate(ScreenOrientation.PORTRAIT);
		return IOSdriver;
	}

	public Properties returnProperty() {
		prop = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream(projectPath + "/src/main/java/Resources/data.properties");
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}

	@BeforeMethod
	public void initialize() throws Exception {
		
		if (prop.getProperty("device").equals("Android")) {
			driver = initializeDriver();
			prop = returnProperty();
			driver.get(prop.getProperty("url"));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		}
		if (prop.getProperty("device").equals("IOS")) {
			IOSdriver = initializeDriverIOS();
			prop = returnProperty();
			IOSdriver.get(prop.getProperty("url"));
			IOSdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			IOSdriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		}

	}

	@AfterMethod
	public void tearDown() {
		if (prop.getProperty("device").equals("Android")) {
			System.out.println("Report URL: " + driver.getCapabilities().getCapability("reportUrl"));
			driver.quit();
		}
		if (prop.getProperty("device").equals("IOS")) {
			System.out.println("Report URL: " + IOSdriver.getCapabilities().getCapability("reportUrl"));
			IOSdriver.quit();
		}
	}

}
