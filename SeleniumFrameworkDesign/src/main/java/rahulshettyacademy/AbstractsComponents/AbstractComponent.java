package rahulshettyacademy.AbstractsComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulsehttyacademy.pageobjects.CartPage;
import rahulsehttyacademy.pageobjects.OrderPage;

public class AbstractComponent {

    WebDriver driver;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[routerLink*='cart']")
    WebElement cartHeader;

    @FindBy(css = "[routerLink*='myorders']")
    WebElement orderHeader;

    public void waitForElementToAppear(By findby) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
    }

    public void waitForWebElementToAppear(WebElement findby) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(findby));
    }

    public CartPage goToCartPage() {
        cartHeader.click();
        return new CartPage(driver);
    }

    public OrderPage goToOrderPage() {
        orderHeader.click();
        return new OrderPage(driver);
    }

    public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
        Thread.sleep(1000);
        // Consider replacing with: 
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // wait.until(ExpectedConditions.invisibilityOf(ele));
    }
}
