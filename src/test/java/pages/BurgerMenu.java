package pages;

import helper.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.xml.xpath.XPath;

public class BurgerMenu {

	private WebDriver driver;
	CommonUtils commonUtils;


	@FindBy(id ="c-button--slide-left")
	public WebElement buttonslideleft;

	//@FindBy(className ="user-my-accounr-logo-out logout-left-new")
	@FindBy(xpath="//*[@id=\"c-menu--push-left\"]/div/div[2]/ul/li/div/ul/li[12]/a")
	public WebElement buttonlogoout_left;


	public BurgerMenu(WebDriver driver) {
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
