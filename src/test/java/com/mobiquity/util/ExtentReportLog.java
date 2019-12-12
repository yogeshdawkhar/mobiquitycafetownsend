package com.mobiquity.util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.mobiquity.base.BaseClass;
import com.mobiquity.constants.LoggingMessageContants;

public class ExtentReportLog extends BaseClass {
	private static final Logger LOGGER = Logger.getLogger(ExtentReportLog.class.getName());

	public static void testCaseFailWithImage(String message) throws IOException {
		if ((extentTest != null) && (!extentTest.toString().contains(LoggingMessageContants.NULL_CHECK_STRING))) {
			extentTest
					.log(Status.FAIL, message,
							MediaEntityBuilder
									.createScreenCaptureFromPath(
											extentReportImagePathForHTML + ScreenshotUtil.createimagename() + ".png")
									.build());
		}
	}

	public static void testCaseFail(String message) {
		if ((extentTest != null) && (!extentTest.toString().contains(LoggingMessageContants.NULL_CHECK_STRING))) {
			extentTest.log(Status.FAIL, MarkupHelper.createLabel(message + " - Test Case Failed", ExtentColor.RED));
		}
	}

	public static void testCaseInfoWithImage(String message) throws IOException {
		if ((extentTest != null) && (!extentTest.toString().contains(LoggingMessageContants.NULL_CHECK_STRING))) {
			extentTest
					.log(Status.INFO, message,
							MediaEntityBuilder
									.createScreenCaptureFromPath(
											extentReportImagePathForHTML + ScreenshotUtil.createimagename() + ".png")
									.build());
		}
	}

	public static void testCaseInfo(String message) {
		if ((extentTest != null) && (!extentTest.toString().contains(LoggingMessageContants.NULL_CHECK_STRING))) {
			extentTest.log(Status.INFO, message);
		}
	}

	public static void testCasePassWithImage(String message) throws IOException {
		if ((extentTest != null) && (!extentTest.toString().contains(LoggingMessageContants.NULL_CHECK_STRING))) {
			extentTest.log(Status.PASS, MarkupHelper.createLabel(message + " Test Case PASSED", ExtentColor.GREEN));
			extentTest
					.log(Status.PASS, message,
							MediaEntityBuilder
									.createScreenCaptureFromPath(
											extentReportImagePathForHTML + ScreenshotUtil.createimagename() + ".png")
									.build());
		}
	}

	public static void testCasePass(String message) {
		if ((extentTest != null) && (!extentTest.toString().contains(LoggingMessageContants.NULL_CHECK_STRING))) {
			extentTest.log(Status.PASS, MarkupHelper.createLabel(message + " Test Case PASSED", ExtentColor.GREEN));
		}
	}

	public static void testCaseSkipWithImage(String message) throws IOException {
		if ((extentTest != null) && (!extentTest.toString().contains(LoggingMessageContants.NULL_CHECK_STRING))) {
			extentTest
					.log(Status.SKIP, message,
							MediaEntityBuilder
									.createScreenCaptureFromPath(
											extentReportImagePathForHTML + ScreenshotUtil.createimagename() + ".png")
									.build());
		}
	}

	public static void testCaseSkip(String message) {
		if ((extentTest != null) && (!extentTest.toString().contains(LoggingMessageContants.NULL_CHECK_STRING))) {
			extentTest.log(Status.SKIP, MarkupHelper.createLabel(message + " - Test Case Skipped", ExtentColor.ORANGE));
		}
	}

	public static void testStart(String message) {
		extentTest = extent.createTest(message);
		LOGGER.log(Level.INFO, () -> "Test Started:" + message);
		extentTest.log(Status.INFO, "Test Started:" + message);
	}
}
