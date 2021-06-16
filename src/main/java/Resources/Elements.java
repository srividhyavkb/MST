package Resources;
import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

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
		public static final By discountLabel= By.xpath("//div[@class='order-discounts-label']");
		public static final By discountAmount=By.xpath("//div[@class='cost discount-amount']");
		public static final By rewardName=By.xpath("(//p[@id='member-reward'])[2]");
		
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
	public static final By appOkButtonXpath = MobileBy.xpath("//android.widget.Button[@text='OK']");
	public static final By appCancelButtonXpath = MobileBy.xpath("//android.widget.Button[@text='CANCEL']");
	public static final By appPopUpCloseXpath = MobileBy.xpath("//*[@id='cancel_btn']");
	public static final By appwelcomeMessageXpath = MobileBy.xpath("//android.widget.TextView[@text='FEED YOUR WHOLE FAMILY WITH']");
	public static final By appLoginButtonXpath = MobileBy.xpath("//android.widget.Button[@id='card_home_logged_out_login']");
	public static final By appUserNameTextBoxXpath = MobileBy.xpath("//android.widget.EditText[@id='login_username_field']");
	public static final By appPassWordTextBoxXpath = MobileBy.xpath("//android.widget.EditText[@id='login_password_field']");
	public static final By appSigninButtonXpath =MobileBy.xpath("//android.widget.Button[@id='login_btn']");
	public static final By apploginHeaderXpath =MobileBy.xpath("//*[@text='LOG IN TO YOUR ACCOUNT']");
	public static final By appMoreButtonXpath = MobileBy.xpath("//*[@id='icon' and ./parent::*[@id='menu_bottom_nav_more']]");
	public static final By appLogoutButtonXpath = MobileBy.xpath("//android.widget.TextView[@text='Log Out']");
	public static final By signOutConfirmButtonXpath = MobileBy.xpath("//*[@text='YES']");
	public static final By applogoutValXpath = MobileBy.xpath("//*[@id='card_home_logged_out_login']");
	
	
//Location Search
	public static final By appfindRestaurantLinkXpath = MobileBy.xpath("//android.widget.Button[@id='card_home_restaurant_not_selected_find']");
	public static final By appResSearchTextBoxXpath = MobileBy.xpath("//android.widget.EditText[@id='address_text_field']");
	public static final By appResSearchAutocompleteTextBoxXpath = MobileBy.xpath("//android.widget.EditText[@id='places_autocomplete_edit_text']");
	public static final By appRestaurantNameXpath = MobileBy.xpath("//android.widget.TextView[@id='restaurant_name']");
	public static final By appRestaurantSelectedAddressXpath = MobileBy.xpath("//android.widget.TextView[@id='card_home_restaurant_selected_address']");
	public static final By appRestaurantChangeButtonXpath = MobileBy.xpath("//android.widget.Button[@text='CHANGE']");
	
		//*[@text='SELECT' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[./*[@text='Delray Beach']]]]
	//*[@text='SELECT' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[./*[@text='2202 N. Congress']]]]
	//*[@text='DELRAY BEACH - 14534 S Military Trail']
	//android.widget.Button[@text='CHANGE']
	//*[@text='Current Location' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[./*[@text='Delray Beach']]]]
//	14534 S Military Trail
	
//Validate Rewards Details
	public static final By appRewardButtonXpath = MobileBy.xpath("//*[@id='icon' and ./parent::*[@id='menu_bottom_nav_rewards']]");
	public static final By appRewardsHeaderXpath = MobileBy.xpath("//android.widget.TextView[@text='My Rewards']");
	public static final By appdisplayedRewardCountXpath = MobileBy.xpath("//android.widget.TextView[@id='myrewards_dashboard_account_rewards_available']");
	public static final By appActiveRewardXpath = MobileBy.xpath("//*[@id='card_active_reward_image']");
	public static final By appTapToViewXpath = MobileBy.xpath("//*[@id='card_active_reward_scan']");
	public static final By appActiveRewardName = MobileBy.xpath("//android.widget.TextView[@id='active_reward_name']");
	public static final By appCloseRewardsPopUp = MobileBy.xpath("//android.widget.ImageButton[@contentDescription='Navigate up']");

//Favourite Items 
	public static final By appFavouriteItems = MobileBy.xpath("//*[@id='card_order_summary']");
	
//My Account Update
	public static final By appMyAccountOptionXpath = MobileBy.xpath("//android.widget.TextView[@text='My Account']");
	public static final By appUpdateButtonXpath = MobileBy.xpath("//android.widget.Button[@text='UPDATE']");
	public static final By appConsentCheckBoxXpath = MobileBy.xpath("//*[@id='mobile-opt-in']");
	public static final By appMyAccountPageHeaderXpath = MobileBy.xpath("//*[@text='UPDATE PROFILE']");
	public static final By appSuccessMessageXpath = MobileBy.xpath("//*[@text='Account successfully updated']");
	
	
	
//IOS 
//Login Logout
	public static final By iosDontAllowButtonXpath=MobileBy.xpath("//*[@text='Horizontal scroll bar, 1 page' and (./preceding-sibling::* | ./following-sibling::*)[./*[./*[./*[@text='Allow Once']]]]]");
	public static final By iosnotificationDontAllowButtonXpath = MobileBy.xpath("//*[@text='Don’t Allow']");
	public static final By iosPopUpClose=MobileBy.xpath("//*[@id='CancelButton']");
	public static final By iosLoginButton=MobileBy.xpath("((//*[@class='UIAScrollView']/*[@class='UIAView'])[2]/*/*/*/*[@text='LOG IN'])[1]");
	public static final By iosUserNameTextBoxXpath=MobileBy.xpath("//*[@id='Mobile Number']");
	public static final By iosPaswordTextBoxXpath=MobileBy.xpath("//*[@id='Password']");
	public static final By iosloginHeaderXpath=MobileBy.xpath("//*[@text='LOG IN TO YOUR ACCOUNT']");
	public static final By ioswelcomeMessageXpath=MobileBy.xpath("(((//*[@class='UIAScrollView']/*[@class='UIAView'])[2]/*/*[@class='UIAView' and ./parent::*[@class='UIAView']])[13]/*[@class='UIAStaticText' and @width>0])[1]");
	public static final By iosSignInButtonXpath=MobileBy.xpath("//*[@text='LOG IN' and @class='UIAStaticText' and ./parent::*[@accessibilityLabel='LogInButton']]");
	public static final By iosMoreButtonXpath=MobileBy.xpath("//*[@id='MORE']");
	public static final By iosLogOutButtonXpath=MobileBy.xpath("//*[@id='LOG OUT']");
	public static final By ioslogoutValXpath=MobileBy.xpath("((//*[@class='UIAScrollView']/*[@class='UIAView'])[1]/*/*/*[@text='Log in'])[1]");
	
	//Location Search
	public static final By iosSearchLocationLinkXpath=MobileBy.xpath("((//*[@class='UIAScrollView']/*[@class='UIAView'])[2]/*/*/*/*/*[@text=concat('FIND A NEARBY CHILI', \"'\", 'S')])[1]");
	public static final By iosRestaurantSearchTextBoxXpath=MobileBy.xpath("//*[@placeholder='City, State, ZIP OR Delivery Address']");
	public static final By iosRestaurantAutocompleteTextBoxXpath=MobileBy.xpath("//*[@id='Search' and @class='UIATextField']");
	
	//Rewards
	public static final By iosRewardsButtonXpath=MobileBy.xpath("//*[@id='REWARDS']");
	public static final By iosRewardsHeaderXpath=MobileBy.xpath("//*[@class='UIAImage' and ./parent::*[@text='Chilis.MyRewardsDashboardView']]");
	public static final By iosDisplayedRewardCount=MobileBy.xpath("(//*[@text='Rewards']/*[@class='UIAStaticText'])[3]");
	public static final By iosClaimedRewardsXpath=MobileBy.xpath("//*[@text='REWARDS' and @class='UIAStaticText' and ./parent::*[@text='Claimed Rewards']]");
	public static final By iosrewardsImageXpath=MobileBy.xpath("//*[@class='UIAImage']");
	public static final By iostapToViewXpath=MobileBy.xpath("//*[@id='Tap to view']");
	public static final By iosRewardsNameXpath=MobileBy.xpath("(//*[@class='UIAStaticText' and @knownSuperClass='UILabel'])[1]");
	public static final By iosBackButtonXpath=MobileBy.xpath("//*[@id='Back']");
	//*[@text='1']
	
	
	//*[@text='Free Chips & Salsa OR Non-Alcoholic Beverage']
	
	
	
	  public static final By appAddToCart = MobileBy.xpath("//*[@text='ADD TO CART']");
	    public static final By appAddToOrder = MobileBy.xpath("//*[@text='ADD TO ORDER']");
	    public static final By appClickOrder = MobileBy.xpath("//*[@text='ORDER']");
	    public static final By appClickCheckout = MobileBy.xpath("//*[@text='CHECKOUT »']");
	    public static final By appSelectCarryOut = MobileBy.xpath("//*[@text='Carryout']");
	    public static final By appSelectCurbside = MobileBy.xpath("//*[@text='Curbside']");
	    public static final By appSelectDelivery = MobileBy.xpath("//*[@text='Delivery']");
	    public static final By appContinuePayment = MobileBy.xpath("//*[@text='CONTINUE TO PAYMENT']");
	    public static final By appCardNuber = MobileBy.xpath("//*[@id='card-number']");
	    public static final By appCVV = MobileBy.xpath("//*[@id='cvv']");
	    public static final By appNameOnCard = MobileBy.xpath("//*[@id='nameOnCard']");
	    public static final By appZipcode = MobileBy.xpath("//*[@id='zipcode']");
	    public static final By appPlaceOrder = MobileBy.xpath("//*[@text='PLACE ORDER']");
	    public static final By appDeliveryAddress = MobileBy.xpath("//*[@id='autocomplete']");
		public static final By appAptNo = MobileBy.xpath("//*[@id='suite-no']");
		public static final By appDelDate = MobileBy.xpath("//*[@id='delivery-date']");
		public static final By appFirstName = MobileBy.xpath("//*[@id='first-name']");
		public static final By appLastName = MobileBy.xpath("//*[@id='last-name']");
		public static final By appContactNumber = MobileBy.xpath("//*[@id='contact-phone']");
		public static final By appeMail = MobileBy.xpath("//*[@id='email']");
		public static final By appCardNo = MobileBy.xpath("//*[@id='card-number']");
		

		
		//Delivery
	 

		
	
}












