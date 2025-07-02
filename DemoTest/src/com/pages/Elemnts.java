package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Elemnts {

	WebDriver driver;

	@BeforeSuite
	public void setup() {
		driver = new ChromeDriver();
		driver.get("https://demoqa.com/elements");
		driver.manage().window().maximize();
	}

	@Test
	public void move_down() throws InterruptedException {
//		WebElement down = driver.findElement(By.xpath("//*[text()=\"Book Store Application\"]"));
//		Actions action = new Actions(driver);
//		action.scrollByAmount(0, 500);
//		//action.moveToElement(down).click().build();
//		Thread.sleep(5000);

		// Cast driver to JavascriptExecutor
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Scroll down by 1000 pixels vertically
		js.executeScript("window.scrollBy(0,1000)");

		Thread.sleep(5000);

		js.executeScript("window.scrollBy(0, -1000)");

	}

	// @Test
	public void move_up() {
		WebElement up = driver.findElement(By.xpath("//span[text()=\"Text Box\"]"));
		Actions action = new Actions(driver);
		action.moveToElement(up);
	}

//	@Test
	public void elements() {
		WebElement text_box = driver.findElement(By.xpath("//span[text()=\"Text Box\"]"));
		text_box.click();

	}
}
