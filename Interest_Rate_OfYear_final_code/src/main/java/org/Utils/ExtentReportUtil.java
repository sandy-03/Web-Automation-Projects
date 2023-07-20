package org.Utils;




import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtil {
	protected ExtentReports extent = null;
	protected ExtentSparkReporter spar = null;
//	protected ExtentTest test ;
	ITestResult result = null;
	
	public ExtentReports  createReport(String report,String title)  {
		String path = System.getProperty("user.dir")+"\\ExtentReport\\TestReport.html";
		if(extent==null) {
		 spar = new ExtentSparkReporter(path);
		spar.config().setReportName(report);
		spar.config().setDocumentTitle(title);
		 extent = new ExtentReports();
		extent.attachReporter(spar);
		}
		return extent;
		
	}
	
	
	public ExtentTest testReport() {
		ExtentTest test=extent.createTest(result.getMethod().getMethodName());
		return test;
		
	}

	public void closeReport() {
		extent.flush();
	}
}
