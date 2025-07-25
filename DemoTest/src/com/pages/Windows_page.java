package com.pages;

import java.time.Duration;
import java.util.Set;

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

public class Windows_page {

	WebDriver driver;
	Actions action;
	JavascriptExecutor js;
	WebDriverWait wait;
	String parent_Window;

	@BeforeClass
	public void SetUp() {
		driver = new ChromeDriver();
		driver.get("https://demoqa.com/browser-windows");
		action = new Actions(driver);
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		parent_Window = driver.getWindowHandle();

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void tabs1() throws Exception {
		WebElement tabButton = driver.findElement(By.xpath("//*[@id = \"tabButton\"]"));
		tabButton.click();
		System.out.println("tabs1()");

		Set<String> child_window = driver.getWindowHandles();
		for (String new_child : child_window) {
			if (!new_child.equals(parent_Window)) {
				driver.switchTo().window(new_child);

				String pageUrl = driver.getCurrentUrl();
				System.err.println(pageUrl + "  --SWITCHED TO NEW CHILD WINDOW");
				Assert.assertTrue(pageUrl.contains("sample"), "not contaning the same url");

				WebElement text = driver.findElement(By.xpath("//*[@id=\"sampleHeading\"]"));
				String text_value = text.getText();
				Assert.assertEquals(text_value, "This is a sample page", "text not matching");

				Thread.sleep(5000);
				driver.close();
				driver.switchTo().window(parent_Window);
				System.out.println("Switched to parentWindow");

			}
		}
		System.out.println("tabs1() verified");

	}

//	@Test(priority = 2)
	public void tab2() {
		driver.navigate().to("https://demoqa.com/browser-windows");
		WebElement windowButton = driver.findElement(By.xpath("//*[@id = \"windowButton\"]"));
		windowButton.click();
		System.out.println("tabs2()");
	}

//	@Test(priority = 3)
	public void tab3() {
		WebElement messageWindowButton = driver.findElement(By.xpath("//*[@id = \"messageWindowButton\"]"));
		messageWindowButton.click();
		System.out.println("tabs3()");
	}

//	@Test(priority = 4)
	public void allWindows_handels() {
		Set<String> new_Window = driver.getWindowHandles();
		for (String allWindows : new_Window) {
			if (!allWindows.equals(parent_Window)) {
				driver.switchTo().window(allWindows);
				System.out.println("windoHandels");
			}
		}

	}

}
