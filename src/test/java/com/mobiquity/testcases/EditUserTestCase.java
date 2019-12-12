package com.mobiquity.testcases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.mobiquity.base.BaseClass;
import com.mobiquity.model.User;
import com.mobiquity.page.CreateUserPage;
import com.mobiquity.page.EditUserPage;
import com.mobiquity.page.HomePage;
import com.mobiquity.page.LoginPage;
import com.mobiquity.util.AssertionUtil;
import com.mobiquity.util.ExtentReportLog;
import com.mobiquity.util.PropertiesReader;

public class EditUserTestCase extends BaseClass {

	CreateUserPage createUserPageObj;
	EditUserPage editUserPageObj;
	LoginPage loginPageObj;
	HomePage homePageObj;

	@Test
	public void editUserDetailsUsingEditButton() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		openUrlAndLoginUser();
		User user = createUser();
		homePageObj.clickOnUser(user.getFirstName() + " " + user.getLastName());
		homePageObj.clickEditButton();
		User userUpdated = updateUserData();
		AssertionUtil.assertEqualsWithImage(
				homePageObj.isAddedUserPresentInList(userUpdated.getFirstName() + " " + userUpdated.getLastName()),
				true, "User Updated successfully.", "User Updated Added");
	}

	@Test
	public void editUserDetailsUsingDoubleClick() throws IOException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		openUrlAndLoginUser();
		User user = createUser();
		homePageObj.doubleClickOnUser(user.getFirstName() + " " + user.getLastName());
		User userUpdated = updateUserData();
		AssertionUtil.assertEquals(homePageObj.isAddedUserPresentInList(user.getFirstName() + " " + user.getLastName()),
				false, "User old record not found.", "User old record found.");
		AssertionUtil.assertEqualsWithImage(
				homePageObj.isAddedUserPresentInList(userUpdated.getFirstName() + " " + userUpdated.getLastName()),
				true, "User Updated successfully.", "User Updated Added");
	}

	private User updateUserData() throws IOException  {
		editUserPageObj = PageFactory.initElements(driver, EditUserPage.class);
		User userUpdated = new User();
		editUserPageObj.enteruserDetailsAndClickUpdateButton(userUpdated.getFirstName(), userUpdated.getLastName(),
				userUpdated.getStartDate(), userUpdated.getEmailId());
		return userUpdated;
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
		loginPageObj.enterUsernamePasswdAndClickLogin(pr.get("userName"),
				pr.get("password"));
	}
}