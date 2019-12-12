package com.mobiquity.page;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mobiquity.base.BaseClass;
import com.mobiquity.util.AssertionUtil;

public class BasePage extends BaseClass {
	protected WebDriverWait wait;
	protected JavascriptExecutor jsExecutor;

	public BasePage() {
		this.wait = new WebDriverWait(driver, 30);
		jsExecutor = ((JavascriptExecutor) driver);
	}

	/**********************************************************************************
	 ** CLICK METHODS
	 **********************************************************************************/
	public void waitAndClickElement(WebElement element) throws IOException {
		boolean clicked = false;
		int attempts = 0;
		while (!clicked && attempts < 10) {
			try {
				this.wait.until(ExpectedConditions.elementToBeClickable(element)).click();
				AssertionUtil.assertInfo("Successfully clicked on the WebElement: " + "<" + element.toString() + ">");
				clicked = true;
			} catch (Exception e) {
				AssertionUtil.assertInfo("Unable to wait and click on WebElement, Exception: " + e.getMessage());
				AssertionUtil.assertFail(
						"Unable to wait and click on the WebElement, using locator: " + "<" + element.toString() + ">");
			}
			attempts++;
		}
	}

	/**********************************************************************************/
	/**********************************************************************************/

	/**********************************************************************************
	 ** ACTION METHODS
	 **********************************************************************************/

	public void actionMoveAndClickByLocator(By element) throws IOException {
		Actions ob = new Actions(driver);
		try {
			Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
			if (Boolean.TRUE.equals(elementPresent)) {
				WebElement elementToClick = driver.findElement(element);
				ob.moveToElement(elementToClick).click().build().perform();
				AssertionUtil.assertInfo("Action moved and clicked on the following element, using By locator : " + "<"
						+ element.toString() + ">");
			}
		} catch (StaleElementReferenceException elementUpdated) {
			WebElement elementToClick = driver.findElement(element);
			ob.moveToElement(elementToClick).click().build().perform();
			AssertionUtil.assertInfo(
					"(Stale Exception) - Action moved and clicked on the following element , using By locator: " + "<"
							+ element.toString() + ">");
		} catch (Exception e) {
			AssertionUtil.assertFail(
					"Unable to Action Move and Click on the WebElement using by locator, Exception:" + e.getMessage());
		}
	}

	public void actionMoveAndClickByWebElement(WebElement element) throws IOException {
		Actions ob = new Actions(driver);
		try {
			Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
			if (Boolean.TRUE.equals(elementPresent)) {
				WebElement elementToClick = element;
				ob.moveToElement(elementToClick).click().build().perform();
				driver.navigate().refresh();
				AssertionUtil.assertInfo("Action moved and clicked on the following element, using By locator: " + "<"
						+ element.toString() + ">");
			}
		} catch (StaleElementReferenceException elementUpdated) {
			WebElement elementToClick = element;
			ob.moveToElement(elementToClick).click().build().perform();
			AssertionUtil.assertInfo(
					"(Stale Exception) - Action moved and clicked on the following element, using By locator: " + "<"
							+ element.toString() + ">");
		} catch (Exception e) {
			AssertionUtil.assertFail(
					"Unable to Action Move and Click on the WebElement using by locator, Exception: " + e.getMessage());
		}
	}

	public boolean isElementDisplayed(WebElement element) {
		Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(element)).isDisplayed();
		if (Boolean.TRUE.equals(elementPresent)) {
			AssertionUtil.assertInfo("Element is displayed: " + "<" + element.toString() + ">");
		} else {
			AssertionUtil.assertInfo("Element is not displayed: " + "<" + element.toString() + ">");
		}
		return elementPresent;
	}

	public void actionMoveToByLocator(By element) throws IOException {
		Actions ob = new Actions(driver);
		try {
			Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
			if (Boolean.TRUE.equals(elementPresent)) {
				WebElement elementToClick = driver.findElement(element);
				ob.moveToElement(elementToClick).build().perform();
				AssertionUtil.assertInfo(
						"Action moved and to following element, using By locator: " + "<" + element.toString() + ">");
			}
		} catch (StaleElementReferenceException elementUpdated) {
			WebElement elementToClick = driver.findElement(element);
			ob.moveToElement(elementToClick).build().perform();
			AssertionUtil.assertInfo(
					"Action moved and to following element, using By locator: " + "<" + element.toString() + ">");
		} catch (Exception e) {
			AssertionUtil
					.assertFail("Unable to Action Move to WebElement using by locator, Exception: " + e.getMessage());
		}
	}

	public void actionMoveAndDoubleClickByLocator(By element) throws IOException {
		Actions ob = new Actions(driver);
		try {
			Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
			if (Boolean.TRUE.equals(elementPresent)) {
				WebElement elementToClick = driver.findElement(element);
				ob.moveToElement(elementToClick).doubleClick().build().perform();
				AssertionUtil.assertInfo("Action moved and clicked on the following element, using By locator: " + "<"
						+ element.toString() + ">");
			}
		} catch (StaleElementReferenceException elementUpdated) {
			WebElement elementToClick = driver.findElement(element);
			ob.moveToElement(elementToClick).click().build().perform();
			AssertionUtil.assertInfo(
					"(Stale Exception) - Action moved and clicked on the following element, using By locator: " + "<"
							+ element.toString() + ">");
		} catch (Exception e) {
			AssertionUtil.assertFail(
					"Unable to Action Move and Click on the WebElement using by locator, Exception: " + e.getMessage());
		}
	}

	/**********************************************************************************/
	/**********************************************************************************/

	/**********************************************************************************
	 ** SEND KEYS METHODS /
	 **********************************************************************************/
	public void sendKeysToWebElement(WebElement element, String textToSend) throws IOException {
		try {
			this.waitUntilWebElementIsVisible(element);
			element.clear();
			element.sendKeys(textToSend);
			AssertionUtil.assertInfo("Successfully Sent the following keys: '" + textToSend + "' to element: " + "<"
					+ element.toString() + ">");
		} catch (Exception e) {
			AssertionUtil.assertFail("Unable to send keys to WebElement, Exception: " + e.getMessage());
		}
	}

	/**********************************************************************************/
	/**********************************************************************************/

	/**********************************************************************************
	 ** WAIT METHODS
	 **********************************************************************************/
	public boolean waitUntilWebElementIsVisible(WebElement element) throws IOException {
		try {
			this.wait.until(ExpectedConditions.visibilityOf(element));
			AssertionUtil.assertInfo("WebElement is visible using locator: " + "<" + element.toString() + ">");
			return true;
		} catch (Exception e) {
			AssertionUtil.assertFail("WebElement is NOT visible, Exception: " + e.getMessage());
			return false;
		}
	}

	/**********************************************************************************/
	/**********************************************************************************/

	/**********************************************************************************
	 ** PAGE METHODS
	 **********************************************************************************/
	public BasePage loadUrl(String url) {
		driver.get(url);
		AssertionUtil.assertInfo("Url opened: " + url);
		return new BasePage();
	}

	public String getCurrentURL() throws IOException {
		try {
			String url = driver.getCurrentUrl();
			AssertionUtil.assertInfo("Found(Got) the following URL: " + url);
			return url;
		} catch (Exception e) {
			AssertionUtil.assertFail("Unable to locate (Get) the current URL, Exception: " + e.getMessage());
			return e.getMessage();
		}
	}

	/**********************************************************************************/
	/**
	 * Alert METHODS
	 ********************************************************************************/
	public void checkAlertAndAccept() throws IOException, InterruptedException {
		if (isAlertPresent()) {
			driver.switchTo().alert();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Thread.sleep(1000);
			AssertionUtil.assertInfo("Alert accepted.");
		} else {
			AssertionUtil.assertFail("Alert not present.");
		}
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getInvalidMessage(WebElement ele) {
		wait.until(ExpectedConditions.textToBePresentInElement(ele, "Invalid username or password!"));
		return ele.getText();
	}

	/**********************************************************************************/
	/**
	 * To Return error message displayed for blank field using WebElement
	 ********************************************************************************/

	public String getBlankfieldMessage(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (String) js.executeScript("return arguments[0].validationMessage;", ele);
	}
}
