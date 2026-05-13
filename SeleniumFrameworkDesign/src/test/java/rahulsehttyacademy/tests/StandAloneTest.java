package rahulsehttyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulsehttyacademy.pageobjects.CartPage;
import rahulsehttyacademy.pageobjects.CheckoutPage;
import rahulsehttyacademy.pageobjects.ConfirmationPage;
import rahulsehttyacademy.pageobjects.LandingPage;
import rahulsehttyacademy.pageobjects.ProdctCatalogue;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {

		String productName = "ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		// driver.get("https://rahulshettyacademy.com/client");

		LandingPage landingpage = new LandingPage(driver);
		landingpage.goTo();
		ProdctCatalogue prodctCatalogue = landingpage.loginApplication("shimpirohit077@gmail.com", "Rohit@123");

		List<WebElement> products = prodctCatalogue.getProductlist();
		prodctCatalogue.addProductToCart(productName);
		CartPage cartPage = prodctCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.goToCheckOut();
		checkoutPage.selectCountry("india");
    	ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationPage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();

		
		
		// driver.findElement(By.id("userEmail")).sendKeys("shimpirohit077@gmail.com");
		// driver.findElement(By.id("userPassword")).sendKeys("Rohit@123");
		// driver.findElement(By.id("login")).click();

		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		// WebElement prod = products.stream()
		// .filter(product ->
		// product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
		// .orElse(null);
		// prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// ng-animating loading element

		// wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ngx-spinner-overlay"))));
		// driver.findElement(By.cssSelector("[routerLink*='cart']")).click();

		// List<WebElement> cartProducts =
		// driver.findElements(By.cssSelector(".cartSection h3"));
		// Boolean match = cartProducts.stream()
		// .anyMatch(cartProdcut-> cartProdcut.getText().equalsIgnoreCase(productName));
		// Assert.assertTrue(match);
		// driver.findElement(By.cssSelector(".totalRow button")).click();

		// Actions a = new Actions(driver);
		// a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select
		// Country']")), "india").build().perform();

		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		// WebElement countryOption =
		// driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]"));
		// ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
		// countryOption); // 👈 Fix for ElementClickInterceptedException

		// WebElement submitBtn = driver.findElement(By.cssSelector(".action__submit"));
		// ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
		// submitBtn);

		// String confirmMessage =
		// driver.findElement(By.cssSelector(".hero-primary")).getText();
		// Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE
		// ORDER."));

		// driver.close();
	}

}
