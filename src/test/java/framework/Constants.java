package framework;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.interactions.internal.Coordinates;
//import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.Select;


public class Constants {

	static private WebDriver driver = DriverSingleton.setDriver();
	public static JavascriptExecutor executor = (JavascriptExecutor) driver;

	public static String matchedLabel = "Matched";
	public static String requiredLabel = "Required";

	public static String captureScreenshot(String screenshotName) {

		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			// String dest = "C:\\Workspace\\Automation\\Automation
			// Results\\Screenshots\\"+screenshotName+".png";
			String dest = System.getProperty("user.dir") + "test-output/automation-results/expert-models/screenshots/"
						+ screenshotName + ".png";
			File destination = new File(dest);

			FileUtils.copyFile(source, destination);
			return dest;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}

	}

	public static void scrollToElement(By locator) {
		WebElement element = driver.findElement(locator);
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);",element);

	}

	public static void scrollToElement(WebElement element) {
//		Coordinates coordinates = ((Locatable) element).getCoordinates();
//		coordinates.inViewPort();
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
	}

	public static void scrollToElementWithJavascript(WebElement element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
	}

	public static void scrollHandsontable(String direction) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (direction == "down") {
			js.executeScript("document.querySelectorAll('.wtHolder')[0].scrollTop = 500");
		} else if (direction == "up") {
			js.executeScript("document.querySelectorAll('.wtHolder')[0].scrollTop = -500");
		} else {
			System.out.println("Incorrect direction provided, only up & down are valid at present");
		}
	}

	public static void hozizontialScroll(WebElement element) {
		executor.executeScript("arguments[0].scrollIntoView()", element);
	}

	// See if Robot class can be used to automate system dialogs when importing
	// files
	public static void upLoadFileViaWindowsPrompt(String filePath) throws AWTException, InterruptedException {

		StringSelection ss = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();

		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		// Thread.sleep(2000);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_V);
		// Thread.sleep(2000);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(2000);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(4000);
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		robot.keyRelease(KeyEvent.VK_ENTER);

	}

	public static void refreshPage() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(2000);
	}

	public static void navigatPage(String Url) throws InterruptedException {
		driver.navigate().to(Url);
		Thread.sleep(2000);
	}
	// Dropdown selection
	public static void dropDownSelectByIndex(WebElement element, int index) throws InterruptedException {
		WaitTypes.waitUntilElementIsVisible(element);
		Select dpt = new Select(element);
		dpt.selectByIndex(index);
	}

	public static void dropDownSelectByNextOption(WebElement element) throws InterruptedException {
		WaitTypes.waitUntilElementIsVisible(element);
		Select dpt = new Select(element);
		dpt.getFirstSelectedOption();
	}

	public static void dropDownSelectByValue(WebElement element, String value) throws InterruptedException {
		WaitTypes.waitUntilElementIsVisible(element);
		Select dpt = new Select(element);
		dpt.selectByValue(value);
	}

	public static void deleteAllBrowserCookies() throws InterruptedException {
		driver.manage().deleteAllCookies();
		Thread.sleep(5000);
	}

	// Change method name to hooverOverElement
	public static void mouseHoover(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	public static void doubleClickOnElement(WebElement element) {
		WaitTypes.waitUntilElementIsClickable(element);
		Actions action = new Actions(driver);
		// Double click
		action.doubleClick(element).perform();
	}

	public static void dragAndDropHTML5() throws IOException, InterruptedException {

		try {
			final String JQUERY_LOAD_SCRIPT = "/src/test/em_a_util/jquery_load_helper.js";
			String jQueryLoader = readFile(JQUERY_LOAD_SCRIPT);

			driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeAsyncScript(jQueryLoader /* , http://localhost:8080/jquery-1.7.2.js */);

			// ready to rock
			js.executeScript("jQuery(function($) { "
				+ " $('input[name=\"q\"]').val('bada-bing').closest('form').submit(); " + " }); ");

			String filePath = "/src/test/em_a_util/drag_and_drop_helper.js";

			String source = "section#wrapper article ul li:nth-child(4) a";
			String target = "section#wrapper article div"; // #bin";

			StringBuffer buffer = new StringBuffer();
			String line;
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			while ((line = br.readLine()) != null)
				buffer.append(line);

			String javaScript = buffer.toString();

			javaScript = javaScript + "$('" + source + "').simulateDragDrop({ dropTarget: '" + target + "'});";
			((JavascriptExecutor) driver).executeScript(javaScript);

			Thread.sleep(1000);
			source = "section#wrapper article ul li:nth-child(2) a";
			javaScript = javaScript + "$('" + source + "').simulateDragDrop({ dropTarget: '" + target + "'});";
			((JavascriptExecutor) driver).executeScript(javaScript);

			Thread.sleep(1000);
			source = "section#wrapper article ul li:nth-child(1) a";
			javaScript = javaScript + "$('" + source + "').simulateDragDrop({ dropTarget: '" + target + "'});";
			((JavascriptExecutor) driver).executeScript(javaScript);

			br.close();

			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	private static String readFile(String file) throws IOException {

		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}

	}

	public static void rightClickAndSelectElement(WebElement element1, WebElement element2) {
		Actions action = new Actions(driver);
		action.contextClick(element1).moveToElement(element2).click().build().perform();
	}

	public static void rightClick(WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element).build().perform();
	}

	public static void LeftClick(WebElement element) {
		Actions action = new Actions(driver);
		action.click(element).build().perform();
	}

	public static void javascriptClickWebElement(WebElement element) {
		executor.executeScript("arguments[0].click();", element);
	}

	public static void findElementContainingText(String elementText) {
		driver.findElement(By.xpath(String.format("//*[text()[contains(., '%1s')]]", elementText)));
	}

	public static WebElement getParentElementByElement(WebElement element) {
		return (WebElement) executor.executeScript("return arguments[0].parentNode;", element);
	}

	public static void switchWindow() throws Exception {

		Thread.sleep(3000);
		String handleStr = driver.getWindowHandle();
		for (String handleStr2 : driver.getWindowHandles()) {
			if (!handleStr.equals(handleStr2)) {
				driver.switchTo().window(handleStr2);
			}
		}
	}

	public static void closeWindow() throws InterruptedException {

		String handleStr = driver.getWindowHandle();

		for (String handle : driver.getWindowHandles()) {
			if (!handleStr.equals(handle)) {
				driver.close();
				Thread.sleep(4000);
				driver.switchTo().window(handle);
			}
		}
	}

	public static void pressArrowKey(String direction, int times) throws InterruptedException {
		Actions action = new Actions(driver);
		for (int i = 0; i < times; i++) {
			if (direction.equals("left")) {
				action.sendKeys(Keys.ARROW_LEFT).build().perform();
			} else if (direction.equals("right")) {
				action.sendKeys(Keys.ARROW_RIGHT).build().perform();
			} else if (direction.equals("down")) {
				action.sendKeys(Keys.ARROW_DOWN).build().perform();
			} else if (direction.equals("up")) {
				action.sendKeys(Keys.ARROW_UP).build().perform();
			}
			Thread.sleep(500);
		}
	}

	public static void pressArrowKeyOnElement(String direction, int times, WebElement element)
		throws InterruptedException {
		for (int i = 0; i < times; i++) {
			if (direction.equals("left")) {
				element.sendKeys(Keys.ARROW_LEFT);
			} else if (direction.equals("right")) {
				element.sendKeys(Keys.ARROW_RIGHT);
			} else if (direction.equals("down")) {
				element.sendKeys(Keys.ARROW_DOWN);
			} else if (direction.equals("up")) {
				element.sendKeys(Keys.ARROW_UP);
			}
			Thread.sleep(500);
		}
	}

	public static String getLocatorFromWebElement(WebElement element) {

		String locator = "";
		if (element.toString().contains("xpath:")) {
			locator = element.toString().split("xpath: ")[1];
		} else if (element.toString().contains("id:")) {
			locator = element.toString().split("id: ")[1];
		}

		return locator.substring(0, locator.length() - 1);
	}

	public static String OSDetector() {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")) {
			return "Windows";
		} else if (os.contains("nux") || os.contains("nix")||os.contains("cen")) {
			return "Linux";
		} else if (os.contains("mac")) {
			return "Mac";
		} else if (os.contains("sunos")) {
			return "Solaris";
		} else {
			return "Other";
		}
	}
}
