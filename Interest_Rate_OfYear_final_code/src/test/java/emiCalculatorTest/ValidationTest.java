package emiCalculatorTest;



import java.io.IOException;

import org.Utils.ExtentReportUtil;

import org.testng.annotations.Test;




import emiCalculatorPages.EMICalculatorPage;
import emiCalculatorPages.LoanAmountCalculatorPage;
import emiCalculatorPages.LoanTenureCalculatorPage;



public class ValidationTest extends ExtentReportUtil {
	
	@Test(dependsOnMethods = {"emiCalculatorTest.HomeLoanTest.homeLoan"})
	public void EMICalculator() throws InterruptedException, IOException {
		System.out.println("******EMICalculator Page******");
		EMICalculatorPage ep = new EMICalculatorPage();
		ep.LoanAmountUICheck("20,00,000",100,0);
		ep.interestRateUICheck("7.5", -30, 0);
		ep.loanTenureUICheck("10","120", 50, 0);
		ep.feesChargeUICheck("15,000", 60, 0);
		ep.clickOnLoanAmountCalculator();
		System.out.println("=========================");
		Thread.sleep(2000);
	}
	
	@Test(dependsOnMethods = "EMICalculator")
	public void loanAmountCalculator() throws InterruptedException, IOException {
		
		System.out.println("******LoanAmountCalculator Page******");
		LoanAmountCalculatorPage lp = new LoanAmountCalculatorPage();
		lp.EMIAmountUICheck("70,000", 60, 0);
		lp.interestRateUICheck("8.3", -20, 0);
		lp.loanTenureUICheck("150","15", 50, 0);
		lp.feesChargeUICheck("20,000", -40, 0);
		lp.clickOnLoanTenureCalculator();
		System.out.println("=========================");
	}
	@Test(dependsOnMethods = "loanAmountCalculator")
	public void loanTenureCalculator() throws InterruptedException, IOException {
		System.out.println("******LoanTenureCalculator Page******");
		LoanTenureCalculatorPage ltp = new LoanTenureCalculatorPage();
		ltp.LoanAmountUICheck("90,45,000", -50, 0);
		ltp.EMIAmountUICheck("85,000", -60, 0);
		ltp.InterestRateUICheck("6.6",-20, 0);
		ltp.feesChargeUICheck("17,000", 20, 0);
		System.out.println("=========================");
		
		
	}
	
}
