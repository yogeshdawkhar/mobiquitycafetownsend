package com.mobiquity.page;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.mobiquity.util.AssertionUtil;

public class HomePage extends BasePage {

	@FindBy(how = How.ID, using = "bAdd")
	public WebElement createButtonWebElement;

	@FindBy(how = How.ID, using = "bEdit")
	public WebElement editButtonWebElement;

	@FindBy(how = How.ID, using = "bDelete")
	public WebElement deleteButtonWebElement;

	@FindBy(how = How.ID, using = "greetings")
	public WebElement welcomeWebElement;

	@FindBy(how = How.XPATH, using = "//p[contains(@ng-click,'logout()')]")
	public WebElement logoutWebElement;

	@FindBy(how = How.ID, using = "employee-list")
	public WebElement employeeListWebElement;

	@FindBy(how = How.XPATH, using = "//li[@ng-repeat='employee in employees']")
	public List<WebElement> userRowWebElement;

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public boolean isWelcomeTextdisplayed() {
		return isElementDisplayed(welcomeWebElement);
	}

	public HomePage clickLogoutButton() throws IOException {
		actionMoveAndClickByWebElement(logoutWebElement);
		return new HomePage(driver);
	}

	public HomePage clickCreateButton() {
		createButtonWebElement.click();
		AssertionUtil.assertInfo("Create Button clicked.");
		return new HomePage(driver);
	}

	public HomePage clickEditButton() {
		editButtonWebElement.click();
		AssertionUtil.assertInfo("Edit Button clicked.");
		return new HomePage(driver);
	}

	public HomePage clickDeleteButton() {
		deleteButtonWebElement.click();
		AssertionUtil.assertInfo("Delete Button clicked.");
		return new HomePage(driver);
	}

	public boolean isAddedUserPresentInList(String text) throws IOException {
		List<WebElement> wl = userRowWebElement;
		for (int i = wl.size(); i > 0; i--) {
			if (wl.get(i - 1).getText().equalsIgnoreCase(text)) {
				actionMoveToByLocator(getXpathOfEmployee(i));
				AssertionUtil.assertInfo("Element is present in list:" + text);
				return true;
			}
		}
		AssertionUtil.assertInfo("Element is not present in list:" + text);
		return false;
	}

	public HomePage clickOnUser(String text) throws IOException {
		List<WebElement> wl = userRowWebElement;
		for (int i = wl.size(); i > 0; i--) {
			if (wl.get(i - 1).getText().equalsIgnoreCase(text)) {
				actionMoveAndClickByLocator(getXpathOfEmployee(i));
				AssertionUtil.assertInfo("Element is present in list :" + text);
				return new HomePage(driver);
			}
		}
		AssertionUtil.assertInfo("Element is not present in list :" + text);
		return new HomePage(driver);
	}

	public HomePage doubleClickOnUser(String text) throws IOException {
		List<WebElement> wl = userRowWebElement;
		for (int i = wl.size(); i > 0; i--) {
			if (wl.get(i - 1).getText().equalsIgnoreCase(text)) {
				actionMoveAndDoubleClickByLocator(getXpathOfEmployee(i));
				AssertionUtil.assertInfo("Element is present in list :" + text);
				return new HomePage(driver);
			}
		}
		AssertionUtil.assertInfo("Element is not present in list :" + text);
		return new HomePage(driver);
	}

	public HomePage acceptAlertAfterClickingDeleteButton() throws InterruptedException, IOException {
		checkAlertAndAccept();
		return new HomePage(driver);
	}
	
	public By getXpathOfEmployee(int i) {
		return By.xpath("//*[@id=\"employee-list\"]/li[" + i + "]");
		
	}
}
