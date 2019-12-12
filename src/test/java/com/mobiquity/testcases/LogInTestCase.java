
package com.mobiquity.testcases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mobiquity.base.BaseClass;
import com.mobiquity.constants.LoggingMessageContants;
import com.mobiquity.page.HomePage;
import com.mobiquity.page.LoginPage;
import com.mobiquity.util.AssertionUtil;
import com.mobiquity.util.ExtentReportLog;
import com.mobiquity.util.PropertiesReader;
import com.mobiquity.util.UtililtyFunctions;

public class LogInTestCase extends BaseClass {

	HomePage homePageObj;
	LoginPage loginPageObj;

	@Test()
	public void logInTestWithValidUser() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + "->"
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		PropertiesReader pr = new PropertiesReader();
		pr.load(userNamePasswordPropertifile);
		openUrlAndClickLogin(pr.get("userName"), pr.get("password"));
		homePageObj = PageFactory.initElements(driver, HomePage.class);
		AssertionUtil.assertEquals(homePageObj.isWelcomeTextdisplayed(), true, "User loggedin successfully.",
				"User not loggedin");
	}

	@DataProvider(name = "invalidCredentials")
	public static Object[][] invalidEmployee() {
		return new Object[][] {
				{ UtililtyFunctions.generateRandomStringWithAlphabetesOnly(5),
						UtililtyFunctions.generateRandomStringWithAlphabetesOnly(5) },
				{ UtililtyFunctions.generateRandomStringWithAlphabetesOnly(5),
						UtililtyFunctions.generateRandomStringWithAlphabetesOnly(5) } };
	}

	@Test(dataProvider = "invalidCredentials")
	public void logInTestWithInvalidUsers(String userName, String password) throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		openUrlAndClickLogin(userName, password);
		AssertionUtil.assertEqualsWithImage(loginPageObj.getInvalidMessage(),
				LoggingMessageContants.INVALID_USERNAME_OR_PASSW,
				"Error message displayed :" + loginPageObj.getInvalidMessage(),
				"Wrong Error message displayed :" + loginPageObj.getInvalidMessage());
	}

	@Test
	public void logInTestWithoutUserName() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		openUrlAndClickLogin("", UtililtyFunctions.generateRandomStringWithAlphabetesOnly(5));
		AssertionUtil.assertEqualsWithImage(loginPageObj.getBlankfieldMessageForUserNameField(),
				LoggingMessageContants.PLEASE_FILL_IN_THIS_FIELD,
				"Error message displayed:" + loginPageObj.getBlankfieldMessageForUserNameField(),
				"Wrong Error message displayed:" + loginPageObj.getBlankfieldMessageForUserNameField());
	}

	@Test
	public void logInTestWithoutPassword() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		openUrlAndClickLogin(UtililtyFunctions.generateRandomStringWithAlphabetesOnly(5), "");
		AssertionUtil.assertEqualsWithImage(loginPageObj.getBlankfieldMessageForPasswordField(),
				LoggingMessageContants.PLEASE_FILL_IN_THIS_FIELD,
				"Error message displayed:" + loginPageObj.getBlankfieldMessageForPasswordField(),
				"Wrong Error message displayed:" + loginPageObj.getBlankfieldMessageForPasswordField());
	}

	private void openUrlAndClickLogin(String userName, String password) throws IOException {
		loginPageObj = PageFactory.initElements(driver, LoginPage.class);
		loginPageObj.loadUrl(baseUrl);
		loginPageObj.enterUsernamePasswdAndClickLogin(userName, password);
	}
}
