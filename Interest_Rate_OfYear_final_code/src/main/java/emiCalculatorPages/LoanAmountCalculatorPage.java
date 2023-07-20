package emiCalculatorPages;

import java.io.IOException;

import org.Utils.ValidationMethods;

import org.Utils.XLUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import emiCalculator.Base.EMIBase;



public class LoanAmountCalculatorPage extends EMIBase {
	
	XLUtility xl = new XLUtility();
	ValidationMethods vm = new ValidationMethods();
	
	
	WebElement EMITextBox = getDriver().findElement(By.xpath("(//div[@class='input-group'])[2]/input"));
	WebElement EMISlider = getDriver().findElement(By.xpath("(//span[@tabindex='0'])[2]"));
	
	WebElement interestRateTextBox = getDriver().findElement(By.xpath("//input[@id='loaninterest']"));
	WebElement interestRateSlider = getDriver().findElement(By.xpath("(//span[@tabindex='0'])[3]"));

	WebElement loanTenureTextBox = getDriver().findElement(By.xpath("//input[@id='loanterm']"));
	WebElement loanTenureSlider = getDriver().findElement(By.xpath("(//span[@tabindex='0'])[4]"));
	WebElement yearField =getDriver().findElement(By.xpath("(//label[@class='btn btn-secondary'])[1]"));

	WebElement feesTextBox = getDriver().findElement(By.xpath("//input[@id='loanfees']"));
	WebElement feesSlider = getDriver().findElement(By.xpath("(//span[@tabindex='0'])[5]"));
	
	WebElement loanTenureCalculator = getDriver().findElement(By.xpath("(//li[@id='loan-tenure-calc']/a)[1]"));
	
	
	public void EMIAmountUICheck(String value,int x,int y) throws InterruptedException, IOException {
		xl.screenShot(this.getClass().getSimpleName(),getDriver());
		vm.UICheckTextBox(getDriver(),EMITextBox,value);
		vm.UICheckScale(getDriver(), EMISlider,x,y);
	}
	
	public void interestRateUICheck(String value,int x,int y) throws InterruptedException {
		xl.scrollTo(interestRateTextBox,getDriver());
		vm.UICheckTextBox(getDriver(),interestRateTextBox,value);
		vm.UICheckScale(getDriver(), interestRateSlider,x,y);
		
	}
	
	public void loanTenureUICheck(String value,String period,int x,int y) throws InterruptedException {
		xl.scrollTo(loanTenureTextBox,getDriver());
		vm.UICheckTextBox(getDriver(),loanTenureTextBox,value);
		vm.UICheckScale(getDriver(), loanTenureSlider,x,y);
		vm.durationCheck(yearField, loanTenureTextBox, getDriver(),period);
		
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
	public LoanTenureCalculatorPage clickOnLoanTenureCalculator() {
		loanTenureCalculator.click();
		return new LoanTenureCalculatorPage();
	}

}
