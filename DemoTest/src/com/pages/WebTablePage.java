package com.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebTablePage {

	WebDriver driver;
	Actions action;
	JavascriptExecutor js;
	WebDriverWait wait;

	public void scrollUp() {
		action.scrollByAmount(0, 500).build().perform();
	}

	public void scroll_to_middlepage() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight / 2);");
	}

	@BeforeClass
	public void SetUp() {
		driver = new ChromeDriver();
		driver.get("https://demoqa.com/webtables");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		action = new Actions(driver);
		js = (JavascriptExecutor) driver;

	}

	@AfterClass
	public void TearDown() {
		driver.quit();
	}

//	@Test //(priority = 1)
	public void data_List() {

		scroll_to_middlepage();
		List<WebElement> rows = driver.findElements(By.xpath("//div[@class='rt-tr-group']"));
		for (WebElement row : rows) {
			List<WebElement> Cell = row.findElements(By.xpath(".//div[@class='rt-td']"));

			for (WebElement Cells : Cell) {
				System.out.print(Cells.getText() + " | ");
			}
			System.out.println();

		}

	}

	// @Test(priority = 2)
	public void search_validation() {

		String url = driver.getCurrentUrl();
		WebElement T_header = driver.findElement(By.xpath("//h1[@class=\"text-center\"]"));
		WebElement Search = driver.findElement(By.xpath("//input[@id=\"searchBox\"]"));
		WebElement Search_Button = driver.findElement(By.xpath("//span[@id=\"basic-addon2\"]"));
		WebElement first_row = driver.findElement(By.xpath("//div[@class='rt-tr -odd']"));

		Search.sendKeys("Vega");
		Search_Button.click();
		String row_data = first_row.getText();
		String Header = T_header.getText();

		System.err.print(row_data);
		Assert.assertTrue(row_data.contains("Vega"), "search data not matching");
		Assert.assertTrue(url.contentEquals("https://demoqa.com/webtables"), "url not matching");
		Assert.assertEquals(Header, "Web Tables", "not matching");

	}

//	@Test(priority = 3)
	public void Wrong_search() {

		WebElement Search = driver.findElement(By.xpath("//input[@id=\"searchBox\"]"));
		Search.clear();
		Search.sendKeys("radhey");

		WebElement No_row_found = driver.findElement(By.xpath("//*[text()='No rows found']"));
		String no_data = No_row_found.getText();

		String border_color = Search.getCssValue("border-color");
		Assert.assertTrue(no_data.contentEquals("No rows found"), "no text matching");
		System.err.println(border_color + "-- border color --");
		System.err.println(no_data);

	}

//	@Test//(priority = 4)
	public void Add_validate() throws Exception {

//		scroll_to_middlepage();
		WebElement Add_button = driver.findElement(By.xpath(".//button[@id=\"addNewRecordButton\"]"));
		Add_button.click();
		Thread.sleep(5000);

		WebElement Cross_Button = driver.findElement(By.xpath(".//span[@aria-hidden=\"true\"]"));
		Cross_Button.click();
		Thread.sleep(5000);
		Add_button.click();

		WebElement First_name = driver.findElement(By.xpath(".//input[@id=\"firstName\"]"));
		WebElement last_name = driver.findElement(By.xpath(".//input[@id=\"lastName\"]"));
		WebElement Email = driver.findElement(By.xpath(".//input[@id=\"userEmail\"]"));
		WebElement Age = driver.findElement(By.xpath(".//input[@id=\"age\"]"));
		WebElement Salary = driver.findElement(By.xpath(".//input[@id=\"salary\"]"));
		WebElement Department = driver.findElement(By.xpath(".//input[@id=\"department\"]"));
		WebElement Submit = driver.findElement(By.xpath(".//button[@id=\"submit\"]"));
		// WebElement form_header =
		// driver.findElement(By.xpath(".//*[@id=\"registration-form-modal\"]"));

		First_name.sendKeys("Radhey");
		last_name.sendKeys("Saran Pandey");
		Email.sendKeys("saran@35515gmail.com");
		Age.sendKeys("30+");
		Salary.sendKeys("39393939393");
		Department.sendKeys("testing");
		Thread.sleep(5000);
		Submit.click();
//		String text = form_header.getText();
//		System.err.println(text);

		data_List();

	}

//	@Test // (priority = 5)
	public void Edit_Page_Validate() {

		scroll_to_middlepage();
		System.out.println("--up--");
		WebElement edit = driver.findElement(By.xpath(".//span[@id=\"edit-record-1\"]"));
		edit.click();
		List<WebElement> edit_page = driver.findElements(By.xpath(".//div[@class=\"modal-content\"]"));
		for (WebElement editPage_text : edit_page) {
			System.out.print(editPage_text.getText());
		}
		System.out.println();

		// Fetch values from input fields
		String firstName = driver.findElement(By.id("firstName")).getAttribute("value");
		String lastName = driver.findElement(By.id("lastName")).getAttribute("value");
		String email = driver.findElement(By.id("userEmail")).getAttribute("value");
		String age = driver.findElement(By.id("age")).getAttribute("value");
		String salary = driver.findElement(By.id("salary")).getAttribute("value");
		String department = driver.findElement(By.id("department")).getAttribute("value");
		WebElement Submit = driver.findElement(By.xpath(".//button[@id=\"submit\"]"));

		// Print values
		System.out.println("First Name: " + firstName);
		System.out.println("Last Name: " + lastName);
		System.out.println("Email: " + email);
		System.out.println("Age: " + age);
		System.out.println("Salary: " + salary);
		System.out.println("Department: " + department);

		Submit.isEnabled();
		Assert.assertTrue(firstName.contains("Cierra"), " firstName value not matching");
		Assert.assertTrue(lastName.contains("Vega"), " lastName value not matching");
		Assert.assertTrue(email.contains("cierra@example.com"), " email value not matching");
		Assert.assertTrue(age.contains("39"), " age value not matching");
		Assert.assertTrue(salary.contains("1000"), " salary value not matching");
		Assert.assertTrue(department.contains("Insurance"), " department value not matching");

	}

//	@Test //(priority = 6)
	public void Edit_Action() {

		scroll_to_middlepage();
		System.out.println("--up--");
		WebElement edit = driver.findElement(By.xpath(".//span[@id=\"edit-record-1\"]"));
		edit.click();

		WebElement First_name = driver.findElement(By.xpath(".//input[@id=\"firstName\"]"));
		WebElement last_name = driver.findElement(By.xpath(".//input[@id=\"lastName\"]"));
		WebElement Email = driver.findElement(By.xpath(".//input[@id=\"userEmail\"]"));
		// WebElement Age = driver.findElement(By.xpath(".//input[@id=\"age\"]"));
		WebElement Salary = driver.findElement(By.xpath(".//input[@id=\"salary\"]"));
		WebElement Department = driver.findElement(By.xpath(".//input[@id=\"department\"]"));

		First_name.clear();
		First_name.sendKeys("Radhey Saran");
		last_name.sendKeys("Pandey");
		Email.clear();
		Email.sendKeys("saran.gmail");
		Salary.clear();
		Salary.sendKeys("ertygh");
		Department.sendKeys("-QA");

		// Fetch values from input fields
		String firstName = driver.findElement(By.id("firstName")).getAttribute("value");
		String lastName = driver.findElement(By.id("lastName")).getAttribute("value");
		String email = driver.findElement(By.id("userEmail")).getAttribute("value");
		String age = driver.findElement(By.id("age")).getAttribute("value");
		String salary = driver.findElement(By.id("salary")).getAttribute("value");
		String department = driver.findElement(By.id("department")).getAttribute("value");
		WebElement Submit = driver.findElement(By.xpath(".//button[@id=\"submit\"]"));

		// Print values
		System.out.println("First Name: " + firstName);
		System.out.println("Last Name: " + lastName);
		System.out.println("Email: " + email);
		System.out.println("Age: " + age);
		System.out.println("Salary: " + salary);
		System.out.println("Department: " + department);
		Submit.click();

		String emailBordercolor = Email.getCssValue("border-color");
		System.out.println(emailBordercolor);

		String salaryBordercolor = Salary.getCssValue("border-color");
		System.out.println(salaryBordercolor);

		Assert.assertTrue(emailBordercolor.contains("rgb"), "email text box not showing error");
		Assert.assertTrue(salaryBordercolor.contains("rgb"), "salary text box not showing error");

		WebElement Cross_Button = driver.findElement(By.xpath(".//span[@aria-hidden=\"true\"]"));
		Cross_Button.click();

		WebElement first_row = driver.findElement(By.xpath("//div[@class='rt-tr -odd']"));
		String firstrowData = first_row.getText();
		Assert.assertFalse(firstrowData.contains("saran.gmail"), "invalid data got added in the list page");
		
		data_List();

	}
	
	@Test(priority = 7)
	public void deleteAction() {
		
		scroll_to_middlepage();
		WebElement deleteButton = driver.findElement(By.xpath(".//*[@title =\"Delete\"]"));
		deleteButton.click();
		
		data_List();
		
		WebElement first_row = driver.findElement(By.xpath("//div[@class='rt-tr -odd']"));
		String firstrowData = first_row.getText();
		Assert.assertFalse(firstrowData.contains("cierra@example.com"), "data not delete from the list page");
		Assert.assertTrue(firstrowData.contains("cierra@example.com"), "data not deleted from the list page");
	
		
	}
}
