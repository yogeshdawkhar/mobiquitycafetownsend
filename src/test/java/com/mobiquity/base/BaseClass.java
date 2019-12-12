package com.mobiquity.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.mobiquity.constants.LoggingMessageContants;
import com.mobiquity.util.ExtentReportLog;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	protected static WebDriver driver;
	protected static String baseUrl = "http://cafetownsend-angular-rails.herokuapp.com/login";
	protected static String browser = "Chrome";

	protected static String randomNumber = new SimpleDateFormat("dd_MM_yy_HHmmssSSSZZZ").format(new Date());
	protected static String fileSeprator = File.separator;
	protected static String userDirSrc = System.getProperty("user.dir") + fileSeprator + "src" + fileSeprator;

	protected static String extentReportFilePath = userDirSrc + "ExtentReports" + fileSeprator + randomNumber;
	protected static String extentReportFilename = extentReportFilePath + fileSeprator + "extentReportFile" + ".html";
	protected static String extentReportPathForStoringImage = extentReportFilePath + fileSeprator + "images"
			+ fileSeprator;
	protected static String extentReportImagePathForHTML = ".." + fileSeprator + randomNumber + fileSeprator + "images"
			+ fileSeprator;
	protected static ExtentTest extentTest;
	protected static ExtentHtmlReporter htmlReporter;
	protected static ExtentReports extent;

	protected static String propertiesFilePath = userDirSrc + "test" + fileSeprator + "resources" + fileSeprator;
	protected static String userNamePasswordPropertifile = propertiesFilePath + "usernamepassword.properties";
	private static final Logger LOGGER = Logger.getLogger(BaseClass.class.getName());

	@BeforeSuite
	public static void startReport() {
		LOGGER.log(Level.INFO, () -> "Extent Report path:" + extentReportFilename);
		htmlReporter = new ExtentHtmlReporter(extentReportFilename);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		htmlReporter.loadXMLConfig("html-config.xml");

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			LOGGER.log(Level.INFO, () -> "Chrome Driver initalized");
		}
		if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			LOGGER.log(Level.INFO, () -> "Firefox Driver initalized");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
	}

	@AfterMethod
	public void getResult(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			ExtentReportLog.testCaseFail("Test Case Failed is " + result.getName());
			ExtentReportLog.testCaseFailWithImage("Test Case Failed Snapshot is below " + result.getThrowable());
			LOGGER.log(Level.SEVERE, () -> "Test Case Failed :" + result.getName());
		} else if (result.getStatus() == ITestResult.SKIP) {
			ExtentReportLog.testCaseSkip("Test Case Skipped is " + result.getName());
			LOGGER.log(Level.SEVERE, () -> "Test Case Skipped :" + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			ExtentReportLog.testCasePass("Test Case Passed is " + result.getName());
			LOGGER.log(Level.INFO, () -> "Test Case Passed :" + result.getName());
		}
		if ((extent != null) && (!extent.toString().contains(LoggingMessageContants.NULL_CHECK_STRING))) {
			extent.flush();
		}
		if ((driver != null) && (!driver.toString().contains(LoggingMessageContants.NULL_CHECK_STRING))) {
			driver.navigate().refresh();
		}
	}

	@AfterTest
	public void endReport() {
		if ((extent != null) && (!extent.toString().contains(LoggingMessageContants.NULL_CHECK_STRING))) {
			extent.flush();
		}
		if ((driver != null) && (!driver.toString().contains(LoggingMessageContants.NULL_CHECK_STRING))) {
			driver.manage().deleteAllCookies();
		}
	}

	@AfterSuite
	public static void closeBrowser() {
		if ((driver != null) && (!driver.toString().contains(LoggingMessageContants.NULL_CHECK_STRING))) {
			driver.quit();
			LOGGER.log(Level.INFO, () -> "Driver closed.");
		}
	}
}