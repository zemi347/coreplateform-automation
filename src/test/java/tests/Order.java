package tests;


import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import framework.DriverSingleton;
import helper.Supertest;
import org.testng.annotations.*;

public class Order  extends Supertest {

   DriverSingleton ds = new DriverSingleton();

    public Order() throws Exception {
    }

    //accountPage acnew = new AccountPage(driver);
/*
    @Test
    @Parameters("myName")
    public void parameterTest(String myName) {
        System.out.println("Parameterized value is : " + myName);
    }
*/
    @Test(priority=1,description = "Login With valid Credential")
    public void User_Login_With_Correct_User_Correct_Password() throws Exception
        {
            logger = report.startTest("Login With valid Credential");

            logger.log(LogStatus.INFO, "Click On VisitorLogin.");
            _commonUtils.leftClickElement(_homepage.btn_actionVisitorLogin);

            logger.log(LogStatus.INFO, "Enter UserName.");
            _commonUtils.leftClickElement(_Loginpage.txtField_username);
            _commonUtils.sendKeyOnElement(_Loginpage.txtField_username,"qa.supermeal@gmail.com");

            logger.log(LogStatus.INFO, "Enter Password.");
            _commonUtils.leftClickElement(_Loginpage.txtField_Password);
            _commonUtils.sendKeyOnElement(_Loginpage.txtField_Password,"Pakistan47");

            logger.log(LogStatus.INFO, "Click On Submit Login.");
            _commonUtils.leftClickElement(_Loginpage.btn_Submit_Login);
            Assert.assertEquals(_commonUtils.getElementText(_homepage.lbl_UserName),"TESTER");
            logger.log(LogStatus.PASS, "User Successfully Login");
            ds.analyzeLog();

    }
}