
package com.mobiquity.testcases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.mobiquity.base.BaseClass;
import com.mobiquity.page.HomePage;
import com.mobiquity.page.LoginPage;
import com.mobiquity.util.AssertionUtil;
import com.mobiquity.util.ExtentReportLog;
import com.mobiquity.util.PropertiesReader;

public class LogOutTestCase extends BaseClass {

	HomePage homePageObj;
	LoginPage loginPageObj;

	@Test()
	public void logOutTest() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		openUrlAndLoginUser();
		homePageObj = PageFactory.initElements(driver, HomePage.class);
		AssertionUtil.assertEquals(homePageObj.isWelcomeTextdisplayed(), true, "User loggedin successfully.",
				"User not loggedin");
		homePageObj.clickLogoutButton();
		AssertionUtil.assertEquals(loginPageObj.isLoginButtonDisplayed(), true, "User logged Out successfully.",
				"User not loggedout");
	}

	private void openUrlAndLoginUser() throws IOException {
		PropertiesReader pr = new PropertiesReader();
		pr.load(userNamePasswordPropertifile);
		loginPageObj = PageFactory.initElements(driver, LoginPage.class);
		loginPageObj.loadUrl(baseUrl);
		loginPageObj.enterUsernamePasswdAndClickLogin(pr.get("userName"), pr.get("password"));
	}
}
