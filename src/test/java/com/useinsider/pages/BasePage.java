package com.useinsider.pages;

import com.useinsider.utilities.Driver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    public BasePage() {

        PageFactory.initElements(Driver.get(), this);

    }

}
