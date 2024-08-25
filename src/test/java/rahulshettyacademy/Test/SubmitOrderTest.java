package rahulshettyacademy.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckOutPage;
import rahulshettyacademy.pageObjects.ConfirmationPage;
import rahulshettyacademy.pageObjects.OrderPage;
import rahulshettyacademy.pageObjects.ProductCataloguePage;

public class SubmitOrderTest extends BaseTest{

	//String productName = "ZARA COAT 3";

	@Test(dataProvider="getData" , groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{	
		String countryName = "india";
		
		//LandingPage landingpage = launchApplication();
		ProductCataloguePage productCatalogue=landingPage.loginApplication(input.get("email"),input.get("password"));
		
		//2nd page- cart page
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		
		//3rd Page -- Verify Product Display Page
		Boolean match= cartPage.verifyProductDisplay(input.get("productName"));
		//asserting that the desired product is displayed in cart section or not
		Assert.assertTrue(match);
		CheckOutPage checkout = cartPage.doCheckout();
		
		//4th page - payment page 
		
		checkout.selectCountry(countryName);
		ConfirmationPage confirmationpage = checkout.submitOrder();
		
		//5th confirmation page 
		String confirmationMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
				
	}

	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest(String productName)
	{
		//verify from orders page that specific order is displayed or not
		ProductCataloguePage productCatalogue=landingPage.loginApplication("shetty@gmail.com","Iamking@000");
		OrderPage orderPage =	productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{

		List<HashMap<String,String>> data = getJsonDataToHashMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\Data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
				
	}
	
	
//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "himanshu.shop@gmail.com");
//	map.put("password", "#Deepak1234#");
//	map.put("productName", "ZARA COAT 3");
//	
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email", "shetty@gmail.com");
//	map1.put("password", "Iamking@000");
//	map1.put("productName", "ADIDAS ORIGINAL");
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
