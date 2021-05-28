package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Sign_Up_Page {

	 private WebDriver driver;

	    //variables
	    private String  URL="https://www.phptravels.net/register/en";
	    // Elements
	    private By firstname_locator = By.name("firstname");
	    private By lastname_locator = By.name("lastname");
	    private By phone_locator = By.name("phone");
	    private By email_locator = By.name("email");
	    private By password_locator = By.name("password");
	    private By confirmpassword_locator = By.name("confirmpassword");
	   
	  

	    // Constructor
	    public Sign_Up_Page(WebDriver driver) {
		this.driver = driver;
	    }
	    
	    
	    

	public void navigateToURL() {
		driver.navigate().to(URL);
	}

	public void  RegisterUser(String firstName, String lastName, String phone, String email,String password) {
		
		driver.findElement(firstname_locator).sendKeys(firstName);
		driver.findElement(lastname_locator).sendKeys(lastName);
		driver.findElement(phone_locator).sendKeys(phone);
		driver.findElement(email_locator).sendKeys(email);
		driver.findElement(password_locator).sendKeys(password);
		driver.findElement(confirmpassword_locator).sendKeys(password+Keys.ENTER);
		
		
	}

}
