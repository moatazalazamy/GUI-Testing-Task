package tests;

import org.testng.annotations.Test;
import Data.JsonDataReader;
import pages.Sign_Up_Page;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import static org.testng.Assert.assertEquals;
import java.util.concurrent.TimeUnit;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import java.io.File;
import java.io.IOException;

public class SignUp_test {

	WebDriver driver;
	// create object from page class
	Sign_Up_Page SignUpPage;
    //Elements
	By HiMassage_locator = By.className("text-align-left");
	By ErrorMassage_locator = By.xpath("//*[@id=\"headersignupform\"]/div[2]/div/p");
	
	
	@Test(priority = 3)
	public void UserCanSignUpSucessfully() throws IOException, ParseException {

		//read data from json file
		JsonDataReader jsonReader = new JsonDataReader();
		jsonReader.JsonReader();
		
        //call registerUser method from page class 
		SignUpPage.RegisterUser(jsonReader.firstname, jsonReader.lastname, jsonReader.phone, jsonReader.email,
				jsonReader.password);
		
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		
		//assert that user signed up successfully by hello massage 
		String Hi_Massage = driver.findElement(HiMassage_locator).getText();
		assertEquals(Hi_Massage, "Hi, " + jsonReader.firstname + " " + jsonReader.lastname, Hi_Massage);

	}

	@Test(priority = 2)
	public void UserCantSginUpDueInvaildPassword() throws IOException, ParseException {

		//read data from json file
		JsonDataReader jsonReader = new JsonDataReader();
		jsonReader.JsonReader();
		
		//call registerUser method from page class
		SignUpPage.RegisterUser(jsonReader.firstname, jsonReader.lastname, jsonReader.phone, jsonReader.email,
				jsonReader.invalidPass);
		
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		
		//assert that error massage appeared 
		String Password_Error_Massage = driver.findElement(ErrorMassage_locator).getText();
		assertEquals(Password_Error_Massage, "The Password field must be at least 6 characters in length.",
				Password_Error_Massage);

	}

	@Test(priority = 1)
	public void UserCantSginUpDueInvaildEmail() throws IOException, ParseException {
		
		//read data from json file
		JsonDataReader jsonReader = new JsonDataReader();
		jsonReader.JsonReader();

		//call registerUser method from page class
		SignUpPage.RegisterUser(jsonReader.firstname, jsonReader.lastname, jsonReader.phone, jsonReader.invalidEmail,
				jsonReader.password);
		
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		
		//assert that error massage appeared 
		String Password_Error_Massage = driver.findElement(ErrorMassage_locator).getText();
		assertEquals(Password_Error_Massage, "The Email field must contain a valid email address.",
				Password_Error_Massage);

	}

	@BeforeMethod
	public void beforeMethod() {
		
		SignUpPage.navigateToURL();
		driver.manage().window().maximize();
		

	}

	@AfterMethod
	public void recordFailure(ITestResult result) {

		if (ITestResult.FAILURE == result.getStatus()) {
			var camera = (TakesScreenshot) driver;
			File screenshot = camera.getScreenshotAs(OutputType.FILE);
			try {
				Files.move(screenshot, new File("src\\main\\resources\\screenshots\\" + result.getName() + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		
		SignUpPage = new Sign_Up_Page(driver);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
