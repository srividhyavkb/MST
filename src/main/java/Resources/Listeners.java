package Resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.mail.EmailException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Listeners extends TestListenerAdapter implements ITestListener {
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	public int testPassed = 0;
	public int testFailed = 0;
	public int testSkipped = 0;
	public int testExecuted = 0;
	public static Base base = new Base();
	public static Properties prop = base.returnProperty();

	public static void main(String[] args) {

		if(prop.getProperty("sendEmail").equals("true"))
		{
		SendEmail.getReportPath(new File(System.getProperty("user.dir") + "/reports"));
		Runtime current = Runtime.getRuntime();
		current.addShutdownHook(new SendEmail());
		}

	}

	public void onTestStart(ITestResult result) {
// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		FunctionalComponents.getExtentTest(extentTest);
		testExecuted++;
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS,
				MarkupHelper.createLabel(result.getName() + " - Test Case PASSED", ExtentColor.GREEN));
		testPassed++;

	}

	public void onTestFailure(ITestResult result) {
		extentTest.get().log(Status.FAIL,
				MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
		testFailed++;

	}

	public void onTestSkipped(ITestResult result) {
		if (result.getThrowable().getMessage() == null)
			extent.removeTest(test);
		else {
			extentTest.get().log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
			testSkipped++;
		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

		try {
			GenerateTestSummary.createTestExecutionTable(testExecuted, testPassed, testFailed, testSkipped);
			if(prop.getProperty("sendEmail").equals("true"))
			extentTest.get().log(Status.INFO, MarkupHelper.createLabel("Email sent to recipients", ExtentColor.LIME));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<ITestResult> skippedTestCases = context.getSkippedTests().getAllResults().iterator();
		while (skippedTestCases.hasNext()) {
			ITestResult skippedTestCase = skippedTestCases.next();
			ITestNGMethod method = skippedTestCase.getMethod();
			if (context.getSkippedTests().getResults(method).size() > 0) {
				System.out.println("Removing:" + skippedTestCase.getTestClass().toString());
				skippedTestCases.remove();

			}
		}
		extent.flush();
		main(null);

	}

}