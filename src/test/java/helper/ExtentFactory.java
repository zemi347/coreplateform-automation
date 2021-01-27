package helper;

import com.relevantcodes.extentreports.ExtentReports;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.format;

public class ExtentFactory {


	public static ExtentReports getInstance(Object testClass) throws ParseException {

//		try {
//			FileUtils.forceDelete(new File(System.getProperty("user.dir") +"/test-output/"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		System.out.println(format("%1s Started", testClass.getClass().getSimpleName()));
		// Date appended to Report.html
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM_dd_hh_mm_ss");
		String Package 	= simpleDateFormat.format(new Date()) + "_" +testClass.getClass().getPackage().getName();
		String testName = testClass.getClass().getSimpleName();

		String Path = format("test-output/automation-results/test-report/%1s_%2s.html", Package,testName);
		//String Path = format("test-output/automation-results/test-report/Automation.html");

		ExtentReports extent;
		extent = new ExtentReports(Path, false);
		extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
		//extent.loadConfig(new File(format("extent-config.xml", System.getProperty("user.dir"))));
		extent	.addSystemInfo("Host Name"	, "QA AUTOMATION TEAM")
				.addSystemInfo("Environment"	, "QA/TEST")
				.addSystemInfo("User Name"	, "Muhammad Ali");

		return extent;

	}
}
