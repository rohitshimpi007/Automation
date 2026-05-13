package rahulsehttyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractsComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	WebDriver driver;
	public CheckoutPage (WebDriver driver) {

		// initialization Constructor- first call
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry (String countryName) {
		
		Actions a= new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		
		waitForElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
		
	}
	
	public ConfirmationPage  submitOrder() {
		
		WebElement submitBtn = driver.findElement(By.cssSelector(".action__submit"));
		 ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
		 submitBtn);
		
	//	submit.click();
		return new ConfirmationPage(driver);
		
	}
	
	

}
