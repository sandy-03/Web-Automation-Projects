package org.Utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class ValidationMethods {
	private static Actions action ;
	 
	
	public String beforeChange(WebElement element) {
		 String text= element.getAttribute("value");
		 return text;
		}
	
	public String afterChange(RemoteWebDriver driver, WebElement element, String amount) {
		 action = new Actions(driver);
		action.doubleClick(element).sendKeys(amount).perform();
		return element.getText();
	
	}
	public void UICheckTextBox(RemoteWebDriver driver,WebElement element,String value ) throws InterruptedException {
		String before = beforeChange(element);
		String after=afterChange(driver,element,value);
		Assert.assertNotEquals(before,after);
		System.out.println("Value Changed");
		TimeUnit.SECONDS.sleep(2);
	}
	
	public String beforeChangeScale(WebElement element) {
		String position =element.getAttribute("style");
		return position ;
		
	}
	public String afterChangeScale(RemoteWebDriver driver,WebElement element,int x,int y) {
		action = new Actions(driver);
		action.dragAndDropBy(element,x,y).build().perform();
		String position = element.getAttribute("style");
		return position ;
		
	}
	
	public void UICheckScale(RemoteWebDriver driver,WebElement element,int x,int y) {
		String beforePosition = beforeChangeScale(element);
		String afterPosition = afterChangeScale(driver,element,x,y);
		Assert.assertNotEquals(beforePosition,afterPosition);
		
	}
	public void durationCheck(WebElement element,WebElement element2,RemoteWebDriver driver,String value) {
		element.click();
		String before = element.getAttribute("value");
		action = new Actions(driver);
		action.doubleClick(element2).sendKeys(value).perform();
		String after = element.getText();
		Assert.assertNotSame(before, after );
	}
	


}
