package functionalTesting;

import java.util.List;
import java.util.stream.Collectors;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.ProductsListPage;
import pages.SingleProductPage;

public class FlipkartTesting 
{

	String valuetoSearch="mobiles";
	
	@Test(priority=1)
	public void verifyTitleandHeading()
	{		
		
		HomePage homepage=new HomePage();
		ProductsListPage productslistpage=new ProductsListPage();
		homepage.openUrl("http://www.flipkart.com");
		homepage.closeLogin();
		homepage.search(valuetoSearch);
		String title=productslistpage.getTitle().toLowerCase();
		String heading=productslistpage.getHeading().toLowerCase();
		
		System.out.println(title+"   "+heading);
		if(title.contains(valuetoSearch) && heading.matches(valuetoSearch))
			Reporter.log("<font color='green'><b>Title and heading are as expected</b></font>");
		else
			Reporter.log("<font color='red'><b>Title or heading is not as expected</b></font>");
		
			
	}
	@Test(priority=2)
	public void verifyPricesSorted()
	{
				
		HomePage homepage=new HomePage();
		ProductsListPage productslistpage=new ProductsListPage();
		
		homepage.openUrl("http://www.flipkart.com");
		homepage.closeLogin();
		homepage.search(valuetoSearch);
		productslistpage.clickLowToHigh();
		List<Integer> actprices= productslistpage.getPrices();
		List<Integer> expprices=actprices.stream().sorted().collect(Collectors.toList());
		System.out.println(actprices);
		System.out.println(expprices);
		if(actprices.equals(expprices))
			System.out.println("All the prices are in sorting order as expected");
		else
			System.out.println("Prices are not in sorting order as expected");
		
	}
	@Test(priority=3)
	public void verifyProductNameandPrice()
	{
		HomePage homepage=new HomePage();
		ProductsListPage productslistpage=new ProductsListPage();
		SingleProductPage singleproductpage=new SingleProductPage();	
		
		homepage.openUrl("http://www.flipkart.com");
		homepage.closeLogin();
		homepage.search(valuetoSearch);
		String first[]=productslistpage.clickOneProduct(8);
		String second[]=singleproductpage.getProductdetails();
		if(second[0].contains(first[0])&& first[1].matches(second[1]))
			System.out.println("Product name, price matched as expected in both pages");
		else
			System.out.println("Product name or price not matched as expected");	
	}	
}
