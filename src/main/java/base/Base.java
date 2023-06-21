package base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base 
{
	public static WebDriver driver;	
	public static ExtentSparkReporter sparkreport;
	public static ExtentReports report;
	public static ExtentTest test;
	
	@BeforeSuite
	public void reportInitialize()
	{
		sparkreport=new ExtentSparkReporter("./Reports/flipkart.html");
		sparkreport.config().setReportName("Flipkart Functional Testing");
		
		report=new ExtentReports();
		report.attachReporter(sparkreport);
		report.setSystemInfo("ApplicationName","Flipkart.com");
		report.setSystemInfo("Tester","Rituraj Gaurav");
		report.setSystemInfo("Environment","TestEnv");
	}

	@BeforeTest
	@Parameters({"browser"})
	public void setUp(String br)
	{
		System.out.println("setup is called");
		if(br.matches("firefox"))
		{
			driver=new FirefoxDriver();
		}
		if(br.matches("chrome"))
		{
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--remote-allow-origins=*");        
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver(option);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	public void openUrl(String url)
	{
		driver.get(url);
	}
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
	
	@AfterSuite
	public void saveReport()
	{
		report.flush();  //save the report.
	}
}
