package emiCalculator.Base;

import java.util.concurrent.TimeUnit;



import org.Utils.DataConfig;
import org.Utils.ExtentReportUtil;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EMIBase {
	ExtentReportUtil ext = new ExtentReportUtil();

	private static RemoteWebDriver driver;

	private static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<RemoteWebDriver>();

	public static RemoteWebDriver getDriver() {
		return dr.get();
	}

	public static void setDriver(RemoteWebDriver driver) {
		dr.set(driver);
	}

	public static void unload() {
		dr.remove();
	}

@Parameters({"browser"})
@Test(priority = 1)
	public void launchBrowser(String browser) {
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println("<<<Chrome Started>>>");
			
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println("<<<Edge Started>>>");
			
			break;
		default:
			System.out.println("invaild");
		}

		setDriver(driver);

		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		try {
			getDriver().get(DataConfig.URL());
		} catch (Exception e) {
			System.out.println(e);
		}

	}

@AfterTest
	public void closeBrowser() {
		getDriver().quit();
		unload();
	}

}
