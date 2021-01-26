
package framework;

import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import helper.Configutil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.*;

public class DriverSingleton  {

	public static String seleniumGrid = "false";
	public static LoggingPreferences logPrefs = new LoggingPreferences();
	public static WebDriver driver;
	public static String BrowserX = "Chrome";//"Edge";//"Chrome";
	public static String baseurl = "https://staging.supermeal.ae";


	public static WebDriver setDriver()
	{
		Properties prop = Configutil.configdatafile("Config.properties");
		String os = System.getProperty("os.name").toLowerCase();

		try {
			if (driver == null) {
				if (os.contains("windows"))
				{
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
					LoggingPreferences logPrefs = new LoggingPreferences();
					logPrefs.enable(LogType.BROWSER, Level.SEVERE);
				}
				else
				{
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

				}
			}
			driver.get(baseurl);
		}catch (Exception e) {
			e.getMessage();
		}

		return driver;
	}

			/*
				if (BrowserX.equalsIgnoreCase("Chrome")) {
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Driver\\chromedriver.exe");
					driver = new ChromeDriver();
					LoggingPreferences logPrefs = new LoggingPreferences();
					logPrefs.enable(LogType.BROWSER, Level.SEVERE);
					System.out.println("Using Google Chrome");

				}

				if (BrowserX.equalsIgnoreCase("Chrome")) {
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
					LoggingPreferences logPrefs = new LoggingPreferences();
					logPrefs.enable(LogType.BROWSER, Level.SEVERE);
					System.out.println("Using Google Chrome");
				}

				else if (BrowserX.equalsIgnoreCase("Firefox")) {
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver();
					LoggingPreferences logPrefs = new LoggingPreferences();
					logPrefs.enable(LogType.BROWSER, Level.SEVERE);
					System.out.println("Using Google Chrome");
				} else if (BrowserX.equalsIgnoreCase("Edge")) {
					WebDriverManager.edgedriver().setup();
					driver = new EdgeDriver();
					LoggingPreferences logPrefs = new LoggingPreferences();
					logPrefs.enable(LogType.BROWSER, Level.SEVERE);
					System.out.println("Using Microsoft Edge");
				}
				else if (BrowserX.equalsIgnoreCase("IE")) {
					WebDriverManager.iedriver().setup();
					driver = new InternetExplorerDriver();
					LoggingPreferences logPrefs = new LoggingPreferences();
					logPrefs.enable(LogType.BROWSER, Level.SEVERE);
					System.out.println("Using Internet Explorer");
				}

				driver.manage().window().maximize();
				driver.get(prop.getProperty("ENVIRONMENTS_url")); */



	public void analyzeLog() {
		Properties prop = Configutil.configdatafile("log4j.properties");
		PropertyConfigurator.configure(prop);
		LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry entry : logEntries) {
			System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());

		}
	}



}