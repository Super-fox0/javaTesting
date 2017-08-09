package test2;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginActual 
{

	@FindBy (css= "  input")
	private WebElement userName;
	
	@FindBy (css= " input[type=\"password\"]")
	private WebElement passWord;
	
	@FindBy (css= " input[type=\"button\"]")
	private WebElement submit;
	
	@FindBy(css= " body > table > tbody > tr > td.auto-style1 > big > blockquote > blockquote > font > center > b")
	private WebElement successfulLogin;
	
	
	public void inputUsername(String username)
	{
		userName.sendKeys(username);
	}

	public void inputPassword(String password)
	{
		passWord.sendKeys(password);
	}
	
	public void submit()
	{
		submit.click();
	}
	
	public String check()
	{
		return successfulLogin.getText();
	}
	
	
	
}
