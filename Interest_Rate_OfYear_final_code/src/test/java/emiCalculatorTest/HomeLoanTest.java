package emiCalculatorTest;

import java.io.IOException;
import org.testng.annotations.Test;
import emiCalculatorPages.HomeLoanPage;

public class HomeLoanTest  {
	
	
	@Test(dependsOnMethods = {"emiCalculatorTest.CarEmiTest.carEMI"})
	public void homeLoan() throws IOException, InterruptedException {
	HomeLoanPage hlp = new HomeLoanPage();
	
	hlp.enterHomePrice("40,00,000");
	hlp.getYearWiseTable();
	hlp.clickOnCalculateMenu();
	hlp.clickOnLoanCalculator();
	
	}
	 
	
	
	

}
