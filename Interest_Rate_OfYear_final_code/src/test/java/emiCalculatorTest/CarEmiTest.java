package emiCalculatorTest;

import java.io.IOException;
import org.testng.annotations.Test;

import emiCalculatorPages.CarEmiPage;

public class CarEmiTest {

	@Test(priority = 2)
	public void carEMI() throws InterruptedException, IOException {

		
		 
		new CarEmiPage()
		.clickOnCarLoanCalculator()
		.enterLoanAmount("15,00,000")
		.enterInterestRate("9.5")
		.enterLoanTenure("1")
		.clickOnEMIType()
		.cickOn2023()
		.getPerMonthRate()
		.clickOnCalculateMenu()
		.clickOnHomeLoan();
		
		
		


	}
}

