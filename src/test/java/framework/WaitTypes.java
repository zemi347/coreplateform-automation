package framework;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

public class WaitTypes {

	final static int WAIT_TIME_OUT_GENERAL = 60;
	private static WebDriver driver = DriverSingleton.setDriver();

	/********************* Wait New Methods to support Selenium latest versions ******************/

	public static void waitUntilElementIsClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_OUT_GENERAL);
		//element = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void clickWhenClickable(WebElement element) throws InterruptedException {
		waitUntilElementIsClickable(element);
		Thread.sleep(650);
		element.click();
	}

	public static void waitUntilElementIsVisible(WebElement element)  throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_OUT_GENERAL);
		if (ExpectedConditions.invisibilityOfAllElements(element).toString() == "False"){
			wait.wait(1000);
		}
	}

	public static void waitUntilElementsAreVisible(List<WebElement> elements) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_OUT_GENERAL);
		//wait.until(ExpectedConditions.visibilityOfAllElements(elements));
		if (ExpectedConditions.visibilityOfAllElements(elements).toString() == "False"){
			wait.wait(1000);
		}
	}

	public static void waitUntilElementsAreInvisible(List<WebElement> element) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_OUT_GENERAL);
		//wait.until(ExpectedConditions.invisibilityOfAllElements(element));
		if (ExpectedConditions.invisibilityOfAllElements(element).toString() == "False"){
			wait.wait(1000);
		}
	}

	public static void waitUntilAttributeHasValue(WebElement element, String attribute, String value,
												  long timeOutInSeconds) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		// wait.until(ExpectedConditions.attributeToBe(element, attribute, value));
		if (ExpectedConditions.attributeToBe(element, attribute, value).toString() == "False"){
			wait.wait(1000);
		}
	}

	public static WebElement fluentWaitFindByWebElement(final WebDriver driver, final WebElement element,
														final int timeoutSeconds) {
		String locator = Constants.getLocatorFromWebElement(element);

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(100))
				.pollingEvery(Duration.ofMillis(600)).ignoring(NoSuchElementException.class);


		return wait.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver webDriver) {
				if (element.toString().contains("xpath")) {
					return driver.findElement(By.xpath(locator));
				} else {
					return driver.findElement(By.id(locator));
				}
			}
		});
	}

	public static void waitUntilElementNotExistsInDom(final String xpath) {

		Wait<WebDriver> wait = new WebDriverWait(driver, 600);
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver webDriver) {
				return webDriver.findElements(By.xpath(xpath)).size() <= 0;
			}
		});
	}


}
