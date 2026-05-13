package rahulsehttyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractsComponents.AbstractComponent;

public class ProdctCatalogue extends AbstractComponent {

	WebDriver driver;

	public ProdctCatalogue(WebDriver driver) {

		// initialization Constructor- first call
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css = ".ngx-spinner-overlay")
	WebElement spinner;

	By prodcutsBy = By.cssSelector(".mb-3");
	By addToCart  = By.cssSelector(".card-body button:last-of-type");
	By tostMassage = By.cssSelector("#toast-container");

	public List<WebElement> getProductlist() {

		// Action Method For Product List
		waitForElementToAppear(prodcutsBy);
		return products;
	}

	public WebElement getProdcutBtName(String productName) {

		WebElement prod = getProductlist().stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;

	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		
	WebElement prod = getProdcutBtName(productName);
			prod.findElement(addToCart).click();
			waitForElementToAppear(tostMassage);
			waitForElementToDisappear(spinner);
		
	}

}
