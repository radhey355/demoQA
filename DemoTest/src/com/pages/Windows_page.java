package com.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Windows_page {

	WebDriver driver;
	Actions action;
	JavascriptExecutor js;
	WebDriverWait wait;

	@BeforeClass
	public void SetUp() {
		driver = new ChromeDriver();
		driver.get("https://demoqa.com/browser-windows");
		action = new Actions(driver);
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void n() {
		System.out.println("open");
	}
}
