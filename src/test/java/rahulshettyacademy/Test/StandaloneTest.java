package rahulshettyacademy.Test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageObjects.LandingPage;

public class StandaloneTest {

	public static void main(String[] args) {
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//1st page
		LandingPage loginPage = new LandingPage(driver);
		loginPage.goTo();
		loginPage.loginApplication("himanshu.shop@gmail.com","#Deepak1234#");
		
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(4));
		// waiting for all product to get visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		
		//2nd page 
		//Stored all products into a List on the basis of common class name for all products
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		//Applying filter for the product which we want to add into the cart from all products
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		//click add to cart button for desired product 
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		//ng-animating class for waiting element
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//Click on Cart button
		driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();
		
		//Collect all products added to cart in a list
		List<WebElement>  cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		
		//asserting that the desired product is displayed in cart section or not
		Assert.assertTrue(match); 
		
		//click on checkout
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		
		//payment page
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		//waiting for results to appeared on dropdown
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		//click 2nd item
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,700)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit")));

		//Click place order
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.close();
		
		
		
		
	}

}
