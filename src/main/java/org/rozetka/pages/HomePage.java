package org.rozetka.pages;

import org.openqa.selenium.WebDriver;
import org.rozetka.pages.modules.HeaderModule;

public class HomePage extends BasePage {

    private HeaderModule headerModule;

    public HomePage(WebDriver driver) {
        super(driver);
        this.headerModule = new HeaderModule(driver);
    }

    public HeaderModule onHeaderModule() {
        return headerModule;
    }
}
