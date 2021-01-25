
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.logging.Level;

public class DriverSingleton {

	public static String seleniumGrid = "false";
	public static LoggingPreferences logPrefs = new LoggingPreferences();
	public static WebDriver driver;
	public static String BrowserX = "Chrome";//"Edge";//"Chrome";
	//public static String baseurl = "https://www.staging.supermeal.ae";


	public static WebDriver setDriver() {
		 String os = System.getProperty("os.name").toLowerCase();
		if (driver == null) {
			if(os.contains("win"))
			{
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				LoggingPreferences logPrefs = new LoggingPreferences();
				logPrefs.enable(LogType.BROWSER, Level.SEVERE);
				System.out.println("Using Google Chrome");
				driver.get("https://www.google.com/");
				System.out.println(os);
			}
			else {

			System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
			String downloadFilepath = System.getProperty("downloadFilepath");
			HashMap<String, Object> chromePrefs = new HashMap<>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilepath);
				//WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				//options.setExperimentalOption("prefs", chromePrefs);
				options.addArguments("--no-sandbox");
				options.addArguments("--headless"); //should be enabled for Jenkins
				options.addArguments("--disable-dev-shm-usage"); //should be enabled for Jenkins
				options.addArguments("--window-size=1920x1080"); //should be enabled for Jenkins

				driver = new ChromeDriver(options);

				LoggingPreferences logPrefs = new LoggingPreferences();
				logPrefs.enable(LogType.BROWSER, Level.SEVERE);
				System.out.println("Using Google Chrome");
				driver.get("https://www.google.com/");
				System.out.println(os);
			}

		}

		return driver;
	}

}
