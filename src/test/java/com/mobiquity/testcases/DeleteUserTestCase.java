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

public class DeleteUserTestCase extends BaseClass {

	HomePage homePageObj;
	CreateUserPage createUserPageObj;
	LoginPage loginPageObj;
	EditUserPage editUserPageObj;

	@Test
	public void deleteUserDetailsUsingDeleteButton() throws IOException, InterruptedException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		openUrlAndLoginUser();
		User user = createUser();
		homePageObj.clickOnUser(user.getFirstName() + " " + user.getLastName());
		homePageObj.clickDeleteButton();
		homePageObj.acceptAlertAfterClickingDeleteButton();
		AssertionUtil.assertEquals(homePageObj.isAddedUserPresentInList(user.getFirstName() + " " + user.getLastName()),
				false, "User deleted successfully.", "User not deleted");
	}

	@Test
	public void deleteUserDetailsUsingDoubleClick() throws IOException, InterruptedException {
		ExtentReportLog.testStart(this.getClass().getSimpleName() + " -> "
				+ Thread.currentThread().getStackTrace()[1].getMethodName() + " On " + browser);
		openUrlAndLoginUser();
		User user = createUser();
		homePageObj.doubleClickOnUser(user.getFirstName() + " " + user.getLastName());
		editUserPageObj = PageFactory.initElements(driver, EditUserPage.class);
		editUserPageObj.clickDeleteButton();
		editUserPageObj.acceptAlertAfterClickingDeleteButton();
		AssertionUtil.assertEquals(homePageObj.isAddedUserPresentInList(user.getFirstName() + " " + user.getLastName()),
				false, "User Deleted successfully.", "User not Deleted");
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