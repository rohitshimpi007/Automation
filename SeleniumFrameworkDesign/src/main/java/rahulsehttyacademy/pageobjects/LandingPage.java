package rahulsehttyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractsComponents.AbstractComponent;

public class LandingPage extends AbstractComponent  { 
	
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		
		super (driver); //all class constructor la takycha parent sodun
		//initialization    Constructor- first call 
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//PageFactory
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement PasswordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
       public ProdctCatalogue loginApplication (String email,String password) {
    	   
    	  //ActionMethod PageFactory Elements
		
		userEmail.sendKeys(email);
		PasswordEle.sendKeys(password);
		submit.click();
		ProdctCatalogue prodctCatalogue = new ProdctCatalogue(driver);
		return prodctCatalogue;
	
       }  
       
       public void goTo() {
    	   
    	   driver.get("https://rahulshettyacademy.com/client");
       }
       
   public String ErrorMessage() {
    	   
	   waitForWebElementToAppear(errorMessage);
	 return errorMessage.getText();
	   
       }
       

}
