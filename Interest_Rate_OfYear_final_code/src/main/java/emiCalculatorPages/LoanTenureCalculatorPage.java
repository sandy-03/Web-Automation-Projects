package emiCalculatorPages;

import java.io.IOException;

import org.Utils.ValidationMethods;

import org.Utils.XLUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import emiCalculator.Base.EMIBase;



public class LoanTenureCalculatorPage extends EMIBase {
	
	XLUtility xl = new XLUtility();
	ValidationMethods vm = new ValidationMethods();
	
	WebElement loanAmountTextBox = getDriver().findElement(By.xpath("//input[@id='loanamount']"));
	WebElement loanAmountSlider = getDriver().findElement(By.xpath("(//span[@tabindex='0'])[1]"));
	
	WebElement EmiTextBox = getDriver().findElement(By.xpath("//input[@id='loanemi']"));
	WebElement EMISlider = getDriver().findElement(By.xpath("(//span[@tabindex='0'])[2]"));

	WebElement interestRateTextBox = getDriver().findElement(By.xpath("//input[@id='loaninterest']"));
	WebElement interestRateSlider = getDriver().findElement(By.xpath("(//span[@tabindex='0'])[3]"));

	WebElement feesTextBox = getDriver().findElement(By.xpath("//input[@id='loanfees']"));
	WebElement feesSlider = getDriver().findElement(By.xpath("(//span[@tabindex='0'])[5]"));
	
	
	
	public void LoanAmountUICheck(String value,int x,int y) throws InterruptedException, IOException {
		xl.screenShot(this.getClass().getSimpleName(),getDriver());
		vm.UICheckTextBox(getDriver(),loanAmountTextBox,value);
		vm.UICheckScale(getDriver(), loanAmountSlider,x,y);
	}
	
	public void EMIAmountUICheck(String value,int x,int y) throws InterruptedException {
		xl.scrollTo(EmiTextBox,getDriver());
		vm.UICheckTextBox(getDriver(),EmiTextBox,value);
		vm.UICheckScale(getDriver(),EMISlider,x,y);
		
	}
	
	public void InterestRateUICheck(String value,int x,int y) throws InterruptedException {
		xl.scrollTo(interestRateTextBox,getDriver());
		vm.UICheckTextBox(getDriver(),interestRateTextBox,value);
		vm.UICheckScale(getDriver(), interestRateSlider,x,y);
		
	}
	
	public void feesChargeUICheck(String value,int x,int y) throws InterruptedException {
		xl.scrollTo(feesTextBox,getDriver());
		String before = feesTextBox.getAttribute("value");
		feesTextBox.clear();
		feesTextBox.sendKeys(Keys.BACK_SPACE,value);
		String after = feesTextBox.getAttribute("value");
		Assert.assertNotEquals( before, after);
		System.out.println("value changed");
		vm.UICheckScale(getDriver(), feesSlider,x,y);
		
	}


}
