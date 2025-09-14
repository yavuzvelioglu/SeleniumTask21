package com.useinsider.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class CareersPage extends BasePage {

    @FindBy(xpath = "//a[text()[contains(.,'See all teams')]]")
    public WebElement seeAllTeamsBtn;

    @FindBy(xpath = "//p[text()[contains(.,'Istanbul')]]")
    public WebElement istanbulLocationHeader;

    @FindBy(xpath = "//h2[text()[contains(.,'Life at Insider')]]")
    public WebElement lifeAtInsiderHeader;

    @FindBy(xpath = "//a[text()[contains(.,'See all QA jobs')]]")
    public WebElement seeAllQAJobsBtn;

    @FindBy(css = "#filter-by-location")
    public WebElement filterByLocation;

    @FindBy(css = "#filter-by-department")
    public WebElement filterByDepartment;

    @FindBy(css = ".btn.btn-navy.rounded.pt-2.pr-5.pb-2.pl-5")
    public List<WebElement> viewRoleBtn;

    @FindBy(css = ".position-title.font-weight-bold")
    public List<WebElement> positionTitle;

    @FindBy(css = ".position-list-item-wrapper.bg-light")
    public List<WebElement> positionListItem;




}


