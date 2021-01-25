import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class TESTAPP {
    protected ExtentReports     report;
    public static ExtentTest    logger;
    protected WebDriver driver = DriverSingleton.setDriver();
    protected String testCaseStatus = "PASSED";

    @BeforeClass(alwaysRun = true)
    public void beforeClassSetup() throws Exception {
        System.out.println("beforeClass");
      //  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        // Set the deployment server

        report = ExtentFactory.getInstance(this);
        logger = report.startTest("Loading Home Page.");
        Assert.assertEquals("a","a");
        logger.log(LogStatus.PASS, "Landing successfully on HomePage");
    }


    @Test(priority = 1, description = "HELLO WORLD")
    public void TESTHELOWORLD() throws Exception
    {
        logger = report.startTest("HELLO WORLD.");
        Assert.assertEquals("HELLO WORLD","HELLO WORLD" );
        logger.log(LogStatus.PASS, "Landing successfully on HomePage");

    }
    @AfterMethod
    public void screenCap(ITestResult result) {
        System.out.println("AfterMethod");
        if (result.getStatus() == ITestResult.FAILURE) {
            testCaseStatus = "FAILED";
            System.out.println(System.getProperty("user.dir"));
            String screenshot_path = ExtentFactory.captureScreenshot(result.getTestClass().getName());
            String imagePath = logger.addScreenCapture(screenshot_path);
            logger.log(LogStatus.FAIL, "Failed ", imagePath);
        }
    }

    public void screenshot(String screenshot_name){
        String screenshot_path = ExtentFactory.captureScreenshot(screenshot_name);
        String imagePath = logger.addScreenCapture(screenshot_path);
        logger.log(LogStatus.INFO, "Screenshot: ", imagePath);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {

        System.out.println("Tearing down test " + getClass().getName() + "....");

        report.endTest(logger);
        report.flush();


    }

    @AfterSuite(alwaysRun = true)
    public void closeBrowser()
    {
        driver.close();
    }
}






