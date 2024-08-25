package rahulshettyacademy.Test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.ProductCataloguePage;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups= {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException
	{	
		String productName = "ZARA COAT 3";
		String countryName = "india";
		
		//LandingPage landingpage = launchApplication();
		ProductCataloguePage productCatalogue=landingPage.loginApplication("himanshu.shop@gmail.com","#Deepak123#");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException
	{	
		String productName = "ZARA COAT 3";
		String countryName = "india";
		
		//LandingPage landingpage = launchApplication();
		ProductCataloguePage productCatalogue=landingPage.loginApplication("himanshu.shop@gmail.com","#Deepak1234#");
		
		//2nd page- cart page
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		//3rd Page -- Verify Product Display Page
		Boolean match= cartPage.verifyProductDisplay("ZARA COAT 33");
		//asserting that the desired product is displayed in cart section or not
		Assert.assertFalse(match);
	}
}
