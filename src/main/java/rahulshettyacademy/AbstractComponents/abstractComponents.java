package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.OrderPage;

public class abstractComponents {
	WebDriver driver;

	public abstractComponents(WebDriver driver)
	{
		this.driver = driver;
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cardHeader;
	
	@FindBy(css="[routerlink*='myorder']")
	WebElement orderHeader;
	
	public void waitForElementToAppear(By findBy)
	{
	
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	// waiting for all product to get visible
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

	}
	public void waitForWebElementToAppear(WebElement findBy)
	{
	
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(findBy));

	}
	
	public void waitForElementToDisAppear(WebElement ele) throws InterruptedException
	{
	//4 seconds - due to practice page load
		Thread.sleep(2000);
//	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(4));
//	wait.until(ExpectedConditions.invisibilityOf(ele));

	}
	
	public void scrollPage()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,700)");
	}
	
	public CartPage goToCartPage()
	{
		cardHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	public OrderPage goToOrdersPage()
	{
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
}
