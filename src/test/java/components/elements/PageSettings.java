package components.elements;

import components.AbstractComponent;
import components.pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageSettings extends AbstractComponent {

    private final By managePagesLink = By.xpath("//span[contains(text(), 'Manage pages')]//ancestor::a");
    private final By addPageLink = By.xpath("//span[contains(text(), 'Add page')]//ancestor::a");
    private final By saveButton = By.xpath("//span[contains(text(), 'Save')]//ancestor::button");

    public PageSettings(WebDriver driver) {
        super(driver, 10);
    }

    public PageSettings managePages() {
        waitClickableElement(managePagesLink).click();
        return this;
    }

    public AddPageModal addNewPage() {
        WebElement addPage = waitClickableElement(addPageLink);
        scrollElementIntoView(addPage);
        addPage.click();
        return new AddPageModal(driver);
    }

    public Page submitChanges() {
        waitClickableElement(saveButton).click();
        return new Page(driver);
    }


}
