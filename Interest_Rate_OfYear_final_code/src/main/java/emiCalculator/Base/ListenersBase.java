package emiCalculator.Base;

import java.io.IOException;

import org.Utils.ExtentReportUtil;
import org.Utils.XLUtility;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ListenersBase extends ExtentReportUtil implements ITestListener {

	XLUtility xl = new XLUtility();

	private ExtentTest test;
	ThreadLocal<ExtentTest> testLocal = new ThreadLocal<ExtentTest>();

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Both validation of Text Box and Scale is Successfull");

	}

	public void onTestStart(ITestResult result) {
		ExtentReports extent = createReport("EMICalculator Test REport", "Hackathon TestReslut");
		test = extent.createTest(result.getMethod().getMethodName());
		testLocal.set(test);
		test.info("Test Started Successfully");

	}

	public void onTestFailedWithTimeout(ITestResult result) {
		test.fail("Failed with TimeOut");

	}

	public void onTestFailure(ITestResult result,RemoteWebDriver driver) {
		test.fail(result.getThrowable() + "This caused the test to fail");
		String name = result.getMethod().getMethodName();
		String path = null;
		try {
			path = xl.screenShot(name,driver);
		} catch (IOException e) {
			System.out.println(e);
		}
		test.addScreenCaptureFromPath(path, name);

	}

	public void onTestSkipped(ITestResult result) {
		test.skip("Test Skipped");

	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();

	}

}
