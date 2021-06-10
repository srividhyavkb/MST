package Resources;
import org.openqa.selenium.By;

public class Elements {
	//Login Logout
	public static final By popUpCloseButton = By.className("close-btn");
	public static final By subHeader = By.xpath("//*[text()='Feed Your Whole Family With']");
	public static final By menuButton = By.xpath("//*[@id='header-container']/a[3]");
	public static final By loginButton = By.xpath("//*[@id='header-login']");
	public static final By userNameTextBox = By.xpath("//input[@id='username']");
	public static final By passwordTextBox = By.xpath("//input[@id='password']");
	public static final By signinButton = By.xpath("//button[@id='login-submit']");
	public static final By logoutButton = By.xpath("//*[@id='header-logout']");
	public static final By loginHeader = By.xpath("//span[@class='summary title-summary-login']/preceding-sibling::h1");
	
	//Location Search
	public static final By locationsButton = By.xpath("//*[@id='header-locations']");
	public static final By locationSearchTextBox = By.xpath("//*[@id='location-search']");
	public static final By searchButton = By.xpath("//*[@id='button-location-query']/span");
	public static final By restaurantName = By.xpath("//*[@class='location-summary']/preceding-sibling::h1");
	//Rewards
	public static final By noOfRewards = By.xpath("//*[@id='rewards-logged-in-summary-rewards']");
	public static final By actualRewardsCount = By.xpath("//*[@id='active-rewards-carousel']/div[1]/div/div");
	//My Account Update
	public static final By myAccountOption = By.xpath("//*[@id='header-account']");
	public static final By emailTextBox = By.xpath("//*[@id='email']");
	public static final By firstNameTextBox = By.xpath("//*[@id='firstName']");
	public static final By lastNameTextBox = By.xpath("//*[@id='lastName']");
	public static final By zipCodeTextBox = By.xpath("//*[@id='postalCode']");
	public static final By consentCheckBox = By.xpath("//*[@for='mobile-opt-in']");
	public static final By updateButton = By.xpath("//button[@type='submit' and text()='Update']");
	public static final By successMessageforUpdate = By.xpath("//span[@class='server-success-message']");
	    //Chilis Favourites
	public static final By menuOption = By.xpath("//*[@id='header-menu']");
	public static final By favouriteMenu = By.xpath("//*[@id='chilis-favorite-carousel']/div[1]/div/div");
	public static final By favouriteItemsTitle = By.xpath("//div[@class='heading-tertiary heading-favorite']");
	//Add My Visit
	public static final By addMyVisitButton = By.xpath("//button[text()='Add My Visit']");
	public static final By restaurantLocTextBox = By.xpath("//*[@id='location-search']");
	public static final By outsideClickLoc = By.xpath("//form[@id='manual-visit-request-form']/div/div/h2");
	public static final By chillisLocDropDown = By.xpath("//*[@id='store-number']");
	public static final By visitMonthDropDown = By.xpath("//*[@id='visit-month']");
	public static final By visitDayDropDown = By.xpath("//*[@id='visit-day']");
	public static final By visitYearDropDown = By.xpath("//*[@id='visit-year']");
	public static final By checkNumberTextBox = By.xpath("//*[@id='check-number']");
	public static final By checkTotalTextBox = By.xpath("//*[@id='check-total']");
	public static final By visitSubmitButton = By.xpath("//*[@id='mvr-confirm']");
	public static final By anyElement = By.xpath("//*[@id=\\\"active-rewards-carousel\\\"]/div[1]/div/div[1]/div/div[3]/span");
	public static final By getSuccessMessageforAddMyVisit = By.xpath("//span[@class='server-success-message']");
	public static final By toGetAddMyVisitButton = By.xpath("//*[@id='page-container']/div/div[4]/div/div/div[1]");
	public static final By chilisLocationOption = By.xpath("//*[text()='Addison - 4500 Beltline Rd., Dallas, TX 75001']");
	public static final By visitMonthOption = By.xpath("//*[text()='June']");
	public static final By visitDayOption = By.xpath("//*[text()='4']");
	public static final By visitYearOption = By.xpath("//*[text()='2018']");
	
	//Logged In Order
	public static final By orderNowButton = By.xpath("//a[contains(@href,'coit-road')]/following-sibling::a");
	public static final By addToCartButton = By.xpath("//button[@id='item-add-to-order']");
	public static final By viewCartButton = By.xpath("//a[@id='mini-cart-view-upsell']");
	public static final By optSilverWareCheckBox = By.xpath("//label[@for='silverware-opt-in-select']");
	public static final By checkOutButton = By.xpath("//*[@id='cart-checkout']");
	public static final By orderTotal = By.xpath("//div[@class='heading-secondary order-total-label']");
	public static final By deliveryOption = By.xpath("//*[@id='location-001.005.0002']/div[3]/div/div/div/span[2]");
	public static final By addItem = By.xpath("//*[text()='Add to order']");
	public static final By cartIcon = By.xpath("//img[@alt='Cart']");
	public static final By addRewardsTitle = By.xpath("//h3[@class='accordion-title']");
	public static final By cartTotal = By.xpath("//div[@class='cart-totals']");
	public static final By paymentButton = By.xpath("//*[@id='continue-to-payment']");
	public static final By successMessageforLoggedInOrder =  By.xpath("//*[@id='page-container']/div/div[1]/div[1]/div/h1");
	public static final By orderPrice = By.xpath("//*[@id='pickup-total-confirm']/td[2]/div");
	public static final By placeOrder = By.xpath("//*[@id='place-order-submit']");
	public static final By expirationMonth = By.xpath("//*[@id='month-selector']");
	public static final By expirationYear = By.xpath("//*[@id='year-selector']");
	public static final By cvvTextBox = By.xpath("//*[@id='cvv']");
	public static final By tipTextBox = By.xpath("//input[@name='tip']");
	public static final By pickUpCost = By.xpath("//*[@id='pickup-cost']");
	public static final By donationCheckBox = By.xpath("//*[@id='roundup-checkbox-online']");
	public static final By locationHeader = By.xpath("//*[@id='page-container']/div/div[1]/div/div/div/div[1]/div/h1");
	public static final By silverWareCheckBox = By.xpath("//*[@id='silverware-opt-in-select']");
	//Re Order
	public static final By reorderOption = By.xpath("//*[@id='header-order-history']");
	public static final By quantity = By.xpath("//*[@id='items0.quantity']");
	public static final By reOrder = By.xpath("//div[contains(@class,'first-order')]/div[4]/div[2]/form/button");
	//Guest Delivery-ASAP //Ayushman
	public static final By orderNowButtonDallas = By.xpath("//a[@id='location-select-001.005.0002']");
	public static final By bigMouthBurgersCategory = By.xpath("//*[@id='menu-category-name-big-mouth-burgers']");
	public static final By baconRancher = By.xpath("//*[@id='menu-item-button-P110316']");
	public static final By addThisItem = By.xpath("//*[@id='item-add-to-order']");
	public static final By selectDeliveryMode = By.xpath("//*[text()='Delivery']");
	public static final By deliveryAddress = By.xpath("//*[@id='autocomplete']");
	public static final By aptNo = By.xpath("//*[@id='suite-no']");
	public static final By delDate = By.xpath("//*[@id='delivery-date']");
	public static final By dateInputASAP = By.xpath("//select[@id='delivery-date']/option[contains(@value,'ASAP')]");
	public static final By dateInputLT = By.xpath("//*[text()='Later Today']");
	public static final By delTime = By.xpath("//*[@id='delivery-time']");
	public static final By timeInputLT = By.xpath("//*[text()='12:30 PM']");
	public static final By firstName = By.xpath("//*[@id='first-name']");
	public static final By lastName = By.xpath("//*[@id='last-name']");
	public static final By contantNumber = By.xpath("//*[@id='contact-phone']");
	public static final By eMail = By.xpath("//*[@id='email']");
	public static final By cardNo = By.xpath("//*[@id='card-number']");
	public static final By nameOnCard = By.xpath("//*[@id='nameOnCard']");
	public static final By billingZip = By.xpath("//*[@id='zipcode']");
	
	//Delivery ASAP MCA user
	public static final By deliveryInstrTextbox = By.xpath("//*[@id='special-instructions']");
	
	
	//Guest Curbside				//Ayushman
		public static final By selectCurbsideMode = By.xpath("//*[text()='Curbside']");
		public static final By vehicleMake = By.xpath("//*[@id='vehicle-make']");
		public static final By vehicleModel = By.xpath("//*[@id='vehicle-model']");
		public static final By vehicleColor = By.xpath("//*[@id='vehicle-color']");
	
		//Guest Carry-Out Future		//Ayushman
		public static final By tempCust = By.xpath("//*[@name='itemChoices[0]']");
		public static final By selectWellDone = By.xpath("//*[text()='Well Done']");
		public static final By sideCust = By.xpath("//*[@name='sideItems[0]']");
		public static final By selectHouseSalad = By.xpath("//*[text()='House Salad ($1.00)']");
		public static final By dressingCust = By.xpath("//*[@name='sideItems[10]']");
		public static final By selectRanch = By.xpath("//*[text()='Ranch Dressing']");
		public static final By beverageCust = By.xpath("//*[@name='additionalItems[0]']");
		public static final By selectBBIcedTea = By.xpath("//*[text()='Blackberry Iced Tea ($3.19)']");
		public static final By soupCust = By.xpath("//*[@name='additionalItems[1]']");
		public static final By selectBowlOfSoup = By.xpath("//*[text()='Bowl of Soup with Entrée ($3.99)']");
		public static final By saladCust = By.xpath("//*[@name='additionalItems[4]']");
		public static final By selectCaeserSalad = By.xpath("//*[text()='Caesar Salad with Entrée ($3.69)']");
		public static final By additionalDressingCust = By.xpath("//*[@name='itemChoices[0]']");
		public static final By selectCarryOutMode = By.xpath("//*[@id='order-type-carryout-btn']");
		public static final By pickDate = By.xpath("//*[@id='pickup-date']");
		public static final By dateInputTom = By.xpath("//*[text()='Tomorrow']");
		public static final By pickTime = By.xpath("//*[@id='pickup-time']");
		public static final By placeOrderButton = By.xpath("//*[@id='place-order']");
		
		//Delivery Later Today MCA
		public static final By LaterToday = By.xpath("//select[@id='pickup-date']/option[contains(text(),'Later')]");
		
		//Curbside ASAP MCA 
		public static final By pickupAsapOrder = By.xpath("//*[@id='pickup-date']/option[contains(@value,'ASAP')]");
		
		//Place order with Rewards
		public static final By addRewards = By.xpath("//*[@name='reward']");
		public static final By rewardItem = By.xpath("//*[contains(text(),'Chips and Salsa')]");
		
		//Carry Out Customized order with Guest user
		public static final By customizeOrderButton = By.xpath("//*[@id='customize-item-button']");
		public static final By addExtraSauce = By.xpath("//input[contains(@id,'extra')]");
		public static final By sauceDropDown = By.xpath("//*[contains(@id,'widget')]/div[2]/div[1]/select");
		public static final By customItem = By.xpath("//*[@id='page-container']/div[4]/form/div[2]/div[2]/div[1]/div[2]/div/ul/li/span");
		
		
		
		
		//*[@id="place-dinein-order"]
		//*[@id="order-type-dine-in-btn"]

		//*[@id="delivery-confirmation"]/a
		//*[@id="order-delivery-status"]
		
		//*[@id='infobar_close_button']
		
//Android App Automation : Login Logout
	public static final String appPopUpCloseXpath = "//*[@id='cancel_btn']";
	public static final String appwelcomeMessageXpath = "//android.widget.TextView[@text='FEED YOUR WHOLE FAMILY WITH']";
	public static final String appLoginButtonXpath = "//android.widget.Button[@id='card_home_logged_out_login']";
	public static final String appUserNameTextBoxXpath = "//android.widget.EditText[@id='login_username_field']";
	public static final String appPassWordTextBoxXpath = "//android.widget.EditText[@id='login_password_field']";
	public static final String appSigninButtonXpath = "//android.widget.Button[@id='login_btn']";
	public static final String apploginHeaderXpath = "//*[@text='LOG IN TO YOUR ACCOUNT']";
	public static final String appMoreButtonXpath = "//*[@id='icon' and ./parent::*[@id='menu_bottom_nav_more']]";
	public static final String appLogoutButtonXpath = "//android.widget.TextView[@text='Log Out']";
	public static final String signOutConfirmButtonXpath = "//android.widget.Button[@text='YES']";
	public static final String applogoutValXpath = "//*[@id='card_home_logged_out_login']";
}
