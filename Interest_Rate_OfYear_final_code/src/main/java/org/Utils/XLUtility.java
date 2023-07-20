package org.Utils;

import java.io.File;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class XLUtility {
	

	
	public void scrollTo(WebElement element,RemoteWebDriver driver) {
		JavascriptExecutor js = driver;
		js.executeScript("arguments[0].scrollIntoView(true);",element);
	}
	
	public String screenShot(String testName,RemoteWebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"\\ScreenShots\\"+testName+".png");
		FileUtils.copyFile(src,dest);
		return System.getProperty("user.dir")+"\\ScreenShots\\"+testName+".png";
	}
}
