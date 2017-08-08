package test2;

import java.util.concurrent.TimeUnit;

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

import com.google.common.base.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class TestClass2 
{
	private LoginPage loginPage;
	private LoginActual loginActual;
	private WebDriver webDriver = new ChromeDriver();
	
	//Wait<WebDriver> wait;
	
	
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
		
		// wait = new FluentWait<WebDriver>(webDriver);
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
		
		
		
		
	}
	
	@After
	public void after()
	{
		System.out.println("after");	
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
		
		WebElement foo = wait.until (new Function<WebDriver, WebElement>()    //
				{
					public WebElement apply(WebDriver driver)
					{
						return driver.findElement(By.cssSelector(css));
					}
				}
			);
		
		
	}
	
	
	
	
	
	
	
	
}
