package pages;

import helper.CommonUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	private WebDriver driver;
	CommonUtils commonUtils;



	public String HomePage_Url="";

	@FindBy(xpath ="//*[@id=\"ctl00_Head1\"]/title")
	public WebElement PageHomeTitle;

	@FindBy(id = "actionVisitorLogin")
	public WebElement btn_actionVisitorLogin;

	@FindBy(id = "UserName")
	public WebElement txtField_username;

	@FindBy(id = "Password")
	public WebElement txtField_Password;

	@FindBy(id = "ctl00_cphContentBody_ctl01_loginControl_LoginButton")
	public WebElement btn_Submit_Login;

	@FindBy(xpath = "//*[@id='MyAccountAction']/span[1]")
	public WebElement lbl_UserName;



	public void PressLoginButton()
	{
		try {
			System.out.print("Press Log In Button \n");
			driver.findElement ((By) PageHomeTitle).click ();
			Thread.sleep(500);

		} catch (Exception e) {
			e.getMessage ();
		}
	}


	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		commonUtils = new CommonUtils(driver);

	}

}
/*
* import org.openqa.selenium.By;
*/
	/*
	By UserName = (By.id( "UserName" ));
	By PageHomeTitle=(By.xpath ("//*[@id=\"ctl00_Head1\"]/title"));

	 */
