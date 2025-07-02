package com.pages;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

import java.time.Duration;

public class AllActionsTest {
    WebDriver driver;
    Actions actions;
    JavascriptExecutor js;

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("==> Before Suite");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("==> Before Test");
    }

    @BeforeClass
    public void setup() {
        System.out.println("==> Before Class");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("==> Before Method");
    }

    @Test(priority = 1)
    public void testBasicActions() {
        driver.get("https://demoqa.com/buttons");

        WebElement doubleClickBtn = driver.findElement(By.id("doubleClickBtn"));
        WebElement rightClickBtn = driver.findElement(By.id("rightClickBtn"));
        WebElement clickMeBtn = driver.findElement(By.xpath("//button[text()='Click Me']"));

        actions.doubleClick(doubleClickBtn).perform();
        actions.contextClick(rightClickBtn).perform();
        actions.click(clickMeBtn).perform();
    }

    @Test(priority = 2)
    public void testClickHoldRelease() {
        driver.get("https://jqueryui.com/resources/demos/droppable/default.html");

        WebElement drag = driver.findElement(By.id("draggable"));
        WebElement drop = driver.findElement(By.id("droppable"));

        actions.clickAndHold(drag).moveToElement(drop).release().build().perform();
    }

    @Test(priority = 3)
    public void testSendKeys_KeyDown_Up() {
        driver.get("https://demoqa.com/text-box");

        WebElement input = driver.findElement(By.id("userName"));

        actions.moveToElement(input).click()
                .keyDown(Keys.SHIFT)
                .sendKeys("radhey")
                .keyUp(Keys.SHIFT)
                .sendKeys(" saran")
                .perform();
    }

    @Test(priority = 4)
    public void testMouseHover_MoveToElement() {
        driver.get("https://demoqa.com/tool-tips");

        WebElement tooltipBtn = driver.findElement(By.id("toolTipButton"));
        actions.moveToElement(tooltipBtn).perform();
    }

    @Test(priority = 5)
    public void testJavaScriptScrolling() {
        driver.get("https://demoqa.com/");

        // Scroll down by 1000 pixels
        js.executeScript("window.scrollBy(0,1000)");

        // Scroll up by 500 pixels
        js.executeScript("window.scrollBy(0,-500)");

        // Scroll to specific element
        WebElement element = driver.findElement(By.xpath("//h5[text()='Elements']"));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Test(priority = 6)
    public void testJavaScriptClickAndSendKeys() {
        driver.get("https://demoqa.com/text-box");

        WebElement nameInput = driver.findElement(By.id("userName"));
        WebElement submitBtn = driver.findElement(By.id("submit"));

        // Send keys using JavaScript
        js.executeScript("arguments[0].value='Radhey Saran';", nameInput);

        // Click using JavaScript
        js.executeScript("arguments[0].click();", submitBtn);
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("==> After Method");
    }

    @AfterClass
    public void tearDown() {
        System.out.println("==> After Class");
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterTest
    public void afterTest() {
        System.out.println("==> After Test");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("==> After Suite");
    }
}
