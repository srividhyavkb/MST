package Resources;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

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
	public static ExtentReports getReportObject()
	{
	base = new Base();
	prop = base.returnProperty();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  
	LocalDateTime now = LocalDateTime.now();  
	String path = System.getProperty("user.dir")+"/reports/index-"+dtf.format(now)+".html";
	SendEmailJava.getReportPath(path);
	ExtentHtmlReporter reporter = new ExtentHtmlReporter(path);
	reporter.config().setReportName("Mobile SeeTest Web Automation Results");
	reporter.config().setDocumentTitle("Test Results");
	reporter.config().setTheme(Theme.STANDARD);
	extent = new ExtentReports();
	extent.attachReporter(reporter);
	extent.setSystemInfo("Tester","Somnath Baul");
	extent.setSystemInfo("Environment","QA");
	extent.setSystemInfo("Application_URL",prop.getProperty("url"));
	return extent;
	}

}
