package pages;

import helper.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {

	private WebDriver driver;
	CommonUtils commonUtils;



	public String CheckOutPage_url ="";









	public CheckOutPage(WebDriver driver) {
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
