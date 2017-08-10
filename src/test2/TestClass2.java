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
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
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
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;

public class TestClass2 
{
	private LoginPage loginPage;
	private LoginActual loginActual;
	private WebDriver webDriver = new ChromeDriver();
	//private WebDriver webDriver = new FirefoxDriver();
	
	private MouseStuff mouse;
	Actions builder = new Actions(webDriver);
	
	private static ExtentReports report;
	private ExtentTest test;
	private static String reportFilePath = "test.html";
	
	private static SpreadSheetReader reader1;
	ArrayList<String> spreadData = new ArrayList<String>();
	
	private String nameBuilder= "Java Test ";
	private static int testNo = 1;
	
	@BeforeClass
	public static void setUp()
	{
		report = new ExtentReports();
		ExtentHtmlReporter extentHtmlReporter = new ExtentHtmlReporter(reportFilePath);
		extentHtmlReporter.config().setChartVisibilityOnOpen(true);
		extentHtmlReporter.config().setReportName("ReportName");
        extentHtmlReporter.config().setDocumentTitle("DocumentTitle");
        
        report.attachReporter(extentHtmlReporter);
       
        reader1 = new SpreadSheetReader("C:\\Users\\Administrator\\workspace\\test2\\spread1.xlsx");
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
		mouse = PageFactory.initElements(webDriver,MouseStuff.class);
		
		test = report.createTest(nameBuilder + testNo);
		testNo++;
	}
	
	@Test
	public void test() throws IOException
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
		test.log(Status.INFO, "Login Test");
		test.pass("Successful Login test performed");
		test.addScreenCaptureFromPath("C:\\Users\\Administrator\\workspace\\test2\\screen1.jpg");
		
		webDriver.close();				
	}
	
	@Test
	public void test2() throws IOException
	{	
		webDriver.navigate().to("http://demoqa.com/");
		wait1("#menu-item-151 > a");
		mouse.clickDrag();	
		
		Point p = mouse.dragThing.getLocation(); 
		System.out.println(p.getX() + " " + p.getY());
		int xStart = p.getX();
		
		builder.dragAndDropBy(mouse.dragThing, 200, 200).perform();	
		p = mouse.dragThing.getLocation(); 
		System.out.println(p.getX() + " " + p.getY());
		int xFinish = p.getX();
		
		take(webDriver, "screen2");
		assertFalse(xStart == xFinish);
		test.log(Status.INFO, "Drag Test");
		test.pass("Successful Drag Test Performed");
		test.addScreenCaptureFromPath("C:\\Users\\Administrator\\workspace\\test2\\screen2.jpg");
		webDriver.close();
	}
	
	@Test
	public void test3()
	{
		spreadData.addAll(reader1.readRow(0, "Input Data")); 
		spreadData.addAll(reader1.readRow(1, "Input Data")); 	
		for(String x : spreadData)
		{
			System.out.println(x);
		}
		test.log(Status.INFO, "Spreadsheet Test");
		test.pass("Successful Spreadsheet test performed");
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
