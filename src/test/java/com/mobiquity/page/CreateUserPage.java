package com.mobiquity.page;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.mobiquity.util.AssertionUtil;

public class CreateUserPage extends BasePage {

	@FindBy(how = How.XPATH, using = "//input[@ng-model='selectedEmployee.firstName']")
	public WebElement fristNameWebElement;

	@FindBy(how = How.XPATH, using = "//input[@ng-model='selectedEmployee.lastName']")
	public WebElement lastNameWebElement;

	@FindBy(how = How.XPATH, using = "//input[@ng-model='selectedEmployee.startDate']")
	public WebElement startDateWebElement;

	@FindBy(how = How.XPATH, using = "//input[@ng-model='selectedEmployee.email']")
	public WebElement emailWebElement;

	@FindBy(how = How.XPATH, using = "//button[@class='main-button']")
	public WebElement addButtonWebElement;

	@FindBy(how = How.XPATH, using = "//p[contains(@ng-click,'browseToOverview()')]")
	public WebElement cancelButtonWebElement;

	public CreateUserPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void enterUserDetailsAndClickAddButton(String fristName, String lastName, String startDate, String emailId)
			throws IOException {
		enterFristName(fristName);
		enterLastName(lastName);
		enterStartDate(startDate);
		enterEmailId(emailId);
		clickAddButton();
	}

	public CreateUserPage enterFristName(String fristName) throws IOException {
		sendKeysToWebElement(fristNameWebElement, fristName);
		return new CreateUserPage(driver);
	}

	public CreateUserPage enterLastName(String lastName) throws IOException {
		sendKeysToWebElement(lastNameWebElement, lastName);
		return new CreateUserPage(driver);
	}

	public CreateUserPage enterStartDate(String startDate) throws IOException {
		sendKeysToWebElement(startDateWebElement, startDate);
		return new CreateUserPage(driver);
	}

	public CreateUserPage enterEmailId(String emailId) throws IOException {
		sendKeysToWebElement(emailWebElement, emailId);
		return new CreateUserPage(driver);
	}

	public CreateUserPage clickAddButton() {
		addButtonWebElement.click();
		AssertionUtil.assertInfo("Add Button clicked.");
		return new CreateUserPage(driver);
	}

	public CreateUserPage clickCancelButton() {
		cancelButtonWebElement.click();
		AssertionUtil.assertInfo("Cancel Button clicked.");
		return new CreateUserPage(driver);
	}
}
