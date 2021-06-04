package Resources;

import java.util.List;
import java.util.Properties;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class FunctionalComponentsIOS extends BaseIOS {

	public IOSDriver<IOSElement> driver = null;
	Properties property = returnProperty();
	ExtentReports extent = ExtentReporterNG.getReportObject();
	Listeners listen;
	ExtentTest test;
	public WebDriverWait wait;
	Logger log;

	public FunctionalComponentsIOS(IOSDriver<IOSElement> driver, Logger log) {
		this.driver = driver;
		this.log = log;
		wait = new WebDriverWait(this.driver, 20);
		listen = new Listener();
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

// Login Logout
	public void validateQASite() {
		log.info("Starting QA site validation");
		try {
			explicitWait(Elements.popUpCloseButton);
			log.info("QA site launch is successful");

		} catch (Exception e) {
			log.error("QA site launch failed");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
			tearDown();

		}

	}

	public void closePopupRewards() {
		try {
			clickableWait(Elements.popUpCloseButton);
			explicitWait(Elements.subHeader);
			log.info("Pop up closed");
		} catch (Exception e) {
			log.error("Pop up close failed");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
			tearDown();
		}

	}

	public void clickMenuButton() {
		try {
			clickableWait(Elements.menuButton);
			log.info("Menu button clicked");
		} catch (Exception e) {
			log.error("Menu button click failed");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
			tearDown();
		}
	}

	public void selectLogin() {
		try {
			clickableWait(Elements.loginButton);
			log.info("Login option selected");
		} catch (Exception e) {
			log.error("Login option not selected");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
			tearDown();
		}
	}

	public void enterUserName() {
		try {
			sendKeysWait(Elements.userNameTextBox, "(512) 212-1212");
			log.info("User name entered");
		} catch (Exception e) {
			log.error("Could not enter user name");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
			tearDown();
		}

	}

	public void enterPassword() {
		try {
			sendKeysWait(Elements.passwordTextBox, property.getProperty("password"));
			log.info("Password entered");
		} catch (Exception e) {
			log.error("Could not enter password");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
			tearDown();
		}

	}

	public void clickSignin() {
		try {
			clickableWait(Elements.signinButton);
			log.info("Sign in button clicked");
			Thread.sleep(5000);
		} catch (Exception e) {
			log.error("Sign in button click failed");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
			tearDown();
		}

	}

	public void logout() {
		try {
			clickableWait(Elements.logoutButton);
			log.info("Logout button clicked");
		} catch (Exception e) {
			log.info("Logout button click failed");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
			tearDown();
		}
	}

	public String validateLogout() {

		try {
			explicitWait(Elements.loginHeader);
			log.info("Login header displayed");
		} catch (Exception e) {
			log.error("Login header not displayed");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());

		}
		return driver.findElement(Elements.loginHeader).getText();
	}

// Rewards
	public int noOfRewards() {
		try {
			explicitWait(Elements.noOfRewards);
			log.info("Displayed rewards count obtained");
		} catch (Exception e) {
			log.error("Displayed rewards count couldn't be obtained");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());

		}

		return Integer.parseInt(driver.findElement(Elements.noOfRewards).getText());
	}

	public int ActualRewardsCount() {
		List<IOSElement> count = null;
		try {
			count = driver.findElements(Elements.actualRewardsCount);
			log.info("Actual rewards count obtained");
		} catch (Exception e) {
			log.error("Actual rewards count couldn't be obtained");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
		}
		return count.size();

	}

// Chilis Favourites
	public void selectMenuOption() {
		try {
			clickableWait(Elements.menuOption);
			log.info("Menu Option selected");
		} catch (Exception e) {
			log.error("Menu Option selection failed");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
			tearDown();
		}
	}

	public void getAllChilisFavouriteItems() {

		List<IOSElement> items = null;
		try {
			items = driver.findElements(By.xpath(property.getProperty("favouriteMenu")));
			for (IOSElement el : items) {
				System.out.println(el.findElement(By.xpath(property.getProperty("favouriteItemsTitle"))).getText());
			}
			log.info("All chilis favourite items obtained");
		} catch (Exception e) {
			log.error("Couldn't obtain chilis favourite items");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
		}

	}

// Location Search
	public void selectLocationsOption() {
		try {
			clickableWait(Elements.locationsButton);
			log.info("Location option selected");
		} catch (Exception e) {
			log.error("Location button selection failed");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
			tearDown();
		}
	}

	public void enterRestaurantLocation(String location) throws InterruptedException {
		try {
			explicitWait(Elements.locationSearchTextBox);
			clickableWait(Elements.locationSearchTextBox);
			driver.getKeyboard().sendKeys(location);
			log.info("Restaurant location entered");
		} catch (Exception e) {
			log.error("Failed to enter Restaurant location");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
			tearDown();
		}
	}

	public void clickSearchButton() {
		try {
			clickableWait(Elements.searchButton);
			log.info("Search button clicked");
		} catch (Exception e) {
			log.error("Search button click failed");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
			tearDown();
		}
	}

	public String getRestaurantName() {
		try {
			explicitWait(Elements.restaurantName);
			log.info("Restaurant name is displayed");
		} catch (Exception e) {
			log.error("Restaurant Name not displayed");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
		}
		return driver.findElement(Elements.restaurantName).getText();

	}

// Update My Account
	public void selectMyAccountOption() {

		try {
			clickableWait(Elements.myAccountOption);
			log.info("My Account option selected");
		} catch (Exception e) {
			log.error("My Account option selection failed");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
			tearDown();
		}

	}

	public void updateEmail() {
		try {
			sendKeysWait(Elements.emailTextBox, "somnath.baul@brinker.com");
			log.info("Email updated");
		} catch (Exception e) {
			log.error("Email updation failed");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
		}

	}

	public void updateLastName() {
		try {
			sendKeysWait(Elements.lastNameTextBox, "Sudhir");
			log.info("Last Name updated");
		} catch (Exception e) {
			log.error("Last Name updation failed");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());

		}

	}

	public void checkConsent() {
		try {
			clickableWait(Elements.consentCheckBox);
			log.info("Consent checked");
		} catch (Exception e) {
			log.error("Consent check failed");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
		}
	}

	public void clickUpdateButton() {
		try {
			clickableWait(Elements.updateButton);
			log.info("Update button clicked");
		} catch (Exception e) {
			log.error("Update button click failed");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
			tearDown();
		}
	}

	public String getSuccessMessage() {
		try {
			explicitWait(Elements.successMessageforUpdate);
			log.info("Success message obtained");
		} catch (Exception e) {
			log.error("Success message not obtained");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
		}
		return driver.findElement(Elements.successMessageforUpdate).getText();
	}
// Logged In Order

	public void enterRestaurantLocationForLoggedInOrder(String location) throws InterruptedException {

		try {
			explicitWait(Elements.locationSearchTextBox);
			clickableWait(Elements.locationSearchTextBox);
			driver.getKeyboard().sendKeys(location);
			log.info("Restaurant location entered");
		} catch (Exception e) {
			log.error("Failed to enter Restaurant location");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
			tearDown();
		}

	}

	public void clickSearchButtonForLoggedInOrder() throws InterruptedException {
		try {
			scrollUp("200");
			clickableWait(Elements.searchButton);
			log.info("Search button clicked");
		} catch (Exception e) {
			log.error("Search button click failed");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
			tearDown();
		}
	}

	public void orderNow() {
		try {
			Thread.sleep(3000);
			scrollDownFromStart("2000");
			Thread.sleep(3000);
			clickElement(Elements.orderNowButton);
			log.info("Site scrolled and order button clicked");

		} catch (Exception e) {
			log.error("Site scrolled but order button not clicked");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
			tearDown();
		}

	}

	public void AddChilisFavouriteToCart() throws InterruptedException {
		try {
			clickableWait(Elements.addItem);
			log.info("Chilis favourite Item selected");
		} catch (Exception e) {
			log.error("Failed to select chilis favourite item");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
			tearDown();
		}
	}

	public void clickViewCart() throws InterruptedException {
		try {
			clickableWait(Elements.cartIcon);
			clickableWait(Elements.viewCartButton);
			log.info("View Cart clicked");
		} catch (Exception e) {
			log.error("Failed to click View Cart");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
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
		} catch (Exception e) {
			log.error("Silver ware opt in failed");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
			tearDown();

		}

	}

	public void clickCheckOut() {
		try {
			scrollDownFromStart("600");
			explicitWait(Elements.cartTotal);
			clickableWait(Elements.checkOutButton);
			log.info("Order checked Out");
		} catch (Exception e) {
			log.error("Order check out failed");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
			tearDown();
		}
	}

	public void continueToPayment() {
		try {
			explicitWait(Elements.orderTotal);
			clickableWait(Elements.paymentButton);
			log.info("Payment button clicked");
		} catch (Exception e) {
			log.error("Failed to click Payment button");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
			tearDown();
		}
	}

	public void enterCVV() {
		try {
			sendKeysWait(Elements.cvvTextBox, "145");
			log.info("CVV entered");
		} catch (Exception e) {
			log.error("Failed to enter CVV");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
			tearDown();
		}

	}

	public void giveTip() {
		try {
			sendKeysWait(Elements.tipTextBox, "15");
			log.info("Tip given");
		} catch (Exception e) {
			log.error("Failed to enter tip");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
		}
	}

	public String OrderTotal() {
		try {
			explicitWait(Elements.pickUpCost);
			log.info("Pick up cost displayed");
		} catch (Exception e) {
			log.error("Pick up cost not displayed");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());

		}
		return driver.findElement(Elements.pickUpCost).getText();
	}

	public void checkRoundOff() {
		try {
			clickableWait(Elements.donationCheckBox);
			log.info("Donation checked");
		} catch (Exception e) {
			log.error("Failed to check donation check box");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());

		}
	}

	public void placeOrder() {
		try {
			scrollDownFromStart("50");
			clickableWait(Elements.placeOrder);
			log.info("Place order button clicked");
		} catch (Exception e) {
			log.error("Failed to click place order button");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
			tearDown();

		}
	}

	public String getSuccessMessageforLoggedInOrder() {
		try {
			scrollDownFromStart("50");
			explicitWait(Elements.successMessageforLoggedInOrder);
			log.info("Success message displayed");
		} catch (Exception e) {
			log.error("Sucess message not displayed");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
			tearDown();
		}
		return driver.findElement(Elements.successMessageforLoggedInOrder).getText();

	}

	public String returnOrderPrice() {
		try {
			explicitWait(Elements.orderPrice);
			log.info("Order price displayed");
		} catch (Exception e) {
			log.error("Order price not displayed");
			log.error(e.getMessage());
			listen.getErrorMessage(e.toString());
			tearDown();
		}
		return driver.findElement(Elements.orderPrice).getText();

	}

}