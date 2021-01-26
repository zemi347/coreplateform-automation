package helper;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.relevantcodes.extentreports.*;
import framework.DriverSingleton;
import pages.*;

public class Supertest {

    // Pages fields declaration:
    protected CommonUtils               _commonUtils;
    protected BlogPage                  _blogpage;
    protected BurgerMenu                _burgerMenu;
    protected CheckOutPage              _checkcutpage;
    protected FAQPage                   _faqpage;
    protected HomePage                  _homepage;
    protected LoginPage                 _Loginpage;
    protected OrderDetailPage           _orderdetailpage;
    protected RestaurantPage            _restaurantpage;
    protected RestaurantRegisterPage    _restaurantregisterpage;
    protected RestaurantSearchPage      _restaurantsearchpage;
    protected ThankyouPage              _thankyoupage;



    // Reports fields:
    protected ExtentReports     report;
    public static ExtentTest    logger;



    // Other fields:

    protected String testCaseStatus = "PASSED";
    public WebDriver driver = DriverSingleton.setDriver();
    protected long time;
    DriverSingleton ds = new DriverSingleton();

    @BeforeClass(alwaysRun = true)
    public void beforeClassSetup() throws Exception {

        // Pages fields initialization:

        System.out.println("beforeClassSetup");
        _commonUtils              = new CommonUtils                  (driver);
        _blogpage                 = new BlogPage                     (driver);
        _burgerMenu               = new BurgerMenu                   (driver);
        _checkcutpage             = new CheckOutPage                 (driver);
        _faqpage                  = new FAQPage                      (driver);
        _homepage                 = new HomePage                     (driver);
        _Loginpage                = new LoginPage                    (driver);
        _orderdetailpage          = new OrderDetailPage              (driver);
        _restaurantpage           = new RestaurantPage               (driver);
        _restaurantregisterpage   = new RestaurantRegisterPage       (driver);
        _restaurantsearchpage     = new RestaurantSearchPage         (driver);
        _thankyoupage             = new ThankyouPage                 (driver);

        time = System.currentTimeMillis();
        ds.analyzeLog();

        driver.manage().window().maximize();
        /*
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        */
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Set the deployment server
        report = ExtentFactory.getInstance(this);
        logger = report.startTest("Loading Home Page.");
        logger.log(LogStatus.PASS, "test user.");


    }

    @AfterMethod
    public void screenCap(ITestResult result) {
        System.out.println("AfterMethod");
        if (result.getStatus() == ITestResult.FAILURE) {
            testCaseStatus = "FAILED";
            System.out.println(System.getProperty("user.dir"));
            String screenshot_path = framework.Constants.captureScreenshot(result.getTestClass().getName());
            String imagePath = logger.addScreenCapture(screenshot_path);
            logger.log(LogStatus.FAIL, "Failed ", imagePath);
        }
    }

    public void screenshot(String screenshot_name){
        String screenshot_path = framework.Constants.captureScreenshot(screenshot_name);
        String imagePath = logger.addScreenCapture(screenshot_path);
        logger.log(LogStatus.INFO, "Screenshot: ", imagePath);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {

        System.out.println("Tearing down test " + getClass().getName() + "....");
        logger = report.startTest("Start tear down.");
        /*if (System.getProperty("browser") != null) {
         if (System.getProperty("browser").contains("edge")) {

                logger.log(LogStatus.PASS, "Go to home page and logout.");
                driver.get(CredentialsData.getBaseURL() + "file/my-data");
                cu.waitThenSingleClick(emHeaderPage.userProfileIconButton, 4);
                cu.clickElements(emHeaderPage.logoutButton);
                cu.waitFor(Duration.ofSeconds(2));
            }

        }*/
        logger.log(LogStatus.PASS, "Clear cache and delete user profile.");
        framework.Constants.deleteAllBrowserCookies();
        logger.log(LogStatus.PASS, "Tear Down Successfully.");
        report.endTest(logger);
        report.flush();
        time = System.currentTimeMillis() - time;


    }

    @AfterSuite(alwaysRun = true)
    public void closeBrowser() {
        driver.close();
        driver.quit();


    }
}
