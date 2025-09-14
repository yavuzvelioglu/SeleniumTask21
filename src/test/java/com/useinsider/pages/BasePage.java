package com.useinsider.pages;

import com.useinsider.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    @FindBy(xpath = "//a[text()[contains(.,'Accept All')]]")
    public WebElement cookiesAccptBtn;

    public BasePage() {

        PageFactory.initElements(Driver.get(), this);


    }

    public void navBarMenuSelect(String menuName){
        Driver.get().findElement(By.xpath("//ul/li/a[text()[contains(.,'"+menuName+"')]]")).click();
    }

    public void navBarSubMenuSelect(String menuName, String subMenuName){
        navBarMenuSelect(menuName);
        Driver.get().findElement(By.xpath("//div/a[text()[contains(.,'"+subMenuName+"')]]")).click();
    }

}
