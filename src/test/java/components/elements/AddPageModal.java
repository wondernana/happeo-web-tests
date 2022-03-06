package components.elements;

import components.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddPageModal extends AbstractComponent {

    private final By pageNameInput = By.id("newPageModalInput");
    private final By createButton = By.xpath("//button[@type='submit']");

    public AddPageModal(WebDriver driver) {
        super(driver, 15);
    }

    public AddPageModal createPageWithName(String name) {
        waitVisibleElement(pageNameInput).sendKeys(name);
        return this;
    }

    public AddPageModal selectParentPageWithName(String name) {
        By parentPageLink = By.xpath("//span[contains(text(), '" + name + "')]//ancestor::a");
        driver.findElement(parentPageLink).click();
        return this;
    }

    public PageSettings createPage() {
        driver.findElement(createButton).click();
        return new PageSettings(driver);
    }
}
