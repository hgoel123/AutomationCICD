package rahulshettyacademy.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.abstractComponents;

public class CheckOutPage extends abstractComponents{

	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		}

	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	private WebElement selectSpecificCountry;
	
	@FindBy(css=".action__submit")
	private WebElement submit;
	
	@FindBy(css="[placeholder='Select Country']")
	private WebElement country;
	
	//By results = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) throws InterruptedException
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		//waitForElementToAppear(By.cssSelector(".ta-results"));//waiting for results to appeared on dropdown
		Thread.sleep(2000);
		selectSpecificCountry.click();//click 2nd item
		scrollPage();
				
	}
	
	public ConfirmationPage submitOrder()
	{
			scrollPage();
			submit.click();//Click place order
			return new ConfirmationPage(driver);
	}
			
}
