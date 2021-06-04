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

public class FunctionalComponentsrtrt extends Base {

	public AndroidDriver<AndroidElement> driver = null;
	Properties property = returnProperty();
	ExtentReports extent = ExtentReporterNG.getReportObject();
	Listeners listen;
	static ThreadLocal<ExtentTest> extTestObj;
	public WebDriverWait wait;
	Logger log;
	ExcelUtils excel;

	public FunctionalComponentsrtrt(AndroidDriver<AndroidElement> driver, Logger log) {
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
			extTestObj.get().createNode("QA site launch failed").fail("FAILED");
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
			extTestObj.get().createNode("Pop up close failed").fail("FAILED");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().createNode("Login option not selected").fail("FAILED");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().createNode("Could not enter user name").fail("FAILED");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().createNode("Could not enter password").fail("FAILED");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().createNode("Sign in button click failed").fail("FAILED");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().createNode("Logout button click failed").fail("FAILED");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().createNode("Login header not displayed").fail("FAILED");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());

		}
		return driver.findElement(Elements.loginHeader).getText();
	}

	/* Validate Location search fields and links for guest user */
	public void selectLocationsOption() {
		try {
			clickableWait(Elements.locationsButton);
			log.info("Location option selected");
			extTestObj.get().log(Status.PASS, "Location option selected");
		} catch (Exception e) {
			log.error("Location button selection failed");
			extTestObj.get().log(Status.FAIL, "Location button selection failed");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "Restaurant location " + locn + " entered");
		} catch (Exception e) {
			log.error("Failed to enter Restaurant location");
			extTestObj.get().log(Status.FAIL, "Failed to enter Restaurant location");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
		}
	}

	public void clickSearchButton() {
		try {
			clickableWait(Elements.searchButton);
			log.info("Search button clicked");
			extTestObj.get().log(Status.PASS, "Search button clicked");
		} catch (Exception e) {
			log.error("Search button click failed");
			extTestObj.get().log(Status.FAIL, "Search button click failed");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
		}
	}

	public String getRestaurantName() {
		try {
			explicitWait(Elements.restaurantName);
			log.info("Restaurant name " + driver.findElement(Elements.restaurantName).getText() + " is displayed");
			extTestObj.get().log(Status.PASS,
					"Restaurant name " + driver.findElement(Elements.restaurantName).getText() + " is displayed");
		} catch (Exception e) {
			log.error("Restaurant Name not displayed");
			extTestObj.get().log(Status.FAIL, "Restaurant Name not displayed");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "Actual rewards count obtained : " + count);
		} catch (Exception e) {
			log.error("Actual rewards count couldn't be obtained");
			extTestObj.get().log(Status.FAIL, "Actual rewards count obtained");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "Reward Titles displayed");
		} catch (Exception e) {
			log.error("Reward titles couldn't be obtained");
			extTestObj.get().log(Status.FAIL, "Reward titles couldn't be obtained");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
		}
	}

	/* Validate user is able to update my Account. */
	public void selectMyAccountOption() {

		try {
			clickableWait(Elements.myAccountOption);
			log.info("My Account option selected");
			extTestObj.get().log(Status.PASS, "My Account option selected");
		} catch (Exception e) {
			log.error("My Account option selection failed");
			extTestObj.get().log(Status.FAIL, "My Account option selection failed");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
		}

	}

	public void getFirstNameBeforeUpdate() {
		String initialFirstName = "";
		try {
			explicitWait(Elements.firstNameTextBox);
			initialFirstName = driver.findElement(Elements.firstNameTextBox).getAttribute("value");
			log.info("First Name before update obtained as : " + initialFirstName);
			extTestObj.get().log(Status.PASS, "First Name before update obtained as : " + initialFirstName);
		} catch (Exception e) {
			log.error("First Name before update not obtained");
			extTestObj.get().log(Status.FAIL, "First Name before update not obtained");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
		}

	}

	public void getLastNameBeforeUpdate() {
		String initialLastName = "";
		try {
			explicitWait(Elements.lastNameTextBox);
			initialLastName = driver.findElement(Elements.lastNameTextBox).getAttribute("value");
			log.info("Last Name before update obtained as : " + initialLastName);
			extTestObj.get().log(Status.PASS, "Last Name before update obtained as : " + initialLastName);
		} catch (Exception e) {
			log.error("Last Name before update not obtained");
			extTestObj.get().log(Status.FAIL, "Last Name before update not obtained");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
		}
	}

	public void getEmailBeforeUpdate() {
		String initialEmail = "";
		try {
			explicitWait(Elements.emailTextBox);
			initialEmail = driver.findElement(Elements.emailTextBox).getAttribute("value");
			log.info("Email before update obtained as : " + initialEmail);
			extTestObj.get().log(Status.PASS, "Email before update obtained as : " + initialEmail);
		} catch (Exception e) {
			log.error("Email before update not obtained");
			extTestObj.get().log(Status.FAIL, "Email before update not obtained");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
		}
	}

	public void getZipCodeBeforeUpdate() {
		String initialZipCode = "";
		try {
			explicitWait(Elements.zipCodeTextBox);
			initialZipCode = driver.findElement(Elements.zipCodeTextBox).getAttribute("value");
			log.info("Zip Code before update obtained as : " + initialZipCode);
			extTestObj.get().log(Status.PASS, "Zip Code before update obtained as : " + initialZipCode);
		} catch (Exception e) {
			log.error("Zip Code before update not obtained");
			extTestObj.get().log(Status.FAIL, "Zip Code before update not obtained");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
		}
	}

	public void updateEmail() {
		try {
			String email = excel.getCellData("UpdateMyAccount", "Email", 2);
			sendKeysWait(Elements.emailTextBox, email);
			log.info("Email updated with : " + email);
			extTestObj.get().log(Status.PASS, "Email updated with : " + email);

		} catch (Exception e) {
			log.error("Email updation failed");
			extTestObj.get().log(Status.FAIL, "Email updation failed");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
		}

	}

	public void updateFirstName() {
		try {
			String firstName = excel.getCellData("UpdateMyAccount", "First Name", 2);
			sendKeysWait(Elements.firstNameTextBox, firstName);
			log.info("First Name updated with : " + firstName);
			extTestObj.get().log(Status.PASS, "First Name updated with : " + firstName);
		} catch (Exception e) {
			log.error("First Name updation failed");
			extTestObj.get().log(Status.FAIL, "First Name updation failed");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());

		}

	}

	public void updateLastName() {
		try {
			String lastName = excel.getCellData("UpdateMyAccount", "Last Name", 2);
			sendKeysWait(Elements.lastNameTextBox, lastName);
			log.info("Last Name updated with : " + lastName);
			extTestObj.get().log(Status.PASS, "Last Name updated with : " + lastName);
		} catch (Exception e) {
			log.error("Last Name updation failed");
			extTestObj.get().log(Status.FAIL, "Last Name updation failed");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());

		}

	}

	public void updateZipCode() {
		try {
			String zipCode = excel.getCellData("UpdateMyAccount", "Zip Code", 2);
			sendKeysWait(Elements.zipCodeTextBox, zipCode);
			log.info("Zip Code updated with : " + zipCode);
			extTestObj.get().log(Status.PASS, "Zip Code updated with : " + zipCode);
		} catch (Exception e) {
			log.error("Zip Code updation failed");
			extTestObj.get().log(Status.FAIL, "Zip Code updation failed");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());

		}
	}

	public void checkConsent() {
		try {
			clickableWait(Elements.consentCheckBox);
			log.info("Consent checked");
			extTestObj.get().log(Status.PASS, "Consent checked");
		} catch (Exception e) {
			log.error("Consent check failed");
			extTestObj.get().log(Status.FAIL, "Consent check failed");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
		}
	}

	public void clickUpdateButton() {
		try {
			clickableWait(Elements.updateButton);
			log.info("Update button clicked");
			extTestObj.get().log(Status.PASS, "Update button clicked");
		} catch (Exception e) {
			log.error("Update button click failed");
			extTestObj.get().log(Status.FAIL, "Update button click failed");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
		}
	}

	public void getSuccessMessage() {
		String successMessage = "";
		try {
			explicitWait(Elements.successMessageforUpdate);
			successMessage = driver.findElement(Elements.successMessageforUpdate).getText();
			log.info("Success message obtained as : " + successMessage);
			extTestObj.get().log(Status.PASS, "Success message obtained as : " + successMessage);
		} catch (Exception e) {
			log.error("Success message not obtained");
			extTestObj.get().log(Status.FAIL, "Success message not obtained");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
		}

	}

	public String getFirstNameAfterUpdate() {
		String finalFirstName = "";
		try {
			explicitWait(Elements.firstNameTextBox);
			finalFirstName = driver.findElement(Elements.firstNameTextBox).getAttribute("value");
			log.info("First Name after update obtained as : " + finalFirstName);
			extTestObj.get().log(Status.PASS, "First Name after update obtained as : " + finalFirstName);
		} catch (Exception e) {
			log.error("First Name after update not obtained");
			extTestObj.get().log(Status.FAIL, "First Name after update not obtained");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
		}
		return finalFirstName;
	}

	public String getLastNameAfterUpdate() {
		String finalLastName = "";
		try {
			explicitWait(Elements.lastNameTextBox);
			finalLastName = driver.findElement(Elements.lastNameTextBox).getAttribute("value");
			log.info("Last Name after update obtained as : " + finalLastName);
			extTestObj.get().log(Status.PASS, "Last Name after update obtained as : " + finalLastName);
		} catch (Exception e) {
			log.error("Last Name after update not obtained");
			extTestObj.get().log(Status.FAIL, "Last Name after update not obtained");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
		}
		return finalLastName;
	}

	public String getEmailAfterUpdate() {
		String finalEmail = "";
		try {
			explicitWait(Elements.emailTextBox);
			finalEmail = driver.findElement(Elements.emailTextBox).getAttribute("value");
			log.info("Email after update obtained as : " + finalEmail);
			extTestObj.get().log(Status.PASS, "Email after update obtained as : " + finalEmail);
		} catch (Exception e) {
			log.error("Email after update not obtained");
			extTestObj.get().log(Status.FAIL, "Email after update not obtained");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
		}
		return finalEmail;
	}

	public String getZipCodeAfterUpdate() {
		String finalZipCode = "";
		try {
			explicitWait(Elements.zipCodeTextBox);
			finalZipCode = driver.findElement(Elements.zipCodeTextBox).getAttribute("value");
			log.info("Zip Code after update obtained as : " + finalZipCode);
			extTestObj.get().log(Status.PASS, "Zip Code after update obtained as : " + finalZipCode);
		} catch (Exception e) {
			log.error("Zip Code after update not obtained");
			extTestObj.get().log(Status.FAIL, "Zip Code after update not obtained");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "All fields are updated");
		} catch (Exception e) {
			log.info("Error observed in fields updation");
			extTestObj.get().log(Status.FAIL, "Error observed in fields updation");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
		}

	}

	/* Verify Favorites items is displaying on menu page */
	public void selectMenuOption() {
		try {
			clickableWait(Elements.menuOption);
			log.info("Menu Option selected");
			extTestObj.get().log(Status.PASS, "Menu Option selected");
		} catch (Exception e) {
			log.error("Menu Option selection failed");
			extTestObj.get().log(Status.FAIL, "Menu Option selection failed");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "All chilis favourite items obtained");
		} catch (Exception e) {
			log.error("Couldn't obtain chilis favourite items");
			extTestObj.get().log(Status.FAIL, "Couldn't obtain chilis favourite items");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
		}

	}

	/* Verify user is able to submit Add my visit form for login member */
	public void clickAddVisit() {

		try {
			Thread.sleep(7000);
			scrollIntoViewBottom(Elements.addMyVisitButton);
			clickableWait(Elements.addMyVisitButton);
			log.info("Add my visit button clicked");
			extTestObj.get().log(Status.PASS, "Add my visit button clicked");
		} catch (Exception e) {
			log.error("Add my visit button click failed");
			extTestObj.get().log(Status.FAIL, "Add my visit button clicked");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "Restaurant location entered as : " + loc);
			Thread.sleep(3000);
		} catch (Exception e) {
			log.error("Failed to enter restaurant location");
			extTestObj.get().log(Status.FAIL, "Failed to enter restaurant location");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "Chilis location selected as : " + locFromDropDown);
			Thread.sleep(3000);

		} catch (Exception e) {
			log.error("Chillis location selection failed");
			extTestObj.get().log(Status.FAIL, "Chillis location selection failed");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
		}

	}

	public void selectVisitMonth() throws InterruptedException {
		String visitMonth = excel.getCellData("AddMyVisit", "Visit Month", 2);
		try {
			explicitWait(Elements.visitMonthDropDown);
			clickElement(Elements.visitMonthDropDown);
			Thread.sleep(2000);
			AndroidElement ele = driver.findElementByXPath("//*[text()='March']");
			Thread.sleep(2000);
			ele.click();
			driver.findElementByXPath("//*[text()='April']").click();
			driver.navigate().back();
			Thread.sleep(3000);
			scrollDownFromStart("2000");
			scrollIntoViewTop(Elements.addMyVisitButton);
			log.info("Visit month selected as :" + visitMonth);
			extTestObj.get().log(Status.PASS, "Visit month selected as :" + visitMonth);
		} catch (Exception e) {
			log.error("Visit month selection failed");
			extTestObj.get().log(Status.FAIL, "Visit month selection failed");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
			// *[@resource-id='android:id/text1' and @text='April' and
			// @class='android.widget.CheckedTextView']

		}

	}

	public void selectVisitDay() {
		String visitDay = excel.getCellData("AddMyVisit", "Visit Day", 2);
		try {
			explicitWait(Elements.visitDayDropDown);
			clickElement(Elements.visitDayDropDown);
			explicitWait(By.xpath("//*[text()='" + visitDay + "']"));
			clickElement(By.xpath("//*[text()='" + visitDay + "']"));
			driver.getKeyboard().sendKeys(Keys.ENTER);
			log.info("Visit day selected as : " + visitDay);
			extTestObj.get().log(Status.PASS, "Visit day selected as : " + visitDay);
		} catch (Exception e) {
			log.error("Visit day selection failed");
			extTestObj.get().log(Status.FAIL, "Visit day selection failed");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
		}
	}

	public void selectVisitYear() {
		String visitYear = excel.getCellData("AddMyVisit", "Visit Year", 2);
		try {
			explicitWait(Elements.visitYearDropDown);
			clickElement(Elements.visitYearDropDown);
			explicitWait(By.xpath("//*[text()='" + visitYear + "']"));
			clickElement(By.xpath("//*[text()='" + visitYear + "']"));
			driver.getKeyboard().sendKeys(Keys.ENTER);
			log.info("Visit year selected as : " + visitYear);
			extTestObj.get().log(Status.PASS, "Visit year selected as : " + visitYear);
		} catch (Exception e) {
			log.error("Visit year selection failed");
			extTestObj.get().log(Status.FAIL, "Visit year selection failed");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
		}
	}

	public void enterCheckNumber() {

		String checkNo = excel.getCellData("AddMyVisit", "Receipt Check Number", 2);

		try {
			sendKeysWait(Elements.checkNumberTextBox, checkNo);
			log.info("Check Number entered as : " + checkNo);
			extTestObj.get().log(Status.PASS, "Check Number entered as : " + checkNo);
		} catch (Exception e) {
			log.error("Failed to enter check number");
			extTestObj.get().log(Status.FAIL, "Failed to enter check number");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
		}

	}

	public void enterCheckTotal() {

		String checkTotal = excel.getCellData("AddMyVisit", "Subtotal", 2);
		try {
			sendKeysWait(Elements.checkTotalTextBox, checkTotal);
			log.info("Check total entered as : " + checkTotal);
			extTestObj.get().log(Status.PASS, "Check total entered as : " + checkTotal);
		} catch (Exception e) {
			log.error("Failed to enter check total");
			extTestObj.get().log(Status.FAIL, "Failed to enter check total");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
		}
	}

	public void clickSubmit() {
		try {
			clickableWait(Elements.visitSubmitButton);
			log.info("Visit submitted");
			extTestObj.get().log(Status.PASS, "Visit submitted");
		} catch (Exception e) {
			log.error("Failed to submit visit");
			extTestObj.get().log(Status.FAIL, "Failed to submit visit");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
		}
	}

	public String retrieveSuccessMessage() {

		String successMsg = "";
		try {
			explicitWait(Elements.getSuccessMessageforAddMyVisit);
			successMsg = driver.findElement(Elements.getSuccessMessageforAddMyVisit).getText();
			log.info("Success message for add my visit displayed as : " + successMsg);
			extTestObj.get().log(Status.PASS, "Success message for add my visit displayed as : " + successMsg);
		} catch (Exception e) {
			log.error("Success message for add my visit not displayed");
			extTestObj.get().log(Status.FAIL, "Success message for add my visit not displayed");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "Restaurant location entered as " + restLocation);
		} catch (Exception e) {
			log.error("Failed to enter Restaurant location");
			extTestObj.get().log(Status.FAIL, "Failed to enter Restaurant location");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
		}

	}

	public void clickSearchButtonForLoggedInOrder() throws InterruptedException {
		try {
			scrollUp("200");
			clickableWait(Elements.searchButton);
			log.info("Search button clicked");
			extTestObj.get().log(Status.PASS, "Search button clicked");
		} catch (Exception e) {
			log.error("Search button click failed");
			extTestObj.get().log(Status.FAIL, "Search button click failed");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "Site scrolled and order button clicked");

		} catch (Exception e) {
			log.error("Order Now button click failed");
			extTestObj.get().log(Status.FAIL, "Order Now button click failed");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "Chilis favourite Item " + chilisFavItem + " selected");
		} catch (Exception e) {
			log.error("Failed to select chilis favourite item");
			extTestObj.get().log(Status.FAIL, "Failed to select chilis favourite item");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
		}
	}

	public void clickViewCart() throws InterruptedException {
		try {
			clickableWait(Elements.cartIcon);
			clickableWait(Elements.viewCartButton);
			log.info("View Cart clicked");
			extTestObj.get().log(Status.PASS, "View Cart clicked");
		} catch (Exception e) {
			log.error("Failed to click View Cart");
			extTestObj.get().log(Status.FAIL, "Failed to click View Cart");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
		}
	}

	public void noClickViewCart() throws InterruptedException {
		try {
			clickableWait(Elements.viewCartButton);
			log.info("View Cart clicked");
			extTestObj.get().log(Status.PASS, "View Cart clicked");
		} catch (Exception e) {
			log.error("Failed to click View Cart");
			extTestObj.get().log(Status.FAIL, "Failed to click View Cart");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "Silver ware opted in");
		} catch (Exception e) {
			log.error("Silver ware opt in failed");
			extTestObj.get().log(Status.FAIL, "Silver ware opt in failed");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();

		}

	}

	public void clickCheckOut() {
		try {
			explicitWait(Elements.cartTotal);
			clickableWait(Elements.checkOutButton);
			log.info("Order checked Out");
			extTestObj.get().log(Status.PASS, "Order checked Out");
		} catch (Exception e) {
			log.error("Order check out failed");
			extTestObj.get().log(Status.FAIL, "Order check out failed");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
		}
	}

	public void continueToPayment() {
		try {
			explicitWait(Elements.orderTotal);
			clickableWait(Elements.paymentButton);
			log.info("Payment button clicked");
			extTestObj.get().log(Status.PASS, "Payment button clicked");
		} catch (Exception e) {
			log.error("Failed to click Payment button");
			extTestObj.get().log(Status.FAIL, "Failed to click Payment button");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "Card Number entered as : " + cardNo);
		} catch (Exception e) {
			log.error("Failed to enter Card Number");
			extTestObj.get().log(Status.FAIL, "Failed to enter Card Number");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
		}
	}

	public void enterCVV() {
		String cvv = excel.getCellData("LoggedInOrder", "CVV", 2);
		try {
			sendKeysWait(Elements.cvvTextBox, cvv);
			log.info("CVV entered as : " + cvv);
			extTestObj.get().log(Status.PASS, "CVV entered as : " + cvv);
		} catch (Exception e) {
			log.error("Failed to enter CVV");
			extTestObj.get().log(Status.FAIL, "Failed to enter CVV");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
		}

	}

	public void selectExpirationMonth() // Ayushman
	{
		String month = excel.getCellData("LoggedInOrder", "Expiration Month", 2);
		String year = excel.getCellData("LoggedInOrder", "Expiration Year", 2);
		try {
			scrollIntoViewBottom(Elements.expirationMonth);
			clickableWait(Elements.expirationMonth);
			scrollIntoViewBottom(By.xpath("//*[contains(text(),'" + month + "')]"));
			clickElement(By.xpath("//*[contains(text(),'" + month + "')]"));
			log.info("Expiration Month selected as : " + month);
			extTestObj.get().log(Status.PASS, "Expiration Month selected as : " + month);
		} catch (Exception e) {
			log.error("Failed to select Expiration Month");
			extTestObj.get().log(Status.FAIL, "Failed to select Expiration Month");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
		}
	}

	public void selectExpirationYear() // Ayushman
	{
		String year = excel.getCellData("LoggedInOrder", "Expiration Year", 2);
		try {
			scrollIntoViewBottom(Elements.expirationYear);
			clickableWait(Elements.expirationYear);
			scrollIntoViewBottom(By.xpath("//*[contains(text(),'" + year + "')]"));
			clickElement(By.xpath("//*[contains(text(),'" + year + "')]"));
			log.info("Expiration Year selected as : " + year);
			extTestObj.get().log(Status.PASS, "Expiration Year selected as : " + year);
		} catch (Exception e) {
			log.error("Failed to select Expiration Year");
			extTestObj.get().log(Status.FAIL, "Failed to select Expiration Year");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
		}
	}

	public void enterNameOnCard() throws InterruptedException { // Ayushman

		String nameOnCard = excel.getCellData("LoggedInOrder", "Name On Card", 2);
		try {
			explicitWait(Elements.nameOnCard);
			clickableWait(Elements.nameOnCard);
			driver.getKeyboard().sendKeys(nameOnCard);
			log.info("Name on Card entered as : " + nameOnCard);
			extTestObj.get().log(Status.PASS, "Name on Card entered as : " + nameOnCard);
		} catch (Exception e) {
			log.error("Failed to enter Name on Card");
			extTestObj.get().log(Status.FAIL, "Failed to enter Name on Card");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "Billing zip code entered as : " + zipCode);
		} catch (Exception e) {
			log.error("Failed to enter Billing zip code");
			extTestObj.get().log(Status.FAIL, "Failed to enter Billing zip code");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
		}
	}

	public void giveTip() {
		String tip = excel.getCellData("LoggedInOrder", "Tip", 2);
		try {
			sendKeysWait(Elements.tipTextBox, tip);
			log.info("Tip given as : " + tip);
			extTestObj.get().log(Status.PASS, "Tip given as : " + tip);
		} catch (Exception e) {
			log.error("Failed to enter tip");
			extTestObj.get().log(Status.FAIL, "Failed to enter tip");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
		}
	}

	public String OrderTotal() {
		try {
			explicitWait(Elements.pickUpCost);
			log.info("Pick up cost displayed as : " + driver.findElement(Elements.pickUpCost).getText());
			extTestObj.get().log(Status.PASS,
					"Pick up cost displayed as : " + driver.findElement(Elements.pickUpCost).getText());
		} catch (Exception e) {
			log.error("Pick up cost not displayed");
			extTestObj.get().log(Status.FAIL, "Pick up cost not displayed");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());

		}
		return driver.findElement(Elements.pickUpCost).getText();
	}

	public void checkRoundOff() {
		try {
			clickableWait(Elements.donationCheckBox);
			log.info("Donation checked");
			extTestObj.get().log(Status.PASS, "Donation checked");
		} catch (Exception e) {
			log.error("Failed to check donation check box");
			extTestObj.get().log(Status.FAIL, "Failed to check donation check box");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());

		}
	}

	public void placeOrder() {
		try {
			scrollDownFromStart("50");
			clickableWait(Elements.placeOrder);
			log.info("Place order button clicked");
			extTestObj.get().log(Status.PASS, "Place order button clicked");
		} catch (Exception e) {
			log.error("Failed to click place order button");
			extTestObj.get().log(Status.FAIL, "Failed to click place order button");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();

		}
	}

	public String getSuccessMessageforLoggedInOrder() {
		try {
			scrollDownFromStart("50");
			explicitWait(Elements.successMessageforLoggedInOrder);
			log.info("Success message displayed as : "
					+ driver.findElement(Elements.successMessageforLoggedInOrder).getText());
			extTestObj.get().log(Status.PASS, "Success message displayed as : "
					+ driver.findElement(Elements.successMessageforLoggedInOrder).getText());
		} catch (Exception e) {
			log.error("Success message not displayed");
			extTestObj.get().log(Status.FAIL, "Success message not displayed");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
		}
		return driver.findElement(Elements.successMessageforLoggedInOrder).getText();

	}

	public String returnOrderPrice() {
		try {
			explicitWait(Elements.orderPrice);
			log.info("Order price displayed as  : " + driver.findElement(Elements.orderPrice).getText());
			extTestObj.get().log(Status.PASS,
					"Order price displayed as  : " + driver.findElement(Elements.orderPrice).getText());
		} catch (Exception e) {
			log.error("Order price not displayed");
			extTestObj.get().log(Status.FAIL, "Order price not displayed");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
		}
		return driver.findElement(Elements.orderPrice).getText();

	}

	/* Validate user is able to place reorder. */
	public void selectReorderOption() {
		try {
			clickableWait(Elements.reorderOption);
			log.info("Reorder option selected");
			Thread.sleep(3000);
		} catch (Exception e) {
			log.error("Failed to select reorder option");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
		}
	}

	public void clickReorderforanOrder() {
		try {
			scrollDownFromStart("350");
			clickableWait(Elements.reOrder);
			log.info("Clicked reorder for a particular order");
			Thread.sleep(3000);
		} catch (Exception e) {
			log.error("Failed to click reorder");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
		} catch (Exception e) {
			log.error("Failed to select and change quantity");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "Site scrolled and order button clicked");

		} catch (Exception e) {
			log.error("Site scrolled but order button not clicked");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			extTestObj.get().log(Status.FAIL, "Site scrolled but order button not clicked");
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
			extTestObj.get().log(Status.PASS, "Site scrolled and category " + catagory + " selected");

		} catch (Exception e) {
			log.error("Site scrolled but selected catagory button not clicked");
			extTestObj.get().log(Status.FAIL, "Site scrolled but selected catagory button not clicked");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "Site scrolled and  item" + item + "clicked");

		} catch (Exception e) {
			log.error("Site scrolled but item not selected");
			extTestObj.get().log(Status.FAIL, "Site scrolled but item not selected");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "Item added to cart");

		} catch (Exception e) {
			log.error("Failed to add item to cart");
			extTestObj.get().log(Status.FAIL, "Failed to add item to cart");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
		}
	}

	public void clickCheckOutforGuestUser() {
		try {
			scrollIntoViewBottom(Elements.checkOutButton);
			explicitWait(Elements.cartTotal);
			clickableWait(Elements.checkOutButton);
			log.info("Order checked Out");
			extTestObj.get().log(Status.PASS, "Order checked Out");
		} catch (Exception e) {
			log.error("Order check out failed");
			extTestObj.get().log(Status.FAIL, "Order check out failed");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "Site scrolled and Delivery mode is selected");

		} catch (Exception e) {
			log.error("Site scrolled but Delivery mode is not selected");
			extTestObj.get().log(Status.FAIL, "Site scrolled but Delivery mode is not selected");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "Delivery location entered as " + location);
		} catch (Exception e) {
			log.error("Failed to enter Delivery location");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			extTestObj.get().log(Status.FAIL, "Failed to enter Delivery location");
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
			extTestObj.get().log(Status.PASS, "Apartment no. entered as : " + aptNo);
		} catch (Exception e) {
			log.error("Failed to enter Apartment no.");
			extTestObj.get().log(Status.FAIL, "Failed to enter Apartment no.");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
		}
	}

	public void selectDeliveryDateASAP() {
		try {
			clickableWait(Elements.delDate);
			clickElement(Elements.dateInputASAP);
			log.info("Delivery Option selected as ASAP");
			extTestObj.get().log(Status.PASS, "Delivery Option selected as ASAP");
		} catch (Exception e) {
			log.error("Failed to select Delivery Option as ASAP");
			extTestObj.get().log(Status.FAIL, "Failed to select Delivery Option as ASAP");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "Delivery date selected as " + dateInput);
		} catch (Exception e) {
			log.error("Failed to select Delivery date");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			extTestObj.get().log(Status.FAIL, "Failed to select Delivery date");
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
			extTestObj.get().log(Status.PASS, "First name entered as : " + firstName);
		} catch (Exception e) {
			log.error("Failed to enter First name");
			extTestObj.get().log(Status.FAIL, "Failed to enter First name");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			log.info("First name entered as : " + LastName);
			extTestObj.get().log(Status.PASS, "First name entered as : " + LastName);
		} catch (Exception e) {
			log.error("Failed to enter Last name");
			extTestObj.get().log(Status.FAIL, "Failed to enter Last name");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "Contact Number entered as : " + contactNum);
		} catch (Exception e) {
			log.error("Failed to enter Contact number");
			extTestObj.get().log(Status.FAIL, "Failed to enter Contact number");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "Email address entered as : " + email);
		} catch (Exception e) {
			log.error("Failed to enter Email id");
			extTestObj.get().log(Status.FAIL, "Failed to enter Email id");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "Delivery time selected as " + timeInput);
		} catch (Exception e) {
			log.error("Failed to select Delivery time");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			extTestObj.get().log(Status.FAIL, "Failed to select Delivery time");
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
			extTestObj.get().log(Status.PASS, "Delivery Instruction given as " + deliveryInstruction);
		} catch (Exception e) {
			log.error("Failed to provide Delivery Instructions");
			extTestObj.get().log(Status.FAIL, "Failed to provide Delivery Instructions");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "Site scrolled and Curbside mode is selected");

		} catch (Exception e) {
			log.error("Site scrolled but Curbside mode is not selected");
			extTestObj.get().log(Status.FAIL, "Site scrolled but Curbside mode is not selected");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "Pickup date selected as " + dateInput);
		} catch (Exception e) {
			log.error("Failed to select Pickup date");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			extTestObj.get().log(Status.FAIL, "Failed to select Pickup date");
			tearDown();
		}
	}

	public void selectPickupTime() // Ayushman
	{
		String timeInput = excel.getCellData("Pickup", "Pickup Time", 2);
		try {
			scrollIntoViewHalf(Elements.pickTime);
			clickableWait(Elements.pickTime);
			Thread.sleep(2000);
			clickableWait(By.xpath("//*[text()='" + timeInput + "']"));
			Thread.sleep(2000);
			log.info("Pickup time selected as " + timeInput);
			extTestObj.get().log(Status.PASS, "Pickup time selected as " + timeInput);
		} catch (Exception e) {
			log.error("Failed to select Pickup time");
			extTestObj.get().log(Status.FAIL, "Failed to select Pickup time");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "Vehicle make entered as " + vehicle);
		} catch (Exception e) {
			log.error("Failed to enter Vehicle make");
			extTestObj.get().log(Status.FAIL, "Failed to enter Vehicle make");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "Vehicle model entered as : " + vehicleModel);
		} catch (Exception e) {
			log.error("Failed to enter Vehicle model");
			extTestObj.get().log(Status.FAIL, "Vehicle model entered as : " + vehicleModel);
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "Vehicle Color entered as " + vehicleColor);
		} catch (Exception e) {
			log.error("Failed to enter Vehicle Color");
			extTestObj.get().log(Status.FAIL, "Failed to enter Vehicle Color");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			extTestObj.get().log(Status.PASS, "Later Today delivery time is selected");
		} catch (Exception e) {
			log.error("Failed to select delivery time Later Today");
			extTestObj.get().log(Status.FAIL, "Failed to select delivery time Later Today");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
		}
	}

	/* Validate user is able to place Curbside-ASAP order for MCA user */
	public void selectPickupAsap() {
		try {
			clickableWait(Elements.pickDate);
			Thread.sleep(3000);
			clickElement(Elements.pickupAsapOrder);
			log.info("'ASAP' pickup time is selected");
			extTestObj.get().log(Status.PASS, "Curbside Button is Clicked");
		} catch (Exception e) {
			log.error("Failed to select pickup time 'ASAP'");
			extTestObj.get().log(Status.FAIL, "Failed to select pickup time 'ASAP'");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
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
			log.info("Carryout Button is Clicked");
			extTestObj.get().log(Status.PASS, "Carryout Button is Clicked");
		} catch (Exception e) {
			log.error("Carryout Button click failed");
			extTestObj.get().log(Status.FAIL, "Carryout Button click failed");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
		}

	}

	/*
	 * public void selectPickupForFuture() {
	 * 
	 * String futureTime = excel.getCellData(sheetName, colName, rowNum); try {
	 * clickableWait(Elements.pickDate); Thread.sleep(3000);
	 * clickElement(By.xpath("//*[text()='"+futureTime+"']"));
	 * log.info("Pickup future time is selected as "+futureTime);
	 * extTestObj.get().log(Status.PASS,"Pickup future time is selected as "
	 * +futureTime); } catch (Exception e) {
	 * log.error("Failed to select pick up future time");
	 * extTestObj.get().log(Status.FAIL,"Failed to select pick up future time");
	 * log.error(e.getMessage()); extTestObj.get().log(Status.ERROR,e.toString());
	 * tearDown(); } }
	 */

	public void selectPickupLaterToday() {
		try {
			clickableWait(Elements.pickDate);
			Thread.sleep(3000);
			clickElement(Elements.LaterToday);
			log.info("Later Today pickup time is selected");
			extTestObj.get().log(Status.PASS, "Later Today pickup time is selected");
		} catch (Exception e) {
			log.error("Failed to select Later Today");
			extTestObj.get().log(Status.FAIL, "Failed to select Later Today");
			log.error(e.getMessage());

			tearDown();
		}
	}

	/* Validate user is able to place order with Rewards. For sign in user */
	public void addReward() {
		try {
			clickableWait(Elements.addRewards);
			log.info("Reward added");
			extTestObj.get().log(Status.PASS, "Reward added");
		} catch (Exception e) {
			log.error("Failed to add reward");
			extTestObj.get().log(Status.FAIL, "Failed to add reward");
			log.error(e.getMessage());
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
		}
	}

	/*
	 * public void addRewardItem() { String rewarditem =
	 * excel.getCellData(sheetName, colName, rowNum) try {
	 * clickableWait(By.xpath("//*[contains(text(),'"+rewarditem+"')]"));
	 * clickableWait(Elements.addThisItem);
	 * log.info("Reward item "+rewarditem+" added");
	 * extTestObj.get().log(Status.PASS,"Reward item "+rewarditem+" added"); }
	 * catch(Exception e) { log.error("Failed to add reward item");
	 * extTestObj.get().log(Status.FAIL,"Failed to add reward item");
	 * log.error(e.getMessage()); extTestObj.get().log(Status.ERROR,e.toString());
	 * tearDown(); } }
	 */
	/*
	 * public void isRewardApplied() {
	 * 
	 * try { driver.findElement(Elements.discount).isDisplayed();
	 * log.info("Discount is applied");
	 * extTestObj.get().log(Status.PASS,"Discount is applied"); } catch (Exception
	 * e) { log.error("Failed to apply discount");
	 * extTestObj.get().log(Status.FAIL,"Failed to apply discount");
	 * log.error(e.getMessage()); extTestObj.get().log(Status.ERROR,e.toString());
	 * tearDown(); }
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
	 * extTestObj.get().log(Status.PASS,"Order customized with "+custItem); }
	 * catch(Exception e) { log.error("Failed to customize order");
	 * extTestObj.get().log(Status.FAIL,"Failed to customize order");
	 * log.error(e.getMessage()); extTestObj.get().log(Status.ERROR,e.toString());
	 * tearDown(); } }
	 */
	/*
	 * public void validateCustomization() { String custItem =
	 * excel.getCellData(sheetName, colName, rowNum); try {
	 * explicitWait(Elements.customItem); String custInfo =
	 * driver.findElement(Elements.customItem).getText();
	 * if(custInfo.contains(custItem)&&(custInfo.contains("Extra")));
	 * log.info("The item added for customization is "+custInfo);
	 * extTestObj.get().log(Status.PASS,"The item added for customization is "
	 * +custInfo); } catch(Exception e) { log.error("Incorrect Customization Info");
	 * extTestObj.get().log(Status.FAIL,"Incorrect Customization Info");
	 * log.error(e.getMessage()); extTestObj.get().log(Status.ERROR,e.toString());
	 * tearDown();
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
			extTestObj.get().log(Status.PASS, "Site scrolled and join line button clicked");

		} catch (Exception e) {
			log.error("Site scrolled but join line button not clicked");
			log.error(e.getMessage());
			extTestObj.get().log(Status.PASS, "Site scrolled but join line button not clicked");
			extTestObj.get().log(Status.ERROR, e.toString());
			tearDown();
		}
	}
	/*
	 * public void clickMinimumPartySize() { //Ayushman try { Thread.sleep(2000);
	 * scrollIntoViewHalf(Elements.partySizeOne); Thread.sleep(2000);
	 * clickableWait(Elements.partySizeOne);
	 * log.info("Site scrolled and party size selected(1)");
	 * 
	 * } catch (Exception e) {
	 * log.error("Site scrolled but party size not selected");
	 * log.error(e.getMessage()); extTestObj.get().log(Status.ERROR,e.toString());
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
	 * log.info("Name on join line entered"); } catch (Exception e) {
	 * log.error("Failed to enter Name on join line"); log.error(e.getMessage());
	 * extTestObj.get().log(Status.ERROR,e.toString()); tearDown(); } }
	 */

	/*
	 * public void enterContactNumberJoinLine() throws InterruptedException {
	 * //Ayushman String cNumber = excel.getCellData(sheetName, colName, rowNum);
	 * try { scrollIntoViewHalf(Elements.joinLineContactNumber);
	 * explicitWait(Elements.joinLineContactNumber);
	 * clickableWait(Elements.joinLineContactNumber);
	 * driver.getKeyboard().sendKeys(cNumber); log.info("Mobile number entered"); }
	 * catch (Exception e) { log.error("Failed to enter Mobile number");
	 * log.error(e.getMessage()); extTestObj.get().log(Status.ERROR,e.toString());
	 * tearDown(); } }
	 */

	/*
	 * public void clickJoinLine() { //Ayushman try { Thread.sleep(2000);
	 * scrollIntoViewHalf(Elements.clickJoinLine); Thread.sleep(2000);
	 * clickableWait(Elements.clickJoinLine);
	 * log.info("Site scrolled and joined line");
	 * 
	 * } catch (Exception e) { log.error("Site scrolled but not joined line");
	 * log.error(e.getMessage()); extTestObj.get().log(Status.ERROR,e.toString());
	 * tearDown(); }
	 * 
	 * }
	 */

}
