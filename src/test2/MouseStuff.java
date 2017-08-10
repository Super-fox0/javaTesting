package test2;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MouseStuff 
{
	
	

	@FindBy (css= "#menu-item-140 > a")
	private WebElement dragButton;
	
//	@FindBy (id = "ui-id-3")
//	private WebElement displayGrid;
	
	@FindBy (id= "draggable")
	public WebElement dragThing;	

	public void clickDrag()
	{
		dragButton.click();
	}
	
	public WebElement giveElement()
	{
		return dragButton;
	}
	
	
	@FindBy (css = "#menu-item-151 > a")
	public WebElement sortableButton;
	
	@FindBy (xpath = "//*[@id=\"sortable\"]/li[2]")
	public WebElement item1;
	
	public void clickSortButton()
	{
		sortableButton.click();
	}
	
	
	
	
}
