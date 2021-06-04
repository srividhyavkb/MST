package Resources;

import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class FunctionalComponents extends Base {

	public AndroidDriver<AndroidElement> driver = null;
	Properties property = returnProperty();
	ExtentReports extent = ExtentReporterNG.getReportObject();
	Listeners listen;
	static ThreadLocal<ExtentTest> extTestObj;
	public WebDriverWait wait;
	Logger log;
	ExcelUtils excel;

	public FunctionalComponents(AndroidDriver<AndroidElement> driver, Logger log) {
		this.driver = driver;
		this.log = log;
		wait = new WebDriverWait(this.driver, 15);
		listen = new Listeners();
		try {
			excel = new ExcelUtils(
					"C:/Users/Somnath Baul/eclipse-workspace/MobileSeeTestWebAutomation/CommonData.xlsx");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getExtentTest(ThreadLocal<ExtentTest> extentTest) {
		extTestObj = extentTest;
	}

	public void scrollIntoViewBottom(By element) throws InterruptedException // Ayushman
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", driver.findElement(element));
		Thread.sleep(500);
	}

	public void scrollIntoViewBottomByElement(AndroidElement element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
		Thread.sleep(500);
	}

	public void scrollIntoViewTop(By element) throws InterruptedException // Ayushman
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
		Thread.sleep(500);
	}

	public void scrollIntoViewHalf(By element) throws InterruptedException // Ayushman
	{
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView(true); window.scrollBy(0, -window.innerHeight / 2);",
				driver.findElement(element));
		Thread.sleep(500);
	}

	public void clickableWait(By element) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public void explicitWait(By element) {
		wait.until(ExpectedConditions.presenceOfElementLocated(element));
	}

	public void clickElement(By element) {
		driver.findElement(element).click();
	}

	public void sendKeysWait(By element, String value) {
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		driver.findElement(element).clear();
		driver.findElement(element).sendKeys(value);
	}

	public void scrollDownFromStart(String endpoint) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + endpoint + ")", "");
	}

	public void scrollUp(String endpoint) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-" + endpoint + ")", "");
	}

	/* Validate user is able to login and then logout for the website */

	public void validateQASite() {
		extTestObj.get().createNode("Starting QA site validation").info("INFO");
		log.info("Starting QA site validation");
		try {
			explicitWait(Elements.popUpCloseButton);
			log.info("QA site launch is successful, Site Name : " + driver.getCurrentUrl());
			extTestObj.get().createNode("QA site launch is successful, Site Name : " + driver.getCurrentUrl())
					.pass("PASSED");

		} catch (Exception e) {
			log.error("QA site launch failed");
			extTestObj.get().createNode("QA site launch failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			extTestObj.get().createNode("Error message : ").error(e);
			tearDown();

		}

	}

	public void closePopupRewards() {
		try {
			clickableWait(Elements.popUpCloseButton);
			explicitWait(Elements.subHeader);
			log.info("Pop up closed");
			extTestObj.get().createNode("Pop up closed").pass("PASSED");
		} catch (Exception e) {
			log.error("Pop up close failed");
			extTestObj.get().createNode("Pop up close failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}

	}

	public void clickMenuButton() {
		try {
			clickableWait(Elements.menuButton);
			log.info("Menu button clicked");
			extTestObj.get().createNode("Menu button clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Menu button click failed");
			extTestObj.get().createNode("Menu button click failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();
		}
	}

	public void selectLogin() {
		try {
			clickableWait(Elements.loginButton);
			log.info("Login option selected");
			extTestObj.get().createNode("Login option selected").pass("PASSED");
		} catch (Exception e) {
			log.error("Login option not selected");
			extTestObj.get().createNode("Login option not selected")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}
	}

	public void enterUserName() {
		try {
			String username = excel.getCellData("Credentials", "UserName", 2);
			explicitWait(Elements.userNameTextBox);
			sendKeysWait(Elements.userNameTextBox, username);
			log.info("User name " + username + " entered");
			extTestObj.get().createNode("User name " + username + " entered").pass("PASSED");
		} catch (Exception e) {
			log.error("Could not enter user name");
			extTestObj.get().createNode("Could not enter user name")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}

	}

	public void enterPassword() {
		try {
			String password = excel.getCellData("Credentials", "Password", 2);
			sendKeysWait(Elements.passwordTextBox, password);
			log.info("Password " + password + " entered");
			extTestObj.get().createNode("Password " + password + " entered").pass("PASSED");
		} catch (Exception e) {
			log.error("Could not enter password");
			extTestObj.get().createNode("Could not enter password")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}

	}

	public void clickSignin() {
		try {
			clickableWait(Elements.signinButton);
			log.info("Sign in button clicked");
			extTestObj.get().createNode("Sign in button clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Sign in button click failed");
			extTestObj.get().createNode("Sign in button click failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}

	}

	public void logout() {
		try {
			clickableWait(Elements.logoutButton);
			log.info("Logout button clicked");
			extTestObj.get().createNode("Logout button clicked").pass("PASSED");
		} catch (Exception e) {
			log.info("Logout button click failed");
			extTestObj.get().createNode("Logout button click failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}
	}

	public String validateLogout() {

		try {
			explicitWait(Elements.loginHeader);
			log.info("Login header displayed : " + driver.findElement(Elements.loginHeader).getText());
			extTestObj.get()
					.createNode("Login header displayed : " + driver.findElement(Elements.loginHeader).getText())
					.pass("PASSED");
		} catch (Exception e) {
			log.error("Login header not displayed");
			extTestObj.get().createNode("Login header not displayed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
		return driver.findElement(Elements.loginHeader).getText();
	}

	/* Validate Location search fields and links for guest user */
	public void selectLocationsOption() {
		try {
			clickableWait(Elements.locationsButton);
			log.info("Location option selected");
			extTestObj.get().createNode("Location option selected").pass("PASSED");
		} catch (Exception e) {
			log.error("Location button selection failed");
			extTestObj.get().createNode("Location button selection failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}
	}

	public void enterRestaurantLocationforSearch() throws InterruptedException {
		try {
			explicitWait(Elements.locationSearchTextBox);
			clickableWait(Elements.locationSearchTextBox);
			String locn = excel.getCellData("Locations", "Location", 2);
			driver.getKeyboard().sendKeys(locn);
			log.info("Restaurant location " + locn + " entered");
			extTestObj.get().createNode("Restaurant location " + locn + " entered").pass("PASSED");

		} catch (Exception e) {
			log.error("Failed to enter Restaurant location");
			extTestObj.get().createNode("Failed to enter Restaurant location")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}
	}

	public void clickSearchButton() {
		try {
			clickableWait(Elements.searchButton);
			log.info("Search button clicked");
			extTestObj.get().createNode("Search button clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Search button click failed");
			extTestObj.get().createNode("Search button click failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();
		}
	}

	public String getRestaurantName() {
		try {
			explicitWait(Elements.restaurantName);
			log.info("Restaurant name " + driver.findElement(Elements.restaurantName).getText() + " is displayed");
			extTestObj.get().createNode(
					"Restaurant name " + driver.findElement(Elements.restaurantName).getText() + " is displayed")
					.pass("PASSED");
		} catch (Exception e) {
			log.error("Restaurant Name not displayed");
			extTestObj.get().createNode("Restaurant Name not displayed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
		return driver.findElement(Elements.restaurantName).getText();

	}

	/* Validate Rewards details page for logged in user */
	public int noOfRewards() {
		String displayedCount = "";
		try {
			explicitWait(Elements.noOfRewards);
			displayedCount = driver.findElement(Elements.noOfRewards).getText();
			log.info("Displayed rewards count obtained : " + displayedCount);
			extTestObj.get().createNode("Displayed rewards count obtained : " + displayedCount).pass("PASSED");
		} catch (Exception e) {
			log.error("Displayed rewards count couldn't be obtained");
			extTestObj.get().createNode("Displayed rewards count couldn't be obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}

		return Integer.parseInt(displayedCount);
	}

	public int ActualRewardsCount() {
		int count = 0;
		try {
			count = driver.findElements(Elements.actualRewardsCount).size();
			log.info("Actual rewards count obtained");
			extTestObj.get().createNode("Actual rewards count obtained : " + count).pass("PASSED");
		} catch (Exception e) {
			log.error("Actual rewards count couldn't be obtained");
			extTestObj.get().createNode("Actual rewards count couldn't be obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
		return count;

	}

	public void getRewardTitle() {
		List<AndroidElement> rewards = driver.findElements(Elements.actualRewardsCount);
		String rewardsTitle = "";
		try {
			log.info("Reward Titles :");
			extTestObj.get().log(Status.INFO, "Reward Titles :");
			for (AndroidElement reward : rewards) {
				rewardsTitle = reward.findElement(By.xpath("//div[@class='rewards-active-title item-title']"))
						.getText();
				log.info(rewardsTitle);
				extTestObj.get().log(Status.INFO, rewardsTitle);
			}
			log.info("Reward Titles displayed");
			extTestObj.get().createNode("Reward Titles displayed").pass("PASSED");
		} catch (Exception e) {
			log.error("Reward titles couldn't be obtained");
			extTestObj.get().createNode("Reward titles couldn't be obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
	}

	/* Validate user is able to update my Account. */
	public void selectMyAccountOption() {

		try {
			clickableWait(Elements.myAccountOption);
			log.info("My Account option selected");
			extTestObj.get().createNode("My Account option selected").pass("PASSED");
		} catch (Exception e) {
			log.error("My Account option selection failed");
			extTestObj.get().createNode("My Account option selection failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}

	}

	public void getFirstNameBeforeUpdate() {
		String initialFirstName = "";
		try {
			explicitWait(Elements.firstNameTextBox);
			initialFirstName = driver.findElement(Elements.firstNameTextBox).getAttribute("value");
			log.info("First Name before update obtained as : " + initialFirstName);
			extTestObj.get().createNode("First Name before update obtained as : " + initialFirstName).pass("PASSED");
		} catch (Exception e) {
			log.error("First Name before update not obtained");
			extTestObj.get().createNode("First Name before update not obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}

	}

	public void getLastNameBeforeUpdate() {
		String initialLastName = "";
		try {
			explicitWait(Elements.lastNameTextBox);
			initialLastName = driver.findElement(Elements.lastNameTextBox).getAttribute("value");
			log.info("Last Name before update obtained as : " + initialLastName);
			extTestObj.get().createNode("Last Name before update obtained as : " + initialLastName).pass("PASSED");
		} catch (Exception e) {
			log.error("Last Name before update not obtained");
			extTestObj.get().createNode("Last Name before update not obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
	}

	public void getEmailBeforeUpdate() {
		String initialEmail = "";
		try {
			explicitWait(Elements.emailTextBox);
			initialEmail = driver.findElement(Elements.emailTextBox).getAttribute("value");
			log.info("Email before update obtained as : " + initialEmail);
			extTestObj.get().createNode("Email before update obtained as : " + initialEmail).pass("PASSED");
		} catch (Exception e) {
			log.error("Email before update not obtained");
			extTestObj.get().createNode("Email before update not obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
	}

	public void getZipCodeBeforeUpdate() {
		String initialZipCode = "";
		try {
			explicitWait(Elements.zipCodeTextBox);
			initialZipCode = driver.findElement(Elements.zipCodeTextBox).getAttribute("value");
			log.info("Zip Code before update obtained as : " + initialZipCode);
			extTestObj.get().createNode("Zip Code before update obtained as : " + initialZipCode).pass("PASSED");
		} catch (Exception e) {
			log.error("Zip Code before update not obtained");
			extTestObj.get().createNode("Zip Code before update not obtained")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
	}

	public void updateEmail() {
		try {
			String email = excel.getCellData("UpdateMyAccount", "Email", 2);
			sendKeysWait(Elements.emailTextBox, email);
			log.info("Email updated with : " + email);
			extTestObj.get().createNode("Email updated with : " + email).pass("PASSED");

		} catch (Exception e) {
			log.error("Email updation failed");
			extTestObj.get().createNode("Email updation failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}

	}

	public void updateFirstName() {
		try {
			String firstName = excel.getCellData("UpdateMyAccount", "First Name", 2);
			sendKeysWait(Elements.firstNameTextBox, firstName);
			log.info("First Name updated with : " + firstName);
			extTestObj.get().createNode("First Name updated with : " + firstName).pass("PASSED");
		} catch (Exception e) {
			log.error("First Name updation failed");
			extTestObj.get().createNode("First Name updation failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}

	}

	public void updateLastName() {
		try {
			String lastName = excel.getCellData("UpdateMyAccount", "Last Name", 2);
			sendKeysWait(Elements.lastNameTextBox, lastName);
			log.info("Last Name updated with : " + lastName);
			extTestObj.get().createNode("Last Name updated with : " + lastName).pass("PASSED");
		} catch (Exception e) {
			log.error("Last Name updation failed");
			extTestObj.get().createNode("Last Name updation failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}

	}

	public void updateZipCode() {
		try {
			String zipCode = excel.getCellData("UpdateMyAccount", "Zip Code", 2);
			sendKeysWait(Elements.zipCodeTextBox, zipCode);
			log.info("Zip Code updated with : " + zipCode);
			extTestObj.get().createNode("Zip Code updated with : " + zipCode).pass("PASSED");
		} catch (Exception e) {
			log.error("Zip Code updation failed");
			extTestObj.get().createNode("Zip Code updation failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
	}

	public void checkConsent() {
		try {
			clickableWait(Elements.consentCheckBox);
			log.info("Consent checked");
			extTestObj.get().createNode("Consent checked").pass("PASSED");
		} catch (Exception e) {
			log.error("Consent check failed");
			extTestObj.get().createNode("Consent check failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
	}

	public void clickUpdateButton() {
		try {
			clickableWait(Elements.updateButton);
			log.info("Update button clicked");
			extTestObj.get().createNode("Update button clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Update button click failed");
			extTestObj.get().createNode("Update button click failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}
	}

	public void getSuccessMessage() {
		String successMessage = "";
		try {
			explicitWait(Elements.successMessageforUpdate);
			successMessage = driver.findElement(Elements.successMessageforUpdate).getText();
			log.info("Success message obtained as : " + successMessage);
			extTestObj.get().createNode("Success message obtained as : " + successMessage).pass("PASSED");
		} catch (Exception e) {
			log.error("Success message not obtained");
			extTestObj.get().createNode("Success message not obtained")
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
			extTestObj.get().createNode("First Name after update obtained as : " + finalFirstName).pass("PASSED");
		} catch (Exception e) {
			log.error("First Name after update not obtained");
			extTestObj.get().createNode("First Name after update not obtained")
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
			extTestObj.get().createNode("Last Name after update obtained as : " + finalLastName).pass("PASSED");
		} catch (Exception e) {
			log.error("Last Name after update not obtained");
			extTestObj.get().createNode("Last Name after update not obtained")
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
			extTestObj.get().createNode("Email after update obtained as : " + finalEmail).pass("PASSED");
		} catch (Exception e) {
			log.error("Email after update not obtained");
			extTestObj.get().createNode("Email after update not obtained")
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
			extTestObj.get().createNode("Zip Code after update obtained as : " + finalZipCode).pass("PASSED");
		} catch (Exception e) {
			log.error("Zip Code after update not obtained");
			extTestObj.get().createNode("Zip Code after update not obtained")
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
			extTestObj.get().createNode("All fields are updated").pass("PASSED");
		} catch (Exception e) {
			log.info("Error observed in fields updation");
			extTestObj.get().createNode("Error observed in fields updation")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}

	}

	/* Verify Favorites items is displaying on menu page */
	public void selectMenuOption() {
		try {
			clickableWait(Elements.menuOption);
			log.info("Menu Option selected");
			extTestObj.get().createNode("Menu Option selected").pass("PASSED");
		} catch (Exception e) {
			log.error("Menu Option selection failed");
			extTestObj.get().createNode("Menu Option selection failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}
	}

	public void getAllChilisFavouriteItems() {

		List<AndroidElement> items = null;
		try {
			items = driver.findElements(Elements.favouriteMenu);
			log.info("Chillis favourite Items : ");
			extTestObj.get().log(Status.INFO, "Chillis favourite Items : ");
			for (AndroidElement el : items) {
				log.info(el.findElement(Elements.favouriteItemsTitle).getText());
				extTestObj.get().log(Status.INFO, el.findElement(Elements.favouriteItemsTitle).getText());
			}
			log.info("All chilis favourite items obtained");
			extTestObj.get().createNode("All chilis favourite items obtained").pass("PASSED");
		} catch (Exception e) {
			log.error("Couldn't obtain chilis favourite items");
			extTestObj.get().createNode("Couldn't obtain chilis favourite items")
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
			extTestObj.get().createNode("Add my visit button clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Add my visit button click failed");
			extTestObj.get().createNode("Add my visit button click failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}
	}

	public void enterRestaurantLocation() throws InterruptedException {
		String loc = excel.getCellData("AddMyVisit", "Location", 2);
		try {
			explicitWait(Elements.restaurantLocTextBox);
			clickableWait(Elements.restaurantLocTextBox);
			driver.getKeyboard().sendKeys(loc);
			scrollIntoViewBottom(By.xpath("//*[text()='" + loc + "']"));
			driver.getKeyboard().sendKeys(Keys.ENTER);
			log.info("Restaurant location entered as : " + loc);
			extTestObj.get().createNode("Restaurant location entered as : " + loc).pass("PASSED");
			Thread.sleep(3000);
		} catch (Exception e) {
			log.error("Failed to enter restaurant location");
			extTestObj.get().createNode("Failed to enter restaurant location")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}
	}

	public void selectChilisLocation() throws Exception {
		String locFromDropDown = excel.getCellData("AddMyVisit", "Chilis Location from DropDown", 2);
		try {
			clickElement(Elements.chillisLocDropDown);
			scrollIntoViewBottom(By.xpath("//*[text()='" + locFromDropDown + "']"));
			clickElement(By.xpath("//*[text()='" + locFromDropDown + "']"));
			log.info("Chilis location selected as : " + locFromDropDown);
			extTestObj.get().createNode("Chilis location selected as : " + locFromDropDown).pass("PASSED");
			Thread.sleep(3000);

		} catch (Exception e) {
			log.error("Chillis location selection failed");
			extTestObj.get().createNode("Chillis location selection failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}

	}

	public void selectVisitMonth() throws InterruptedException {
		String visitMonth = excel.getCellData("AddMyVisit", "Visit Month", 2);
		try {
			scrollIntoViewBottom(Elements.visitMonthDropDown);
			scrollIntoViewBottom(By.xpath("//*[text()='"+visitMonth+"']"));
			clickElement(By.xpath("//*[text()='"+visitMonth+"']"));
			log.info("Visit month selected as :" + visitMonth);
			extTestObj.get().createNode("Visit month selected as :" + visitMonth).pass("PASSED");
		} catch (Exception e) {
			log.error("Visit month selection failed");
			extTestObj.get().createNode("Visit month selection failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}

	}

	public void selectVisitDay() {
		String visitDay = excel.getCellData("AddMyVisit", "Visit Day", 2);
		try {
			scrollIntoViewBottom(Elements.visitDayDropDown);
			scrollIntoViewBottom(By.xpath("//*[text()='"+visitDay+"']"));
			clickElement(By.xpath("//*[text()='"+visitDay+"']"));
			log.info("Visit day selected as : " + visitDay);
			extTestObj.get().createNode("Visit day selected as : " + visitDay).pass("PASSED");
		} catch (Exception e) {
			log.error("Visit day selection failed");
			extTestObj.get().createNode("Visit day selection failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}
	}

	public void selectVisitYear() {
		String visitYear = excel.getCellData("AddMyVisit", "Visit Year", 2);
		try {
			explicitWait(Elements.visitYearDropDown);
			scrollIntoViewBottom(By.xpath("//*[text()='"+visitYear+"']"));
			clickElement(By.xpath("//*[text()='"+visitYear+"']"));
			log.info("Visit year selected as : " + visitYear);
			extTestObj.get().createNode("Visit year selected as : " + visitYear).pass("PASSED");
		} catch (Exception e) {
			log.error("Visit year selection failed");
			extTestObj.get().createNode("Visit year selection failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}
	}

	public void enterCheckNumber() {

		String checkNo = excel.getCellData("AddMyVisit", "Receipt Check Number", 2);

		try {
			sendKeysWait(Elements.checkNumberTextBox, checkNo);
			log.info("Check Number entered as : " + checkNo);
			extTestObj.get().createNode("Check Number entered as : " + checkNo).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter check number");
			extTestObj.get().createNode("Failed to enter check number")
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
			extTestObj.get().createNode("Check total entered as : " + checkTotal).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter check total");
			extTestObj.get().createNode("Failed to enter check total")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
	}

	public void clickSubmit() {
		try {
			clickableWait(Elements.visitSubmitButton);
			log.info("Visit submitted");
			extTestObj.get().createNode("Visit submitted").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to submit visit");
			extTestObj.get().createNode("Failed to submit visit")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}
	}

	public String retrieveSuccessMessage() {

		String successMsg = "";
		try {
			explicitWait(Elements.getSuccessMessageforAddMyVisit);
			successMsg = driver.findElement(Elements.getSuccessMessageforAddMyVisit).getText();
			log.info("Success message for add my visit displayed as : " + successMsg);
			extTestObj.get().createNode("Success message for add my visit displayed as : " + successMsg).pass("PASSED");
		} catch (Exception e) {
			log.error("Success message for add my visit not displayed");
			extTestObj.get().createNode("Success message for add my visit not displayed")
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
			extTestObj.get().createNode("Restaurant location entered as " + restLocation).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter Restaurant location");
			extTestObj.get().createNode("Failed to enter Restaurant location")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}

	}

	public void clickSearchButtonForLoggedInOrder() throws InterruptedException {
		try {
			scrollUp("200");
			clickableWait(Elements.searchButton);
			log.info("Search button clicked");
			extTestObj.get().createNode("Search button clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Search button click failed");
			extTestObj.get().createNode("Search button click failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}
	}

	public void orderNow() {
		try {
			Thread.sleep(3000);
			scrollIntoViewHalf(Elements.orderNowButton);
			Thread.sleep(3000);
			clickElement(Elements.orderNowButton);
			log.info("Site scrolled and order button clicked");
			extTestObj.get().createNode("Site scrolled and order button clicked").pass("PASSED");

		} catch (Exception e) {
			log.error("Order Now button click failed");
			extTestObj.get().createNode("Order Now button click failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}

	}

	public void AddChilisFavouriteToCart() throws InterruptedException {

		String chilisFavItem = excel.getCellData("LoggedInOrder", "Chilis Favourite Items", 2).trim();
		try {
			List<AndroidElement> itemNames = driver
					.findElements(By.xpath("//div[@class='heading-tertiary heading-favorite']"));
			for (int i = 0; i < itemNames.size(); i++) {
				String name = itemNames.get(i).getText().trim();
				if (name.equalsIgnoreCase(chilisFavItem)) {
					AndroidElement ele = driver.findElements(By.xpath("//div[@class='favorite-action']/button")).get(i);
					scrollIntoViewBottomByElement(ele);
					ele.click();
					break;
				}
			}
			log.info("Chilis favourite Item " + chilisFavItem + " selected");
			extTestObj.get().createNode("Chilis favourite Item " + chilisFavItem + " selected").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select chilis favourite item");
			extTestObj.get().createNode("Failed to select chilis favourite item")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}
	}

	public void clickViewCart() throws InterruptedException {
		try {
			clickableWait(Elements.cartIcon);
			clickableWait(Elements.viewCartButton);
			log.info("View Cart clicked");
			extTestObj.get().createNode("View Cart clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to click View Cart");
			extTestObj.get().createNode("Failed to click View Cart")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}
	}

	public void noClickViewCart() throws InterruptedException {
		try {
			clickableWait(Elements.viewCartButton);
			log.info("View Cart clicked");
			extTestObj.get().createNode("View Cart clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to click View Cart");
			extTestObj.get().createNode("Failed to click View Cart")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}
	}

	public void selectSilverWare() throws InterruptedException {

		try {
			Thread.sleep(3000);
			scrollDownFromStart("450");
			explicitWait(Elements.silverWareCheckBox);
			clickableWait(Elements.optSilverWareCheckBox);
			log.info("Silver ware opted in");
			extTestObj.get().createNode("Silver ware opted in").pass("PASSED");
		} catch (Exception e) {
			log.error("Silver ware opt in failed");
			extTestObj.get().createNode("Silver ware opt in failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();

		}

	}

	public void clickCheckOut() {
		try {
			scrollIntoViewBottom(Elements.checkOutButton);
			clickableWait(Elements.checkOutButton);
			log.info("Order checked Out");
			extTestObj.get().createNode("Order checked Out").pass("PASSED");
		} catch (Exception e) {
			log.error("Order check out failed");
			extTestObj.get().createNode("Order check out failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}
	}

	public void continueToPayment() {
		try {
			explicitWait(Elements.orderTotal);
			clickableWait(Elements.paymentButton);
			log.info("Payment button clicked");
			extTestObj.get().createNode("Payment button clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to click Payment button");
			extTestObj.get().createNode("Failed to click Payment button")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}
	}

	public void enterCardNo() throws InterruptedException { // Ayushman

		String cardNo = excel.getCellData("LoggedInOrder", "Card Number", 2);
		try {
			explicitWait(Elements.cardNo);
			clickableWait(Elements.cardNo);
			driver.getKeyboard().sendKeys(cardNo);
			log.info("Card Number entered as : " + cardNo);
			extTestObj.get().createNode("Card Number entered as : " + cardNo).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter Card Number");
			extTestObj.get().createNode("Failed to enter Card Number")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}
	}

	public void enterCVV() {
		String cvv = excel.getCellData("LoggedInOrder", "CVV", 2);
		try {
			sendKeysWait(Elements.cvvTextBox, cvv);
			log.info("CVV entered as : " + cvv);
			extTestObj.get().createNode("CVV entered as : " + cvv).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter CVV");
			extTestObj.get().createNode("Failed to enter CVV")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}

	}

	public void selectExpirationMonth() 
	{
		String month = excel.getCellData("LoggedInOrder", "Expiration Month", 2);
		try {
			scrollIntoViewBottom(Elements.expirationMonth);
//			clickableWait(Elements.expirationMonth);
			scrollIntoViewBottom(By.xpath("//*[contains(text(),'(0" + month + ")')]"));
			clickElement(By.xpath("//*[contains(text(),'(0" + month + ")')]"));
			log.info("Expiration Month selected as : " + month);
			extTestObj.get().createNode("Expiration Month selected as : " + month).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select Expiration Month");
			extTestObj.get().createNode("Failed to select Expiration Month")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}
	}

	public void selectExpirationYear() // Ayushman
	{
		String year = excel.getCellData("LoggedInOrder", "Expiration Year", 2);
		try {
			scrollIntoViewBottom(Elements.expirationYear);
//			clickableWait(Elements.expirationYear);
			scrollIntoViewBottom(By.xpath("//*[text()='"+year+"']"));
			clickElement(By.xpath("//*[text()='"+year+"']"));
			log.info("Expiration Year selected as : " + year);
			extTestObj.get().createNode("Expiration Year selected as : " + year).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select Expiration Year");
			extTestObj.get().createNode("Failed to select Expiration Year")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}
	}

	public void enterNameOnCard() throws InterruptedException { // Ayushman

		String nameOnCard = excel.getCellData("LoggedInOrder", "Name On Card", 2);
		try {
			scrollIntoViewBottom(Elements.nameOnCard);
			explicitWait(Elements.nameOnCard);
			clickableWait(Elements.nameOnCard);
			driver.getKeyboard().sendKeys(nameOnCard);
			log.info("Name on Card entered as : " + nameOnCard);
			extTestObj.get().createNode("Name on Card entered as : " + nameOnCard);
		} catch (Exception e) {
			log.error("Failed to enter Name on Card");
			extTestObj.get().createNode( "Failed to enter Name on Card").fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);;
			log.error(e.getMessage());

			tearDown();
		}
	}

	public void enterBillingZipCode() throws InterruptedException { // Ayushman

		String zipCode = excel.getCellData("LoggedInOrder", "Zip Code", 2);
		try {
			explicitWait(Elements.billingZip);
			clickableWait(Elements.billingZip);
			driver.getKeyboard().sendKeys(zipCode);
			log.info("Billing zip code entered as : " + zipCode);
			extTestObj.get().createNode("Billing zip code entered as : " + zipCode).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter Billing zip code");
			extTestObj.get().createNode("Failed to enter Billing zip code")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();
		}
	}

	public void giveTip() {
		String tip = excel.getCellData("LoggedInOrder", "Tip", 2);
		try {
			scrollIntoViewBottom(Elements.tipTextBox);
			sendKeysWait(Elements.tipTextBox, tip);
			log.info("Tip given as : " + tip);
			extTestObj.get().createNode("Tip has entered").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter tip");
			extTestObj.get().createNode("Failed to enter tip")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

		}
	}

	public String OrderTotal() {
		try {
			explicitWait(Elements.pickUpCost);
			log.info("Pick up cost displayed as : " + driver.findElement(Elements.pickUpCost).getText());
			extTestObj.get()
					.createNode("Order Total  cost displayed as : " + driver.findElement(Elements.pickUpCost).getText())
					.pass("PASSED");
		} catch (Exception e) {
			log.error("Pick up cost not displayed");
			extTestObj.get().createNode("Order Total cost not displayed")
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
			extTestObj.get().createNode("Donation checked").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to check donation check box");
			extTestObj.get().createNode("Failed to check donation check box")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
		}
	}

	public void placeOrder() {
		try {
			scrollDownFromStart("50");
			clickableWait(Elements.placeOrder);
			log.info("Place order button clicked");
			extTestObj.get().createNode("Place order button clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to click place order button");
			extTestObj.get().createNode("Failed to click place order button")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();

		}
	}

	public String getSuccessMessageforLoggedInOrder() {
		try {
			scrollDownFromStart("50");
			explicitWait(Elements.successMessageforLoggedInOrder);
			log.info("Success message displayed as : "
					+ driver.findElement(Elements.successMessageforLoggedInOrder).getText());

			extTestObj.get().createNode("Success message displayed as : "
					+ driver.findElement(Elements.successMessageforLoggedInOrder).getText()).pass("PASSED");
		} catch (Exception e) {
			log.error("Success message not displayed");
			extTestObj.get().createNode("Success message not displayed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();
		}
		return driver.findElement(Elements.successMessageforLoggedInOrder).getText();

	}

	public String returnOrderPrice() {
		try {
			explicitWait(Elements.orderPrice);
			log.info("Order price displayed as  : " + driver.findElement(Elements.orderPrice).getText());
			extTestObj.get().createNode(
					"Order price after payment displayed as  : " + driver.findElement(Elements.orderPrice).getText())
					.pass("PASSED");
		} catch (Exception e) {
			log.error("Order price not displayed");
			extTestObj.get().createNode("Order price after payment not displayed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();
		}
		return driver.findElement(Elements.orderPrice).getText();

	}

	/* Validate user is able to place reorder. */
	public void selectReorderOption() {
		try {
			clickableWait(Elements.reorderOption);
			Thread.sleep(3000);
			log.info("Reorder option selected");
			extTestObj.get().createNode("Reorder option selected").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select reorder option");
			log.error(e.getMessage());
			extTestObj.get().createNode("Failed to select reorder option")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			tearDown();
		}
	}

	public void clickReorderforanOrder() {
		try {
			scrollDownFromStart("350");
			clickableWait(Elements.reOrder);
			log.info("Clicked reorder for a particular order");
			Thread.sleep(3000);
			extTestObj.get().createNode("Clicked reorder for a particular order").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to click reorder");
			log.error(e.getMessage());
			extTestObj.get().createNode("Failed to click reorder")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			tearDown();
		}
	}

	public void changeQuantity() {
		try {
			scrollDownFromStart("400");
			clickableWait(Elements.quantity);
			explicitWait(Elements.quantityOption);
			clickElement(Elements.quantityOption);
			log.info("Quantity changed");
			extTestObj.get().createNode("Quantity changed").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select and change quantity");
			log.error(e.getMessage());
			extTestObj.get().createNode("Failed to select and change quantity")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			tearDown();
		}
	}

	/* Validate user is able to place Delivery-ASAP order. For Guest user */

	public void clickOrderNow() { // Ayushman
		String restID = excel.getCellData("Common", "Restaurant ID", 2);
		try {
			Thread.sleep(2000);
			scrollIntoViewBottom(By.xpath("//a[@id='location-select-" + restID + "']"));
			Thread.sleep(2000);
			clickableWait(By.xpath("//a[@id='location-select-" + restID + "]"));
			log.info("Site scrolled and order button clicked");
			extTestObj.get().createNode("Site scrolled and order button clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Site scrolled but order button not clicked");
			log.error(e.getMessage());
			extTestObj.get().createNode("Order now button not clicked")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			tearDown();
		}
	}

	public void clickMenuCatagory() { // Ayushman
		String catagory = excel.getCellData("Common", "Menu Category", 2);
		try {
			Thread.sleep(3000);
			scrollIntoViewHalf(By.xpath("//*[contains(id()," + catagory + ")]"));
			Thread.sleep(3000);
			clickElement(By.xpath("//*[contains(id()," + catagory + ")]"));
			log.info("Site scrolled and category " + catagory + " selected");
			extTestObj.get().createNode("Site scrolled and category " + catagory + " selected").pass("PASSED");
		} catch (Exception e) {
			log.error("Site scrolled but selected catagory button not clicked");
			extTestObj.get().createNode("Selected catagory button not clicked")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();
		}
	}

	public void clickItemOrder() { // Ayushman
		String item = excel.getCellData("Common", "Item", 2);
		try {
			Thread.sleep(2000);
			scrollIntoViewHalf(By.xpath("//*[contains(text()," + item + ")]"));
			Thread.sleep(2000);
			clickElement(By.xpath("//*[contains(text()," + item + ")]"));
			log.info("Site scrolled and item" + item + "clicked");
			extTestObj.get().createNode("Site scrolled and item" + item + "clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Site scrolled but item not selected");
			extTestObj.get().createNode("Item not selected")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();
		}
	}

	public void clickAddItem() { // Ayushman
		try {
			Thread.sleep(2000);
			scrollIntoViewHalf(Elements.addThisItem);
			Thread.sleep(2000);
			clickElement(Elements.addThisItem);
			log.info("Item added to cart");
			extTestObj.get().createNode("Item added to cart").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to add item to cart");
			extTestObj.get().createNode("Failed to add item to cart")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();
		}
	}

	public void clickCheckOutforGuestUser() {
		try {
			scrollIntoViewBottom(Elements.checkOutButton);
			explicitWait(Elements.cartTotal);
			clickableWait(Elements.checkOutButton);
			log.info("Order checked Out");
			extTestObj.get().createNode("Order checked Out").pass("PASSED");
		} catch (Exception e) {
			log.error("Order check out failed");
			extTestObj.get().createNode("Order check out failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();
		}
	}

	public void selectDelivery() { // Ayushman
		try {
			Thread.sleep(2000);
			scrollIntoViewBottom(Elements.selectDeliveryMode);
			Thread.sleep(2000);
			clickableWait(Elements.selectDeliveryMode);
			log.info("Site scrolled and Delivery mode is selected");
			extTestObj.get().createNode("Site scrolled and Delivery mode is selected").pass("PASSED");
		} catch (Exception e) {
			log.error("Site scrolled but Delivery mode is not selected");
			extTestObj.get().createNode("Delivery mode is not selected")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();
		}
	}

	@SuppressWarnings("deprecation")
	public void enterDeliveryLocation() throws InterruptedException { // Ayushman
		String location = excel.getCellData("Delivery", "Restaurant Address", 2);
		try {
			explicitWait(Elements.deliveryAddress);
			clickableWait(Elements.deliveryAddress);
			driver.getKeyboard().sendKeys(location);
			driver.pressKeyCode(AndroidKeyCode.ENTER);
			Thread.sleep(3000);
			log.info("Delivery location entered as " + location);
			extTestObj.get().createNode("Delivery location entered as " + location).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter Delivery location");
			log.error(e.getMessage());
			extTestObj.get().createNode("Failed to enter Delivery location")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			tearDown();
		}
	}

	public void enterApartmentNo() throws InterruptedException { // Ayushman
		String aptNo = excel.getCellData("Delivery", "Apt. no", 2);
		try {
			scrollIntoViewHalf(Elements.aptNo);
			explicitWait(Elements.aptNo);
			clickableWait(Elements.aptNo);
			driver.getKeyboard().sendKeys(aptNo);
			log.info("Apartment no. entered as : " + aptNo);
			extTestObj.get().createNode("Apartment no. entered as : " + aptNo).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter Apartment no.");
			extTestObj.get().createNode("Failed to enter Apartment no.")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();
		}
	}

	public void selectDeliveryDateASAP() {
		try {
			clickableWait(Elements.delDate);
			clickElement(Elements.dateInputASAP);
			log.info("Delivery Option selected as ASAP");
			extTestObj.get().createNode("Delivery Option selected as ASAP").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select Delivery Option as ASAP");
			extTestObj.get().createNode("Failed to select Delivery Option as ASAP")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();

		}

	}

	public void selectDeliveryDate() // Ayushman
	{
		String dateInput = excel.getCellData("Delivery", "Delivery Date", 2);
		try {
			scrollIntoViewHalf(Elements.delDate);
			clickableWait(Elements.delDate);
			Thread.sleep(2000);
			scrollIntoViewHalf(By.xpath("//*[contains(text(), '" + dateInput + "')]"));
			clickableWait(By.xpath("//*[contains(text(), '" + dateInput + "')]"));
			Thread.sleep(2000);
			log.info("Delivery date selected as " + dateInput);
			extTestObj.get().createNode("Delivery date selected as " + dateInput).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select Delivery date");
			log.error(e.getMessage());
			extTestObj.get().createNode("Failed to select Delivery date")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			tearDown();
		}
	}

	public void enterFirstName() throws InterruptedException { // Ayushman

		String firstName = excel.getCellData("DeliveryASAP", "First Name", 2);
		try {
			scrollIntoViewHalf(Elements.firstName);
			explicitWait(Elements.firstName);
			clickableWait(Elements.firstName);
			driver.getKeyboard().sendKeys(firstName);
			log.info("First name entered as : " + firstName);
			extTestObj.get().createNode("First name entered as : " + firstName).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter First name");
			extTestObj.get().createNode("Failed to enter First name")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();
		}
	}

	public void enterLastName() throws InterruptedException { // Ayushman
		String LastName = excel.getCellData("DeliveryASAP", "Last Name", 2);
		try {
			scrollIntoViewHalf(Elements.lastName);
			explicitWait(Elements.lastName);
			clickableWait(Elements.lastName);
			driver.getKeyboard().sendKeys(LastName);
			log.info("Last name entered as : " + LastName);
			extTestObj.get().createNode("Last name entered as : " + LastName).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter Last name");
			extTestObj.get().createNode("Failed to enter Last name")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();
		}
	}

	public void enterContactNumber() throws InterruptedException { // Ayushman

		String contactNum = excel.getCellData("DeliveryASAP", "Contact Number", 2);
		try {
			scrollIntoViewHalf(Elements.contantNumber);
			explicitWait(Elements.contantNumber);
			clickableWait(Elements.contantNumber);
			driver.getKeyboard().sendKeys(contactNum);
			log.info("Contact Number entered as : " + contactNum);
			extTestObj.get().createNode("Contact Number entered as : " + contactNum).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter Contact number");
			extTestObj.get().createNode("Failed to enter Contact number")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();
		}
	}

	public void enterEMail() throws InterruptedException { // Ayushman
		String email = excel.getCellData("DeliveryASAP", "Email", 2);
		try {
			scrollIntoViewHalf(Elements.eMail);
			explicitWait(Elements.eMail);
			clickableWait(Elements.eMail);
			driver.getKeyboard().sendKeys(email);
			log.info("Email address entered as : " + email);
			extTestObj.get().createNode("Email address entered as : " + email).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter Email id");
			extTestObj.get().createNode("Failed to enter Email id")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());

			tearDown();
		}
	}

	/* Validate user is able to place Delivery-Later today order. For Guest user */
	public void selectDeliveryTime() // Ayushman
	{
		String timeInput = excel.getCellData("Delivery", "Delivery Time", 2);
		try {

			scrollIntoViewHalf(Elements.delTime);
			clickableWait(Elements.delTime);
			Thread.sleep(2000);
			scrollIntoViewHalf(By.xpath("//*[contains(text(), '" + timeInput + "')]"));
			clickableWait(By.xpath("//*[contains(text(), '" + timeInput + "')]"));
			Thread.sleep(2000);
			log.info("Delivery time selected as " + timeInput);
			extTestObj.get().createNode("Delivery time selected as " + timeInput).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select Delivery time");
			log.error(e.getMessage());
			extTestObj.get().createNode("Failed to select Delivery time")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			tearDown();
		}
	}

	/* Validate user is able to place Delivery-ASAP order. For MCA user */
	public void clickDeliveryInstrBox() {
		String deliveryInstruction = excel.getCellData("DeliveryASAP", "Instruction", 2);
		try {

			clickableWait(Elements.deliveryInstrTextbox);
			driver.getKeyboard().sendKeys(deliveryInstruction);
			log.info("Delivery Instruction given as " + deliveryInstruction);
			extTestObj.get().createNode("Delivery Instruction given as " + deliveryInstruction).pass("PASSED");

		} catch (Exception e) {
			log.error("Failed to provide Delivery Instructions");
			extTestObj.get().createNode("Failed to provide Delivery Instructions")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();
		}
	}

	/* Validate if user is able to place a curbside order. */
	public void selectCurbside() { // Ayushman
		try {
			Thread.sleep(2000);
			scrollIntoViewBottom(Elements.selectCurbsideMode);
			Thread.sleep(2000);
			clickableWait(Elements.selectCurbsideMode);
			log.info("Site scrolled and Curbside mode is selected");
			extTestObj.get().createNode("Site scrolled and Curbside mode is selected").pass("PASSED");

		} catch (Exception e) {
			log.error("Site scrolled but Curbside mode is not selected");
			extTestObj.get().createNode("Curbside mode is not selected")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();
		}
	}

	public void selectPickupDate() // Ayushman
	{
		String dateInput = excel.getCellData("Pickup", "Pickup Date", 2);
		try {
			scrollIntoViewHalf(Elements.pickDate);
			clickableWait(Elements.pickDate);
			Thread.sleep(2000);
			scrollIntoViewHalf(By.xpath("//*[contains(text(), '" + dateInput + "')]"));
			clickableWait(By.xpath("//*[contains(text(), '" + dateInput + "')]"));
			Thread.sleep(2000);
			log.info("Pickup date selected as " + dateInput);
			extTestObj.get().createNode("Pickup date selected as " + dateInput).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select Pickup date");
			log.error(e.getMessage());
			extTestObj.get().createNode("Failed to select Pickup date")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			tearDown();
		}
	}

	public void selectPickupTime() 
	{
		String timeInput = excel.getCellData("CarryOut", "Pickup Time", 2);
		try {
			scrollIntoViewBottom(Elements.pickTime);
			scrollIntoViewBottom(By.xpath("//select[@id='pickup-time']/option[text()='" + timeInput + "']"));
			clickElement(By.xpath("//select[@id='pickup-time']/option[text()='" + timeInput + "']"));
			log.info("Pickup time selected as " + timeInput);
			extTestObj.get().createNode("Pickup time selected as " + timeInput).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select Pickup time");
			extTestObj.get().createNode("Failed to select Pickup time")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();
		}
	}

	public void enterVehicleMake() throws InterruptedException { // Ayushman

		String vehicle = excel.getCellData("GuestUserCurbSide", "Vehicle Make", 2);
		try {
			scrollIntoViewHalf(Elements.vehicleMake);
			explicitWait(Elements.vehicleMake);
			clickableWait(Elements.vehicleMake);
			driver.getKeyboard().sendKeys(vehicle);
			log.info("Vehicle make entered as " + vehicle);
			extTestObj.get().createNode("Vehicle make entered as " + vehicle).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter Vehicle make");
			extTestObj.get().createNode("Failed to enter Vehicle make")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();
		}
	}

	public void enterVehicleModel() throws InterruptedException { // Ayushman

		String vehicleModel = excel.getCellData("GuestUserCurbSide", "Vehicle Model", 2);
		try {
			scrollIntoViewHalf(Elements.vehicleModel);
			explicitWait(Elements.vehicleModel);
			clickableWait(Elements.vehicleMake);
			driver.getKeyboard().sendKeys(vehicleModel);
			log.info("Vehicle model entered as : " + vehicleModel);
			extTestObj.get().createNode("Vehicle model entered as : " + vehicleModel).pass("PASSED");

		} catch (Exception e) {
			log.error("Failed to enter Vehicle model");
			extTestObj.get().createNode("Failed to enter Vehicle model")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();
		}
	}

	public void enterVehicleColor() throws InterruptedException { // Ayushman

		String vehicleColor = excel.getCellData("GuestUserCurbSide", "Vehicle Color", 2);
		try {
			scrollIntoViewHalf(Elements.vehicleColor);
			explicitWait(Elements.vehicleColor);
			clickableWait(Elements.vehicleColor);
			driver.getKeyboard().sendKeys(vehicleColor);
			log.info("Vehicle Color entered as " + vehicleColor);
			extTestObj.get().createNode("Vehicle Color entered as " + vehicleColor).pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to enter Vehicle Color");
			extTestObj.get().createNode("Failed to enter Vehicle Color")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();
		}
	}

	/* Validate user is able to place Delivery-Later today order. For MCA user */

	public void selectDeliveryLaterToday() {
		try {
			clickableWait(Elements.delDate);
			Thread.sleep(3000);
			clickElement(Elements.LaterToday);
			log.info("Later Today delivery time is selected");
			extTestObj.get().createNode("Later Today delivery time is selecte").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select delivery time Later Today");
			extTestObj.get().createNode("Failed to select delivery time Later Today")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();
		}
	}

	/* Validate user is able to place Curbside-ASAP order for MCA user */
	public void selectPickupAsap() {
		try {
			scrollIntoViewBottom(Elements.pickDate);
			scrollIntoViewBottom(Elements.pickupAsapOrder);
			clickElement(Elements.pickupAsapOrder);
			log.info("'ASAP' pickup time is selected");
			extTestObj.get().createNode("'ASAP' pickup time is selected").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select pickup time 'ASAP'");
			extTestObj.get().createNode("Failed to select pickup time 'ASAP'")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();
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
			extTestObj.get().createNode("Carryout Button is Clicked").pass("PASSED");
		} catch (Exception e) {
			log.error("Carryout Button click failed");
			extTestObj.get().createNode("Carryout Button click failed")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();
		}

	}

	/*
	 * 
	 * String futureTime = excel.getCellData(sheetName, colName, rowNum); try {
	 * clickableWait(Elements.pickDate); Thread.sleep(3000);
	 * clickElement(By.xpath("//*[text()='"+futureTime+"']"));
	 * log.info("Pickup future time is selected as "+futureTime);
	 * extTestObj.get().createNode("Pickup future time is selected as "+
	 * futureTime).pass("PASSED"); } catch (Exception e) {
	 * log.error("Failed to select pick up future time");
	 * extTestObj.get().createNode("Failed to select pick up future time").
	 * fail("Method Name : "+Thread.currentThread().getStackTrace()[1].getMethodName
	 * ()+"()").error(e); log.error(e.getMessage()); tearDown(); } }
	 * 
	 */

	public void selectPickupLaterToday() {
		try {
			scrollIntoViewBottom(Elements.pickDate);
			scrollIntoViewBottom(Elements.LaterToday);
			clickElement(Elements.LaterToday);
			log.info("Later Today pickup time is selected");
			extTestObj.get().createNode("Later Today pickup time is selected").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to select Later Today");
			extTestObj.get().createNode("Failed to select Later Today")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();
		}
	}

	/* Validate user is able to place order with Rewards. For sign in user */
	public void addReward() {
		try {
			clickableWait(Elements.addRewards);
			log.info("Reward added");
			extTestObj.get().createNode("Reward added").pass("PASSED");
		} catch (Exception e) {
			log.error("Failed to add reward");
			extTestObj.get().createNode("Failed to add reward")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			log.error(e.getMessage());
			tearDown();
		}
	}

	/*
	 * public void addRewardItem()
	 * 
	 * { String rewarditem = excel.getCellData(sheetName, colName, rowNum) try {
	 * clickableWait(By.xpath("//*[contains(text(),'"+rewarditem+"')]"));
	 * clickableWait(Elements.addThisItem);
	 * log.info("Reward item "+rewarditem+" added");
	 * extTestObj.get().createNode("Reward item "+ rewarditem
	 * +" added").pass("PASSED"); } catch(Exception e) {
	 * log.error("Failed to add reward item");
	 * extTestObj.get().createNode("Failed to add reward item").
	 * fail("Method Name : "+Thread.currentThread().getStackTrace()[1].getMethodName
	 * ()+"()").error(e); log.error(e.getMessage()); tearDown(); } }
	 */

	/*
	 * public void isRewardApplied() {
	 * 
	 * try { driver.findElement(Elements.discount).isDisplayed();
	 * log.info("Discount is applied");
	 * extTestObj.get().createNode("Discount is applied").pass("PASSED"); } catch
	 * (Exception e) { log.error("Failed to apply discount");
	 * extTestObj.get().createNode("Failed to apply discount").fail("Method Name : "
	 * +Thread.currentThread().getStackTrace()[1].getMethodName()+"()").error(e);
	 * log.error(e.getMessage()); tearDown(); }
	 * 
	 * 
	 * }
	 */

	/*
	 * Validate user is able to place Carry Out-Future order -item customization.
	 * For Guest user
	 */

	/*
	 * public void customizeOrder() { String custItem = excel.getCellData(sheetName,
	 * colName, rowNum); try { clickableWait(Elements.customizeOrderButton);
	 * clickableWait(Elements.sauceDropDown);
	 * clickElement(By.xpath("//*[text()='"+custItem+"']"));
	 * clickableWait(Elements.addExtraSauce);
	 * log.info("Order customized with "+custItem);
	 * extTestObj.get().createNode("Order customized with "+custItem).pass("PASSED")
	 * ; } catch(Exception e) { log.error("Failed to customize order");
	 * extTestObj.get().createNode("Failed to customize order").
	 * fail("Method Name : "+Thread.currentThread().getStackTrace()[1].getMethodName
	 * ()+"()").error(e); log.error(e.getMessage()); tearDown(); } }
	 */

	/*
	 * public void validateCustomization() { String custItem =
	 * excel.getCellData(sheetName, colName, rowNum); try {
	 * explicitWait(Elements.customItem); String custInfo =
	 * driver.findElement(Elements.customItem).getText();
	 * if(custInfo.contains(custItem)&&(custInfo.contains("Extra")));
	 * log.info("The item added for customization is "+custInfo);
	 * extTestObj.get().createNode("The item added for customization is "+
	 * custInfo).pass("PASSED"); } catch(Exception e) {
	 * log.error("Incorrect Customization Info");
	 * extTestObj.get().createNode("Incorrect Customization Info").
	 * fail("Method Name : "+Thread.currentThread().getStackTrace()[1].getMethodName
	 * ()+"()").error(e); log.error(e.getMessage()); tearDown();
	 * 
	 * } }
	 */

	/*
	 * Dine In : Place Dine-In order as MCA user (Join the Line thru Join Line
	 * option)
	 */

	public void initiateJoinLine() { // Ayushman
		String restID = excel.getCellData("Common", "Restaurant ID", 2);
		try {
			Thread.sleep(2000);
			scrollIntoViewHalf(By.xpath("//a[@id='location-join-line-" + restID + "']"));
			Thread.sleep(2000);
			clickableWait(By.xpath("//a[@id='location-join-line-" + restID + "']"));
			log.info("Site scrolled and join line button clicked");
			extTestObj.get().createNode("Site scrolled and join line button clicked").pass("PASSED");

		} catch (Exception e) {
			log.error("Site scrolled but join line button not clicked");
			log.error(e.getMessage());
			extTestObj.get().createNode("Join line button not clicked")
					.fail("Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName() + "()").error(e);
			tearDown();
		}
	}
	/*
	 * public void clickMinimumPartySize() { //Ayushman try { Thread.sleep(2000);
	 * scrollIntoViewHalf(Elements.partySizeOne); Thread.sleep(2000);
	 * clickableWait(Elements.partySizeOne);
	 * log.info("Site scrolled and party size selected(1)");
	 * extTestObj.get().createNode("Site scrolled and party size selected(1)").pass(
	 * "PASSED");
	 * 
	 * } catch (Exception e) {
	 * log.error("Site scrolled but party size not selected");
	 * log.error(e.getMessage());
	 * extTestObj.get().createNode("Party size not selected").fail("Method Name : "
	 * +Thread.currentThread().getStackTrace()[1].getMethodName()+"()").error(e);
	 * tearDown(); }
	 * 
	 * }
	 */

	/*
	 * public void enterNameJoinLine() throws InterruptedException { //Ayushman
	 * String nameJoinLine = excel.getCellData(sheetName, colName, rowNum); try {
	 * scrollIntoViewHalf(Elements.joinLineName);
	 * explicitWait(Elements.joinLineName); clickableWait(Elements.joinLineName);
	 * driver.getKeyboard().sendKeys(nameJoinLine);
	 * log.info("Name on join line entered");
	 * extTestObj.get().createNode("Name on join line entered").pass("PASSED");
	 * 
	 * } catch (Exception e) { log.error("Failed to enter Name on join line");
	 * log.error(e.getMessage());
	 * extTestObj.get().createNode("Failed to enter Name on join line").
	 * fail("Method Name : "+Thread.currentThread().getStackTrace()[1].getMethodName
	 * ()+"()").error(e); tearDown(); } }
	 */

	/*
	 * public void enterContactNumberJoinLine() throws InterruptedException {
	 * //Ayushman String cNumber = excel.getCellData(sheetName, colName, rowNum);
	 * try { scrollIntoViewHalf(Elements.joinLineContactNumber);
	 * explicitWait(Elements.joinLineContactNumber);
	 * clickableWait(Elements.joinLineContactNumber);
	 * driver.getKeyboard().sendKeys(cNumber); log.info("Mobile number entered");
	 * extTestObj.get().createNode("Mobile no on join line entered").pass("PASSED");
	 * } catch (Exception e) { log.error("Failed to enter Mobile number");
	 * log.error(e.getMessage());
	 * extTestObj.get().createNode("Failed to enter Mobile number").
	 * fail("Method Name : "+Thread.currentThread().getStackTrace()[1].getMethodName
	 * ()+"()").error(e); tearDown(); } }
	 */

	/*
	 * public void clickJoinLine() { //Ayushman try { Thread.sleep(2000);
	 * scrollIntoViewHalf(Elements.clickJoinLine); Thread.sleep(2000);
	 * clickableWait(Elements.clickJoinLine);
	 * log.info("Site scrolled and joined the line");
	 * extTestObj.get().createNode("Site scrolled and joined the line").pass(
	 * "PASSED"); } catch (Exception e) {
	 * log.error("Site scrolled but not joined line"); log.error(e.getMessage());
	 * extTestObj.get().createNode("Joined line is failed").fail("Method Name : "
	 * +Thread.currentThread().getStackTrace()[1].getMethodName()+"()").error(e);
	 * tearDown(); }
	 * 
	 * }
	 */

}
