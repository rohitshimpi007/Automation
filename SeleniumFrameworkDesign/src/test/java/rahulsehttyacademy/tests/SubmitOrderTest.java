package rahulsehttyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulsehttyacademy.pageobjects.CartPage;
import rahulsehttyacademy.pageobjects.CheckoutPage;
import rahulsehttyacademy.pageobjects.ConfirmationPage;
import rahulsehttyacademy.pageobjects.LandingPage;
import rahulsehttyacademy.pageobjects.OrderPage;
import rahulsehttyacademy.pageobjects.ProdctCatalogue;
import rahulshettyacademy.TestComponents.BaseTest;

public class SubmitOrderTest<TakeScreenshot> extends BaseTest {
	
	String productName = "ADIDAS ORIGINAL";

	@Test (dataProvider="getData", groups= {"Purchase"})
	public  void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException
	{

		
		ProdctCatalogue prodctCatalogue = landingpage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = prodctCatalogue.getProductlist();
		prodctCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = prodctCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckOut();
		checkoutPage.selectCountry("india");
    	ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationPage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
	}
	
	@Test (dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() {
		
		ProdctCatalogue prodctCatalogue = landingpage.loginApplication("shimpirohit077@gmail.com", "Rohit@123");
		OrderPage ordersPage =prodctCatalogue.goToOrderPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}
	
	

	@DataProvider
	public Object[][] getData() throws IOException {
		

	
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "/src/test/java/rahulshettyacademy/data/PurchaseOrder.json");
	    return new Object[][] { {data.get(0)}, {data.get(1)} };
	
	}
	
	
	
	//@DataProvider
	//public Object[][] getData() {
	
     //return new	Object[][] {"shimpirohit077@gmail.com","Rohit@123","ADIDAS ORIGINAL"},
	                      //   {"rohitshimpi077@gmail.com","Rohit@123","ZARA COAT 3"}
	
	//}
	//	HashMap<String,String> map = new HashMap<String,String>();
	//	map.put("email", "shimpirohit077@gmail.com");
	//	map.put("password", "Rohit@123");
	//	map.put("productName", "ADIDAS ORIGINAL");
		
	//	HashMap<String,String> map1 = new HashMap<String,String>();
	//	map1.put("email", "rohitshimpi077@gmail.com");
	//	map1.put("password", "Rohit@123");
	//	map1.put("productName","ZARA COAT 3");

}
