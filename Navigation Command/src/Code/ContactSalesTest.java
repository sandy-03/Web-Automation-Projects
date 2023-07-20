package Code;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ContactSalesTest extends DriverSetup {

    public static void main(String[] args) throws InterruptedException, IOException, AWTException {
        setDriver();
        searchOrangeHRM();
        navigate();
        navigateToContactSales();
        fillFields();
        driverClose();
    }
    private static void searchOrangeHRM() {
        driver.findElement(By.name("q")).sendKeys(searchInputs);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Google Search']")));
        driver.findElement(By.xpath("//input[@value='Google Search']")).click();
    }

    private static void navigate() throws InterruptedException {
        driver.navigate().back();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        System.out.println("Back navigation successful");
        TimeUnit.SECONDS.sleep(5);
        driver.navigate().forward();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        TimeUnit.SECONDS.sleep(5);
        if (driver.getTitle().contains("Google"))
            System.out.println("Forward navigation successful");
        //first result link
        driver.findElement(By.xpath("//*[text()='OrangeHRM Demo.']")).click();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        TimeUnit.SECONDS.sleep(3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='OrangeHRM, Inc']")));
        driver.findElement(By.xpath("//*[text()='OrangeHRM, Inc']")).click();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        TimeUnit.SECONDS.sleep(5);
    }

    private static void navigateToContactSales() throws InterruptedException {
    	Set<String> windowHandle1 = driver.getWindowHandles();
        
        List<String> windows = new ArrayList<>(windowHandle1);
        driver.switchTo().window(windows.get(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"navbarSupportedContent\"]/div[2]/ul/li[2]/a/button")));
        driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/div[2]/ul/li[2]/a/button")).click();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(80));
        TimeUnit.SECONDS.sleep(3);
        driver.findElement(By.xpath("//a[@title='Accept Cookies']")).click();
        TimeUnit.SECONDS.sleep(3);
    }

    private static void fillFields() throws InterruptedException, IOException {
        driver.findElement(By.id("Form_getForm_FullName")).sendKeys(fullName);
        driver.findElement(By.xpath("//*[@id=\"Form_getForm_Country\"]")).sendKeys(country);
        driver.findElement(By.id("Form_getForm_JobTitle")).sendKeys(jobTitle);
        WebElement numberOfEmployee = driver.findElement(By.xpath("//*[@id=\"Form_getForm_NoOfEmployees\"]"));
        Select select =  new Select(numberOfEmployee);
        select.selectByValue("11 - 15");
        driver.findElement(By.id("Form_getForm_Contact")).sendKeys(mobileNumber);
        driver.findElement(By.id("Form_getForm_Email")).sendKeys(mailId);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)", "");
        TimeUnit.SECONDS.sleep(4);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        TimeUnit.SECONDS.sleep(3);
        File fileSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest =  new File("C:\\Users\\2271417\\eclipse-workspace\\Orange_Hrm\\src\\Screenshot\\img.png");
        FileHandler.copy(fileSrc, dest);
        System.out.println("Screenshot of the page is taken.");
    }
}
