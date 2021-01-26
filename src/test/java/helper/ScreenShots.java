package helper;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenShots {
	
	
		public static String capture(WebDriver driver) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File("src/../test-output/ErrImage/" + System.currentTimeMillis()
		+ ".png");
		String errflpath = Dest.getAbsolutePath();
			try {
				FileUtils.copyFile(scrFile, Dest);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return errflpath;
		}
}
