import com.relevantcodes.extentreports.ExtentReports;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.format;

public class ExtentFactory {
	static private WebDriver driver = DriverSingleton.setDriver();
	@SuppressWarnings("deprecation")
	public static ExtentReports getInstance(Object testClass) throws ParseException {

//		try {
//			FileUtils.forceDelete(new File(System.getProperty("user.dir") +"/test-output/"));
//		} catch (IOException e)

			System.out.println(String.format("%1s Started", testClass.getClass().getSimpleName()));
			// Date appended to Report.html
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM_dd_hh_mm_ss");
			String testName = simpleDateFormat.format(new Date()) + "_" + testClass.getClass().getSimpleName();
		//	String folderName = testClass.getClass().getPackage().getName();
			String Path = String.format("test-output/automation-results/expert-models/test-report/%1s.html", testName);

			ExtentReports extent;
			extent = new ExtentReports(Path, false);
		extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
		extent	.addSystemInfo("Host Name"	, "QA AUTOMATION TEAM")
				.addSystemInfo("Environment"	, "QA/TEST")
				.addSystemInfo("User Name"	, "Muhammad Ali");


		return extent;

	}

	public static String captureScreenshot(String screenshotName) {

		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			// String dest = "C:\\Workspace\\Automation\\Automation
			// Results\\Screenshots\\"+screenshotName+".png";
			String dest = System.getProperty("user.dir")
					+"/test-output/automation-results/expert-models/screenshots/"+ screenshotName + ".png";
			File destination = new File(dest);
			FileUtils.copyFile(source, destination);
			return dest;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}

	}

}
