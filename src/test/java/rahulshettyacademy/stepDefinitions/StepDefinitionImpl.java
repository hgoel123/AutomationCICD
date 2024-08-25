package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckOutPage;
import rahulshettyacademy.pageObjects.ConfirmationPage;
import rahulshettyacademy.pageObjects.LandingPage;
import rahulshettyacademy.pageObjects.ProductCataloguePage;

public class StepDefinitionImpl extends BaseTest{
	public LandingPage landingPage;
	public ProductCataloguePage productCatalogue;
	public ConfirmationPage confirmationpage;
	
	@Given("I landed on Ecommerce Home Page")
	public void I_landed_on_Ecommerce_Home_Page() throws IOException
	{
		landingPage= launchApplication();
	}
	
	@Given("^Logged in with Username (.+) and password (.+)$")
	public void logged_in_Username_and_password(String username, String password)
	{
		productCatalogue=landingPage.loginApplication(username,password);
		
	}
	
	@When("^I add product(.+) to cart$")
	public void i_add_product_to_cart(String productName) throws Exception
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	@When("^checkout (.+) and submit the order$")
    public void checkout_submit_Order(String productName) throws Exception
    {
		CartPage cartPage = productCatalogue.goToCartPage();
		
		//3rd Page -- Verify Product Display Page
		Boolean match= cartPage.verifyProductDisplay(productName);
		//asserting that the desired product is displayed in cart section or not
		Assert.assertTrue(match);
		CheckOutPage checkout = cartPage.doCheckout();
		checkout.selectCountry("india");
		confirmationpage = checkout.submitOrder();
    }
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_ConfirmationPage(String string)
	{
		String confirmationMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase(string));
		driver.close();
	}
	@Then("{string} error message is displayed on ConfirmationPage")
	public void error_message_displayed_ConfirmationPage(String string)
	{
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.close();
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
}
