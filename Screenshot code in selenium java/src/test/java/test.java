import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import org.testng.ITestResult;
import org.testng.annotations.*;


import java.io.File;
import java.time.Duration;



public class test  {

  WebDriver driver;


    @BeforeTest

    public void LaunchBrowser() {

        WebDriverManager.firefoxdriver();
        FirefoxOptions options = new FirefoxOptions();
        // if we want to take screenshot of failed testcases, in headless mode just add given query in @BeforeTest Method

        //options.addArguments("-headless");

        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");

    }

    @AfterMethod
    public void teardown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot(result.getName());
        }
    }

    private void captureScreenshot(String testName) {
        if (driver instanceof TakesScreenshot) {
            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
            File screenshotFile = screenshotDriver.getScreenshotAs(OutputType.FILE);
            //Don't forget to set pathname where you want to save the .png file
            try {
                FileUtils.copyFile(screenshotFile, new File("C:\\Users\\Admin\\Desktop\\testresult\\" + testName + ".png"));
            } catch (Exception e) {
                System.out.println("Failed to capture screenshot: " + e.getMessage());
            }
        }


    }

    @Test(priority = 1)
    void test() {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
        WebElement login =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name='login']")));
        login.click();
    }
    @Test(priority = 2)
    void email(){
        Wait <WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name='login']")));
        email.sendKeys("Eleion Mask");
    }

    @Test (priority = 3)
    void create(){
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
        WebElement create = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='u_0_0_8T']")));
        create.click();
    }

    @AfterTest
   public void close() {
        if (driver != null)
        {
            driver.quit();
        }
    }
}