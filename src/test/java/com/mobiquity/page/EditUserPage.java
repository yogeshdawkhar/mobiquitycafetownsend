package com.mobiquity.page;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.mobiquity.util.AssertionUtil;

public class EditUserPage extends BasePage {

	@FindBy(how = How.XPATH, using = "//input[@ng-model='selectedEmployee.firstName']")
	public WebElement fristNameWebElement;

	@FindBy(how = How.XPATH, using = "//input[@ng-model='selectedEmployee.lastName']")
	public WebElement lastNameWebElement;

	@FindBy(how = How.XPATH, using = "//input[@ng-model='selectedEmployee.startDate']")
	public WebElement startDateWebElement;

	@FindBy(how = How.XPATH, using = "//input[@ng-model='selectedEmployee.email']")
	public WebElement emailWebElement;

	@FindBy(how = How.XPATH, using = "/html/body/div/div/div/form/fieldset/div/button[1]")
	public WebElement updateButtonWebElement;

	@FindBy(how = How.XPATH, using = "//p[contains(@ng-click,'browseToOverview()')]")
	public WebElement backButtonWebElement;

	@FindBy(how = How.XPATH, using = "//p[contains(@ng-click,'deleteEmployee()')]")
	public WebElement deleteButtonEditWebElement;

	public EditUserPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void enteruserDetailsAndClickUpdateButton(String fristName, String lastName, String startDate,
			String emailId) throws IOException {
		enterFristName(fristName);
		enterLastName(lastName);
		enterStartDate(startDate);
		enterEmailId(emailId);
		clickUpdateButton();
	}

	public EditUserPage enterFristName(String fristName) throws IOException {
		sendKeysToWebElement(fristNameWebElement, fristName);
		return new EditUserPage(driver);
	}

	public EditUserPage enterLastName(String lastName) throws IOException {
		sendKeysToWebElement(lastNameWebElement, lastName);
		return new EditUserPage(driver);
	}

	public EditUserPage enterStartDate(String startDate) throws IOException {
		sendKeysToWebElement(startDateWebElement, startDate);
		return new EditUserPage(driver);
	}

	public EditUserPage enterEmailId(String emailId) throws IOException {
		sendKeysToWebElement(emailWebElement, emailId);
		return new EditUserPage(driver);
	}

	public EditUserPage clickUpdateButton() {
		updateButtonWebElement.click();
		AssertionUtil.assertInfo("Update Button clicked.");
		return new EditUserPage(driver);
	}

	public EditUserPage clickDeleteButton() {
		deleteButtonEditWebElement.click();
		AssertionUtil.assertInfo("Delete Button clicked.");
		return new EditUserPage(driver);
	}

	public EditUserPage acceptAlertAfterClickingDeleteButton() throws InterruptedException, IOException {
		checkAlertAndAccept();
		return new EditUserPage(driver);
	}

}
