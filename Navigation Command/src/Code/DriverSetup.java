package Code;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class DriverSetup {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static int driverSelection;
    public static String url, name, searchInputs, fullName, country, jobTitle, mobileNumber, mailId;

    public static void setDriver() throws IOException {
        System.out.println("Select your Browser: 1.Chrome 2.Edge");
        Scanner scanner = new Scanner(System.in);
        driverSelection = scanner.nextInt();
        scanner.close();
        switch (driverSelection) {
            case 1:
                chromeDriverSetup();
                break;
            case 2:
                edgeDriverSetup();
                break;
            default:
                System.out.println("Invalid Input");
        }
        loadProperties();
        openUrl();
    }

    private static void loadProperties() throws IOException {
        Properties prop = new Properties();
        FileInputStream readFile = new FileInputStream("C:\\Users\\2271417\\eclipse-workspace\\Orange_Hrm\\src\\config.properties");
        prop.load(readFile);
        url = prop.getProperty("url");
        searchInputs = prop.getProperty("searchInputs");
        fullName = prop.getProperty("fullName");
        country = prop.getProperty("country");
        jobTitle = prop.getProperty("jobTitle");
        mobileNumber = prop.getProperty("mobileNumber");
        mailId = prop.getProperty("mailId");
    }

    private static void openUrl() {
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    }

    private static void chromeDriverSetup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\2271417\\Downloads\\chromedriver_win32 (3)\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    public static void edgeDriverSetup() {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\2271417\\eclipse-workspace\\Orange_Hrm\\src\\Drivers\\msedgedriver.exe");
        driver = new EdgeDriver();
    }


    public static void driverClose() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        driver.quit();
    }

}
