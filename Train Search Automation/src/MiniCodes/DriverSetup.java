package MiniCodes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class DriverSetup {
	static WebDriver driver;
	static int driverSelection;
	static String url, from, to, chromeDriver, edgeDriver, fireFoxDriver, screenshot;

	public void setDriver() throws IOException {
		// Scanner should not be closed here, since it closes the underlying input stream as well
		// Instead, use a try-with-resources block to ensure the scanner is properly closed
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Select your Browser: 1.Chrome 2.Edge 3.Firefox");
			driverSelection = scanner.nextInt();
		}
		loadProperties();
		switch (driverSelection) {
			case 1:
				chromeDriverSetup();
				break;
			case 2:
				edgeDriverSetup();
				break;
			case 3:
				fireFoxDriverSetup();
				break;
			default:
				System.out.println("Invalid Input");
		}
		openUrl();
	}

	private static void loadProperties() throws IOException {
		Properties prop = new Properties();
		// You should avoid using hard-coded paths, since they can be different on different machines
		// Instead, use relative paths or pass the path as a program argument
		// Also, you should close the input stream after using it, to free up system resources
		try (FileInputStream readFile = new FileInputStream("C:\\Users\\2269278\\eclipse-workspace\\FinalProject\\src\\MiniCodes\\config.properties")) {
			prop.load(readFile);
			url = prop.getProperty("url");
			from = prop.getProperty("from");
			to = prop.getProperty("to");
			chromeDriver = prop.getProperty("chromeDriverPath");
			edgeDriver = prop.getProperty("edgeDriverPath");
			fireFoxDriver = prop.getProperty("FirefoxDriverPath");
			screenshot = prop.getProperty("screenshotpath");
		}
	}

	private void openUrl() {
		if (driver != null) {
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(90));
		} else {
			System.out.println("Driver is null, cannot open URL");
		}
	}

	private void chromeDriverSetup() {
		System.setProperty("webdriver.chrome.driver", chromeDriver);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
	}

	public void edgeDriverSetup() {
		System.setProperty("webdriver.edge.driver", edgeDriver);
		driver = new EdgeDriver();
	}

	public void fireFoxDriverSetup() {
		System.setProperty("webdriver.gecko.driver", fireFoxDriver);
		driver = new FirefoxDriver();
	}

	public void driverClose() throws InterruptedException {
		if (driver != null) {
			TimeUnit.SECONDS.sleep(5);
			driver.quit();
		} else {
			System.out.println("Driver is null, cannot close it");
		}
	}
}