package com.mobiquity.page;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.mobiquity.util.AssertionUtil;

public class LoginPage extends BasePage {

	@FindBy(how = How.XPATH, using = "//input[@ng-model='user.name']")
	public WebElement userNameWebElement;

	@FindBy(how = How.XPATH, using = "//input[@ng-model='user.password']")
	public WebElement passwordWebElement;

	@FindBy(how = How.XPATH, using = "//*[@id=\"login-form\"]/fieldset/button")
	public WebElement loginButtonWebElement;

	@FindBy(how = How.XPATH, using = "//*[@id=\"login-form\"]/fieldset/p[1]")
	public WebElement messageWebElement;

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void enterUsernamePasswdAndClickLogin(String userName, String passwd) throws IOException {
		enterUserName(userName);
		enterPassword(passwd);
		clickLoginButton();
	}

	public LoginPage enterUserName(String userName) throws IOException {
		sendKeysToWebElement(userNameWebElement, userName);
		return new LoginPage(driver);
	}

	public LoginPage enterPassword(String password) throws IOException {
		sendKeysToWebElement(passwordWebElement, password);
		return new LoginPage(driver);
	}

	public LoginPage clickLoginButton() {
		loginButtonWebElement.click();
		AssertionUtil.assertInfo("Login Button clicked.");
		return new LoginPage(driver);
	}

	public boolean isLoginButtonDisplayed() {
		return isElementDisplayed(loginButtonWebElement);
	}

	public String getInvalidMessage() {
		return getInvalidMessage(messageWebElement);
	}

	public String getBlankfieldMessageForUserNameField() {
		return getBlankfieldMessage(userNameWebElement);
	}

	public String getBlankfieldMessageForPasswordField() {
		return getBlankfieldMessage(passwordWebElement);
	}

}
