package emiCalculatorPages;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;

import org.Utils.XLUtility;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import emiCalculator.Base.EMIBase;



public class HomeLoanPage extends EMIBase {
	

	private XSSFSheet sh;
	private File fil;
	private XSSFWorkbook wb;
	private XLUtility xl = new XLUtility();


	By homePrice = By.name("homeprice");
	By yearTable = By.xpath("//table[@class='noextras']");
	By headOfTable = By.xpath("(//tr[@class=\"row no-margin\"])[1]");
	By noOfRows = By.xpath("//tr[@class=\"row no-margin yearlypaymentdetails\"]");
	By calculatorMenu = By.xpath("//a[@title=\"Calculators\"]");
	By loanCalculator = By.xpath("//li[@id='menu-item-2423']");

	public void enterHomePrice(String amount) throws IOException {
		WebElement price = getDriver().findElement(homePrice);
		price.clear();
		price.sendKeys(Keys.BACK_SPACE, amount, Keys.RETURN);
		xl.screenShot(this.getClass().getSimpleName(), getDriver());
	}

	public WebElement locateTable() throws IOException {
		WebElement table = getDriver().findElement(yearTable);
		xl.scrollTo(table,getDriver());
		return table;
	}
	
	public void screenShotOfTable() throws WebDriverException, IOException  {
		File src = locateTable().getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"\\ScreenShots\\Table.png");
		FileHandler.copy(src, dest);
	}
	
	public List<WebElement> locateHeaderField() throws IOException {
		WebElement head = locateTable().findElement(headOfTable);
		List<WebElement> headerfield = head.findElements(By.tagName("th"));
		return headerfield;
	}

	public void writeHeaderField() throws IOException {
		XSSFRow row1 = sh.createRow(0);
		for (int h = 0; h < locateHeaderField().size(); h++) {
			String text = locateHeaderField().get(h).getText();
			row1.createCell(h).setCellValue(text);
		}
	}

	public List<WebElement> locateRows() throws IOException {
		List<WebElement> totalRow = locateTable().findElements(noOfRows);
		
		return totalRow;
	}

	public void writeRowsToExcle() throws IOException {
		for (int row = 0; row < locateRows().size(); row++) {
			List<WebElement> totalCol = locateRows().get(row).findElements(By.tagName("td"));
			XSSFRow rowValue = sh.createRow(row + 1);
			for (int col = 0; col < totalCol.size(); col++) {
				String cellValue = totalCol.get(col).getText();
				System.out.println(cellValue);
				rowValue.createCell(col).setCellValue(cellValue);
			}
			System.out.println();
		}
	}

	public void getYearWiseTable() throws IOException {
		String path = System.getProperty("user.dir")+"\\Test_data\\Web_Table.xlsx";

		fil = new File(path);
		wb = new XSSFWorkbook();
		sh = wb.createSheet("HomeLone");

		locateTable();
		
		screenShotOfTable();

		locateHeaderField();

		writeHeaderField();

		locateRows();

		writeRowsToExcle();

		FileOutputStream fio = new FileOutputStream(fil);
		wb.write(fio);
		wb.close();
		System.out.println("WebTable stored successfully");
	}
	
	 public HomeLoanPage clickOnCalculateMenu() {
		getDriver().findElement(calculatorMenu).click();
		return this;
	}

	 public EMICalculatorPage clickOnLoanCalculator() {
		 getDriver().findElement(loanCalculator).click();
		 try {
				new Actions(getDriver()).moveByOffset(70, 100).click().perform();
				}catch(Exception e) {
					System.out.println(e);
				}
			 
		 return new EMICalculatorPage();
	 }

}


