package test2;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage 
{


	
	
	@FindBy (css= "input")
	private WebElement usernameInput;
	
	@FindBy (css= "input[type=\"password\"]")
	private WebElement passwordInput;
	
	@FindBy (css= " input[type=\"button\"]")
	private WebElement submitButton;
	
	@FindBy (css= "tbody > tr > td:nth-child(2) > p > small > a:nth-child(7)")
	private WebElement switchPage;
	
	@FindBy (css= "body > div > center > table > tbody > tr:nth-child(2) > td > div > center > table > tbody > tr > td:nth-child(2) > p > small > a:nth-child(6)")
	private WebElement gotoPage;
	
	
	public void gotoPage()
	{
		gotoPage.click();
	}

	public void enterUsername(String username)
	{
		usernameInput.sendKeys(username);
	}
	
	public void enterPassword(String password)
	{
		passwordInput.sendKeys(password);
	}
	
	public void submit()
	{
		submitButton.click();
	}
	
	public void changePage()
	{
		switchPage.click();
	}
	
	
	
	
}
