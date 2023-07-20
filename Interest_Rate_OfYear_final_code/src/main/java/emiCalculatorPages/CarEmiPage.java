package emiCalculatorPages;



import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.Utils.XLUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import emiCalculator.Base.EMIBase;



public class CarEmiPage extends EMIBase {
	
	XLUtility xl = new XLUtility();



	By carLoanCalculator = By.xpath("//li[@id='car-loan']");
	By loanAmountField = By.xpath("//input[@id='loanamount']");
	By interestRateField = By.xpath("//input[@id='loaninterest']");
	By loanTenureField = By.xpath("//input[@id='loanterm']");
	By EMIType = By.xpath("(//label[@class='btn btn-secondary active'])[2]");
	By yearWisebutton = By.xpath("//td[@id='year2023']");
	By perMonth = By.xpath("(//tr[@class='row no-margin'])[2]");
//	By homeLoanCalculator = By.xpath("//li[@id=\"home-loan\"]");
	By calculatorMenu = By.xpath("//a[@title=\"Calculators\"]");
	By homeLoanEMI = By.xpath("//li[@id='menu-item-3294']");

	public CarEmiPage clickOnCarLoanCalculator() {
		getDriver().findElement(carLoanCalculator).click();
		return this;
	}

	public CarEmiPage enterLoanAmount(String amount) throws InterruptedException, IOException {
		xl.screenShot(this.getClass().getSimpleName(),getDriver());
		WebElement loanAmount = getDriver().findElement(loanAmountField);
		loanAmount.clear();
		TimeUnit.SECONDS.sleep(2);
		loanAmount.sendKeys(Keys.BACK_SPACE,amount);
		return this;
	}

	public CarEmiPage enterInterestRate(String rate) throws InterruptedException {
		WebElement interestRate = getDriver().findElement(interestRateField);
		interestRate.clear();
		TimeUnit.SECONDS.sleep(2);
		interestRate.sendKeys(Keys.BACK_SPACE,rate);
		return this;
	}

	public CarEmiPage enterLoanTenure(String years) throws InterruptedException {
		WebElement loanTenure = getDriver().findElement(loanTenureField);
		loanTenure.clear();
		TimeUnit.SECONDS.sleep(2);
		loanTenure.sendKeys(Keys.BACK_SPACE,years);
		return this;
	}

	public CarEmiPage clickOnEMIType() {
		getDriver().findElement(EMIType).click();
		return this;
	}
	public CarEmiPage cickOn2023() {
		WebElement yearWise = getDriver().findElement(yearWisebutton);
//		JavascriptExecutor js =(JavascriptExecutor) getDriver();
//		js.executeScript("arguments[0].scrollIntoView(true)",yearWise);
		yearWise.click();
		return this;		
	}

	public CarEmiPage getPerMonthRate() {
		WebElement rate = getDriver().findElement(perMonth);
/*		Jun ₹ 1,19,650 ₹ 11,875 ₹ 1,31,525 ₹ 13,80,350 7.98%*/
		String[] value = rate.getText().split(" ");
		System.err.println("The Principal Amount is "+value[2]+" and the Interest is "+value[4]);
		System.out.println("Principal :"+value[2]);
		System.out.println("Interest :"+value[4]);
		return this;
	}
	/*
	 * public HomeLoanPage clickOnHomeLoan() {
	 * getDriver().findElement(homeLoanCalculator).click(); return new HomeLoanPage(); }
	 */
	
	public CarEmiPage clickOnCalculateMenu() {
		getDriver().findElement(calculatorMenu).click();
		return this;
	}
	public HomeLoanPage clickOnHomeLoan() {
		getDriver().findElement(homeLoanEMI).click();
		try {
		new Actions(getDriver()).moveByOffset(70, 100).click().perform();
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return new HomeLoanPage();
	}

	

}
