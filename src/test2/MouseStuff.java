package test2;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MouseStuff 
{
	
	

	@FindBy (css= "#menu-item-151 > a")
	private WebElement sortableButton;
	
	@FindBy (id = "ui-id-3")
	private WebElement displayGrid;
	
	@FindBy (css= "#sortable_grid > li:nth-child(1)")
	public WebElement tile1;	

	public void clickSortable()
	{
		sortableButton.click();
	}
	
	public void gridFormat()
	{
		displayGrid.click();
	}
	
	
}
