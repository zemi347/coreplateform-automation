package helper;

import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentFactory {

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
}
