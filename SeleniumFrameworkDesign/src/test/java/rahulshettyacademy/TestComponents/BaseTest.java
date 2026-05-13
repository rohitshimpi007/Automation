package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulsehttyacademy.pageobjects.LandingPage;

public class BaseTest {

	// Thread-safe WebDriver
	protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public LandingPage landingpage;

	public WebDriver getDriver() {
		return driver.get();
	}

	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/rahulshettyacademy/resources/GlobalData.properties");
		prop.load(fis);

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		// prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
            if (browserName.contains("headless")) {
            	
            	options.addArguments("headless");
            }
			
			/*
			 * options.addArguments("--remote-allow-origins=*");
			 * options.addArguments("--disable-dev-shm-usage");
			 * options.addArguments("--no-sandbox"); options.addArguments("--disable-gpu");
			 * options.addArguments("--disable-extensions")
			 */;

			driver.set(new ChromeDriver(options));
		//	driver.manage().window().setSize(new Dimension(1440,900));
			
	
		} 
		
		else if (browserName.equalsIgnoreCase("firefox")) {
			
			//System.setProperty("webdriver.gecko.driver", "C:\\\\drivers\\\\geckodriver.exe\")
			
			//driver = new FirefoxDriver();
		}
		
		
		else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
		} else {
			throw new RuntimeException("Unsupported browser: " + browserName);
		}

		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().manage().window().maximize();

		return getDriver();
	}

	public List<HashMap<String, String>> getJsonDataToMap(String FilePath) throws IOException {

		// Read Json To String
		String jsonContent = FileUtils.readFileToString(new File(FilePath), StandardCharsets.UTF_8);

		// String to Hashmap Jakson Databind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;

	}

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
		File destination = new File(path);
		FileUtils.copyFile(source, destination);

		return path;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		initializeDriver();
		landingpage = new LandingPage(getDriver());
		landingpage.goTo();
		return landingpage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		getDriver().quit();
		driver.remove(); // Clean up thread-local memory
	}
}
