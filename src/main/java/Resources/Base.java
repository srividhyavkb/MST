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

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileBrowserType;

public class Base {

	private String accessKey = "eyJhbGciOiJIUzI1NiJ9.eyJ4cC51Ijo5NDY2NzQxLCJ4cC5wIjo5NDY2NzQwLCJ4cC5tIjoxNjAxNTI2MzAxODAxLCJleHAiOjE5MTY4OTI5NDQsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.H9SHgMkl1nXr3WVmM5BPwU4nX05Qt9IVSVItBur8WE0";
	DesiredCapabilities dc = new DesiredCapabilities();
	DesiredCapabilities dcIOS = new DesiredCapabilities();
	public AppiumDriver driver = null;
	String projectPath = System.getProperty("user.dir");
	protected Properties prop;
	
	public Base()
	{
		prop = returnProperty();
	}

	public DesiredCapabilities sendAndroidBrowserCapabilities() throws Exception {

		if(prop.getProperty("Android").equals("true"))
		{
		dc.setCapability("accessKey", accessKey);
		dc.setCapability("testName", "Quick Start Android Browser Demo");
		dc.setCapability("deviceQuery",
				"@os='android' and @version='" + prop.getProperty("android_version") + "' and @category='PHONE'");
		dc.setBrowserName(MobileBrowserType.CHROMIUM);
		}
		return dc;
		
	}

	public DesiredCapabilities sendIOSBrowserCapabilities() throws Exception {
		
       if(prop.getProperty("IOS").equals("true"))
       {
		dcIOS.setCapability("accessKey", accessKey);
		dcIOS.setCapability("testName", "Quick Start iOS Browser Demo");
		dcIOS.setCapability("deviceQuery", "@os='ios' and @version='"+prop.getProperty("IOS_version")+"' and @category='PHONE'");
		dcIOS.setBrowserName(MobileBrowserType.SAFARI);
       }
		return dcIOS;
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
	
	public void getDriver(AppiumDriver driver)
	{
		this.driver = driver;
	}
	
	public void stopTest()
	{
		System.out.println("Report URL: " + driver.getCapabilities().getCapability("reportUrl"));
	    driver.quit();
		
	}
	


}
