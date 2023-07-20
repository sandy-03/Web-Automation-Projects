package emiCalculatorPages;

import java.io.IOException;

import org.Utils.ValidationMethods;
import org.Utils.XLUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import emiCalculator.Base.EMIBase;


public class EMICalculatorPage extends EMIBase{
	

	XLUtility xl = new XLUtility();
	ValidationMethods vm = new ValidationMethods();
	
	WebElement loanAmount = getDriver().findElement(By.xpath("//input[@id='loanamount']"));
	WebElement interestRate =getDriver().findElement(By.xpath("//input[@id='loaninterest']"));
	WebElement loanTenure =getDriver().findElement(By.xpath("//input[@id='loanterm']"));
	WebElement feesCharge =getDriver().findElement(By.xpath("//input[@id='loanfees']"));
	WebElement loanAmountSlider =getDriver().findElement(By.xpath("(//span[@tabindex='0'])[1]"));
	WebElement interesrRateslider =getDriver().findElement(By.xpath("(//span[@tabindex='0'])[3]"));
	WebElement loanTenureSlider =getDriver().findElement(By.xpath("(//span[@tabindex='0'])[4]"));
	WebElement FeesChargeSlider =getDriver().findElement(By.xpath("(//span[@tabindex='0'])[5]"));
	WebElement monthField = getDriver().findElement(By.xpath("(//label[@class='btn btn-secondary'])[1]"));
	WebElement loanAmountCalculator = getDriver().findElement(By.xpath("(//li[@id='loan-amount-calc']/a)[1] "));
	
	

	
	public void LoanAmountUICheck(String value,int x,int y) throws InterruptedException, IOException {
		xl.screenShot(this.getClass().getSimpleName(),getDriver());
		vm.UICheckTextBox(getDriver(),loanAmount,value);
		vm.UICheckScale(getDriver(), loanAmountSlider,x,y);
	}
	
	public void interestRateUICheck(String value,int x,int y) throws InterruptedException {
		xl.scrollTo(interestRate,getDriver());
		vm.UICheckTextBox(getDriver(),interestRate,value);
		vm.UICheckScale(getDriver(), interesrRateslider,x,y);
		
	}
	
	public void loanTenureUICheck(String value,String month,int x,int y) throws InterruptedException {
		xl.scrollTo(loanTenure,getDriver());
		vm.UICheckTextBox(getDriver(),loanTenure,value);
		vm.UICheckScale(getDriver(), loanTenureSlider,x,y);
		vm.durationCheck(monthField, loanTenure, getDriver(), month);
		
	}
	
	public void feesChargeUICheck(String value,int x,int y) throws InterruptedException {
		xl.scrollTo(feesCharge,getDriver());
		String before = feesCharge.getAttribute("value");
		feesCharge.clear();
		feesCharge.sendKeys(Keys.BACK_SPACE,value);
		String after = feesCharge.getAttribute("value");
		Assert.assertNotEquals( before, after);
		System.out.println("value changed");
		vm.UICheckScale(getDriver(), FeesChargeSlider,x,y);
		
	}
	public LoanAmountCalculatorPage clickOnLoanAmountCalculator() {
		loanAmountCalculator.click();
		return new LoanAmountCalculatorPage();
	}

	
}

