package rahulsehttyacademy.tests;

import java.io.IOException;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.Retry; 


import rahulshettyacademy.TestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest {

	@Test (groups= {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void submitOrder() throws InterruptedException, IOException {

		String productName = "ADIDAS ORIGINAL";

		landingpage.loginApplication("shimpirohit077@gmail.com", "Rohit123");

		AssertJUnit.assertEquals("Incorrect email or password.", landingpage.ErrorMessage());

	}
}
