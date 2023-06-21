package pages;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;

import base.Base;

public class ProductsListPage extends Base
{
    By heading=By.cssSelector("._10Ermr span");
    By lowtohigh=By.xpath("//div[text()='Price -- Low to High']");
    By prices=By.cssSelector("._30jeq3");
    By products=By.cssSelector("._4rR01T");
     
	public String getTitle()
	{
		String title=driver.getTitle();
		return title;
	}
	public String getHeading()
	{
		String str=driver.findElement(heading).getText();
		return str;	
	}
	public void clickLowToHigh()
	{
		driver.findElement(lowtohigh).click();
		try {Thread.sleep(2000);}catch(Exception e) {}
	}
	public List<Integer> getPrices()
	{
		List<Integer> actprices= driver.findElements(prices).stream().map(w->Integer.parseInt(w.getText().substring(1).replace(",",""))).collect(Collectors.toList());
		return actprices;
	}
	
	public String[] clickOneProduct(int index)
	{
		String first[]=new String[2];
		first[0]=driver.findElements(products).get(index).getText();
		first[1]=driver.findElements(prices).get(index).getText();
		driver.findElements(products).get(index).click();
		try {Thread.sleep(2000);}catch(Exception e) {}
		return first;	
	}	
}
