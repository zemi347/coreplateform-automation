
package framework;

import helper.Configutil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;

public class DriverSingleton_webdrivermanager {

	public static String seleniumGrid = "false";
	public static LoggingPreferences logPrefs = new LoggingPreferences();
	public static WebDriver driver;
	public static String BrowserX = "Chrome";//"Edge";//"Chrome";
	//public static String baseurl = "https://www.staging.supermeal.ae";


	public static WebDriver setDriver() {
		Properties prop = Configutil.configdatafile("Config.properties");
		if (driver == null) {
			try {

				if (BrowserX.equalsIgnoreCase("Chrome")) {
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
					LoggingPreferences logPrefs = new LoggingPreferences();
					logPrefs.enable(LogType.BROWSER, Level.SEVERE);
					System.out.println("Using Google Chrome");

				} else if (BrowserX.equalsIgnoreCase("Firefox")) {
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver();
					LoggingPreferences logPrefs = new LoggingPreferences();
					logPrefs.enable(LogType.BROWSER, Level.SEVERE);
					System.out.println("Using Mozilla Firefox");

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
				driver.get(prop.getProperty("ENVIRONMENTS_url"));

			} catch (Exception e) {
				e.getMessage();
			}
		}
		return driver;
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