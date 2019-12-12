package com.mobiquity.testcases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.mobiquity.base.BaseClass;
import com.mobiquity.model.User;
import com.mobiquity.page.CreateUserPage;
import com.mobiquity.page.HomePage;
import com.mobiquity.page.LoginPage;
import com.mobiquity.util.AssertionUtil;
import com.mobiquity.util.ExtentReportLog;
import com.mobiquity.util.PropertiesReader;

public class CreateUserTestCase extends BaseClass {

	HomePage homePageObj;
	CreateUserPage createUserPageObj;
	LoginPage loginPageObj;

	@Test
	public void createUserWithValidDetails() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		openUrlAndLoginUser();
		User user = createUser();
		AssertionUtil.assertEqualsWithImage(
				homePageObj.isAddedUserPresentInList(user.getFirstName() + " " + user.getLastName()), true,
				"User Added successfully.", "User not Added");
	}

	private User createUser() throws IOException {
		homePageObj = PageFactory.initElements(driver, HomePage.class);
		createUserPageObj = PageFactory.initElements(driver, CreateUserPage.class);
		homePageObj.clickCreateButton();
		User user = new User();
		createUserPageObj.enterUserDetailsAndClickAddButton(user.getFirstName(), user.getLastName(),
				user.getStartDate(), user.getEmailId());
		return user;
	}

	private void openUrlAndLoginUser() throws IOException {
		PropertiesReader pr = new PropertiesReader();
		pr.load(userNamePasswordPropertifile);
		loginPageObj = PageFactory.initElements(driver, LoginPage.class);
		loginPageObj.loadUrl(baseUrl);
		loginPageObj.enterUsernamePasswdAndClickLogin(pr.get("userName"), pr.get("password"));
	}
}