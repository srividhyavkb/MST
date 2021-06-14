package Resources;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.commons.mail.EmailException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentEmailReporter;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReporterNG {
	
	static ExtentReports extent;
	static Base base;
	static Properties prop;
	
	@SuppressWarnings("deprecation")
	public static ExtentReports getReportObjectAndroidBrowser() 
	{
	base = new Base();
	prop = base.returnProperty();
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  
	LocalDateTime now = LocalDateTime.now();  
	String path = System.getProperty("user.dir")+"/reports/index-"+dtf.format(now)+".html";
	ExtentHtmlReporter reporter = new ExtentHtmlReporter(path);
	reporter.config().setReportName("Mobile SeeTest Android Browser Automation Results");
	reporter.config().setDocumentTitle("Test Results");
	reporter.config().setTheme(Theme.STANDARD);
	extent = new ExtentReports();
	extent.attachReporter(reporter);
	extent.setSystemInfo("Tester","Somnath Baul");
	extent.setSystemInfo("Environment","QA");
	extent.setSystemInfo("Platform","Android Browser");
	extent.setSystemInfo("Application_URL",prop.getProperty("url"));
	return extent;
	}
	
	@SuppressWarnings("deprecation")
	public static ExtentReports getReportObjectIOSBrowser() 
	{
	base = new Base();
	prop = base.returnProperty();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  
	LocalDateTime now = LocalDateTime.now();  
	String path = System.getProperty("user.dir")+"/reports/index-"+dtf.format(now)+".html";
	ExtentHtmlReporter reporter = new ExtentHtmlReporter(path);
	reporter.config().setReportName("Mobile SeeTest IOS Browser Automation Results");
	reporter.config().setDocumentTitle("Test Results");
	reporter.config().setTheme(Theme.STANDARD);
	extent = new ExtentReports();
	extent.attachReporter(reporter);
	extent.setSystemInfo("Tester","Somnath Baul");
	extent.setSystemInfo("Environment","QA");
	extent.setSystemInfo("Platform","IOS Browser");
	extent.setSystemInfo("Application_URL",prop.getProperty("url"));
	return extent;
	}
	
	@SuppressWarnings("deprecation")
	public static ExtentReports getReportObjectAndroidApp() 
	{
	base = new Base();
	prop = base.returnProperty();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  
	LocalDateTime now = LocalDateTime.now();  
	String path = System.getProperty("user.dir")+"/reports/index-"+dtf.format(now)+".html";
	ExtentHtmlReporter reporter = new ExtentHtmlReporter(path);
	reporter.config().setReportName("Mobile SeeTest IOS Browser Automation Results");
	reporter.config().setDocumentTitle("Test Results");
	reporter.config().setTheme(Theme.STANDARD);
	extent = new ExtentReports();
	extent.attachReporter(reporter);
	extent.setSystemInfo("Tester","Somnath Baul");
	extent.setSystemInfo("Environment","QA");
	extent.setSystemInfo("Platform","Android App");
	extent.setSystemInfo("Application_URL",prop.getProperty("url"));
	return extent;
	}
	@SuppressWarnings("deprecation")
	public static ExtentReports getReportObjectIOSApp() 
	{
	base = new Base();
	prop = base.returnProperty();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  
	LocalDateTime now = LocalDateTime.now();  
	String path = System.getProperty("user.dir")+"/reports/index-"+dtf.format(now)+".html";
	ExtentHtmlReporter reporter = new ExtentHtmlReporter(path);
	reporter.config().setReportName("Mobile SeeTest IOS App Automation Results");
	reporter.config().setDocumentTitle("Test Results");
	reporter.config().setTheme(Theme.STANDARD);
	extent = new ExtentReports();
	extent.attachReporter(reporter);
	extent.setSystemInfo("Tester","Somnath Baul");
	extent.setSystemInfo("Environment","QA");
	extent.setSystemInfo("Platform","IOS App");
	return extent;
	}

}
