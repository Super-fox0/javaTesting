package test2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestClass2 
{

	@BeforeClass
	public static void beforeClass()
	{
		System.out.println("before class");
	}
	
	@Before
	public void before()
	{
		System.out.println("before");
	}
	
	@Test
	public void test()
	{
		System.out.println("test");	
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
}
