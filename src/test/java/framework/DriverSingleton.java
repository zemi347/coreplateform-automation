
package framework;

import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import helper.Configutil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.*;

public class DriverSingleton  {

	public static String seleniumGrid = "false";
	public static LoggingPreferences logPrefs = new LoggingPreferences();
	public static WebDriver driver;
	public static String BrowserX = "Chrome";//"Edge";//"Chrome";
	public static String baseurl = "https://staging.supermeal.ae";


	public static WebDriver setDriver() {
		String os = System.getProperty("os.name").toLowerCase();
		if (driver == null)
		{
			if(os.contains("win"))
			{
				System.out.println(os);
				OS_WIN();
			}
			else
			if(os.contains("linux"))
			{
				OS_Linux();
			}
		}
		return driver;
	}

	public static void OS_WIN() {
		try {

			if (BrowserX.equalsIgnoreCase("Chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				LoggingPreferences logPrefs = new LoggingPreferences();
				logPrefs.enable(LogType.BROWSER, Level.SEVERE);
				System.out.println("Using Google Chrome On Windows");

			} else if (BrowserX.equalsIgnoreCase("Firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				LoggingPreferences logPrefs = new LoggingPreferences();
				logPrefs.enable(LogType.BROWSER, Level.SEVERE);
				System.out.println("Using Mozilla Firefox On Windows");

			} else if (BrowserX.equalsIgnoreCase("Edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				LoggingPreferences logPrefs = new LoggingPreferences();
				logPrefs.enable(LogType.BROWSER, Level.SEVERE);
				System.out.println("Using Microsoft Edge On Windows");
			}
			else if (BrowserX.equalsIgnoreCase("IE")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				LoggingPreferences logPrefs = new LoggingPreferences();
				logPrefs.enable(LogType.BROWSER, Level.SEVERE);
				System.out.println("Using Internet Explorer On Windows");
			}
			driver.manage().window().maximize();
			driver.get(baseurl);

		} catch (Exception e) {
			e.getMessage();
		}

	}


	public static void OS_Linux() {
		try {
			if (BrowserX.equalsIgnoreCase("Chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--headless");
				chromeOptions.addArguments("start-maximized");
				chromeOptions.addArguments("disable-infobars");
				chromeOptions.addArguments("--disable-extensions");
				chromeOptions.addArguments("--disable-gpu");
				chromeOptions.addArguments("--disable-dev-shm-usage");
				chromeOptions.addArguments("--no-sandbox");
				driver = new ChromeDriver(chromeOptions);
				LoggingPreferences logPrefs = new LoggingPreferences();
				logPrefs.enable(LogType.BROWSER, Level.SEVERE);
				System.out.println("Using Google Chrome1 On Linux");

			}else
			if (BrowserX.equalsIgnoreCase("Firefox")){
				WebDriverManager.firefoxdriver().setup();
				FirefoxBinary firefoxBinary = new FirefoxBinary();
				firefoxBinary.addCommandLineOptions("--headless");
				firefoxBinary.addCommandLineOptions("start-maximized");
				firefoxBinary.addCommandLineOptions("disable-infobars");
				firefoxBinary.addCommandLineOptions("--disable-extensions");
				firefoxBinary.addCommandLineOptions("--disable-gpu");
				firefoxBinary.addCommandLineOptions("--disable-dev-shm-usage");
				firefoxBinary.addCommandLineOptions("--no-sandbox");
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.setBinary(firefoxBinary);
				driver = new FirefoxDriver(firefoxOptions);
				System.out.println("Using Mozilla Firefox : OS " + Constants.OSDetector());
			}
		} catch (Exception e) {

			System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
			String downloadFilepath = System.getProperty("downloadFilepath");
			HashMap<String, Object> chromePrefs = new HashMap<>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilepath);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("--no-sandbox");
			options.addArguments("--headless"); //should be enabled for Jenkins
			options.addArguments("--disable-dev-shm-usage"); //should be enabled for Jenkins
			options.addArguments("--window-size=1920x1080"); //should be enabled for Jenkins
			driver = new ChromeDriver(options);
			//driver.manage().window().setPosition(new Point(0, 0));
			//driver.manage().window().setSize(new Dimension(1920, 1080));
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.BROWSER, Level.SEVERE);
			System.out.println("Using Google Chrome2 On Linux");


		}

	}

	public static void OS_Mac() {
		try {
			if (BrowserX.equalsIgnoreCase("Chrome")) {
				WebDriverManager.chromiumdriver().setup();
				driver = new ChromeDriver();
				LoggingPreferences logPrefs = new LoggingPreferences();
				logPrefs.enable(LogType.BROWSER, Level.SEVERE);
				System.out.println("Using Google Chrome On Mac");
				driver.manage().window().maximize();

			} else if (BrowserX.equalsIgnoreCase("Firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				LoggingPreferences logPrefs = new LoggingPreferences();
				logPrefs.enable(LogType.BROWSER, Level.SEVERE);
				System.out.println("Using Mozilla Firefox On Mac");
				driver.manage().window().maximize();

			} else if (BrowserX.equalsIgnoreCase("Edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				LoggingPreferences logPrefs = new LoggingPreferences();
				logPrefs.enable(LogType.BROWSER, Level.SEVERE);
				System.out.println("Using Microsoft Edge On Mac");
				driver.manage().window().maximize();

			} else if (BrowserX.equalsIgnoreCase("IE")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				LoggingPreferences logPrefs = new LoggingPreferences();
				logPrefs.enable(LogType.BROWSER, Level.SEVERE);
				System.out.println("Using Internet Explorer On Mac");
				driver.manage().window().maximize();
			}
		} catch (Exception e) {
			e.getMessage();

		}

	}






	public void analyzeLog() {
		Properties prop = Configutil.configdatafile("log4j.properties");
		PropertyConfigurator.configure(prop);
		LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : logEntries) {
			System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());

		}
	}



}