package pages;

import java.util.ArrayList;

import org.openqa.selenium.By;

import base.Base;

public class SingleProductPage extends Base 
{
	By productname=By.cssSelector(".B_NuCI");
	By price=By.cssSelector("._30jeq3");

	public String[] getProductdetails()
	{
	   String second[]=new String[2];
	   
	   driver.switchTo().window(new ArrayList<String>(driver.getWindowHandles()).get(1));	   
	   second[0]=driver.findElement(productname).getText();
	   second[1]=driver.findElement(price).getText();
	   
	   return second;  
	}	
}
