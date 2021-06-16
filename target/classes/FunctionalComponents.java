package Resources;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.experitest.appium.SeeTestClient;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

@SuppressWarnings("rawtypes")
public class FunctionalComponents extends Base {

	public RemoteWebDriver driver = null;
	Properties property = returnProperty();

	Listeners listen;
	static ExtentTest extTestObj;
	public WebDriverWait wait;
	Logger log;
	ExcelUtils excel;
	public SeeTestClient seetest;

	/* Constructor for invoking Android driver */
	public FunctionalComponents(AndroidDriver<AndroidElement> driver, Logger log) {
		this.driver = driver;
		this.log = log;
		wait = new WebDriverWait(this.driver, 30);
		listen = new Listeners();
		seetest = new SeeTestClient(driver);
		try {
			excel = new ExcelUtils(projectPath + "/CommonData.xlsx");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Constructor for invoking IOS driver */
	public FunctionalComponents(IOSDriver<IOSElement> driver, Logger log) {
		this.driver = driver;
		this.log = log;
		wait = new WebDriverWait(this.driver, 25);
		listen = new Listeners();
		seetest = new SeeTestClient(driver);
		try {
			excel = new ExcelUtils(projectPath + "/CommonData.xlsx");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* To obtain the Extent Test object for logging in Extent Report */
	public static void getExtentTest(ExtentTest extentTest) {
		extTestObj = extentTest;
	}

	/*
	 * Scroll functions for bringing an web element into view
	 * scrollIntoViewBottom(By element) : for bringing an element into view at the
	 * bottom of the screen with argument as locator
	 * 
	 * scrollIntoViewBottomByElement(AndroidElement element) : for bringing an
	 * element into view at the bottom of the screen with argument as web element
	 * 
	 * scrollIntoViewTop(By element) : for bringing an element into view at the top
	 * of the screen with argument as locator
	 * 
	 * scrollIntoViewHalf(By element) : for bringing an element into view at the
	 * middle of the screen with argument as locator
	 */
	public void scrollIntoViewBottom(By element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", driver.findElement(element));
		Thread.sleep(500);
	}

	public void scrollIntoViewBottomByElement(WebElement element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
		Thread.sleep(500);
	}

	public void scrollIntoViewTop(By element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
		Thread.sleep(500);
	}

	public void scrollIntoViewHalf(By element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView(true); window.scrollBy(0, -window.innerHeight / 2);",
				driver.findElement(element));
		Thread.sleep(500);
	}

	/*
	 * function to click an element after certain wait time with argument as locator
	 */
	public void clickableWait(By element) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	/* function to wait for the presence of an element with argument as locator */
	public void explicitWait(By element) {
		wait.until(ExpectedConditions.presenceOfElementLocated(element));
	}

	/* function to click an element with argument as locator */
	public void clickElement(By element) {
		driver.findElement(element).click();
	}

	/*
	 * function to enter data in a text box after certain wait time with argument as
	 * locator and the data
	 */
	public void sendKeysWait(By element, String value) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		driver.findElement(element).clear();
		driver.findElement(element).sendKeys(value);
	}

	/* function to scroll down from start with argument as end coordinate */
	public void scrollDownFromStart(String endpoint) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + endpoint + ")", "");
	}

	/* function to scroll up with argument as end coordinate */
	public void scrollUp(String endpoint) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-" + endpoint + ")", "");
	}

	/* Validate user is able to login and then logout for the website */

	/* function to validate QA site launch */
	public void validateQASite() {
		extTestObj.createNode("Starting QA site validation").info("INFO");
		log.info("Starting QA site validation");
		try {
			seetest.hybridClearCache();
			driver.get(prop.getProperty("url"));
			explicitWait(Elements.popUpCloseButton);
			log.info("QA site launch is successful, Site Name : " + driver.getCurrentUrl());
			extTestObj.createNode("QA site launch is successful, Site Name : " + driver.getCurrentUrl()).pass("PASSED");

		} catch (Exception e) {
			log.error("QA site launch failed");
			extTestObj.createNode("QA site launch failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();

		}

	}

	/* function to close the pop up that comes on invoking Chilis website */
	public void closePopupRewards() {
		try {
			clickableWait(Elements.popUpCloseButton);
			explicitWait(Elements.subHeader);
			log.info("Pop up closed");
			extTestObj.createNode("Pop up closed").pass("PASSED");
		} catch (Exception e) {
			log.error("Pop up close failed");
			extTestObj.createNode("Pop up close failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}

	}

	/* function to click on the menu hamburger button */
	public void clickMenuButton() {
		try {
			clickableWait(Elements.menuButton);
			log.info("Menu button clicked");
			extTestObj.createNode("Menu button clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Menu button click failed");
			extTestObj.createNode("Menu button click failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	/* function to select login option */
	public void selectLogin() {
		try {
			clickableWait(Elements.loginButton);
			log.info("Login option selected");
			extTestObj.createNode("Login option selected").pass("PASSED");
		} catch (Exception e) {
			log.error("Login option not selected");
			extTestObj.createNode("Login option not selected")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}
	}

	/* function to enter username in login page */
	public void enterUserName() {
		try {
			String username = excel.getCellData("Credentials", "UserName", 2);
			explicitWait(Elements.userNameTextBox);
			sendKeysWait(Elements.userNameTextBox, username);
			log.info("User name " + username + " entered");
			extTestObj.createNode("User name " + username + " entered").pass("PASSED");
		} catch (Exception e) {
			log.error("Could not enter user name");
			extTestObj.createNode("Could not enter user name")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}

	}

	/* function to enter password in login page */
	public void enterPassword() {
		try {
			String password = excel.getCellData("Credentials", "Password", 2);
			sendKeysWait(Elements.passwordTextBox, password);
			log.info("Password " + password + " entered");
			extTestObj.createNode("Password " + password + " entered").pass("PASSED");
		} catch (Exception e) {
			log.error("Could not enter password");
			extTestObj.createNode("Enter password")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}

	}

	/* function to click sign in button in the login page */
	public void clickSignin() {
		try {
			scrollIntoViewBottom(Elements.signinButton);
			clickableWait(Elements.signinButton);
			log.info("Sign in button clicked");
			extTestObj.createNode("Sign in button clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Sign in button click failed");
			extTestObj.createNode("Sign in button click failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}

	}

	/* function to select log out option after clicking on menu hamburger */
	public void logout() {
		try {
			clickableWait(Elements.logoutButton);
			log.info("Logout button clicked");
			extTestObj.createNode("Logout button clicked").pass("PASSED");
		} catch (Exception e) {
			log.info("Logout button click failed");
			extTestObj.createNode("Logout button click failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}
	}

	/*
	 * function to validate whether log out is successful based on the "LOGIN"
	 * header in the login page
	 */
	public String validateLogout() {

		try {
			explicitWait(Elements.loginHeader);
			log.info("Login header displayed : " + driver.findElement(Elements.loginHeader).getText());
			extTestObj.createNode("Login header displayed : " + driver.findElement(Elements.loginHeader).getText())
					.pass("PASSED");
		} catch (Exception e) {
			log.error("Login header not displayed");
			extTestObj.createNode("Login header not displayed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
		return driver.findElement(Elements.loginHeader).getText();
	}

	/* Validate Location search fields and links for guest user */

	/* function to select location option after clicking on menu hamburger */
	public void selectLocationsOption() {
		try {
			clickableWait(Elements.locationsButton);
			log.info("Location option selected");
			extTestObj.createNode("Location option selected").pass("PASSED");
		} catch (Exception e) {
			log.error("Location button selection failed");
			extTestObj.createNode("Location button selection failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}
	}

	/* function to enter Restaurant location search filter in the search text box */
	public void enterRestaurantLocationforSearch() throws InterruptedException {
		try {
			explicitWait(Elements.locationSearchTextBox);
			clickableWait(Elements.locationSearchTextBox);
			String locn = excel.getCellData("Locations", "Location", 2);
			driver.getKeyboard().sendKeys(locn);
			log.info("Restaurant location " + locn + " entered");
			extTestObj.createNode("Restaurant location " + locn + " entered").pass("PASSED");

		} catch (Exception e) {
			log.error("Failed to enter Restaurant location");
			extTestObj.createNode("Failed to enter Restaurant location")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}
	}

	/*
	 * function to click search button in the search restaurant page after entering
	 * search filter
	 */
	public void clickSearchButton() {
		try {
			clickableWait(Elements.searchButton);
			log.info("Search button clicked");
			extTestObj.createNode("Search button clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Search button click failed");
			extTestObj.createNode("Search button click failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	/* function to retrieve the restaurant name in the search result page */
	public String getRestaurantName() {
		try {
			explicitWait(Elements.restaurantName);
			log.info("Restaurant name " + driver.findElement(Elements.restaurantName).getText() + " is displayed");
			extTestObj.createNode(
					"Restaurant name " + driver.findElement(Elements.restaurantName).getText() + " is displayed")
					.pass("PASSED");
		} catch (Exception e) {
			log.error("Restaurant Name not displayed");
			extTestObj.createNode("Restaurant Name not displayed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
		return driver.findElement(Elements.restaurantName).getText();

	}

	/* Validate Rewards details page for logged in user */

	/*
	 * function to retrieve the reward count displayed in the reward page of MCA
	 * user
	 */
	public int noOfRewards() {
		String displayedCount = "";
		try {
			explicitWait(Elements.noOfRewards);
			displayedCount = driver.findElement(Elements.noOfRewards).getText();
			log.info("Displayed rewards count obtained : " + displayedCount);
			extTestObj.createNode("Displayed rewards count obtained : " + displayedCount).pass("PASSED");
		} catch (Exception e) {
			log.error("Displayed rewards count couldn't be obtained");
			extTestObj.createNode("Displayed rewards count couldn't be obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}

		return Integer.parseInt(displayedCount);
	}

	/*
	 * function to count the no of rewards available in the rewards page of MCA user
	 * for comparison
	 */
	public int ActualRewardsCount() {
		int count = 0;
		try {
			count = driver.findElements(Elements.actualRewardsCount).size();
			log.info("Actual rewards count obtained");
			extTestObj.createNode("Actual rewards count obtained : " + count).pass("PASSED");
		} catch (Exception e) {
			log.error("Actual rewards count couldn't be obtained");
			extTestObj.createNode("Actual rewards count couldn't be obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
		return count;

	}

	/*
	 * function to retrieve the titles of rewards available in the rewards page of
	 * MCA user getRewardTitle() : for Android getRewardTitleIOS() : for IOS
	 */
	public void getRewardTitle() {
		List<WebElement> rewards = driver.findElements(Elements.actualRewardsCount);
		String rewardsTitle = "";
		try {
			log.info("Reward Titles :");
			extTestObj.createNode("Reward Titles :").info("INFO");
			for (WebElement reward : rewards) {
				rewardsTitle = reward.findElement(By.xpath("//div[@class='rewards-active-title item-title']"))
						.getText();
				log.info(rewardsTitle);
				extTestObj.createNode(rewardsTitle).pass("PASSED");
			}
			log.info("Reward Titles displayed");
			extTestObj.createNode("Reward Titles displayed").pass("PASSED");
		} catch (Exception e) {
			log.error("Reward titles couldn't be obtained");
			extTestObj.createNode("Reward titles couldn't be obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
	}

	/* Validate user is able to update my Account. */

	/* function to select My Account option from hamburger menu */
	public void selectMyAccountOption() {

		try {
			clickableWait(Elements.myAccountOption);
			log.info("My Account option selected");
			extTestObj.createNode("My Account option selected").pass("PASSED");
		} catch (Exception e) {
			log.error("My Account option selection failed");
			extTestObj.createNode("My Account option selection failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}

	}

	/* function to retrieve the first name before My Account update */
	public void getFirstNameBeforeUpdate() {
		String initialFirstName = "";
		try {
			explicitWait(Elements.firstNameTextBox);
			initialFirstName = driver.findElement(Elements.firstNameTextBox).getAttribute("value");
			log.info("First Name before update obtained as : " + initialFirstName);
			extTestObj.createNode("First Name before update obtained as : " + initialFirstName).pass("PASSED");
		} catch (Exception e) {
			log.error("First Name before update not obtained");
			extTestObj.createNode("First Name before update not obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}

	}

	/* function to get the last name before My Account update */
	public void getLastNameBeforeUpdate() {
		String initialLastName = "";
		try {
			explicitWait(Elements.lastNameTextBox);
			initialLastName = driver.findElement(Elements.lastNameTextBox).getAttribute("value");
			log.info("Last Name before update obtained as : " + initialLastName);
			extTestObj.createNode("Last Name before update obtained as : " + initialLastName).pass("PASSED");
		} catch (Exception e) {
			log.error("Last Name before update not obtained");
			extTestObj.createNode("Last Name before update not obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
	}

	/* function to retrieve email address before my account update */
	public void getEmailBeforeUpdate() {
		String initialEmail = "";
		try {
			explicitWait(Elements.emailTextBox);
			initialEmail = driver.findElement(Elements.emailTextBox).getAttribute("value");
			log.info("Email before update obtained as : " + initialEmail);
			extTestObj.createNode("Email before update obtained as : " + initialEmail).pass("PASSED");
		} catch (Exception e) {
			log.error("Email before update not obtained");
			extTestObj.createNode("Email before update not obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
	}

	/* function to retrieve zip code before my account update */
	public void getZipCodeBeforeUpdate() {
		String initialZipCode = "";
		try {
			explicitWait(Elements.zipCodeTextBox);
			initialZipCode = driver.findElement(Elements.zipCodeTextBox).getAttribute("value");
			log.info("Zip Code before update obtained as : " + initialZipCode);
			extTestObj.createNode("Zip Code before update obtained as : " + initialZipCode).pass("PASSED");
		} catch (Exception e) {
			log.error("Zip Code before update not obtained");
			extTestObj.createNode("Zip Code before update not obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
	}

	public void updateEmail() {
		try {
			String email = excel.getCellData("UpdateMyAccount", "Email", 2);
			sendKeysWait(Elements.emailTextBox, email);
			log.info("Email updated with : " + email);
			extTestObj.createNode("Email updated with : " + email).pass("PASSED");

		} catch (Exception e) {
			log.error("Email updation failed");
			extTestObj.createNode("Email updation failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}

	}

	public void updateFirstName() {
		try {
			String firstName = excel.getCellData("UpdateMyAccount", "First Name", 2);
			sendKeysWait(Elements.firstNameTextBox, firstName);
			log.info("First Name updated with : " + firstName);
			extTestObj.createNode("First Name updated with : " + firstName).pass("PASSED");
		} catch (Exception e) {
			log.error("First Name updation failed");
			extTestObj.createNode("First Name updation failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}

	}

	public void updateLastName() {
		try {
			String lastName = excel.getCellData("UpdateMyAccount", "Last Name", 2);
			sendKeysWait(Elements.lastNameTextBox, lastName);
			log.info("Last Name updated with : " + lastName);
			extTestObj.createNode("Last Name updated with : " + lastName).pass("PASSED");
		} catch (Exception e) {
			log.error("Last Name updation failed");
			extTestObj.createNode("Last Name updation failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}

	}

	public void updateZipCode() {
		try {
			String zipCode = excel.getCellData("UpdateMyAccount", "Zip Code", 2);
			sendKeysWait(Elements.zipCodeTextBox, zipCode);
			log.info("Zip Code updated with : " + zipCode);
			extTestObj.createNode("Zip Code updated with : " + zipCode).pass("PASSED");
		} catch (Exception e) {
			log.error("Zip Code updation failed");
			extTestObj.createNode("Zip Code updation failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
	}

	public void checkConsent() {
		try {
			clickableWait(Elements.consentCheckBox);
			log.info("Consent checked");
			extTestObj.createNode("Consent checked").pass("PASSED");
		} catch (Exception e) {
			log.error("Consent check failed");
			extTestObj.createNode("Consent check failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
	}

	public void clickUpdateButton() {
		try {
			clickableWait(Elements.updateButton);
			log.info("Update button clicked");
			extTestObj.createNode("Update button clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Update button click failed");
			extTestObj.createNode("Update button click failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}
	}

	public void getSuccessMessage() {
		String successMessage = "";
		try {
			explicitWait(Elements.successMessageforUpdate);
			successMessage = driver.findElement(Elements.successMessageforUpdate).getText();
			log.info("Success message obtained as : " + successMessage);
			extTestObj.createNode("Success message obtained as : " + successMessage).pass("PASSED");
		} catch (Exception e) {
			log.error("Success message not obtained");
			extTestObj.createNode("Success message not obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}

	}

	public String getFirstNameAfterUpdate() {
		String finalFirstName = "";
		try {
			explicitWait(Elements.firstNameTextBox);
			finalFirstName = driver.findElement(Elements.firstNameTextBox).getAttribute("value");
			log.info("First Name after update obtained as : " + finalFirstName);
			extTestObj.createNode("First Name after update obtained as : " + finalFirstName).pass("PASSED");
		} catch (Exception e) {
			log.error("First Name after update not obtained");
			extTestObj.createNode("First Name after update not obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
		return finalFirstName;
	}

	public String getLastNameAfterUpdate() {
		String finalLastName = "";
		try {
			explicitWait(Elements.lastNameTextBox);
			finalLastName = driver.findElement(Elements.lastNameTextBox).getAttribute("value");
			log.info("Last Name after update obtained as : " + finalLastName);
			extTestObj.createNode("Last Name after update obtained as : " + finalLastName).pass("PASSED");
		} catch (Exception e) {
			log.error("Last Name after update not obtained");
			extTestObj.createNode("Last Name after update not obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
		return finalLastName;
	}

	public String getEmailAfterUpdate() {
		String finalEmail = "";
		try {
			explicitWait(Elements.emailTextBox);
			finalEmail = driver.findElement(Elements.emailTextBox).getAttribute("value");
			log.info("Email after update obtained as : " + finalEmail);
			extTestObj.createNode("Email after update obtained as : " + finalEmail).pass("PASSED");
		} catch (Exception e) {
			log.error("Email after update not obtained");
			extTestObj.createNode("Email after update not obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
		return finalEmail;
	}

	public String getZipCodeAfterUpdate() {
		String finalZipCode = "";
		try {
			explicitWait(Elements.zipCodeTextBox);
			finalZipCode = driver.findElement(Elements.zipCodeTextBox).getAttribute("value");
			log.info("Zip Code after update obtained as : " + finalZipCode);
			extTestObj.createNode("Zip Code after update obtained as : " + finalZipCode).pass("PASSED");
		} catch (Exception e) {
			log.error("Zip Code after update not obtained");
			extTestObj.createNode("Zip Code after update not obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
		return finalZipCode;
	}

	public void ValidateUpdate() {
		try {
			String firstNameAfterUpdate = getFirstNameAfterUpdate();
			String lastNameAfterUpdate = getLastNameAfterUpdate();
			String emailAfterUpdate = getEmailAfterUpdate();
			String zipCodeAfterUpdate = getZipCodeAfterUpdate();
			Assert.assertEquals(excel.getCellData("UpdateMyAccount", "First Name", 2), firstNameAfterUpdate);
			Assert.assertEquals(excel.getCellData("UpdateMyAccount", "Last Name", 2), lastNameAfterUpdate);
			Assert.assertEquals(excel.getCellData("UpdateMyAccount", "Email", 2), emailAfterUpdate);
			Assert.assertEquals(excel.getCellData("UpdateMyAccount", "Zip Code", 2), zipCodeAfterUpdate);
			log.info("All fields are updated");
			extTestObj.createNode("All fields are updated").pass("PASSED");
		} catch (Exception e) {
			log.info("Error observed in fields updation");
			extTestObj.createNode("Error observed in fields updation")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}

	}

	/* Verify Favorites items is displaying on menu page */
	public void selectMenuOption() {
		try {
			clickableWait(Elements.menuOption);
			log.info("Menu Option selected");
			extTestObj.createNode("Menu Option selected").pass("PASSED");
		} catch (Exception e) {
			log.error("Menu Option selection failed");
			extTestObj.createNode("Menu Option selection failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}
	}

	public void getAllChilisFavouriteItems() {

		List<WebElement> items = null;
		try {
			items = driver.findElements(Elements.favouriteMenu);
			log.info("Chillis favourite Items : ");
			extTestObj.log(Status.INFO, "Chillis favourite Items : ");
			for (WebElement el : items) {
				log.info(el.findElement(Elements.favouriteItemsTitle).getText());
				extTestObj.createNode(el.findElement(Elements.favouriteItemsTitle).getText()).info("INFO");
			}
			log.info("All chilis favourite items obtained");
			extTestObj.createNode("All chilis favourite items obtained").pass("PASSED");
		} catch (Exception e) {
			log.error("Couldn't obtain chilis favourite items");
			extTestObj.createNode("Couldn't obtain chilis favourite items")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}

	}

	/* Verify user is able to submit Add my visit form for login member */
	public void clickAddVisit() {

		try {
			Thread.sleep(7000);
			scrollIntoViewBottom(Elements.addMyVisitButton);
			clickableWait(Elements.addMyVisitButton);
			log.info("Add my visit button clicked");
			extTestObj.createNode("Add my visit button clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Add my visit button click failed");
			extTestObj.createNode("Add my visit button click failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}
	}

	public void enterRestaurantLocation() throws InterruptedException {
		String loc = excel.getCellData("AddMyVisit", "Location", 2);
		try {
			scrollIntoViewBottom(Elements.restaurantLocTextBox);
			explicitWait(Elements.restaurantLocTextBox);
			clickableWait(Elements.restaurantLocTextBox);
			driver.getKeyboard().sendKeys(loc);
			scrollIntoViewBottom(By.xpath("//*[text()='" + loc + "']"));
			((PressesKey) driver).pressKeyCode(AndroidKeyCode.ENTER);
			log.info("Restaurant location entered as : " + loc);
			extTestObj.createNode("Restaurant location entered as : " + loc).pass("PASSED");
			Thread.sleep(3000);
		} catch (Exception e) {
			log.error("Failed to enter restaurant location");
			extTestObj.createNode("Failed to enter restaurant location")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}
	}

	public void enterRestaurantLocationIOS() throws InterruptedException {
		String loc = excel.getCellData("AddMyVisit", "Location", 2);
		try {
			scrollIntoViewBottom(Elements.restaurantLocTextBox);
			explicitWait(Elements.restaurantLocTextBox);
			clickableWait(Elements.restaurantLocTextBox);
			driver.getKeyboard().sendKeys(loc);
			scrollIntoViewBottom(By.xpath("//*[text()='" + loc + "']"));
			seetest.click("WEB", "xpath=//*[text()='" + loc + "']", 0, 1);
			log.info("Restaurant location entered as : " + loc);
			extTestObj.createNode("Restaurant location entered as : " + loc).pass("PASSED");
			Thread.sleep(3000);
		} catch (Exception e) {
			log.error("Failed to enter restaurant location");
			extTestObj.createNode("Failed to enter restaurant location")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}
	}

	public void selectChilisLocation() throws Exception {
		String locFromDropDown = excel.getCellData("AddMyVisit", "Chilis Location from DropDown", 2);
		try {
//			clickElement(Elements.chillisLocDropDown);
			scrollIntoViewBottom(By.xpath("//*[text()='" + locFromDropDown + "']"));
			clickElement(By.xpath("//*[text()='" + locFromDropDown + "']"));
			log.info("Chilis location selected as : " + locFromDropDown);
			extTestObj.createNode("Chilis location selected as : " + locFromDropDown).pass("PASSED");
			Thread.sleep(3000);

		} catch (Exception e) {
			log.error("Chillis location selection failed");
			extTestObj.createNode("Chillis location selection failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}

	}

	public void selectChilisLocationIOS() throws Exception {
		String locFromDropDown = excel.getCellData("AddMyVisit", "Chilis Location from DropDown", 2);
		try {
			scrollIntoViewBottom(Elements.chillisLocDropDown);
			explicitWait(Elements.chillisLocDropDown);
			clickableWait(Elements.chillisLocDropDown);
		Select s= new Select(driver.findElement(Elements.chillisLocDropDown));
			WebElement deff=s.getFirstSelectedOption();
			if(deff.isSelected())
		     {
				seetest.setPickerValues("WEB", "xpath=//*[@id='store-number']", 0, 0,locFromDropDown);
				Thread.sleep(1000);
				seetest.click("NATIVE", "xpath=//*[@id='Done']", 0, 1);
				seetest.setPickerValues("WEB", "xpath=//*[@id='store-number']", 0, 0,locFromDropDown);			    
		} 
		
			log.info("Chilis location selected as : " + locFromDropDown);
			extTestObj.createNode("Chilis location selected as : " + locFromDropDown).pass("PASSED");
			Thread.sleep(3000);

		} catch (Exception e) {
			log.error("Chillis location selection failed");
			extTestObj.createNode("Chillis location selection failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}

	}

	public void selectVisitMonth() throws InterruptedException {
		String visitMonth = excel.getCellData("AddMyVisit", "Visit Month", 2);
		try {
			scrollIntoViewBottom(Elements.visitMonthDropDown);
			scrollIntoViewBottom(By.xpath("//*[text()='" + visitMonth + "']"));
			clickElement(By.xpath("//*[text()='" + visitMonth + "']"));
			log.info("Visit month selected as :" + visitMonth);
			extTestObj.createNode("Visit month selected as :" + visitMonth).pass("PASSED");
		} catch (Exception e) {
			log.error("Visit month selection failed");
			extTestObj.createNode("Visit month selection failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}
	}

	public void selectVisitMonthIOS() throws InterruptedException {
		String visitMonth = excel.getCellData("AddMyVisit", "Visit Month", 2);
		try {
			scrollIntoViewBottom(Elements.visitMonthDropDown);
			explicitWait(Elements.visitMonthDropDown);
			clickableWait(Elements.visitMonthDropDown);
			Select s= new Select(driver.findElement(Elements.visitMonthDropDown));
			WebElement deff=s.getFirstSelectedOption();
			if(deff.isSelected())
		     {
				seetest.setPickerValues("WEB", "xpath=//*[@id='visit-month']", 0, 0,visitMonth);
				Thread.sleep(1000);
				seetest.click("NATIVE", "xpath=//*[@id='Done']", 0, 1);
				seetest.setPickerValues("WEB", "xpath=//*[@id='visit-month']", 0, 0,visitMonth);			    
		} 
			log.info("Visit month selected as :" + visitMonth);
			extTestObj.createNode("Visit month selected as :" + visitMonth).pass("PASSED");
		} catch (Exception e) {
			log.error("Visit month selection failed");
			extTestObj.createNode("Visit month selection failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}

	}

	public void selectVisitDay() {
		String visitDay = excel.getCellData("AddMyVisit", "Visit Day", 2);
		try {
			scrollIntoViewBottom(Elements.visitDayDropDown);
			scrollIntoViewBottom(By.xpath("//*[text()='" + visitDay + "']"));
			clickElement(By.xpath("//*[text()='" + visitDay + "']"));
			log.info("Visit day selected as : " + visitDay);
			extTestObj.createNode("Visit day selected as : " + visitDay).pass("PASSED");
		} catch (Exception e) {
			log.error("Visit day selection failed");
			extTestObj.createNode("Visit day selection failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}
	}

	public void selectVisitDayIOS() {
		String visitDay = excel.getCellData("AddMyVisit", "Visit Day", 2);
		try {
			scrollIntoViewBottom(Elements.visitDayDropDown);
			explicitWait(Elements.visitDayDropDown);
			clickableWait(Elements.visitDayDropDown);
			Select s= new Select(driver.findElement(Elements.visitDayDropDown));
			WebElement deff=s.getFirstSelectedOption();
			if(deff.isSelected())
		     {
				seetest.setPickerValues("WEB", "xpath=//*[@id='visit-day']", 0, 0,visitDay);
				Thread.sleep(1000);
				seetest.click("NATIVE", "xpath=//*[@id='Done']", 0, 1);
				seetest.setPickerValues("WEB", "xpath=//*[@id='visit-day']", 0, 0,visitDay);			    
		} 
			
			log.info("Visit day selected as : " + visitDay);
			extTestObj.createNode("Visit day selected as : " + visitDay).pass("PASSED");
		} catch (Exception e) {
			log.error("Visit day selection failed");
			extTestObj.createNode("Visit day selection failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	public void selectVisitYear() {
		String visitYear = excel.getCellData("AddMyVisit", "Visit Year", 2);
		try {
			explicitWait(Elements.visitYearDropDown);
			scrollIntoViewBottom(By.xpath("//*[text()='" + visitYear + "']"));
			clickElement(By.xpath("//*[text()='" + visitYear + "']"));
			log.info("Visit year selected as : " + visitYear);
			extTestObj.createNode("Visit year selected as : " + visitYear).pass("PASSED");
		} catch (Exception e) {
			log.error("Visit year selection failed");
			extTestObj.createNode("Visit year selection failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}
	}

	public void selectVisitYearIOS() {
		String visitYear = excel.getCellData("AddMyVisit", "Visit Year", 2);
		try {
			scrollIntoViewBottom(Elements.visitYearDropDown);
			explicitWait(Elements.visitYearDropDown);
			clickableWait(Elements.visitYearDropDown);
			Select s= new Select(driver.findElement(Elements.visitDayDropDown));
			WebElement deff=s.getFirstSelectedOption();
			if(deff.isSelected())
		     {
				seetest.setPickerValues("WEB", "xpath=//*[@id='visit-year']", 0, 0,visitYear);
				Thread.sleep(1000);
				seetest.click("NATIVE", "xpath=//*[@id='Done']", 0, 1);
				seetest.setPickerValues("WEB", "xpath=//*[@id='visit-year']", 0, 0,visitYear);			    
		} 
			log.info("Visit year selected as : " + visitYear);
			extTestObj.createNode("Visit year selected as : " + visitYear).pass("PASSED");
		} 
		catch (Exception e) {
			log.error("Visit year selection failed");
			extTestObj.createNode("Visit year selection failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}
	}

	public void enterCheckNumber() {

		String checkNo = excel.getCellData("AddMyVisit", "Receipt Check Number", 2);

		try {
			sendKeysWait(Elements.checkNumberTextBox, checkNo);
			log.info("Check Number entered as : " + checkNo);
			extTestObj.createNode("Check Number entered as : " + checkNo).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter check number");
			extTestObj.createNode("Failed to enter check number")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}

	}

	public void enterCheckTotal() {

		String checkTotal = excel.getCellData("AddMyVisit", "Subtotal", 2);
		try {
			clickableWait(Elements.checkTotalTextBox);
			driver.getKeyboard().sendKeys(checkTotal);
			log.info("Check total entered as : " + checkTotal);
			extTestObj.createNode("Check total entered as : " + checkTotal).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter check total");
			extTestObj.createNode("Failed to enter check total")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
	}

	public void clickSubmit() {
		try {
			clickableWait(Elements.visitSubmitButton);
			log.info("Visit submitted");
			extTestObj.createNode("Visit submitted").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to submit visit");
			extTestObj.createNode("Failed to submit visit")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}
	}

	public String retrieveSuccessMessage() {

		String successMsg = "";
		try {
			explicitWait(Elements.getSuccessMessageforAddMyVisit);
			successMsg = driver.findElement(Elements.getSuccessMessageforAddMyVisit).getText();
			log.info("Success message for add my visit displayed as : " + successMsg);
			extTestObj.createNode("Success message for add my visit displayed as : " + successMsg).pass("PASSED");
		} catch (Exception e) {
			log.error("Success message for add my visit not displayed");
			extTestObj.createNode("Success message for add my visit not displayed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
		return driver.findElement(Elements.getSuccessMessageforAddMyVisit).getText();

	}
	/* Validate user is able order placement with existing log in user */

	public void enterRestaurantLocationForLoggedInOrder() throws InterruptedException {

		String restLocation = excel.getCellData("Locations", "Location", 2);

		try {
			explicitWait(Elements.locationSearchTextBox);
			clickableWait(Elements.locationSearchTextBox);
			driver.getKeyboard().sendKeys(restLocation);
			log.info("Restaurant location entered as " + restLocation);
			extTestObj.createNode("Restaurant location entered as " + restLocation).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter Restaurant location");
			extTestObj.createNode("Failed to enter Restaurant location")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}

	}

	public void clickSearchButtonForLoggedInOrder() throws InterruptedException {
		try {
			scrollUp("200");
			clickableWait(Elements.searchButton);
			log.info("Search button clicked");
			extTestObj.createNode("Search button clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Search button click failed");
			extTestObj.createNode("Search button click failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}
	}

	public void orderNow() {
		String storeName = excel.getCellData("LoggedInOrder", "Store Name", 2);
		try {
			Thread.sleep(3000);
			scrollIntoViewHalf(
					By.xpath("//a[contains(@href,'" + storeName + "')]/following-sibling::a[text()='Order Now']"));
			Thread.sleep(3000);
			clickElement(By.xpath("//a[contains(@href,'" + storeName + "')]/following-sibling::a[text()='Order Now']"));
			log.info("Site scrolled and order button clicked");
			extTestObj.createNode("Site scrolled and order button clicked").pass("PASSED");

		} catch (Exception e) {
			log.error("Order Now button click failed");
			extTestObj.createNode("Order Now button click failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}

	}

	public void AddChilisFavouriteToCart() throws InterruptedException {

		String chilisFavItem = excel.getCellData("LoggedInOrder", "Chilis Favourite Items", 2).trim();
		try {

			List<WebElement> itemNames = driver
					.findElements(By.xpath("//div[@class='heading-tertiary heading-favorite']"));
			for (int i = 0; i < itemNames.size(); i++) {
				String name = itemNames.get(i).getText().trim();
				if (name.equalsIgnoreCase(chilisFavItem)) {
					WebElement ele =  driver
							.findElements(By.xpath("//div[@class='favorite-action']/button")).get(i);
					scrollIntoViewBottomByElement(ele);
					ele.click();
					break;
				}
			}
			log.info("Chilis favourite Item " + chilisFavItem + " selected");
			extTestObj.createNode("Chilis favourite Item " + chilisFavItem + " selected").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select chilis favourite item");
			extTestObj.createNode("Failed to select chilis favourite item")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}
	}

	public void clickViewCart() throws InterruptedException {
		try {
			clickableWait(Elements.cartIcon);
			clickableWait(Elements.viewCartButton);
			log.info("View Cart clicked");
			extTestObj.createNode("View Cart clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to click View Cart");
			extTestObj.createNode("Failed to click View Cart")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}
	}

	public void guestClickViewCart() throws InterruptedException {
		try {
			clickableWait(Elements.viewCartButton);
			log.info("View Cart clicked");
			extTestObj.createNode("View Cart clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to click View Cart");
			extTestObj.createNode("Failed to click View Cart")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}
	}

	public void selectSilverWare() throws InterruptedException {

		try {
			Thread.sleep(3000);
			scrollDownFromStart("450");
			explicitWait(Elements.silverWareCheckBox);
			clickableWait(Elements.optSilverWareCheckBox);
			log.info("Silver ware opted in");
			extTestObj.createNode("Silver ware opted in").pass("PASSED");
		} catch (Exception e) {
			log.error("Silver ware opt in failed");
			extTestObj.createNode("Silver ware opt in failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();

		}

	}

	public void clickCheckOut() {
		try {
			scrollIntoViewBottom(Elements.checkOutButton);
			clickableWait(Elements.checkOutButton);
			log.info("Order checked Out");
			extTestObj.createNode("Order checked Out").pass("PASSED");
		} catch (Exception e) {
			log.error("Order check out failed");
			extTestObj.createNode("Order check out failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}
	}

	public void continueToPayment() {
		try {
			explicitWait(Elements.orderTotal);
			scrollIntoViewBottom(Elements.paymentButton);
			clickableWait(Elements.paymentButton);
			log.info("Payment button clicked");
			extTestObj.createNode("Payment button clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to click Payment button");
			extTestObj.createNode("Failed to click Payment button")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}
	}

	public void enterCardNo() throws InterruptedException {

		String cardNo = excel.getCellData("LoggedInOrder", "Card Number", 2);
		try {
			Thread.sleep(4000);
			scrollIntoViewBottom(Elements.cardNo);
			explicitWait(Elements.cardNo);
			clickableWait(Elements.cardNo);
			driver.getKeyboard().sendKeys(cardNo);
			log.info("Card Number entered as : " + cardNo);
			extTestObj.createNode("Card Number entered as : " + cardNo).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter Card Number");
			extTestObj.createNode("Failed to enter Card Number")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}
	}

	public void enterCVV() {
		String cvv = excel.getCellData("LoggedInOrder", "CVV", 2);
		try {
			sendKeysWait(Elements.cvvTextBox, cvv);
			log.info("CVV entered as : " + cvv);
			extTestObj.createNode("CVV entered as : " + cvv).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter CVV");
			extTestObj.createNode("Failed to enter CVV")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}

	}

	public void selectExpirationMonth() {
		String month = excel.getCellData("LoggedInOrder", "Expiration Month", 2);
		try {
			scrollIntoViewBottom(Elements.expirationMonth);
			scrollIntoViewBottom(By.xpath("//*[contains(text(),'(0" + month + ")')]"));
			clickElement(By.xpath("//*[contains(text(),'(0" + month + ")')]"));
			log.info("Expiration Month selected as : " + month);
			extTestObj.createNode("Expiration Month selected as : " + month).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select Expiration Month");
			extTestObj.createNode("Failed to select Expiration Month")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}
	}
	
	public void selectExpirationMonthIOS() {
		String month = excel.getCellData("LoggedInOrder", "Expiration Month", 2);
		try {
			scrollIntoViewBottom(Elements.expirationMonth);
			explicitWait(Elements.expirationMonth);
			clickableWait(Elements.expirationMonth);
			Select s= new Select(driver.findElement(Elements.expirationMonth));
			WebElement deff=s.getFirstSelectedOption();
			if(deff.isSelected())
			{
				seetest.setPickerValues("WEB", "xpath=//*[@id='month-selector']", 0, 0,month);
				Thread.sleep(1000);
				seetest.click("NATIVE", "xpath=//*[@id='Done']", 0, 1);
				seetest.setPickerValues("WEB", "xpath=//*[@id='month-selector']", 0, 0,month);
			}
			log.info("Expiration Month selected as : " + month);
			extTestObj.createNode("Expiration Month selected as : " + month).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select Expiration Month");
			extTestObj.createNode("Failed to select Expiration Month")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}
	}

	public void selectExpirationYear() {
		String year = excel.getCellData("LoggedInOrder", "Expiration Year", 2);
		try {
			scrollIntoViewBottom(Elements.expirationYear);
			scrollIntoViewBottom(By.xpath("//*[text()='" + year + "']"));
			clickElement(By.xpath("//*[text()='" + year + "']"));
			log.info("Expiration Year selected as : " + year);
			extTestObj.createNode("Expiration Year selected as : " + year).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select Expiration Year");
			extTestObj.createNode("Failed to select Expiration Year")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}
	}

	public void selectExpirationYearIOS() {
		String year = excel.getCellData("LoggedInOrder", "Expiration Year", 2);
		try {
			scrollIntoViewBottom(Elements.expirationYear);
			explicitWait(Elements.expirationYear);
			clickableWait(Elements.expirationYear);
			Select s= new Select(driver.findElement(Elements.expirationYear));
			WebElement deff=s.getFirstSelectedOption();
			if(deff.isSelected())
			{
				seetest.setPickerValues("WEB", "xpath=//*[@id='year-selector']", 0, 0,year);
				Thread.sleep(1000);
				seetest.click("NATIVE", "xpath=//*[@id='Done']", 0, 1);
				seetest.setPickerValues("WEB", "xpath=//*[@id='year-selector']", 0, 0,year);
			}
			log.info("Expiration Year selected as : " + year);
			extTestObj.createNode("Expiration Year selected as : " + year).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select Expiration Year");
			extTestObj.createNode("Failed to select Expiration Year")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}
	}
	public void enterNameOnCard() throws InterruptedException {

		String nameOnCard = excel.getCellData("LoggedInOrder", "Name On Card", 2);
		try {
			scrollIntoViewBottom(Elements.nameOnCard);
			explicitWait(Elements.nameOnCard);
			clickableWait(Elements.nameOnCard);
			driver.getKeyboard().sendKeys(nameOnCard);
			log.info("Name on Card entered as : " + nameOnCard);
			extTestObj.createNode("Name on Card entered as : " + nameOnCard);
		} catch (Exception e) {
			log.error("Failed to enter Name on Card");
			extTestObj.createNode("Failed to enter Name on Card")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			;
			log.error(e.getMessage());

			stopTest();
		}
	}

	public void enterBillingZipCode() throws InterruptedException {

		String zipCode = excel.getCellData("LoggedInOrder", "Zip Code", 2);
		try {
			scrollIntoViewBottom(Elements.billingZip);
			explicitWait(Elements.billingZip);
			clickableWait(Elements.billingZip);
			driver.getKeyboard().sendKeys(zipCode);
			log.info("Billing zip code entered as : " + zipCode);
			extTestObj.createNode("Billing zip code entered as : " + zipCode).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter Billing zip code");
			extTestObj.createNode("Failed to enter Billing zip code")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	public void giveTip() {
		String tip = excel.getCellData("LoggedInOrder", "Tip", 2);
		try {
			scrollIntoViewBottom(Elements.tipTextBox);
			sendKeysWait(Elements.tipTextBox, tip);
			log.info("Tip given as : " + tip);
			extTestObj.createNode("Tip has entered").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter tip");
			extTestObj.createNode("Failed to enter tip")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
	}

	public String OrderTotal() {
		try {
			scrollIntoViewBottom(Elements.pickUpCost);
			explicitWait(Elements.pickUpCost);
			log.info("Pick up cost displayed as : " + driver.findElement(Elements.pickUpCost).getText());
			extTestObj
					.createNode("Order Total  cost displayed as : " + driver.findElement(Elements.pickUpCost).getText())
					.pass("PASSED");
		} catch (Exception e) {
			log.error("Pick up cost not displayed");
			extTestObj.createNode("Order Total cost not displayed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
		return driver.findElement(Elements.pickUpCost).getText();
	}

	public void checkRoundOff() {
		try {
			scrollIntoViewBottom(Elements.donationCheckBox);
			clickableWait(Elements.donationCheckBox);
			log.info("Donation checked");
			extTestObj.createNode("Donation checked").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to check donation check box");
			extTestObj.createNode("Failed to check donation check box")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
		}
	}

	public void placeOrder() {
		try {
			scrollIntoViewBottom(Elements.placeOrder);
			clickableWait(Elements.placeOrder);
			try {
			seetest.click("NATIVE", "xpath=//*[@text='No, thanks']", 0, 1);
			}
			catch(Exception exp) {}
			log.info("Place order button clicked");
			extTestObj.createNode("Place order button clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to click place order button");
			extTestObj.createNode("Failed to click place order button")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();

		}
	}

	public String getSuccessMessageforLoggedInOrder() {
		try {
			scrollIntoViewBottom(Elements.successMessageforLoggedInOrder);
			explicitWait(Elements.successMessageforLoggedInOrder);
			log.info("Success message displayed as : "
					+ driver.findElement(Elements.successMessageforLoggedInOrder).getText());

			extTestObj.createNode("Success message displayed as : "
					+ driver.findElement(Elements.successMessageforLoggedInOrder).getText()).pass("PASSED");
		} catch (Exception e) {
			log.error("Success message not displayed");
			extTestObj.createNode("Success message not displayed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
		return driver.findElement(Elements.successMessageforLoggedInOrder).getText();

	}

	public String returnOrderPrice() {
		try {
			scrollIntoViewBottom(Elements.orderPrice);
			explicitWait(Elements.orderPrice);
			log.info("Order price displayed as  : " + driver.findElement(Elements.orderPrice).getText());
			extTestObj.createNode(
					"Order price after payment displayed as  : " + driver.findElement(Elements.orderPrice).getText())
					.pass("PASSED");
		} catch (Exception e) {
			log.error("Order price not displayed");
			extTestObj.createNode("Order price after payment not displayed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
		return driver.findElement(Elements.orderPrice).getText();

	}

	/* Validate user is able to place reorder. */
	public void selectReorderOption() {
		try {
			clickableWait(Elements.reorderOption);
			Thread.sleep(3000);
			log.info("Reorder option selected");
			extTestObj.createNode("Reorder option selected").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select reorder option");
			log.error(e.getMessage());
			extTestObj.createNode("Failed to select reorder option")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			stopTest();
		}
	}

	public void selectPickupForFuture() {
		String futureTime = excel.getCellData("Future Order", "Future Date", 2);
		try {
			scrollIntoViewHalf(Elements.pickDate);
			clickableWait(Elements.pickDate);
			Thread.sleep(3000);
			clickElement(By.xpath("//*[contains(text(),'" + futureTime + "')]"));
			log.info("Pickup future time is selected as " + futureTime);
			extTestObj.createNode("Pickup future time is selected as " + futureTime).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select pick up future time");

			extTestObj.createNode("Failed to select pick up future time")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	public void clickReorderforanOrder() {
		try {
			scrollIntoViewBottom(Elements.reOrder);
			clickableWait(Elements.reOrder);
			log.info("Clicked reorder for a particular order");
			Thread.sleep(3000);
			extTestObj.createNode("Clicked reorder for a particular order").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to click reorder");
			log.error(e.getMessage());
			extTestObj.createNode("Failed to click reorder")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			stopTest();
		}
	}

	public void changeQuantity() {
		String quantity = excel.getCellData("ReOrder", "Quantity", 2);
		try {
			scrollIntoViewBottom(Elements.quantity);
			scrollIntoViewBottom(By.xpath("//*[text()='" + quantity + "']"));
			clickElement(By.xpath("//*[text()='" + quantity + "']"));
			log.info("Quantity changed");
			extTestObj.createNode("Quantity changed").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select and change quantity");
			log.error(e.getMessage());
			extTestObj.createNode("Failed to select and change quantity")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			stopTest();
		}
	}
	
	public void changeQuantityIOS() {
		String quantity = excel.getCellData("ReOrder", "Quantity", 2);
		try {
			scrollIntoViewBottom(Elements.quantity);
			explicitWait(Elements.quantity);
			clickableWait(Elements.quantity);
			Select s= new Select(driver.findElement(Elements.quantity));
			WebElement deff=s.getFirstSelectedOption();
			if(deff.isSelected())
			{
				seetest.setPickerValues("WEB", "xpath=//*[@id='items0.quantity']", 0, 0,quantity);
				Thread.sleep(1000);
				seetest.click("NATIVE", "xpath=//*[@id='Done']", 0, 1);
				seetest.setPickerValues("WEB", "xpath=//*[@id='items0.quantity']", 0, 0,quantity);
			}
			log.info("Quantity changed");
			extTestObj.createNode("Quantity changed").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select and change quantity");
			log.error(e.getMessage());
			extTestObj.createNode("Failed to select and change quantity")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			stopTest();
		}
	}

	/* Validate user is able to place Delivery-ASAP order. For Guest user */

	public void clickOrderNow() {
		String restID = excel.getCellData("Common", "Restaurant ID", 2);
		try {
			Thread.sleep(2000);
			scrollIntoViewBottom(By.xpath("//a[@id='location-select-" + restID + "']"));
			Thread.sleep(2000);
			clickableWait(By.xpath("//a[@id='location-select-" + restID + "]"));
			log.info("Site scrolled and order button clicked");
			extTestObj.createNode("Site scrolled and order button clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Site scrolled but order button not clicked");
			log.error(e.getMessage());
			extTestObj.createNode("Order now button not clicked")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			stopTest();
		}
	}

	public void clickMenuCatagory() {
		String category = excel.getCellData("Menu", "Category", 2);
		try {
			Thread.sleep(3000);
			scrollIntoViewHalf(By.xpath("//a[@title='" + category + "']"));
			Thread.sleep(3000);
			clickElement(By.xpath("//a[@title='" + category + "']"));
			log.info("Site scrolled and category " + category + " selected");
			extTestObj.createNode("Site scrolled and category " + category + " selected").pass("PASSED");
		} catch (Exception e) {
			log.error("Site scrolled but selected catagory button not clicked");
			extTestObj.createNode("Selected catagory button not clicked")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	public void clickItemOrder() {
		String item = excel.getCellData("Menu", "Item", 2);
		try {
			Thread.sleep(2000);
			scrollIntoViewHalf(By.xpath("//a[@title='" + item + "']"));
			Thread.sleep(2000);
			clickElement(By.xpath("//a[@title='" + item + "']"));
			log.info("Site scrolled and item" + item + "clicked");
			extTestObj.createNode("Site scrolled and item" + item + "clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Site scrolled but item not selected");
			extTestObj.createNode("Item not selected")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	public void clickAddItem() {
		try {
			scrollIntoViewBottom(Elements.addThisItem);
			explicitWait(Elements.addThisItem);
			clickElement(Elements.addThisItem);
			log.info("Item added to cart");
			extTestObj.createNode("Item added to cart").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to add item to cart");
			extTestObj.createNode("Failed to add item to cart")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	public void clickCheckOutforGuestUser() {
		try {
			scrollIntoViewBottom(Elements.checkOutButton);
			clickableWait(Elements.checkOutButton);
			log.info("Order checked Out");
			extTestObj.createNode("Order checked Out").pass("PASSED");
		} catch (Exception e) {
			log.error("Order check out failed");
			extTestObj.createNode("Order check out failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	public void selectDelivery() {
		try {
			Thread.sleep(2000);
			scrollIntoViewBottom(Elements.selectDeliveryMode);
			Thread.sleep(2000);
			clickableWait(Elements.selectDeliveryMode);
			log.info("Site scrolled and Delivery mode is selected");
			extTestObj.createNode("Site scrolled and Delivery mode is selected").pass("PASSED");
		} catch (Exception e) {
			log.error("Site scrolled but Delivery mode is not selected");
			extTestObj.createNode("Delivery mode is not selected")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	@SuppressWarnings("deprecation")
	public void enterDeliveryLocation() throws InterruptedException {
		String location = excel.getCellData("DeliveryASAP", "Restaurant Address", 2);
		try {
			scrollIntoViewBottom(Elements.deliveryAddress);
			explicitWait(Elements.deliveryAddress);
			driver.findElement(Elements.deliveryAddress).sendKeys(location);
			Thread.sleep(2000);

			seetest.click("WEB", "xpath=//*[@text='14534 South Military Trail']", 0, 1);
			Thread.sleep(2000);
			log.info("Delivery location entered as " + location);
			extTestObj.createNode("Delivery location entered as " + location).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter Delivery location");
			log.error(e.getMessage());
			extTestObj.createNode("Failed to enter Delivery location")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			stopTest();
		}
	}

	public void enterApartmentNo() throws InterruptedException {
		String aptNo = excel.getCellData("DeliveryASAP", "Apt. no", 2);
		try {
			scrollIntoViewHalf(Elements.aptNo);
			explicitWait(Elements.aptNo);
			clickableWait(Elements.aptNo);
			driver.getKeyboard().sendKeys(aptNo);
			log.info("Apartment no. entered as : " + aptNo);
			extTestObj.createNode("Apartment no. entered as : " + aptNo).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter Apartment no.");
			extTestObj.createNode("Failed to enter Apartment no.")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	public void selectDeliveryDateASAP() {
		try {
			scrollIntoViewBottom(Elements.delDate);
			scrollIntoViewBottom(Elements.dateInputASAP);
			clickElement(Elements.dateInputASAP);
			log.info("Delivery Option selected as ASAP");
			extTestObj.createNode("Delivery Option selected as ASAP").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select Delivery Option as ASAP");
			extTestObj.createNode("Failed to select Delivery Option as ASAP")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();

		}

	}

	public void selectDeliveryDate() {
		String dateInput = excel.getCellData("Delivery", "Delivery Date", 2);
		try {
			scrollIntoViewBottom(Elements.delDate);
			Thread.sleep(2000);
			scrollIntoViewBottom(By.xpath("//*[contains(text(), '" + dateInput + "')]"));
			clickElement(By.xpath("//*[contains(text(), '" + dateInput + "')]"));
			Thread.sleep(2000);
			log.info("Delivery date selected as " + dateInput);
			extTestObj.createNode("Delivery date selected as " + dateInput).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select Delivery date");
			log.error(e.getMessage());
			extTestObj.createNode("Failed to select Delivery date")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			stopTest();
		}
	}

	public void enterFirstName() throws InterruptedException {

		String firstName = excel.getCellData("DeliveryASAP", "First Name", 2);
		try {
			scrollIntoViewHalf(Elements.firstName);
			explicitWait(Elements.firstName);
			clickableWait(Elements.firstName);
			Thread.sleep(1000);
			driver.getKeyboard().sendKeys(firstName);
			log.info("First name entered as : " + firstName);
			extTestObj.createNode("First name entered as : " + firstName).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter First name");
			extTestObj.createNode("Failed to enter First name")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	public void enterLastName() throws InterruptedException {
		String LastName = excel.getCellData("DeliveryASAP", "Last Name", 2);
		try {
			scrollIntoViewHalf(Elements.lastName);
			explicitWait(Elements.lastName);
			clickableWait(Elements.lastName);
			driver.getKeyboard().sendKeys(LastName);
			log.info("Last name entered as : " + LastName);
			extTestObj.createNode("Last name entered as : " + LastName).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter Last name");
			extTestObj.createNode("Failed to enter Last name")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	public void enterContactNumber() throws InterruptedException {

		String contactNum = excel.getCellData("DeliveryASAP", "Contact Number", 2);
		try {
			scrollIntoViewHalf(Elements.contantNumber);
			explicitWait(Elements.contantNumber);
			clickableWait(Elements.contantNumber);
			driver.getKeyboard().sendKeys(contactNum);
			log.info("Contact Number entered as : " + contactNum);
			extTestObj.createNode("Contact Number entered as : " + contactNum).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter Contact number");
			extTestObj.createNode("Failed to enter Contact number")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	public void enterEMail() throws InterruptedException {
		String email = excel.getCellData("DeliveryASAP", "Email", 2);
		try {
			scrollIntoViewHalf(Elements.eMail);
			explicitWait(Elements.eMail);
			clickableWait(Elements.eMail);
			driver.getKeyboard().sendKeys(email);
			log.info("Email address entered as : " + email);
			extTestObj.createNode("Email address entered as : " + email).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter Email id");
			extTestObj.createNode("Failed to enter Email id")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			stopTest();
		}
	}

	/* Validate user is able to place Delivery-Later today order. For Guest user */
	public void selectDeliveryTime() { // Ayushman01
		String timeInput = excel.getCellData("Delivery", "Delivery Time", 2);
		try {
			scrollIntoViewHalf(Elements.delTime);
			// clickableWait(Elements.delTime);
			Thread.sleep(2000);
			scrollIntoViewBottom(By.xpath("//select[@id='delivery-time']/option[text()='" + timeInput + "']"));
			clickElement(By.xpath("//select[@id='delivery-time']/option[text()='" + timeInput + "']"));
			Thread.sleep(2000);
			log.info("Delivery time selected as " + timeInput);
			extTestObj.createNode("Delivery time selected as " + timeInput).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select Delivery time");
			log.error(e.getMessage());
			extTestObj.createNode("Failed to select Delivery time")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			stopTest();
		}
	}

	/* Validate user is able to place Delivery-ASAP order. For MCA user */
	public void enterDeliveryInstrBox() {
		String deliveryInstruction = excel.getCellData("DeliveryASAP", "Instruction", 2);
		try {

			clickableWait(Elements.deliveryInstrTextbox);
			driver.getKeyboard().sendKeys(deliveryInstruction);
			log.info("Delivery Instruction given as " + deliveryInstruction);
			extTestObj.createNode("Delivery Instruction given as " + deliveryInstruction).pass("PASSED");

		} catch (Exception e) {
			log.error("Failed to provide Delivery Instructions");
			extTestObj.createNode("Failed to provide Delivery Instructions")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	/* Validate if user is able to place a curbside order. */
	public void selectCurbside() {
		try {
			Thread.sleep(2000);
			scrollIntoViewBottom(Elements.selectCurbsideMode);
			Thread.sleep(2000);
			clickableWait(Elements.selectCurbsideMode);
			log.info("Site scrolled and Curbside mode is selected");
			extTestObj.createNode("Site scrolled and Curbside mode is selected").pass("PASSED");

		} catch (Exception e) {
			log.error("Site scrolled but Curbside mode is not selected");
			extTestObj.createNode("Curbside mode is not selected")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	public void selectPickupDate() {
		String dateInput = excel.getCellData("Pickup", "Pickup Date", 2);
		try {
			scrollIntoViewHalf(Elements.pickDate);
			clickableWait(Elements.pickDate);
			Thread.sleep(2000);
			scrollIntoViewHalf(By.xpath("//*[contains(text(), '" + dateInput + "')]"));
			clickableWait(By.xpath("//*[contains(text(), '" + dateInput + "')]"));
			Thread.sleep(2000);
			log.info("Pickup date selected as " + dateInput);
			extTestObj.createNode("Pickup date selected as " + dateInput).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select Pickup date");
			log.error(e.getMessage());
			extTestObj.createNode("Failed to select Pickup date")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			stopTest();
		}
	}

	public void selectPickupTime() {
		String timeInput = excel.getCellData("CarryOut", "Pickup Time", 2);
		try {
			scrollIntoViewBottom(Elements.pickTime);
			scrollIntoViewBottom(By.xpath("//select[@id='pickup-time']/option[text()='" + timeInput + "']"));
			clickElement(By.xpath("//select[@id='pickup-time']/option[text()='" + timeInput + "']"));
			log.info("Pickup time selected as " + timeInput);
			extTestObj.createNode("Pickup time selected as " + timeInput).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select Pickup time");
			extTestObj.createNode("Failed to select Pickup time")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}
	
	public void selectPickupTimeIOS() {
		String timeInput = excel.getCellData("CarryOut", "Pickup Time", 2);
		try {
			scrollIntoViewBottom(Elements.pickTime);
			explicitWait(Elements.pickTime);
			clickableWait(Elements.pickTime);
			Select s= new Select(driver.findElement(Elements.pickTime));
			WebElement deff=s.getFirstSelectedOption();
			if(deff.isSelected())
			{
				seetest.setPickerValues("WEB", "xpath=//*[@id='pickup-time']", 0, 0,timeInput);
				Thread.sleep(1000);
				seetest.click("NATIVE", "xpath=//*[@id='Done']", 0, 1);
				seetest.setPickerValues("WEB", "xpath=//*[@id='pickup-time']", 0, 0,timeInput);
			}
			log.info("Pickup time selected as " + timeInput);
			extTestObj.createNode("Pickup time selected as " + timeInput).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select Pickup time");
			extTestObj.createNode("Failed to select Pickup time")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	public void enterVehicleMake() throws InterruptedException {

		String vehicle = excel.getCellData("GuestUserCurbSide", "Vehicle Make", 2);
		try {
			scrollIntoViewBottom(Elements.vehicleMake);
			explicitWait(Elements.vehicleMake);
			clickableWait(Elements.vehicleMake);
			driver.getKeyboard().sendKeys(vehicle);
			log.info("Vehicle make entered as " + vehicle);
			extTestObj.createNode("Vehicle make entered as " + vehicle).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter Vehicle make");
			extTestObj.createNode("Failed to enter Vehicle make")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	public void enterVehicleModel() throws InterruptedException {

		String vehicleModel = excel.getCellData("GuestUserCurbSide", "Vehicle Model", 2);
		try {
			scrollIntoViewHalf(Elements.vehicleModel);
			explicitWait(Elements.vehicleModel);
			clickableWait(Elements.vehicleModel);
			driver.getKeyboard().sendKeys(vehicleModel);
			log.info("Vehicle model entered as : " + vehicleModel);
			extTestObj.createNode("Vehicle model entered as : " + vehicleModel).pass("PASSED");

		} catch (Exception e) {
			log.error("Failed to enter Vehicle model");
			extTestObj.createNode("Failed to enter Vehicle model")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	public void enterVehicleColor() throws InterruptedException {

		String vehicleColor = excel.getCellData("GuestUserCurbSide", "Vehicle Color", 2);
		try {
			scrollIntoViewBottom(Elements.vehicleColor);
			explicitWait(Elements.vehicleColor);
			clickableWait(Elements.vehicleColor);
			driver.getKeyboard().sendKeys(vehicleColor);
			log.info("Vehicle Color entered as " + vehicleColor);
			extTestObj.createNode("Vehicle Color entered as " + vehicleColor).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter Vehicle Color");
			extTestObj.createNode("Failed to enter Vehicle Color")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	/* Validate user is able to place Delivery-Later today order. For MCA user */

	public void selectDeliveryLaterToday() {
		try {
			clickableWait(Elements.delDate);
			Thread.sleep(3000);
			clickElement(Elements.LaterToday);
			log.info("Later Today delivery time is selected");
			extTestObj.createNode("Later Today delivery time is selecte").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select delivery time Later Today");
			extTestObj.createNode("Failed to select delivery time Later Today")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	/* Validate user is able to place Curbside-ASAP order for MCA user */
	public void selectPickupAsap() {
		try {
			scrollIntoViewBottom(Elements.pickDate);
			scrollIntoViewBottom(Elements.pickupAsapOrder);
			clickElement(Elements.pickupAsapOrder);
			log.info("'ASAP' pickup time is selected");
			extTestObj.createNode("'ASAP' pickup time is selected").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select pickup time 'ASAP'");
			extTestObj.createNode("Failed to select pickup time 'ASAP'")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}
	
	public void selectPickupAsapIOS() {
		try {
			scrollIntoViewBottom(Elements.pickDate);
			explicitWait(Elements.pickDate);
			clickableWait(Elements.pickDate);
			Select s= new Select(driver.findElement(Elements.pickDate));
			WebElement deff=s.getFirstSelectedOption();
			if(deff.isSelected())
			{
				seetest.setPickerValues("WEB", "xpath=//*[@id='pickup-date']", 0, 0,"ASAP");
				Thread.sleep(1000);
				seetest.click("NATIVE", "xpath=//*[@id='Done']", 0, 1);
				seetest.setPickerValues("WEB", "xpath=//*[@id='pickup-date']", 0, 0,"ASAP");
			}
			log.info("'ASAP' pickup time is selected");
			extTestObj.createNode("'ASAP' pickup time is selected").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select pickup time 'ASAP'");
			extTestObj.createNode("Failed to select pickup time 'ASAP'")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	/*
	 * Validate user is able to place Carryout-future date-favorite items order. For
	 * sign in user
	 */

	public void clickOnCarryOut() {
		try {
			explicitWait(Elements.selectCarryOutMode);
			clickElement(Elements.selectCarryOutMode);
			log.info("Carryout Button is Clicked");
			extTestObj.createNode("Carryout Button is Clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Carryout Button click failed");
			extTestObj.createNode("Carryout Button click failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}

	}

	/*
	 * 
	 * String futureTime = excel.getCellData(sheetName, colName, rowNum); try {
	 * clickableWait(Elements.pickDate); Thread.sleep(3000);
	 * clickElement(By.xpath("//*[text()='"+futureTime+"']"));
	 * log.info("Pickup future time is selected as "+futureTime);
	 * extTestObj.createNode("Pickup future time is selected as "+
	 * futureTime).pass("PASSED"); } catch (Exception e) {
	 * log.error("Failed to select pick up future time");
	 * extTestObj.createNode("Failed to select pick up future time").
	 * fail("Method Name : "+Thread.currentThread().getStackTrace()[1].getMethodName
	 * ()+"()").error(e); log.error(e.getMessage()); stopTest(); } }
	 * 
	 */

	public void selectPickupLaterToday() {
		try {
			scrollIntoViewBottom(Elements.pickDate);
			scrollIntoViewBottom(Elements.LaterToday);
			clickElement(Elements.LaterToday);
			log.info("Later Today pickup time is selected");
			extTestObj.createNode("Later Today pickup time is selected").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select Later Today");
			extTestObj.createNode("Failed to select Later Today")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}
	
	public void selectPickupLaterTodayIOS() {
		try {
			scrollIntoViewBottom(Elements.pickDate);
			explicitWait(Elements.pickDate);
			clickableWait(Elements.pickDate);
			Select s= new Select(driver.findElement(Elements.pickDate));
			WebElement deff=s.getFirstSelectedOption();
			if(deff.isSelected())
			{
				seetest.setPickerValues("WEB", "xpath=//*[@id='pickup-date']", 0, 0,"Later Today");
				Thread.sleep(1000);
				seetest.click("NATIVE", "xpath=//*[@id='Done']", 0, 1);
				seetest.setPickerValues("WEB", "xpath=//*[@id='pickup-date']", 0, 0,"Later Today");
			}
			log.info("Later Today pickup time is selected");
			extTestObj.createNode("Later Today pickup time is selected").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select Later Today");
			extTestObj.createNode("Failed to select Later Today")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	/* Validate user is able to place order with Rewards. For sign in user */
	public void addReward() {
		try {
			explicitWait(Elements.addRewards);
			scrollIntoViewBottom(Elements.addRewards);
			clickableWait(Elements.addRewards);
			log.info("Reward added");
			extTestObj.createNode("Reward added").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to add reward");
			extTestObj.createNode("Failed to add reward")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	
	 public void addRewardItem()
	  
	 { String rewarditem = excel.getCellData("OrderWithRewards","Reward Item", 2);
	 try {
	  clickableWait(By.xpath("//*[contains(text(),'"+rewarditem+"')]"));
	  explicitWait(Elements.addThisItem);
	  scrollIntoViewBottom(Elements.addThisItem);
	  clickableWait(Elements.addThisItem);
	  log.info("Reward item "+rewarditem+" added");
	  extTestObj.createNode("Reward item "+ rewarditem +" added").pass("PASSED"); }
	  catch(Exception e) { 
	 log.error("Failed to add reward item");
	 extTestObj.createNode("Failed to add reward item").
	  fail("Method Name : "+Thread.currentThread().getStackTrace()[1].getMethodName
	  ()+"()").error(e); log.error(e.getMessage()); stopTest(); } }
	 

	
public void isRewardApplied() {

	try {
		scrollIntoViewBottom(Elements.paymentButton);
		explicitWait(Elements.rewardName);
		if (driver.findElement(Elements.rewardName).isDisplayed()
				&& driver.findElement(Elements.discountLabel).isDisplayed()
				&& driver.findElement(Elements.discountAmount).isDisplayed()) {
			log.info("Reward " + driver.findElement(Elements.rewardName).getText() + " applied");
			log.info("Discount amount :" + driver.findElement(Elements.discountAmount).getText());
			extTestObj.createNode("Reward " + driver.findElement(Elements.rewardName).getText() + " applied")
					.pass("PASSED");
			extTestObj.createNode("Discount amount :" + driver.findElement(Elements.discountAmount).getText())
					.info("INFO");
		}
	} catch (Exception e) {
		log.error("Failed to apply discount");
		extTestObj.createNode("Failed to apply discount")
				.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
		log.error(e.getMessage());
		stopTest();
	}
}
	 

	/*
	 * Validate user is able to place Carry Out-Future order -item customization.
	 * For Guest user
	 */

	public void customizeOrder() {
		String custItem = excel.getCellData("Order Customization", "Custom Item", 2);
		System.out.println(custItem);

		try {
			scrollIntoViewHalf(Elements.customizeOrderButton); // added
			clickableWait(Elements.customizeOrderButton);
			// clickableWait(Elements.sauceDropDown);
			clickElement(By.xpath("//*[text()='" + custItem + "']"));
			clickableWait(Elements.addExtraSauce);
			// added
			log.info("Order customized with " + custItem);
			extTestObj.createNode("Order customized with " + custItem).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to customize order");
			extTestObj.createNode("Failed to customize order")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
			stopTest();
		}
	}

	public void validateCustomization() {
		String custItem = excel.getCellData("Order Customization", "Custom Item", 2);
		System.out.println(custItem);
		try {

			scrollIntoViewBottom(Elements.customItem);
			// span[text()='Avocado-Ranch Dressing - Extra']
			explicitWait(Elements.customItem);
			String custInfo = driver.findElement(Elements.customItem).getText();
			System.out.println(custInfo);

			if (custInfo.contains(custItem) && (custInfo.contains("Extra"))) {
				log.info("The item added for customization is " + custInfo);
				extTestObj.createNode("The item added for customization is " + custInfo).pass("PASSED");
			}
		} catch (Exception e) {
			log.error("Incorrect Customization Info");
			extTestObj.createNode("Incorrect Customization Info")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
			stopTest();
		}
	}

	/*
	 * Dine In : Place Dine-In order as MCA user (Join the Line thru Join Line
	 * option)
	 */

	public void initiateJoinLine() {
		String restID = excel.getCellData("Common", "Restaurant ID", 2);
		try {
			Thread.sleep(2000);
			scrollIntoViewHalf(By.xpath("//a[@id='location-join-line-" + restID + "']"));
			Thread.sleep(2000);
			clickableWait(By.xpath("//a[@id='location-join-line-" + restID + "']"));
			log.info("Site scrolled and join line button clicked");
			extTestObj.createNode("Site scrolled and join line button clicked").pass("PASSED");

		} catch (Exception e) {
			log.error("Site scrolled but join line button not clicked");
			log.error(e.getMessage());
			extTestObj.createNode("Join line button not clicked")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			stopTest();
		}
	}
	/*
	 * public void clickMinimumPartySize() { try { Thread.sleep(2000);
	 * scrollIntoViewHalf(Elements.partySizeOne); Thread.sleep(2000);
	 * clickableWait(Elements.partySizeOne);
	 * log.info("Site scrolled and party size selected(1)");
	 * extTestObj.createNode("Site scrolled and party size selected(1)").pass(
	 * "PASSED");
	 * 
	 * } catch (Exception e) {
	 * log.error("Site scrolled but party size not selected");
	 * log.error(e.getMessage());
	 * extTestObj.createNode("Party size not selected").fail("Method Name : "
	 * +Thread.currentThread().getStackTrace()[1].getMethodName()+"()").error(e);
	 * stopTest(); }
	 * 
	 * }
	 */

	/*
	 * public void enterNameJoinLine() throws InterruptedException { String
	 * nameJoinLine = excel.getCellData(sheetName, colName, rowNum); try {
	 * scrollIntoViewHalf(Elements.joinLineName);
	 * explicitWait(Elements.joinLineName); clickableWait(Elements.joinLineName);
	 * driver.getKeyboard().sendKeys(nameJoinLine);
	 * log.info("Name on join line entered");
	 * extTestObj.createNode("Name on join line entered").pass("PASSED");
	 * 
	 * } catch (Exception e) { log.error("Failed to enter Name on join line");
	 * log.error(e.getMessage());
	 * extTestObj.createNode("Failed to enter Name on join line").
	 * fail("Method Name : "+Thread.currentThread().getStackTrace()[1].getMethodName
	 * ()+"()").error(e); stopTest(); } }
	 */

	/*
	 * public void enterContactNumberJoinLine() throws InterruptedException { String
	 * cNumber = excel.getCellData(sheetName, colName, rowNum); try {
	 * scrollIntoViewHalf(Elements.joinLineContactNumber);
	 * explicitWait(Elements.joinLineContactNumber);
	 * clickableWait(Elements.joinLineContactNumber);
	 * driver.getKeyboard().sendKeys(cNumber); log.info("Mobile number entered");
	 * extTestObj.createNode("Mobile no on join line entered").pass("PASSED"); }
	 * catch (Exception e) { log.error("Failed to enter Mobile number");
	 * log.error(e.getMessage());
	 * extTestObj.createNode("Failed to enter Mobile number").
	 * fail("Method Name : "+Thread.currentThread().getStackTrace()[1].getMethodName
	 * ()+"()").error(e); stopTest(); } }
	 */

	/*
	 * public void clickJoinLine() { try { Thread.sleep(2000);
	 * scrollIntoViewHalf(Elements.clickJoinLine); Thread.sleep(2000);
	 * clickableWait(Elements.clickJoinLine);
	 * log.info("Site scrolled and joined the line");
	 * extTestObj.createNode("Site scrolled and joined the line").pass( "PASSED"); }
	 * catch (Exception e) { log.error("Site scrolled but not joined line");
	 * log.error(e.getMessage());
	 * extTestObj.createNode("Joined line is failed").fail("Method Name : "
	 * +Thread.currentThread().getStackTrace()[1].getMethodName()+"()").error(e);
	 * stopTest(); }
	 * 
	 * }
	 */
	/*****************************************************************************************************************/

	public void androidAppClosePopUp() {
		try {
			clickableWait(By.xpath("//*[@id='cancel_btn']"));
			explicitWait(Elements.appwelcomeMessageXpath);
			if (driver.findElement(Elements.appwelcomeMessageXpath).isDisplayed()) {
				log.info("Pop up closed");
				extTestObj.createNode("Pop up closed").pass("PASSED");
			}
		} catch (Exception e) {
			log.error("Failed to close pop up");
			extTestObj.createNode("Failed to close pop up")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}

	}

	public void appClickLoginButton() {
		try {
			clickableWait(Elements.appLoginButtonXpath);
			explicitWait(Elements.apploginHeaderXpath);
			if (driver.findElement(Elements.apploginHeaderXpath).isDisplayed()) {
				log.info("Login button clicked");
				extTestObj.createNode("Login button clicked").pass("PASSED");
			}
		} catch (Exception e) {
			log.error("Login button clicked");
			extTestObj.createNode("Login button clicked")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();

		}

	}

	public void appEnterUserName() {
		String username = excel.getCellData("Credentials", "UserName", 2);
		try {
			sendKeysWait(Elements.appUserNameTextBoxXpath, username);
			log.info("User name " + username + " entered");
			extTestObj.createNode("User name " + username + " entered").pass("PASSED");
		} catch (Exception e) {
			log.error("Could not enter user name");
			extTestObj.createNode("Could not enter user name")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	public void appEnterPassword() {
		String password = excel.getCellData("Credentials", "Password", 2);
		try {
			sendKeysWait(Elements.appPassWordTextBoxXpath, password);
			log.info("Password" + password + " entered");
			extTestObj.createNode("Password " + password + " entered").pass("PASSED");
		} catch (Exception e) {
			log.error("Could not enter Password");
			extTestObj.createNode("Could not enter Password")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	public void appClickSignIn() {
		try {
			clickableWait(Elements.appSigninButtonXpath);
//			clickableWait(Elements.appCancelButtonXpath);
			log.info("Sign in button clicked");
			extTestObj.createNode("Sign in button clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to click Sign in button");
			extTestObj.createNode("Failed to click Sign in button")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	public void appClickMoreButton() {
		try {
			clickableWait(Elements.appMoreButtonXpath);
			explicitWait(Elements.appLogoutButtonXpath);
			if (driver.findElement(Elements.appLogoutButtonXpath).isDisplayed()) {
				log.info("More button clicked");
				extTestObj.createNode("More button clicked").pass("PASSED");
			}
		} catch (Exception e) {
			log.error("Failed to click More button");
			extTestObj.createNode("Failed to click More button")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	public void appClickLogoutButton() {
		try {
			clickableWait(Elements.appLogoutButtonXpath);
			clickableWait(Elements.signOutConfirmButtonXpath);
			explicitWait(Elements.applogoutValXpath);
			if (driver.findElement(Elements.applogoutValXpath).isDisplayed()) {
				log.info("Logout button clicked and log out successful");
				extTestObj.createNode("Logout button clicked and log out successful").pass("PASSED");
			}
		} catch (Exception e) {
			log.error("Logout button clicked and log out successful");
			extTestObj.createNode("Logout button clicked and log out successful")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}

	}

	public void appClickFindRestaurant() {
		try {
			clickableWait(Elements.appfindRestaurantLinkXpath);
			explicitWait(Elements.appResSearchTextBoxXpath);
			if (driver.findElement(Elements.appResSearchTextBoxXpath).isDisplayed()) {
				log.info("Find Restaurant link clicked");
				extTestObj.createNode("Find Restaurant link clicked").pass("PASSED");
			}
		} catch (Exception e) {
			log.error("Failed to click Find Restaurant link");
			extTestObj.createNode("Failed to click Find Restaurant link")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	public void appEnterRestaurantLocation() {
		String restaurantLocation = excel.getCellData("Locations", "Location", 2);
		String[] str = restaurantLocation.split(",");
		try {
			clickableWait(Elements.appResSearchTextBoxXpath);
			sendKeysWait(Elements.appResSearchAutocompleteTextBoxXpath, restaurantLocation);
			clickElement(MobileBy.xpath("//android.widget.TextView[@text='" + str[0] + "']"));
			String displayedAddress = driver.findElement(Elements.appResSearchTextBoxXpath).getAttribute("text");
			String[] dispString = displayedAddress.split(",");
			if (str[0].equalsIgnoreCase(dispString[0])) {
				log.info("Restaurant location entered as : " + restaurantLocation);
				extTestObj.createNode("Restaurant location entered as : " + restaurantLocation).pass("PASSED");
			}
		}

		catch (Exception e) {
			log.error("Failed to enter Restaurant Location");
			extTestObj.createNode("Failed to enter Restaurant Location")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();

		}
	}

	// Rewards
	public void appClickRewardsButton() {
		try {
			clickableWait(Elements.appRewardButtonXpath);
			explicitWait(Elements.appRewardsHeaderXpath);
			if (driver.findElement(Elements.appRewardsHeaderXpath).isDisplayed()) {
				log.info("Rewards button clicked");
				extTestObj.createNode("Rewards button clicked").pass("PASSED");

			}
		}

		catch (Exception e) {
			log.error("Rewards button clicked");
			extTestObj.createNode("Rewards button clicked")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}

	public void appValidateRewardsCount() {
		try {
			explicitWait(Elements.appdisplayedRewardCountXpath);
			int displayedCount = Integer.parseInt(driver.findElement(Elements.appdisplayedRewardCountXpath).getText());
			List<WebElement> displayedRewards = driver.findElements(Elements.appActiveRewardXpath);
			int actualCount = displayedRewards.size();
			log.info("Displayed reward count : " + displayedCount);
			log.info("Actual reward count : " + actualCount);
			extTestObj.createNode("Displayed reward count : " + displayedCount).info("INFO");
			extTestObj.createNode("Actual reward count : " + actualCount).info("INFO");
			if (displayedCount == actualCount) {
				log.info("Rewards count matched");
				extTestObj.createNode("Rewards count matched").pass("PASSED");
			}
		}

		catch (Exception e) {
			log.error("Rewards count did not match");
			extTestObj.createNode("Rewards count did not match")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();

		}
	}

	public void appViewRewardNames() {
		try {
			List<WebElement> displayedRewards = driver.findElements(Elements.appActiveRewardXpath);

			for (int i = 0; i < displayedRewards.size(); i++) {
				AndroidElement element = (AndroidElement) driver.findElements(Elements.appTapToViewXpath).get(i);
				element.click();
				explicitWait(Elements.appActiveRewardName);
				log.info("Reward Name : " + driver.findElement(Elements.appActiveRewardName).getText());
				extTestObj.createNode("Reward Name : " + driver.findElement(Elements.appActiveRewardName).getText())
						.info("INFO");
				clickableWait(Elements.appCloseRewardsPopUp);

			}
		} catch (Exception e) {
			log.error("Failed to retrieve Reward names");
			extTestObj.createNode("Failed to retrieve Reward names")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();

		}

	}

	// Chilis Favourite Items
	public void appgetChilisFavouriteItems(AndroidDriver<AndroidElement> androidDriver) {
		System.out.println("Starting chilis fav");
		String []favoriteItems =new String[5];
		TouchAction action = new TouchAction(androidDriver);
		for(int i=0;i<favoriteItems.length;i++)
			favoriteItems[i]=excel.getCellData("Menu","ChilisFavourites",i+2);
		try {
			explicitWait(Elements.appfindRestaurantLinkXpath);
			androidDriver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(text(\"SELECT ITEMS\"))");
			
			for(int i =0;i<favoriteItems.length;i++)
			{
				String fav = favoriteItems[i];
			   if(driver.findElement(MobileBy.xpath("//*[@text='"+fav+"']")).isDisplayed())
			   {
				   String dispItem = driver.findElement(MobileBy.xpath("//*[@text='"+fav+"']")).getText();
				   log.info(" Favourite Item Name : " +dispItem);
					extTestObj.createNode("Favourite Item Name : " +dispItem).info("INFO");
			   }
			   else
			   {
				   String prevItem = favoriteItems[i-1];
				   System.out.println("Prev Item : "+prevItem);
				   String nextItem = favoriteItems[i];
				   System.out.println("Next Item : "+nextItem);
				   WebElement prevItemElement = driver.findElement(MobileBy.xpath("//*[@text='"+prevItem+"']"));
				   WebElement nextItemElement = driver.findElement(MobileBy.xpath("//*[@text='"+nextItem+"']"));
				   action.longPress(longPressOptions().withElement(element(prevItemElement)).
						   withDuration(Duration.ofSeconds(2))).
				   moveTo(element(nextItemElement)).release().perform();
			   }
			}
			log.info("Chilis Favourite Item names obtained");
			extTestObj.createNode("Chilis Favourite Item names obtained").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to retrieve Reward names");
			extTestObj.createNode("Failed to retrieve Reward names")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();

		}
	}
	
	public void appSelectMyAccountOption()
	{
		try {
			clickableWait(Elements.appMyAccountOptionXpath);
			explicitWait(Elements.appMyAccountPageHeaderXpath);
			if(driver.findElement(Elements.appMyAccountPageHeaderXpath).isDisplayed())
			{
				log.info("My Account option selected");
				extTestObj.createNode("My Account option selected").pass("PASSED");
			}
		}
		catch(Exception e)
		{
			log.error("My Account option selected");
			extTestObj.createNode("My Account option selected")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();

		}
	}
	public void appCheckConsent()
	{
		try {
			clickableWait(Elements.appConsentCheckBoxXpath);
			if(driver.findElement(Elements.appConsentCheckBoxXpath).getAttribute("checked").equalsIgnoreCase("true"))
			{
			log.info("Consent checked");
			extTestObj.createNode("Consent checked").pass("PASSED");
			}
		} catch (Exception e) {
			log.error("Consent check failed");
			extTestObj.createNode("Consent check failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();

		}
	}
	
	public void appClickUpdate()
	{
		try {
			clickableWait(Elements.appUpdateButtonXpath);
			explicitWait(Elements.appSuccessMessageXpath);
			if(driver.findElement(Elements.appSuccessMessageXpath).isDisplayed())
			{
				log.info("Update button click successful");
				extTestObj.createNode("Update button click successful").pass("PASSED");
			}
		}
		catch (Exception e) {
			log.error("Update button click failed");
			extTestObj.createNode("Update button click failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();

		}
		
	}
	
	public void appRetrieveSuccessMessage()
	{
		try {
			String msg = driver.findElement(Elements.appSuccessMessageXpath).getText();
				log.info("Success Message : "+msg);
				extTestObj.createNode("Success Message : "+msg).pass("PASSED");
		}
		catch (Exception e) {
			log.error("Success Message not displayed");
			extTestObj.createNode("Success Message not displayed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();

		}
		
	}
	
	/* function to retrieve the first name before My Account update */
	public void appgetFirstNameBeforeUpdate(AndroidDriver<AndroidElement> androidDriver) {
		String initialFirstName = "";
		try {
			androidDriver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(resourceId(\"firstName\"))");
			explicitWait(Elements.firstNameTextBox);
			initialFirstName = driver.findElement(Elements.firstNameTextBox).getAttribute("text");
			log.info("First Name before update obtained as : " + initialFirstName);
			extTestObj.createNode("First Name before update obtained as : " + initialFirstName).pass("PASSED");
		} catch (Exception e) {
			log.error("First Name before update not obtained");
			extTestObj.createNode("First Name before update not obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}

	}
	
	public void appgetLastNameBeforeUpdate(AndroidDriver<AndroidElement> androidDriver) {
		String initialLastName = "";
		try {
			androidDriver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(resourceId(\"lastName\"))");
			explicitWait(Elements.lastNameTextBox);
			initialLastName = driver.findElement(Elements.lastNameTextBox).getAttribute("text");
			log.info("Last Name before update obtained as : " + initialLastName);
			extTestObj.createNode("Last Name before update obtained as : " + initialLastName).pass("PASSED");
		} catch (Exception e) {
			log.error("Last Name before update not obtained");
			extTestObj.createNode("Last Name before update not obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
	}
	public void appgetEmailBeforeUpdate(AndroidDriver<AndroidElement> androidDriver) {
		String initialEmail = "";
		try {
			androidDriver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(resourceId(\"email\"))");
			explicitWait(Elements.emailTextBox);
			initialEmail = driver.findElement(Elements.emailTextBox).getAttribute("text");
			log.info("Email before update obtained as : " + initialEmail);
			extTestObj.createNode("Email before update obtained as : " + initialEmail).pass("PASSED");
		} catch (Exception e) {
			log.error("Email before update not obtained");
			extTestObj.createNode("Email before update not obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
	}
	
	public void appgetZipCodeBeforeUpdate(AndroidDriver<AndroidElement> androidDriver) {
		String initialZipCode = "";
		try {
			androidDriver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(resourceId(\"postalCode\"))");
			explicitWait(Elements.zipCodeTextBox);
			initialZipCode = driver.findElement(Elements.zipCodeTextBox).getAttribute("text");
			log.info("Zip Code before update obtained as : " + initialZipCode);
			extTestObj.createNode("Zip Code before update obtained as : " + initialZipCode).pass("PASSED");
		} catch (Exception e) {
			log.error("Zip Code before update not obtained");
			extTestObj.createNode("Zip Code before update not obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
	}
	
	public void appUpdateFirstName(AndroidDriver<AndroidElement> androidDriver) {
		try {
			
			String firstName = excel.getCellData("UpdateMyAccount", "First Name", 2);
			androidDriver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(resourceId(\"firstName\"))");
			sendKeysWait(Elements.firstNameTextBox, firstName);
			log.info("First Name updated with : " + firstName);
			extTestObj.createNode("First Name updated with : " + firstName).pass("PASSED");
		} catch (Exception e) {
			log.error("First Name updation failed");
			extTestObj.createNode("First Name updation failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}

	}
	
	public void appUpdateLastName(AndroidDriver<AndroidElement> androidDriver) {
		try {
			String lastName = excel.getCellData("UpdateMyAccount", "Last Name", 2);
			androidDriver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(resourceId(\"lastName\"))");
			sendKeysWait(Elements.lastNameTextBox, lastName);
			log.info("Last Name updated with : " + lastName);
			extTestObj.createNode("Last Name updated with : " + lastName).pass("PASSED");
		} catch (Exception e) {
			log.error("Last Name updation failed");
			extTestObj.createNode("Last Name updation failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}

	}
	
	public void appUpdateEmail(AndroidDriver<AndroidElement> androidDriver) {
		try {
			String email = excel.getCellData("UpdateMyAccount", "Email", 2);
			androidDriver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(resourceId(\"email\"))");
			sendKeysWait(Elements.emailTextBox, email);
			log.info("Email updated with : " + email);
			extTestObj.createNode("Email updated with : " + email).pass("PASSED");

		} catch (Exception e) {
			log.error("Email updation failed");
			extTestObj.createNode("Email updation failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}

	}
	public void appUpdateZipCode(AndroidDriver<AndroidElement> androidDriver) {
		try {
			String zipCode = excel.getCellData("UpdateMyAccount", "Zip Code", 2);
			androidDriver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(resourceId(\"postalCode\"))");
			sendKeysWait(Elements.zipCodeTextBox, zipCode);
			log.info("Zip Code updated with : " + zipCode);
			extTestObj.createNode("Zip Code updated with : " + zipCode).pass("PASSED");
		} catch (Exception e) {
			log.error("Zip Code updation failed");
			extTestObj.createNode("Zip Code updation failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
	}
	
	public String appgetFirstNameAfterUpdate(AndroidDriver<AndroidElement> androidDriver) {
		String finalFirstName = "";
		try {
			androidDriver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(resourceId(\"firstName\"))");
			explicitWait(Elements.firstNameTextBox);
			finalFirstName = driver.findElement(Elements.firstNameTextBox).getAttribute("text");
			log.info("First Name after update obtained as : " + finalFirstName);
			extTestObj.createNode("First Name after update obtained as : " + finalFirstName).pass("PASSED");
		} catch (Exception e) {
			log.error("First Name after update not obtained");
			extTestObj.createNode("First Name after update not obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
		return finalFirstName;
	}
	
	public String appgetLastNameAfterUpdate(AndroidDriver<AndroidElement> androidDriver) {
		String finalLastName = "";
		try {
			androidDriver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(resourceId(\"lastName\"))");
			explicitWait(Elements.lastNameTextBox);
			finalLastName = driver.findElement(Elements.lastNameTextBox).getAttribute("text");
			log.info("Last Name after update obtained as : " + finalLastName);
			extTestObj.createNode("Last Name after update obtained as : " + finalLastName).pass("PASSED");
		} catch (Exception e) {
			log.error("Last Name after update not obtained");
			extTestObj.createNode("Last Name after update not obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
		return finalLastName;
	}
	
	public String appgetEmailAfterUpdate(AndroidDriver<AndroidElement> androidDriver) {
		String finalEmail = "";
		try {
			androidDriver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(resourceId(\"email\"))");
			explicitWait(Elements.emailTextBox);
			finalEmail = driver.findElement(Elements.emailTextBox).getAttribute("text");
			log.info("Email after update obtained as : " + finalEmail);
			extTestObj.createNode("Email after update obtained as : " + finalEmail).pass("PASSED");
		} catch (Exception e) {
			log.error("Email after update not obtained");
			extTestObj.createNode("Email after update not obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
		return finalEmail;
	}
	
	public String appgetZipCodeAfterUpdate(AndroidDriver<AndroidElement> androidDriver) {
		String finalZipCode = "";
		try {
			androidDriver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(resourceId(\"postalCode\"))");
			explicitWait(Elements.zipCodeTextBox);
			finalZipCode = driver.findElement(Elements.zipCodeTextBox).getAttribute("text");
			log.info("Zip Code after update obtained as : " + finalZipCode);
			extTestObj.createNode("Zip Code after update obtained as : " + finalZipCode).pass("PASSED");
		} catch (Exception e) {
			log.error("Zip Code after update not obtained");
			extTestObj.createNode("Zip Code after update not obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
		return finalZipCode;
	}
	
	public void appValidateUpdate(AndroidDriver<AndroidElement> androidDriver) {
		try {
			String firstNameAfterUpdate = appgetFirstNameAfterUpdate(androidDriver);
			String lastNameAfterUpdate = appgetLastNameAfterUpdate(androidDriver);
			String emailAfterUpdate = appgetEmailAfterUpdate(androidDriver);
			String zipCodeAfterUpdate = appgetZipCodeAfterUpdate(androidDriver);
			Assert.assertEquals(excel.getCellData("UpdateMyAccount", "First Name", 2), firstNameAfterUpdate);
			Assert.assertEquals(excel.getCellData("UpdateMyAccount", "Last Name", 2), lastNameAfterUpdate);
			Assert.assertEquals(excel.getCellData("UpdateMyAccount", "Email", 2), emailAfterUpdate);
			Assert.assertEquals(excel.getCellData("UpdateMyAccount", "Zip Code", 2), zipCodeAfterUpdate);
			log.info("All fields are updated");
			extTestObj.createNode("All fields are updated").pass("PASSED");
		} catch (Exception e) {
			log.info("Error observed in fields updation");
			extTestObj.createNode("Error observed in fields updation")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}

	}
	
	public void appClosePopUpIOS() {
		try {
			clickableWait(Elements.iosPopUpClose);
			explicitWait(Elements.ioswelcomeMessageXpath);
			if (driver.findElement(Elements.ioswelcomeMessageXpath).isDisplayed()) {
				log.info("Pop up closed");
				extTestObj.createNode("Pop up closed").pass("PASSED");
			}
		} catch (Exception e) {
			log.error("Failed to close pop up");
			extTestObj.createNode("Failed to close pop up")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}

	}
	public void appClickLoginButtonIOS() {
		try {
			clickableWait(Elements.iosLoginButton);
			explicitWait(Elements.iosloginHeaderXpath);
			if (driver.findElement(Elements.iosloginHeaderXpath).isDisplayed()) {
				log.info("Login button clicked");
				extTestObj.createNode("Login button clicked").pass("PASSED");
			}
		} catch (Exception e) {
			log.error("Login button clicked");
			extTestObj.createNode("Login button clicked")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();

		}

	}
	public void appEnterUserNameIOS() {
		String username = excel.getCellData("Credentials", "UserName", 2);
		try {
			sendKeysWait(Elements.iosUserNameTextBoxXpath, username);
			log.info("User name " + username + " entered");
			extTestObj.createNode("User name " + username + " entered").pass("PASSED");
		} catch (Exception e) {
			log.error("Could not enter user name");
			extTestObj.createNode("Could not enter user name")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}
	
	public void appEnterPasswordIOS() {
		String password = excel.getCellData("Credentials", "Password", 2);
		try {
			sendKeysWait(Elements.iosPaswordTextBoxXpath, password);
			log.info("Password" + password + " entered");
			extTestObj.createNode("Password " + password + " entered").pass("PASSED");
		} catch (Exception e) {
			log.error("Could not enter Password");
			extTestObj.createNode("Could not enter Password")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}
	
	public void appClickSignInIOS() {
		try {
			clickableWait(Elements.iosSignInButtonXpath);
//			clickableWait(Elements.appCancelButtonXpath);
			log.info("Sign in button clicked");
			extTestObj.createNode("Sign in button clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to click Sign in button");
			extTestObj.createNode("Failed to click Sign in button")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}
	
	public void appClickMoreButtonIOS() {
		try {
			clickableWait(Elements.iosMoreButtonXpath);
			explicitWait(Elements.iosLogOutButtonXpath);
			if (driver.findElement(Elements.iosLogOutButtonXpath).isDisplayed()) {
				log.info("More button clicked");
				extTestObj.createNode("More button clicked").pass("PASSED");
			}
		} catch (Exception e) {
			log.error("Failed to click More button");
			extTestObj.createNode("Failed to click More button")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}
	
	public void appClickLogoutButtonIOS() {
		try {
			clickableWait(Elements.iosLogOutButtonXpath);
			explicitWait(Elements.ioslogoutValXpath);
			if (driver.findElement(Elements.ioslogoutValXpath).isDisplayed()) {
				log.info("Logout button clicked and log out successful");
				extTestObj.createNode("Logout button clicked and log out successful").pass("PASSED");
			}
		} catch (Exception e) {
			log.error("Logout button clicked and log out successful");
			extTestObj.createNode("Logout button clicked and log out successful")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}

	}
	
	public void appClickFindRestaurantIOS() {
		try {
			clickableWait(Elements.iosSearchLocationLinkXpath);
			explicitWait(Elements.iosRestaurantSearchTextBoxXpath);
			if (driver.findElement(Elements.iosRestaurantSearchTextBoxXpath).isDisplayed()) {
				log.info("Find Restaurant link clicked");
				extTestObj.createNode("Find Restaurant link clicked").pass("PASSED");
			}
		} catch (Exception e) {
			log.error("Failed to click Find Restaurant link");
			extTestObj.createNode("Failed to click Find Restaurant link")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}
	
	public void appEnterRestaurantLocationIOS() {
		String restaurantLocation = excel.getCellData("Locations", "Location", 2);
		String[] str = restaurantLocation.split(",");
		try {
			clickableWait(Elements.iosRestaurantSearchTextBoxXpath);
			sendKeysWait(Elements.iosRestaurantAutocompleteTextBoxXpath, restaurantLocation);
			clickElement(MobileBy.xpath("(//*[@name='"+str[0]+"' and @class='UIAStaticText'])[1]"));
			String displayedAddress = driver.findElement(Elements.iosRestaurantSearchTextBoxXpath).getAttribute("text");
			if (restaurantLocation.equalsIgnoreCase(displayedAddress)) {
				log.info("Restaurant location entered as : " + restaurantLocation);
				extTestObj.createNode("Restaurant location entered as : " + restaurantLocation).pass("PASSED");
			}
		}

		catch (Exception e) {
			log.error("Failed to enter Restaurant Location");
			extTestObj.createNode("Failed to enter Restaurant Location")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();

		}
	}
	
	public void appClickRewardsButtonIOS() {
		try {
			clickableWait(Elements.iosRewardsButtonXpath);
			explicitWait(Elements.iosRewardsHeaderXpath);
			if (driver.findElement(Elements.iosRewardsHeaderXpath).isDisplayed()) {
				log.info("Rewards button clicked");
				extTestObj.createNode("Rewards button clicked").pass("PASSED");

			}
		}

		catch (Exception e) {
			log.error("Rewards button clicked");
			extTestObj.createNode("Rewards button clicked")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();
		}
	}
	
	public void appValidateRewardsCountIOS() {
		try {
			explicitWait(Elements.appdisplayedRewardCountXpath);
			int displayedCount = Integer.parseInt(driver.findElement(Elements.iosDisplayedRewardCount).getText());
			clickableWait(Elements.iosClaimedRewardsXpath);
			List<WebElement> displayedRewards = driver.findElements(Elements.iosrewardsImageXpath);
			int actualCount = displayedRewards.size();
			log.info("Displayed reward count : " + displayedCount);
			log.info("Actual reward count : " + actualCount);
			extTestObj.createNode("Displayed reward count : " + displayedCount).info("INFO");
			extTestObj.createNode("Actual reward count : " + actualCount).info("INFO");
			if (displayedCount == actualCount) {
				log.info("Rewards count matched");
				extTestObj.createNode("Rewards count matched").pass("PASSED");
			}
		}

		catch (Exception e) {
			log.error("Rewards count did not match");
			extTestObj.createNode("Rewards count did not match")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();

		}
	}
	
	public void appViewRewardNamesIOS() {
		try {
			List<WebElement> displayedRewards = driver.findElements(Elements.iosrewardsImageXpath);

			for (int i = 0; i < displayedRewards.size(); i++) {
				WebElement element = (WebElement) driver.findElements(Elements.iostapToViewXpath).get(i);
				element.click();
				explicitWait(Elements.iosRewardsNameXpath);
				log.info("Reward Name : " + driver.findElement(Elements.iosRewardsNameXpath).getText());
				extTestObj.createNode("Reward Name : " + driver.findElement(Elements.iosRewardsNameXpath).getText())
						.info("INFO");
				clickableWait(Elements.iosBackButtonXpath);

			}
		} catch (Exception e) {
			log.error("Failed to retrieve Reward names");
			extTestObj.createNode("Failed to retrieve Reward names")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			stopTest();

		}

	}
		


}
