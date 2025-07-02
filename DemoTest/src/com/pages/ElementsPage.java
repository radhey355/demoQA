package com.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ElementsPage {

	WebDriver driver;
	Actions action;
	JavascriptExecutor js;
	WebDriverWait wait;

	public void ScrollBy() {
		action.scrollByAmount(0, 500).build().perform();

	}

	public void down_page() {
		action.sendKeys(Keys.CONTROL, Keys.END).perform(); // Scroll to bottom
		System.err.println("--down--");
	}

	public void up_page() {
		action.sendKeys(Keys.CONTROL, Keys.HOME).perform(); // Scroll to top
		System.err.println("--up--");

	}

	@BeforeClass
	public void browser() {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		action = new Actions(driver);
		js = (JavascriptExecutor) driver;
		driver.get("https://demoqa.com");

	}

//	@AfterClass
	public void TearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void ElememtsPage() {

		ScrollBy();
		WebElement element = driver.findElement(By.xpath("//*[text()=\"Elements\"]"));
		element.click();
		String page_URL = driver.getCurrentUrl();
		System.out.println(page_URL);
		Assert.assertEquals(page_URL, "https://demoqa.com/elements");

	}

	@Test(priority = 2)
	public void Text_Box() throws Exception {
		WebElement check_Box = driver.findElement(By.xpath("//span[text()=\"Check Box\"]"));
		check_Box.click();
		System.err.println("--check_Box--");

		down_page();
		Thread.sleep(5000);
		up_page();
		Thread.sleep(5000);

		WebElement Text_box = driver.findElement(By.xpath("//span[text()=\"Text Box\"]"));
		wait.until(ExpectedConditions.elementToBeClickable(Text_box));
		Text_box.click();
		System.err.println("--clicked--");

		String Text_Box_URL = driver.getCurrentUrl();
		Assert.assertEquals(Text_Box_URL, "https://demoqa.com/text-box");
		Thread.sleep(5000);

	}

	@Test(priority = 3)
	public void Verify_Element_presence() throws Exception {
		String Text_Box_URL = driver.getCurrentUrl();
		WebElement form_Header = driver.findElement(By.xpath("//h1[@class=\"text-center\"]"));
		WebElement Email_Text = driver.findElement(By.xpath("//label[@id=\"userEmail-label\"]"));
		WebElement Address_Text = driver.findElement(By.xpath("//label[@id=\"currentAddress-label\"]"));
		WebElement Permanent_Address_Text = driver.findElement(By.xpath("//label[@id=\"permanentAddress-label\"]"));
		WebElement Submit_Button = driver.findElement(By.xpath("//button[@id=\"submit\"]"));
		WebElement Output_Box = driver.findElement(By.xpath("//div[@id=\"output\"]"));

		Assert.assertEquals(Text_Box_URL, "https://demoqa.com/text-box");
		Assert.assertTrue(form_Header.isDisplayed(), "form_Header is not displaying");
		Assert.assertTrue(Email_Text.isDisplayed(), "Email_Text is not displaying");
		Assert.assertTrue(Address_Text.isDisplayed(), "Address_Text is not displaying");
		Assert.assertTrue(Permanent_Address_Text.isDisplayed(), "Permanent_Address_Text is not displaying");
		Assert.assertTrue(Submit_Button.isDisplayed(), "Submit_Button is not displaying");
		Assert.assertTrue(Submit_Button.isEnabled(), "Submit_Button is not enable");
		Assert.assertFalse(Output_Box.isDisplayed(), "Output_Box is not enable before submit");
		Thread.sleep(5000);

	}

	@Test(priority = 4, dependsOnMethods = "Verify_Element_presence")
	public void Text_page_form() throws Exception {
		ScrollBy();
		WebElement Full_Name = driver.findElement(By.xpath("//input[@id=\"userName\"]"));
		WebElement Email = driver.findElement(By.xpath("//input[@placeholder=\"name@example.com\"]"));
		WebElement Current_Address = driver.findElement(By.xpath("//textarea[@id=\"currentAddress\"]"));
		WebElement Permanent_Address = driver.findElement(By.xpath("//textarea[@id=\"permanentAddress\"]"));
		WebElement submit_button = driver.findElement(By.xpath("//button[@id=\"submit\"]"));
		WebElement Output_Box = driver.findElement(By.xpath("//div[@id=\"output\"]"));

		Full_Name.sendKeys("RADHEY SRAN PANDEY  12345");
		Email.sendKeys("saran35515@gmail.com");
		Current_Address.sendKeys(
				"With the help of these functions, you can create complex XPath expressions. If you need to understand how to use contains in XPath specifically, the contains in selenium function provides an easy way to search web elements based on partial text match.");
		Permanent_Address.sendKeys("Summary\r\n"
				+ "There are some situations when regular XPath cannot be used to find an element. In such situation, we need different functions from the xpath query.\r\n"
				+ "There some important XPath functions like XPath contains, parent, ancestors, following-sibling, etc.\r\n"
				+ "With the help of these functions, you can create complex XPath expressions. If you need to understand how to use contains in XPath specifically, the contains in selenium function provides an easy way to search web elements based on partial text match.");
		Permanent_Address.click();
		Thread.sleep(5000);
		js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", Permanent_Address);
		Thread.sleep(5000);
		submit_button.click();
		Assert.assertTrue(Output_Box.isDisplayed(), "output box is not displaying");
		Thread.sleep(5000);
	}

	@Test(priority = 5)
	public void Clear_TextBox() throws Exception {
		WebElement Full_Name = driver.findElement(By.xpath("//input[@id=\"userName\"]"));
		WebElement Email = driver.findElement(By.xpath("//input[@placeholder=\"name@example.com\"]"));
		WebElement Current_Address = driver.findElement(By.xpath("//textarea[@id=\"currentAddress\"]"));
		WebElement Permanent_Address = driver.findElement(By.xpath("//textarea[@id=\"permanentAddress\"]"));
		WebElement submit_button = driver.findElement(By.xpath("//button[@id=\"submit\"]"));
		WebElement Output_Box = driver.findElement(By.xpath("//div[@id=\"output\"]"));

		Full_Name.clear();
		Email.clear();
		Current_Address.clear();
		Permanent_Address.clear();
		submit_button.click();
		String output_Box_Text = Output_Box.getText();

		Assert.assertTrue(submit_button.isEnabled(), "Submit_Button is not working after form clear");
		Assert.assertTrue(output_Box_Text.isEmpty(), "Output_Box is not empty");
		Thread.sleep(5000);

	}

	@Test(priority = 6)
	public void Tab_Button_Verification() {
		WebElement Full_Name = driver.findElement(By.xpath("//input[@id=\"userName\"]"));
		Full_Name.click();
		action.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB).perform();
		WebElement Permanent_Address = driver.findElement(By.xpath("//textarea[@id=\"permanentAddress\"]"));
		String color_Code = Permanent_Address.getCssValue("border-color");
		System.out.println(color_Code + "--BLUE COLOR");

	}
	/*
	 * Leave the Full Name empty and click Submit → verify no output or error.
	 * 
	 * Leave the Email field empty and click Submit → verify no output or error.
	 * 
	 * Enter an invalid email format (e.g., abc@) → verify red border or error.
	 * 
	 * Enter only spaces in the fields and submit → verify behavior or validation.
	 */

	@Test(priority = 7)
	public void Validate_wrong_data() throws Exception {

		WebElement Full_Name = driver.findElement(By.xpath("//input[@id=\"userName\"]"));
		WebElement Email = driver.findElement(By.xpath("//input[@placeholder=\"name@example.com\"]"));
		WebElement submit_button = driver.findElement(By.xpath("//button[@id=\"submit\"]"));
		WebElement Output_Box = driver.findElement(By.xpath("//div[@id=\"output\"]"));

		Full_Name.click();
		action.keyDown(Keys.TAB);
		action.keyUp(Keys.TAB).build().perform();
		Email.sendKeys("saran35515@g");
		submit_button.click();
		Assert.assertTrue(Output_Box.isDisplayed(), "output box is not displaying");
		String red_Color = Email.getCssValue("border-color");
		System.err.println(red_Color + "--RED COLOR");

	}

	@Test(priority = 8)
	public void validate_outputbox_data() throws Exception {
		driver.navigate().to("https://demoqa.com/checkbox");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
