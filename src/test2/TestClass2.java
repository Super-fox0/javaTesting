package test2;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.base.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TestClass2 
{
	private LoginPage loginPage;
	private LoginActual loginActual;
	private WebDriver webDriver = new ChromeDriver();
	
	//Wait<WebDriver> wait;
	
	
	private ExtentReports report;
	private ExtentTest test;
	private String reportFilePath = "test.html";
	
	private SpreadSheetReader reader1;
	//private SpreadSheetExample example1;
	
	ArrayList<String> spreadData = new ArrayList<String>();
	
	public void setUp()
	{
		report = new ExtentReports();
		ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(reportFilePath);
		extentHtmlReporter.config().setChartVisibilityOnOpen(true);
		extentHtmlReporter.config().setReportName("ReportName");
        extentHtmlReporter.config().setDocumentTitle("DocumentTitle");
      // extentHtmlReporter.config().setTheme(reportDetails.getTheme());
        report.attachReporter(extentHtmlReporter);
        test = report.createTest("TestName");
      // return extentHtmlReporter;
	}
	
	@BeforeClass
	public static void beforeClass()
	{
		System.out.println("before class");
	}
	
	@Before
	public void before()
	{
		System.out.println("before");
		loginPage = PageFactory.initElements(webDriver, LoginPage.class);
		loginActual = PageFactory.initElements(webDriver, LoginActual.class);
		setUp();
		// wait = new FluentWait<WebDriver>(webDriver);
		
		reader1 = new SpreadSheetReader("C:\\Users\\Administrator\\workspace\\test2\\spread1.xlsx");
		//example1 = new SpreadSheetExample();
	}
	
	@Test
	public void test()
	{
		System.out.println("test");		
		
		webDriver.navigate().to("http://thedemosite.co.uk");
		loginPage.gotoPage();
		loginPage.enterUsername("abcde");
		loginPage.enterPassword("12345");
		wait1("input[type=\"button\"]");
		loginPage.submit();
		
		loginPage.changePage();
		
		loginActual.inputUsername("abcde");
		loginActual.inputPassword("12345");
		loginActual.submit();
		
		assertEquals("**Successful Login**", loginActual.check());
		
		take(webDriver, "screen1");
		
		test.log(Status.PASS, "info level");
		test.pass("Successful Login test performed");
					
		
		spreadData.addAll(reader1.readRow(0, "Input Data")); 
		spreadData.addAll(reader1.readRow(1, "Input Data")); 
		
		for(String x : spreadData)
		{
			System.out.println(x);
		}
		
		
		webDriver.quit();
		
		
		
	}
	
	@After
	public void after()
	{
		System.out.println("after");	
		report.flush();
	}
	
	@AfterClass
	public static void afterClass()
	{
		System.out.println("After class");
	}
	
	public void wait1(String css)
	{
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver) //global - instant in before
				.withTimeout(10, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		
		@SuppressWarnings("unused")
		WebElement foo = wait.until (new Function<WebDriver, WebElement>()    //
				{
					public WebElement apply(WebDriver driver)
					{
						return driver.findElement(By.cssSelector(css));
					}
				}
			);
		
		
	}
	
	public static String take(WebDriver webDriver, String fileName)  
	 {
		 try
		 {
			 
	        File scrFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
	        String pathname = System.getProperty("user.dir") + File.separatorChar + fileName +".jpg";
	        FileUtils.copyFile(scrFile, new File(pathname));
	        
	        System.out.println("File Saved at: " + pathname);
	        return pathname;
		 }
		 
		 catch(IOException ioe)
		 {
			ioe.printStackTrace();
		 }
		 return "";
	 }
	
	
	
	
	
	
	
}
