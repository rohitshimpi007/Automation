package rahulsehttyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractsComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	
	
	WebDriver driver;
	public ConfirmationPage (WebDriver driver) {

		// initialization Constructor- first call
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	@FindBy(css=".hero-primary")
	WebElement confirmationMassage;
	
	public String getConfirmationPage() {
		
		return confirmationMassage.getText();
		
	}

}
