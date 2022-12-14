package org.rozetka.pages;

import org.openqa.selenium.WebDriver;
import org.rozetka.pages.components.HeaderComponent;

public abstract class BasePageWithHeader extends BasePage {

    private HeaderComponent headerComponent;

    public BasePageWithHeader(WebDriver driver) {
        super(driver);
        this.headerComponent = new HeaderComponent(driver);
    }

    public HeaderComponent onHeaderComponent() {
        return headerComponent;
    }
}
