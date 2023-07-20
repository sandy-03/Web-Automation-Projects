package MiniCodes;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Process extends DriverSetup {
	public static WebDriverWait wait;

	public void city() throws InterruptedException {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		TimeUnit.SECONDS.sleep(5);
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"origin\"]/span/input"))));
		WebElement fromField = driver.findElement(By.xpath("//*[@id=\"origin\"]/span/input"));
		fromField.click();
//		TimeUnit.SECONDS.sleep(10);
		fromField.sendKeys(from);
		TimeUnit.SECONDS.sleep(3);
		driver.findElement(By.xpath("//*[text()='HYDERABAD DECAN - HYB']")).click();
//		fromField.sendKeys(Keys.ARROW_DOWN);
//		fromField.sendKeys(Keys.RETURN);

		WebElement toField = driver.findElement(By.xpath("//*[@id=\"destination\"]/span/input"));
		toField.sendKeys(to);
		TimeUnit.SECONDS.sleep(3);
		toField.sendKeys(Keys.RETURN);
		driver.findElement(By.xpath("//img[@id='disha-banner-close']")).click();
		driver.findElement(By.xpath("//div[@id='askDishaLuncher']/img[2]")).click();
		// toField.sendKeys(Keys.ARROW_DOWN);
	}

	public void date() throws InterruptedException {
		LocalDate futureDate = LocalDate.now().plusDays(4);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedFutureDate = futureDate.format(formatter);
		WebElement departureDateField = driver.findElement(By.xpath("//*[@id=\"jDate\"]/span/input"));
		departureDateField.click();
		// a[@class='ui-state-default ng-tns-c58-10 ng-star-inserted' and text()='28']
		// 28/05/2023 split by "/"
		// [0]/[1]/[2]
		// System.out.println(formattedFutureDate);
		driver.findElement(By.xpath("//a[@class='ui-state-default ng-tns-c58-10 ng-star-inserted' and text()='"
				+ formattedFutureDate.split("/")[0] + "']")).click();

		TimeUnit.SECONDS.sleep(2);
		notification();
	}

	private void notification() {
		// firefox ad block
		try {

			driver.findElement(By.xpath("/html/body/div/div/div[2]/button[1]")).click();

		} catch (Exception e) {

		}
	}

	public void businessClass() throws InterruptedException {
		WebElement traveller = driver.findElement(By.xpath("//*[@id=\"journeyClass\"]/div/div[3]"));
		traveller.click();
		TimeUnit.SECONDS.sleep(3);

		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//span[normalize-space()='Sleeper (SL)']"))));
		driver.findElement(By.xpath("//span[normalize-space()='Sleeper (SL)']")).click();
		TimeUnit.SECONDS.sleep(3);
	}

	public void search() throws InterruptedException {
		TimeUnit.SECONDS.sleep(3);
		driver.findElement(By.xpath("//div[@class='col-xs-12 remove-padding']/div/span[1]/label")).click();

		wait.until(ExpectedConditions.visibilityOf(
				driver.findElement(By.xpath("//span[@class='ui-button-icon-left ui-clickable pi pi-check']"))));
		driver.findElement(By.xpath("//span[@class='ui-button-icon-left ui-clickable pi pi-check']")).click();
		TimeUnit.SECONDS.sleep(3);

		WebElement searchButton = driver.findElement(By.xpath(
				"//*[@id=\"divMain\"]/div/app-main-page/div/div/div[1]/div[1]/div[1]/app-jp-input/div/form/div[5]/div[1]/button"));
		searchButton.click();
		TimeUnit.SECONDS.sleep(10);
	}

	public void validation() throws ParseException, InterruptedException {
		WebElement resultLine = driver.findElement(By.xpath("//div[@class='hidden-xs']/span"));
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", resultLine);
		} catch (Exception e) {

		}
		// 5 Results for HYDERABAD DECAN PUNE JN | Sun, 28 May 2023 For Quota | General
		// 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
		String fromLocation = resultLine.getText().split(" ")[3].concat(" ").concat(resultLine.getText().split(" ")[4]);
		// fromLocation = HYDERABAD DECAN

		String toLocation = resultLine.getText().split(" ")[5].concat(" ").concat(resultLine.getText().split(" ")[6]);
		String sDate = resultLine.getText().split(" ")[9].concat(resultLine.getText().split(" ")[10])
				.concat(resultLine.getText().split(" ")[11]);

		DateTimeFormatter format = DateTimeFormatter.ofPattern("ddMMMyyyy");
//		System.out.println(sDate);
		// 28052023
		LocalDate resultDate = LocalDate.parse(sDate, format);
		LocalDate foursDaysFromToday = LocalDate.now().plusDays(4);

		boolean validateDate = resultDate.equals(foursDaysFromToday);
		boolean validateFrom = fromLocation.equals("HYDERABAD DECAN");
		boolean validateTo = toLocation.equals("PUNE JN");

		if (validateDate && validateFrom && validateTo) {
			System.out.println("Cities and Date are verified");
		} else {
			System.out.println("Cities and Date are not verified");
		}

	}

	public void print() throws InterruptedException {
		List<WebElement> trainName = driver
				.findElements(By.xpath("//div[@class='col-sm-5 col-xs-11 train-heading']/strong"));
		System.out.println("Total number of trains available are: " + trainName.size());
		int j = 1;
		for (int i = 0; i < trainName.size(); i++) {
			System.out.println(trainName.get(i).getText() + " - " + j++);
		}
		TimeUnit.SECONDS.sleep(5);

	}

	public void screenShots() throws InterruptedException, IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(screenshot);
		FileHandler.copy(src, dest);
		TimeUnit.SECONDS.sleep(5);
		System.out.println("screenshot taken");

	}
}
