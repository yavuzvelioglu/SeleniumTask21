package com.useinsider.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.useinsider.utilities.BrowserUtils;
import com.useinsider.utilities.ConfigurationReader;
import com.useinsider.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.time.Duration;

public class TestBase {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;
    protected ExtentReports extent;
    protected ExtentTest test;
    protected ExtentTest node;
    protected ExtentSparkReporter spark;  // see topic 4.1.3 in 'CHANGELOG.md' file shared on GitHub by 'ExtentReports' page under 'extentreports-java' repo



    @BeforeTest
    public void setUp() {

        //This will initialize the ExtentReports Class
        extent = new ExtentReports();

        //Initialize the HTML report with the report path
        spark = new ExtentSparkReporter( "target/Spark/Spark.html");

        //Attach the HTML Report to the report object
        extent.attachReporter(spark);

        //We need to give a title for report
        spark.config().setReportName("Selenium Task Report");

        //Set environment information--> Test name, Tester name, Browser, Test Steps, Test Data, Date and Time, Operating System, Result
        extent.setSystemInfo("Environment", "Testing Environment");
        extent.setSystemInfo("Browser", ConfigurationReader.get("browser"));
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Test Engineer", "yavuzVelioglu");


    }

    @AfterTest
    public void tearDown() {

        //This is when the report is actually created
        extent.flush();

        driver.quit();


    }

    @BeforeMethod
    public void openBrowser() {
        driver = Driver.get();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //"see 'Documentation/WebDriver/Troubleshooting/Upgrade to Selenium 4' webpage on 'selenium.dev' website"
        //driver.get(ConfigurationReader.get("url"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);


    }

    @AfterMethod
    public void closeBrowser(ITestResult result) throws InterruptedException, IOException {
        //If test is failed
        if (result.getStatus()== ITestResult.FAILURE){
            //Record the name of the failed test
            test.fail(result.getName());

            //Take the screenshot (evidence) and return its location
            String screenShotPath= BrowserUtils.getScreenshot(result.getName());

            //Add the screenshot to the report
            test.addScreenCaptureFromPath(screenShotPath);

            //Capture the exception and put inside the report
            test.fail(result.getThrowable());
        }
        Thread.sleep(1000);
        Driver.closeDriver();

    }


}
