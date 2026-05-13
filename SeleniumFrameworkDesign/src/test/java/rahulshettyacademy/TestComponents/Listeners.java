package rahulshettyacademy.TestComponents;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import rahulshettyacademy.resources.ExtentReporterNG;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {

    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReportObject();

    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // Thread-safe for parallel tests

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test); // uniq thread id(error validation test )-> Test
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
    }
    
    

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());
        

        String filePath = null;
        try {
            // Get WebDriver instance from the test class
            WebDriver driver = ((BaseTest) result.getInstance()).getDriver();
            
            // Capture screenshot
            filePath = getScreenShot(result.getMethod().getMethodName(), driver);

            // Attach screenshot to report
            extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

        } catch (IOException e) {
            extentTest.get().fail("Exception while capturing screenshot: " + e.getMessage());
        }
    }

    
    

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onStart(ITestContext context) {
        // Optional: log suite start
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // Write everything to report
    }
}
