package com.mobiquity.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

public class ViewUserTestCase extends BaseClass {

	HomePage homePageObj;
	CreateUserPage createUserPageObj;
	LoginPage loginPageObj;

	@Test
	public void verifyAddedUserOnList() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		openUrlAndLoginUser();
		homePageObj = PageFactory.initElements(driver, HomePage.class);
		createUserPageObj = PageFactory.initElements(driver, CreateUserPage.class);
		List<User> users = createUsersOfSize(2);
		for (User user : users) {
			homePageObj.clickCreateButton();
			String fristName = user.getFirstName();
			String lastName = user.getLastName();
			String startDate = user.getStartDate();
			String emailId = user.getEmailId();
			createUserPageObj.enterUserDetailsAndClickAddButton(fristName, lastName, startDate, emailId);
			AssertionUtil.assertEqualsWithImage(homePageObj.isAddedUserPresentInList(fristName + " " + lastName), true,
					"Added user is displayed on list.", "Added user is displayed on list.");
		}
	}

	private void openUrlAndLoginUser() throws IOException {
		PropertiesReader pr = new PropertiesReader();
		pr.load(userNamePasswordPropertifile);
		loginPageObj = PageFactory.initElements(driver, LoginPage.class);
		loginPageObj.loadUrl(baseUrl);
		loginPageObj.enterUsernamePasswdAndClickLogin(pr.get("userName"), pr.get("password"));
	}

	private List<User> createUsersOfSize(int i) {
		List<User> users = new ArrayList<>();
		for (int j = 0; j < i; j++) {
			users.add(new User());
		}
		return users;
	}
}