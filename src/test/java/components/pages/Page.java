package components.pages;

import components.AbstractComponent;
import components.elements.PageSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Page extends AbstractComponent {

    private final By editPageButton = By.xpath("//a[contains(@class, 'edit')]");

    public Page(WebDriver driver) {
        super(driver, 10);
    }

    public PageSettings editPage() {
        waitClickableElement(editPageButton).click();
        return new PageSettings(driver);
    }
}
