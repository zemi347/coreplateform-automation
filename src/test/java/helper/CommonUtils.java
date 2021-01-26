package helper;

import com.relevantcodes.extentreports.LogStatus;
import framework.Constants;
import framework.WaitTypes;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.*;
import java.time.Duration;
import java.util.*;

import static org.testng.Assert.*;


public class CommonUtils {
	WebDriver driver;

	// Initialize Page Elements
	public CommonUtils(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public static Properties configdatafile(String filename)
	{
		Properties Prop =new Properties();
		File datafile = new File( System.getProperty("user.dir") + "/Util/"+filename);
		FileInputStream inputfilestream = null;
		try {
			inputfilestream = new FileInputStream(datafile);
		} catch (FileNotFoundException e) {

		}
		try {
			Prop.load(inputfilestream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Prop;
	}

	public void leftClickElement(WebElement element) throws Exception {
		Thread.sleep(1000);
		WaitTypes.waitUntilElementIsVisible(element);
		Constants.LeftClick(element);
	}


//	public void dragAndDropLayers(WebElement source, int xOffset, int yOffset ) throws InterruptedException {
//		Actions actions = new Actions(driver);
//		actions.dragAndDropBy(source, xOffset, yOffset).build().perform();
//	}

	public void dragAndDropLayers(String layer) throws InterruptedException {
		Actions actions = new Actions(driver);
		Thread.sleep(7000);
		WebElement source = driver.findElement(By.xpath("//h4[contains(text(),'Material Layers')]//parent::div//following-sibling::md-list//md-list-item//div//span//strong[contains(text(), '"+layer+"')]//parent::span//parent::div//parent::md-list-item"));
//		System.out.println(source);
		WebElement destination = driver.findElement(By.xpath("//h4[contains(text(),'Materials in prolonged skin contact')]//parent::div//following-sibling::md-input-container[1]//md-list"));
//		System.out.println(destination);
//		actions.dragAndDrop(source,destination).build().perform();
//		List<WebElement> s2 = driver.findElement(By.xpath("//h4[contains(text(),'Material Layers')]//parent::div//following-sibling::md-list//md-list-item//div//span//strong[contains(text(), 'Blue leather')]//parent::span//parent::div//parent::md-list-item"));
//		listOfColumnHeaders



		Thread.sleep(5000);
		actions.clickAndHold(source).build().perform();
		Thread.sleep(5000);
		actions.moveToElement(destination);
		Thread.sleep(10000);
		actions.release(source).build().perform();

//
//		Point destepoint = destination.getLocation();
//		int destX = destepoint.getX();
//		int destY = destepoint.getY();
//		System.out.println("dest element X -> "+destX+" dest element Y -> "+destY);
//
//		Point sourcepoint = source.getLocation();
//		int sourceX = sourcepoint.getX();
//		int sourceY = sourcepoint.getY();
//		System.out.println("source element X -> "+sourceX+" source element Y -> "+sourceY);

	}

	public void verifyElementsAreDisplayedWithScrolling(WebElement... elements) throws InterruptedException {
		for (WebElement element : elements) {
			// highlightElements(driver, "red", "5px solid", element);
			Constants.scrollToElement(element);
			WaitTypes.waitUntilElementIsVisible(element);
		}
	}

	public void verifyElementsAreDisplayed(WebElement... elements) throws InterruptedException {
		for (WebElement element : elements) {
			// highlightElements(driver, "red", "5px solid", element);
			WaitTypes.waitUntilElementIsVisible(element);
		}
	}

	public void verifyElementsAreDisplayedWithIsDisplayed(WebElement... elements) throws InterruptedException {
		for (WebElement element : elements) {
			element.isDisplayed();
		}
	}

	public void verifyElementsAreNotDisplayed(WebElement... elements) throws Exception {
		List<WebElement> myElementsList = Arrays.asList(elements);
		WaitTypes.waitUntilElementsAreInvisible(myElementsList);
	}

	public void verifyElementsAreNotDisplayedBySleep(WebElement... elements) throws Exception {
		List<WebElement> myElementsList = Arrays.asList(elements);
		//WaitTypes.waitUntilElementIsVisible(myElementsList);
		WaitTypes.waitUntilElementsAreInvisible(myElementsList);
	}

	public void verifyElementsDoNotExist(WebElement... elements) throws InterruptedException {
		Thread.sleep(1000);
		for (WebElement element : elements) {
			// Boolean isNOTPresent =
			// driver.findElements(By.xpath(Constants.getLocatorFromWebElement(element)))
			// .size() < 1;
			// Assert.assertTrue(isNOTPresent);
			// above implementation commented out and left for a comparison with
			// below implementation
			String locator = Constants.getLocatorFromWebElement(element);
			WaitTypes.waitUntilElementNotExistsInDom(locator);

		}
	}

	public Boolean isPresent(WebElement element) throws InterruptedException {
		Thread.sleep(1000);
		Boolean isPresent = driver.findElements(By.xpath(Constants.getLocatorFromWebElement(element))).size() > 0;
		return isPresent;
	}

	public void clickElementsWithJavascript(WebElement... elements) throws Exception {
		for (WebElement element : elements) {
			Thread.sleep(200);
			Constants.javascriptClickWebElement(element);
		}
	}

	public void clickGraphicElements(WebElement... elements) throws Exception {
		for (WebElement element : elements) {
			Actions builder = new Actions(driver);
			builder.click(element).build().perform();
		}
	}

	public void clickGraphicElementsWithCoordinates(int x, int y, WebElement... elements) throws Exception {
		for (WebElement element : elements) {
			Actions builder = new Actions(driver);
			builder.moveToElement(element, x, y).click().build().perform();
		}
	}

	public void doubleClickOnElements(WebElement... elements) throws Exception {
		for (WebElement e : elements) {
			Constants.doubleClickOnElement(e);
		}
	}

	public void doubleClickOnCellElements(WebElement... elements) throws Exception {
		for (WebElement e : elements) {
			Actions action = new Actions(driver);
			// Double click
			action.doubleClick(e).perform();
		}
	}

	public void rightClickElement(WebElement element) throws Exception {
		Thread.sleep(1000);
		WaitTypes.waitUntilElementIsVisible(element);
		Constants.rightClick(element);
	}

	public void rightClickElementAndVerifyItemsExist(WebElement element, WebElement... elements) throws Exception {
		rightClickElement(element);
		verifyElementsAreDisplayed(elements);
	}

	public void rightClickElementAndVerifyItemsDoNotExist(WebElement element, WebElement... elements) throws Exception {
		rightClickElement(element);
		verifyElementsDoNotExist(elements);
	}

	public void rightClickElementAndClickContextElement(WebElement element, WebElement contextElement)
			throws Exception {
		rightClickElement(element);
		WaitTypes.clickWhenClickable(contextElement);
	}

	public void rightClickElementAndVerifyContextElements(WebElement element, WebElement... contextElements)
			throws Exception {
		rightClickElement(element);
		verifyElementsAreDisplayed(contextElements);
	}

	public void scrollToElement(WebElement element) throws InterruptedException {
		Constants.scrollToElement(element);
	}

	public void scrollTable(int numberOfRows) throws InterruptedException {
		for (int i = 0; i < numberOfRows; i++) {
			String xpath = "//*[@row='" + i + "'][@col='0']";
			WebElement element = driver.findElement(By.xpath(xpath));
			Constants.scrollToElement(element);
		}
	}

	public void scrollTableColumns(int numberOfColumns) throws InterruptedException {
		for (int i = 0; i < numberOfColumns; i++) {
			String xpath = "//*[@row='0'][@col='" + i + "']";
			WebElement element = driver.findElement(By.xpath(xpath));
			Constants.scrollToElement(element);
		}
	}

	public void scrollToAndClickElement(WebElement element) {
		Constants.scrollToElement(element);
		element.click();
	}

	public void scrollToAndVerifyElement(WebElement element) throws InterruptedException {
		Constants.scrollToElement(element);
		verifyElementsAreDisplayed(element);
	}

	public void keyboardPress(Keys key) throws InterruptedException {
		Actions action = new Actions(driver);
		action.sendKeys(key).build().perform();
		Thread.sleep(300);
	}

	public void keyboardInput(String value) throws InterruptedException {
		Actions action = new Actions(driver);
		action.sendKeys(value).build().perform();
		Thread.sleep(300);
	}

	public void controlDoubleKeyOnElement(WebElement element, String key, Keys key2) throws InterruptedException {

		WaitTypes.clickWhenClickable(element);
		Actions action = new Actions(driver);

		if (Constants.OSDetector().equals("Mac")) {
			action.keyDown(Keys.COMMAND).sendKeys(key).sendKeys(key2).keyUp(Keys.COMMAND).build().perform();
		} else {
			action.keyDown(Keys.CONTROL).sendKeys(key).sendKeys(key2).keyUp(Keys.CONTROL).build().perform();
		}
	}

	public void controlCmdOnElement(WebElement element, String key) throws InterruptedException {
		WaitTypes.clickWhenClickable(element);
		Actions action = new Actions(driver);
		if (Constants.OSDetector().equals("Mac")) {
			action.keyDown(Keys.COMMAND).sendKeys(key).keyUp(Keys.COMMAND).build().perform();
		} else {
			action.keyDown(Keys.CONTROL).sendKeys(key).keyUp(Keys.CONTROL).build().perform();
		}
	}

	public void controlOrCmdPressClickOnElement(WebElement element) throws InterruptedException {
		Actions action = new Actions(driver);
		if (Constants.OSDetector().equals("Mac")) {
			action.keyDown(Keys.COMMAND).click(element).keyUp(Keys.COMMAND).build().perform();
		} else {
			action.keyDown(Keys.CONTROL).click(element).keyUp(Keys.CONTROL).build().perform();
		}
	}

	public void keyOnElement(WebElement element, String key) throws InterruptedException {
		WaitTypes.clickWhenClickable(element);
		Actions action = new Actions(driver);
		action.sendKeys(key).build().perform();
	}

	public void sendKeyOnElement(WebElement element, CharSequence key) throws InterruptedException {
		WaitTypes.clickWhenClickable(element);
		element.sendKeys(key);
	}

	public void multipleKeyboardArrowPress(String direction, int times) throws InterruptedException {
		Constants.pressArrowKey(direction, times);
	}

	public void verifyElementIsSelected(WebElement element) throws InterruptedException {
		WaitTypes.waitUntilElementIsVisible(element);
		assertTrue(element.isSelected());
	}

	public void verifyElementIsNotSelected(WebElement element) throws InterruptedException {
		WaitTypes.waitUntilElementIsVisible(element);
		assertFalse(element.isSelected());
	}

	public void verifyElementsAreNotSelected(WebElement... elements) throws Exception {
		for (WebElement e : elements) {
			WaitTypes.waitUntilElementIsVisible(e);
			assertFalse(e.isSelected());
		}
	}

	public void verifyElementIsEnabled(WebElement element) throws InterruptedException {
		WaitTypes.waitUntilElementIsVisible(element);
		assertTrue(element.isEnabled());
	}

	public void verifyElementIsDisabled(WebElement element) throws InterruptedException {
		WaitTypes.waitUntilElementIsVisible(element);
		assertFalse(element.isEnabled());
	}

	public void verifyElementsAreDisabled(WebElement... elements) throws InterruptedException {
		List<WebElement> myElementsList = Arrays.asList(elements);
		WaitTypes.waitUntilElementsAreVisible(myElementsList);
		for (WebElement e : elements) {
			assertFalse(e.isEnabled());
		}
	}

	public void verifyElementsAreEnabled(WebElement... elements) throws InterruptedException {
		List<WebElement> myElementsList = Arrays.asList(elements);
//		WaitTypes.waitUntilElementsAreVisible(myElementsList);
		for (WebElement e : elements) {
			assertTrue(e.isEnabled());
		}
	}

	public void verifyElementsAreVisable(WebElement... elements) throws InterruptedException {
		WaitTypes.waitUntilElementsAreVisible(Arrays.asList(elements));
	}

	public void verifyElementsHaveDisabledAttribute(WebElement... elements) throws InterruptedException {
		List<WebElement> myElementsList = Arrays.asList(elements);
		WaitTypes.waitUntilElementsAreVisible(myElementsList);
		for (WebElement e : elements) {
			String disabledValue = e.getAttribute("disabled");
			Assert.assertEquals(disabledValue.compareTo("true"), 0);

		}
	}

	public void verifyElementsHaveNgDisabledAttribute(WebElement... elements) throws InterruptedException {
		List<WebElement> myElementsList = Arrays.asList(elements);
		WaitTypes.waitUntilElementsAreVisible(myElementsList);
		Thread.sleep(1000);
		for (WebElement e : elements) {
			Thread.sleep(1000);
			String disabledValue = e.getAttribute("ng-disabled");
			Assert.assertEquals(disabledValue.compareTo("true"), 0);

		}
	}

	public void verifyElementsHaveAriaCheckedAttributeTrue(WebElement... elements) throws InterruptedException {
		List<WebElement> myElementsList = Arrays.asList(elements);
		WaitTypes.waitUntilElementsAreVisible(myElementsList);
		for (WebElement e : elements) {
			String Value = e.getAttribute("aria-checked");
			Assert.assertEquals(Value.compareTo("true"), 0);
		}
	}


	public void verifyElementsHaveAttributeWhichDoesNotContainValue(String attribute, String value,
																	WebElement... elements) throws InterruptedException {
		// List<WebElement> myElementsList = Arrays.asList(elements);
		for (WebElement element : elements) {
			String myAttributeValue = element.getAttribute(attribute);
			// Assert.assertEquals(myValue.contains(value), true);
			assertFalse(myAttributeValue.contains(value));
		}
	}

	public void verifyParentElementsAreDisabled(WebElement... elements) throws InterruptedException {
		List<WebElement> myElementsList = Arrays.asList(elements);
		WaitTypes.waitUntilElementsAreVisible(myElementsList);
		for (WebElement e : elements) {
			assertFalse(Constants.getParentElementByElement(e).isEnabled());
		}
	}

	public void clickParentElement(WebElement element) throws InterruptedException {
		WaitTypes.clickWhenClickable(Constants.getParentElementByElement(element));
	}

	public void doubleClickOpenAndVerifyItem(WebElement fileSystemItem, WebElement title) throws InterruptedException {

		Thread.sleep(1000);
		WaitTypes.clickWhenClickable(fileSystemItem);
		Thread.sleep(1000);
		Constants.doubleClickOnElement(fileSystemItem);
		Thread.sleep(1000);
		WaitTypes.waitUntilElementIsVisible(title);

	}

	public void singleClickOpenAndVerifyItem(WebElement fileSystemItem, WebElement title) throws InterruptedException {

		Thread.sleep(1000);
		WaitTypes.clickWhenClickable(fileSystemItem);
		Thread.sleep(1000);
		WaitTypes.waitUntilElementIsVisible(title);

	}

	public void assertElementHasText(WebElement element, String text) throws InterruptedException {
		verifyElementsAreDisplayed(element);
		assertEquals(element.getText(), text, "Text do not match!");
	}


	public void writeValueToField(WebElement element, String text) throws InterruptedException {
		WaitTypes.clickWhenClickable(element);
		element.clear();
		element.sendKeys(text);
	}

	public void verifyFieldIsNotEditable(WebElement element) throws InterruptedException {

		try {
			WaitTypes.clickWhenClickable(element);
			element.sendKeys("Is this editable??");
			waitFor(Duration.ofSeconds(1));
			verifyElementDoesNotContainText(element, "Is this editable??");
		} catch (WebDriverException e) {
			assertTrue(e.getMessage().contains("unknown error: cannot focus element"));

		}

	}

	public void sendKeysStringValueToWebelement(String value, WebElement element) throws InterruptedException {
		WaitTypes.clickWhenClickable(element);
		element.clear();
		element.sendKeys(value);
	}

	public void clearFields(WebElement... elements) throws InterruptedException {
		for (WebElement element : elements) {
			verifyElementsAreDisplayed(elements);
			element.clear();
		}
	}

	public void verifyElementDoesNotContainText(WebElement element, String text) throws InterruptedException {
		Thread.sleep(1000);
		assertTrue(!element.getText().contains(text), "Contains is contained in element");
	}

	public void verifyElementHasText(WebElement element, String text) throws InterruptedException {
		verifyElementsAreDisplayed(element);
		Thread.sleep(2000);
		assertEquals(element.getText().trim(), text.trim(), "Text do not match!");
	}

	public void verifyElementContainsText(WebElement element, String text) throws InterruptedException {
		verifyElementsAreDisplayed(element);
		Thread.sleep(1000);
		assertTrue(element.getText().toLowerCase().contains(text.toLowerCase()), "Expected: '" + text + "' Actual: '"
				+ element.getText() + "'");
	}

	public void verifyElementsArePopulated(WebElement... elements) throws InterruptedException {
		for (WebElement element : elements) {
			verifyElementsAreDisplayed(element);
			Thread.sleep(1000);
			assertNotEquals(element.getText().length(), 0);
		}
	}

	public void verifyWithJavascriptThatElementHasText(WebElement element, String expectedTextValue) {
		String actualTextValue = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].value;",
				element);
		// System.out.println(actualTextValue);
		assertEquals(actualTextValue, expectedTextValue, "Text values does not match");
	}

	public void verifyWithJavascriptThatElementHasNotText(WebElement element, String expectedTextValue) {
		String actualTextValue = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].value;",
				element);
		assertNotEquals(actualTextValue, expectedTextValue);
	}

	public void switchBetweenTwoWindows() throws Exception {
		Constants.switchWindow();
	}

	public void closeBrowserTab() throws InterruptedException {
		Constants.closeWindow();
	}

	public void waitFor(Duration timeSpan) throws InterruptedException {
		Thread.sleep(timeSpan.getSeconds() * 1000);
	}

	public void executeRunExeFile(String pathToExeFile) throws IOException {
		Runtime.getRuntime().exec(pathToExeFile);
	}
	public void waitRefreshWaitUntilDisplayed(WebElement element, int iterations, int secondsToWaitAfterRefresh)
			throws Exception {
		for (int i = 0; i < iterations; i++) {
			if (isPresent(element)) {
				break;
			} else {
				Constants.refreshPage();
				Thread.sleep(secondsToWaitAfterRefresh);
			}
		}
	}

	public void refreshPage() throws InterruptedException {
		Constants.refreshPage();
	}
	public void NavigatePage(String Url) throws InterruptedException {
		Constants.navigatPage(Url);
	}

	public static void assertDropDownOptionValueIsNotPresent(WebElement element, String value) throws InterruptedException {
		WaitTypes.waitUntilElementIsVisible(element);
		Select dpt = new Select(element);
		List<WebElement> allOptions = dpt.getOptions();
		for (WebElement option : allOptions) {
			// System.out.println(option.getAttribute("value"));
			// System.out.println(value);
			assertFalse(option.getAttribute("value") == value);
		}
	}

	public static boolean doesElementHaveClass(WebElement element, String className) {
		String classes = element.getAttribute("class");
		for (String cls : classes.split(" ")) {
			if (cls.equals(className)) {
				return true;
			}
		}
		return false;
	}

	public void hooverOverElement(WebElement element) {
		Constants.mouseHoover(element);
	}


	public void waitUntilElementIsClickable(WebElement element) {
		WaitTypes.waitUntilElementIsClickable(element);
	}

	public void waitThenDoubleClick(WebElement element, int timeoutSeconds) throws Exception {
		WaitTypes.fluentWaitFindByWebElement(driver, element, timeoutSeconds);
		Thread.sleep(2000);
		doubleClickOnElements(element);
	}


	public void verifyListContainsCaseInsensitiveText(List<WebElement> list, String text) throws InterruptedException {
		for (int i = 0; i < list.size(); i++) {
			String optionValue = list.get(i).getAttribute("aria-label");
			assertTrue(optionValue.toLowerCase().contains(text.toLowerCase()));

		}
	}
	public void compareTwoLists(List<WebElement> webelements, List<String> webelementsTextValues)
			throws InterruptedException {
		verifyElementsAreDisplayed(webelements.get(0));
		for (int i = 0; i < webelements.size(); i++) {
//			 System.out.print("\"" + webelements.get(i).getText() + "\", ");
			Boolean b = replaceLineSeparator(webelements.get(i), "|").equals(webelementsTextValues.get(i));
			if (b != true) {
				System.out.println("  Actual: " + replaceLineSeparator(webelements.get(i), "|"));
				System.out.println("Expected: " + webelementsTextValues.get(i));
			}
			assertTrue(b);
			// Assert.assertEquals(webelements.get(i).getText(), webelementsTextValues.get(i));
			// Assert.assertEquals(webelements.get(i).getText(), webelementsTextValues.get(i));
		}
//		 System.out.println("\n" + "--new_line--");
	}

	public void compareTwoListsWithContains(List<WebElement> webelements, List<String> webelementsTextValues)
			throws InterruptedException {
		verifyElementsAreDisplayed(webelements.get(0));
		for (int i = 0; i < webelements.size(); i++) {
			// System.out.print("\"" + webelements.get(i).getText() + "\", ");
			Boolean b = webelements.get(i).getText().contains(webelementsTextValues.get(i));
			if (b != true) {
				System.out.println("  Actual: " + webelements.get(i).getText());
				System.out.println("Expected: " + webelementsTextValues.get(i));
			}
			assertTrue(b);
			// System.out.println(" Actual: " +
			// webelements.get(i).getText().replace(System.lineSeparator(), "
			// "));
			// System.out.println("Expected: " +
			// webelementsTextValues.get(i).replace(System.lineSeparator(), "
			// "));
			// System.out.println();
		}
		// System.out.println("\n" + "--new_line--");
	}

	public void printTextsOfListOfWebelements(List<WebElement> elements) {
		for (WebElement element : elements) {
			System.out.println(replaceLineSeparator(element.getText(), " | "));
		}
	}

	public void printListOfTextsOfString(List<String> elements) {
		for (String element : elements) {
			// System.out.print("\"" +
			// element.replaceAll(System.lineSeparator(), "\" + ls + \"") + "\",
			// ");
			System.out.print("\"" + element + "\", ");
		}
	}

	public void printTextOfWebelements(List<WebElement> elements) {
		for (WebElement element : elements) {
			System.out.print("\"" + replaceLineSeparator(element, "|") + "\", ");
		}

	}

	public void sleep(int seconds) throws InterruptedException {
		Thread.sleep(seconds * 1000);
	}

	public String getElementText(WebElement element) {
		return element.getText();
	}

	public void changeAttributeWithJavascript(WebDriver driver, String attribute, String value,
											  WebElement... elements) {
		for (WebElement element : elements) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('" + attribute + "', '" + value + "')", element);
		}
	}

	public void verifyOneOfElementsIsDisplayed(List<WebElement> webelements) throws InterruptedException {
		for (WebElement webelement : webelements) {
			if (webelement.getCssValue("display").equals("inline")) {
				verifyElementsAreDisplayed(webelement);
			}
		}
	}

	public void verifyOneOfElementsIsEnabled(List<WebElement> webelements) throws InterruptedException {
		for (WebElement webelement : webelements) {
			if (webelement.getCssValue("display").equals("inline")) {
				verifyElementsAreEnabled(webelement);
			}
		}
	}

	public WebElement findByXpathContainsText(String text) {
		String xpath = "//*[contains(text(),\"" + text + "\")]";
		return driver.findElement(By.xpath(xpath));
	}

	public void compareTwoStringsWithContains(String string1, String string2) {
		Boolean b = string1.contains(string2);
		if (b != true) {
			System.out.println("  Actual: " + string1);
			System.out.println("Expected: " + string2);
		}
		assertTrue(b);
	}

	public void compareTwoStrings(String string1, String string2) {
		Boolean b = string1.equals(string2);
		if (b != true) {
			System.out.println("  Actual: " + string1);
			System.out.println("Expected: " + string2);
		}
		assertTrue(b);
	}
	public void writeToMultipleItemsByIndexesFromListOfItems(List<WebElement> elements, List<Integer> indexes,
															 List<String> value) throws Exception {
		int valueIndex = 0;
		for (int index : indexes) {
			writeValueToField(elements.get(index), value.get(valueIndex++));
		}
	}

	public void setValueInTableCell(WebElement cell, String value) throws InterruptedException {
		WaitTypes.clickWhenClickable(cell);
		keyboardPress(Keys.F2);
		String xpath = "//div[contains(@class,'handsontableInputHolder')][contains(@style, 'display: block')]//textarea";
		WebElement textAreaElement = driver.findElement(By.xpath(xpath));
		textAreaElement.sendKeys(value);
		Thread.sleep(500);
		keyboardPress(Keys.TAB);
		Thread.sleep(500);
	}

	public String replaceLineSeparator(WebElement element, String replacementString) {
		return element.getText().replace("\n", replacementString).replace("\r", replacementString);
	}

	public String replaceLineSeparator(String text, String replacementString) {
		return text.replace("\n", replacementString).replace("\r", replacementString);
	}

	public String replaceWithRegex(WebElement element, String regex, String replacementString) {
		return element.getText().replaceAll(regex, replacementString);
	}

	public String replaceWithRegex(String text, String regex, String replaceWith) {
		return text.replaceAll(regex, replaceWith);
	}


	// PRINT

	public void printElementAttributeValue(WebElement element, String... attributes) {
		for (String attribute : attributes) {
			System.out.println(element.getAttribute(attribute));
		}
	}


	public void writeValueToField(String ItemIdentifier, WebElement element) throws InterruptedException {
		WaitTypes.clickWhenClickable(element);
		element.clear();
		element.sendKeys(ItemIdentifier.toString());
	}




	// WAIT

	public void waitUntilElementsAreInvisibleOrNotPresentInDom(int timeoutSeconds, WebElement... elements) {
		for (WebElement element : elements) {
			WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(Constants.getLocatorFromWebElement(element))));
		}
	}

	public void waitUntilElementIsInvisibleOrNotPresentInDom(int timeoutSeconds, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(Constants.getLocatorFromWebElement(element))));
	}



	public void verifyListOfElementsAreDisabled(List<WebElement> list) throws InterruptedException {
		for (WebElement element : list) {
			verifyElementsHaveAttributeWithValue("disabled", "true", element);
		}
	}

	public void clickOnDisplayedElementOutOfMultiple(List<WebElement> webelements) throws Exception {
		WebElement displayedWebelement = null;
		for (WebElement webelement : webelements) {
			if (webelement.getCssValue("display").equals("inline")) {
				displayedWebelement = webelement;
			}
		}
		clickElements(displayedWebelement);
	}


	public void verifyElementsHaveAttributeWithValue(String attribute, String expectedValue, WebElement... elements)
			throws InterruptedException {
		for (WebElement element : elements) {
			highlightElements(driver, "yellow", "5px solid", element);
			String foundValue = element.getAttribute(attribute);
			Boolean b = foundValue.equals(expectedValue);
			if (!foundValue.equals(expectedValue)) {
				System.out.println(element);
				System.out.println(element.getText());
				System.out.println("found: " + foundValue + " but expected: " + expectedValue);
				assertEquals(foundValue, expectedValue);
			}
		}
	}



	public void verifyElementsHaveAttributeWhichContainsValue(String attribute, String expectedValue,
															  WebElement... elements) throws InterruptedException {
		for (WebElement element : elements) {
			highlightElements(driver, "yellow", "5px solid", element);
			String foundValue = element.getAttribute(attribute);
			Boolean b = foundValue.contains(expectedValue);
			if (!foundValue.contains(expectedValue)) {
				System.out.println(element);
				System.out.println(element.getText());
				System.out.println("found: " + foundValue + " but expected: " + expectedValue);
				assertTrue(foundValue.contains(expectedValue));
			}
		}
	}

	public void refreshWaitClick(WebElement element, int secondsToWaitAfterRefresh) throws Exception {
		Constants.refreshPage();
		Thread.sleep(secondsToWaitAfterRefresh);
		clickElements(element);
	}



	public void hooverOverElement1ClickElement2(WebElement element1, WebElement element2) throws Exception {
		verifyElementsAreDisplayed(element1);
		hooverOverElement(element1);
		Thread.sleep(1000);
		clickElements(element2);
	}



	public void waitThenSingleClick(WebElement element, int timeoutSeconds) throws Exception {
		WaitTypes.fluentWaitFindByWebElement(driver, element, timeoutSeconds);
		Thread.sleep(2000);
		clickElements(element);
	}






	public void clickUntilDisplayed(WebElement elementToClick, WebElement elementToBeDisplayed) throws Exception {
		clickElements(elementToClick);
		if (!isPresent(elementToBeDisplayed)) {
			clickElements(elementToClick);
		}
	}



	public void clickMultipleItemsByIndexesFromListOfItems(List<WebElement> elements, List<Integer> indexes)
			throws Exception {
		for (int index : indexes) {
			clickElements(elements.get(index));
		}
	}




	@SuppressWarnings("unused")
	public void highlightElements(WebDriver driver, String myColor, String myBorder, WebElement... elements)
			throws InterruptedException {
			for (WebElement element : elements) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "border: " + myBorder
						+ " " + myColor + ";");
				// sleep(1);
				// js.executeScript("arguments[0].setAttribute('style',
				// arguments[1]);",
				// element, "");
			}
		}


	public void logOrPrint(String text) {
		Supertest.logger.log(LogStatus.PASS, text);
		{
			System.out.println("'" + text + "'");
		}
	}


	public void verifyElementHasText(WebElement element, WebElement item) throws InterruptedException {
		Thread.sleep(1000);
		// highlightElements(driver, "red", "5px solid", element);
		assertEquals(element.getText(), item.toString(), "Text do not match!");
	}

	public void verifyElementContainsText(WebElement element, WebElement item) throws InterruptedException {
		Thread.sleep(1000);
		assertTrue(element.getText().contains(item.toString()), "Text do not match!");
	}

	public void verifyElementDoesNotContainText(WebElement element, WebElement item) throws InterruptedException {
		Thread.sleep(1000);
		assertTrue(!element.getText().contains(item.toString()), "Contains is contained in element");
	}


	public void clickElements(WebElement... elements) throws Exception {
		for (WebElement elem : elements) {
			// scrollToElement(elem);
			{
				System.out.print("**" + elem.getTagName() + "**:");
				System.out.print(elem.getText() + ", ");
				highlightElements(driver, "red", "5px solid", elem);
			}
			WaitTypes.clickWhenClickable(elem);
			Thread.sleep(800);
		}
	}

	public void waitRefreshWaitClick(WebElement element, int iterations, int secondsToWaitAfterRefresh)
			throws Exception {
		for (int i = 0; i < iterations; i++) {
			if (isPresent(element)) {
				clickElements(element);
				break;
			} else {
				Constants.refreshPage();
				Thread.sleep(secondsToWaitAfterRefresh);
			}
		}
	}


}
