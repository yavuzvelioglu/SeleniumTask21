package com.useinsider.tests;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.useinsider.pages.CareersPage;
import com.useinsider.utilities.BrowserUtils;
import com.useinsider.utilities.ConfigurationReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;



public class seleniumTask extends TestBase {

    CareersPage careersPage = new CareersPage();

    @Test
    public void seleniumTaskSteps() throws IOException {

        test = extent.createTest("seleniumTaskSteps");
        test.assignAuthor("yavuzVelioglu");
        test.assignDevice("Chrome Version 140.0.7339.128 (Official Build) (64-bit)");
        /** 1. Visit https://useinsider.com/ and check Insider home page is opened or not */

        node = test.createNode("1. Visit https://useinsider.com/ and check Insider home page is opened or not");

        /** Visit https://useinsider.com/ */
        driver.get(ConfigurationReader.get("url"));

        /** check Insider home page is opened or not */
        String homePageTitle = driver.getTitle();
        Assert.assertTrue(homePageTitle.contains("#1 Leader in Individualized, Cross-Channel CX — Insider"), "Home page didn't load properly!");
        if (test.getStatus() == Status.PASS) {
            node.pass("1. PASSED");

        } else {
            BrowserUtils.getScreenshot("seleniumTaskSteps_1");
            node.addScreenCaptureFromPath(BrowserUtils.getScreenshot("seleniumTaskSteps_1"));
            node.fail("1. FAILED" , MediaEntityBuilder.createScreenCaptureFromPath(BrowserUtils.getScreenshot("seleniumTaskSteps_1")).build());

        }


        /** 2. Select the “Company” menu in the navigation bar, select “Careers” and check Career page, its Locations, Teams, and Life at Insider blocks
         * are open or not */

        node = test.createNode("2. Select the “Company” menu in the navigation bar, select “Careers” and check Career page, its Locations, Teams, and Life at Insider blocks are open or not");

        /** Select the “Company” menu in the navigation bar, select “Careers” */
        careersPage.cookiesAccptBtn.click();
        careersPage.navBarSubMenuSelect("Company", "Careers");

        /** check Career page, its Locations, Teams, and Life at Insider blocks are open or not */
        String careersPageTitle = driver.getTitle();
        Assert.assertTrue(careersPageTitle.contains("Ready to disrupt? | Insider Careers"), "Careers page didn't load properly!");
        Assert.assertTrue(careersPage.seeAllTeamsBtn.isDisplayed(), "Teams block are not open!");
        Assert.assertTrue(careersPage.istanbulLocationHeader.isEnabled(), "Locations block are not open!");
        Assert.assertTrue(careersPage.lifeAtInsiderHeader.isDisplayed(), "Life at Insider block are not open!");
        if (test.getStatus() == Status.PASS) {
            node.pass("2. PASSED");

        } else {
            BrowserUtils.getScreenshot("seleniumTaskSteps_2");
            node.addScreenCaptureFromPath(BrowserUtils.getScreenshot("seleniumTaskSteps_2"));
            node.fail("2. FAILED" , MediaEntityBuilder.createScreenCaptureFromPath(BrowserUtils.getScreenshot("seleniumTaskSteps_2")).build());

        }


        /** 3. Go to https://useinsider.com/careers/quality-assurance/, click “See all QA jobs”, filter jobs by Location: “Istanbul, Turkey”,
         * and Department: “Quality Assurance”, check the presence of the jobs list */

        node = test.createNode("3. Go to https://useinsider.com/careers/quality-assurance/, click “See all QA jobs”, filter jobs by Location: “Istanbul, Turkey”, and Department: “Quality Assurance”, check the presence of the jobs list");

        /** Go to https://useinsider.com/careers/quality-assurance/ */
        driver.get(ConfigurationReader.get("url") + "careers/quality-assurance/");

        /** click “See all QA jobs” */
        careersPage.seeAllQAJobsBtn.click();

        /** filter jobs by Location: “Istanbul, Turkey”, and Department: “Quality Assurance” */
        Select dropDownLocation = new Select(careersPage.filterByLocation);
        dropDownLocation.selectByVisibleText("Istanbul, Turkiye");
        Select dropDownDepartment = new Select(careersPage.filterByDepartment);
        dropDownDepartment.selectByVisibleText("Quality Assurance");


        /** check the presence of the jobs list */
        Assert.assertTrue(careersPage.viewRoleBtn.getFirst().isEnabled(), "Jobs list is not available!");
        if (test.getStatus() == Status.PASS) {
            node.pass("3. PASSED");

        } else {
            BrowserUtils.getScreenshot("seleniumTaskSteps_3");
            node.addScreenCaptureFromPath(BrowserUtils.getScreenshot("seleniumTaskSteps_3"));
            node.fail("3. FAILED" , MediaEntityBuilder.createScreenCaptureFromPath(BrowserUtils.getScreenshot("seleniumTaskSteps_3")).build());

        }


        /** 4. Check that all jobs’ Position contains “Quality Assurance”, Department contains “Quality Assurance”, and Location contains “Istanbul, Turkey” */

        node = test.createNode("4. Check that all jobs’ Position contains “Quality Assurance”, Department contains “Quality Assurance”, and Location contains “Istanbul, Turkey”");

        if (careersPage.positionListItem.isEmpty()) {
            throw new RuntimeException("No job cards found. Ensure the filters are applied and selectors are correct.");
        }

        BrowserUtils.waitFor(10);

        for (WebElement item : careersPage.positionListItem) {
            BrowserUtils.scrollToElement(careersPage.positionTitle.getFirst());
            String position = item.findElement(By.cssSelector(".position-title.font-weight-bold")).getText();
            String department = item.findElement(By.cssSelector(".position-department.text-large.font-weight-600.text-primary")).getText();
            String location = item.findElement(By.cssSelector(".position-location.text-large")).getText();
            Assert.assertTrue(position.contains("Quality Assurance"), "Position doesn't contain 'Quality Assurance'");
            Assert.assertTrue(department.contains("Quality Assurance"), "Department doesn't contain 'Quality Assurance'");
            Assert.assertTrue(location.contains("Istanbul, Turkiye"), "Location doesn't contain 'Istanbul, Turkiye'");
        }
        if (test.getStatus() == Status.PASS) {
            node.pass("4. PASSED");

        } else {
            BrowserUtils.getScreenshot("seleniumTaskSteps_4");
            node.addScreenCaptureFromPath(BrowserUtils.getScreenshot("seleniumTaskSteps_4"));
            node.fail("4. FAILED" , MediaEntityBuilder.createScreenCaptureFromPath(BrowserUtils.getScreenshot("seleniumTaskSteps_4")).build());

        }


        /** 5. Click the “View Role” button and check that this action redirects us to the Lever Application form page */

        node = test.createNode("5. Click the “View Role” button and check that this action redirects us to the Lever Application form page");

        /** Click the “View Role” button */
        BrowserUtils.clickWithJS(careersPage.viewRoleBtn.getFirst());
        BrowserUtils.waitFor(10);

        /** check that this action redirects us to the Lever Application form page */
        Object[] windowHandles = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("jobs.lever.co/useinsider"), "Redirection didn't happen to the expected page!");
        if (test.getStatus() == Status.PASS) {
            node.pass("5. PASSED");

        } else {
            BrowserUtils.getScreenshot("seleniumTaskSteps_5");
            test.addScreenCaptureFromPath(BrowserUtils.getScreenshot("seleniumTaskSteps_5"));
            test.fail("5. FAILED" , MediaEntityBuilder.createScreenCaptureFromPath(BrowserUtils.getScreenshot("seleniumTaskSteps_5")).build());

        }

        if (test.getStatus() == Status.PASS) {
            test.pass("PASSED");

        } else {
            test.fail("FAILED");
        }

    }

}
