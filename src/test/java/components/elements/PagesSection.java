package components.elements;

import components.AbstractComponent;
import components.pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PagesSection extends AbstractComponent {

public PagesSection(WebDriver driver) {
        super(driver, 15);
    }

    public Page openPageWithName(String pageName) {
        By pageLink = By.xpath("//p[contains(text(), '" + pageName + "')]//ancestor::a");
        waitClickableElement(pageLink).click();
        return new Page(driver);
    }

    public PagesSection expandPageListUnder(String pageName) {
        WebElement expandButton = waitClickableElement(By
                .xpath("//div[contains(@href, '" + pageName.replaceAll("\\s", "") + "')]//button[@aria-label='Expand page list']"));
        scrollElementIntoView(expandButton);
        expandButton.click();
        return this;
    }

    public List<WebElement> getAllPagesNestedUnder(String pageName) {
    try {
        //without this wait test sometimes fails
        Thread.sleep(1000);
        expandPageListUnder(pageName);
    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Waiting for nested page with name: " + pageName + " failed");
    }
        return waitPresenceOfAllElements(By
                .xpath("//ul[@data-testid = 'page-list-drag-drop']//a[contains(text(), '" + pageName + "')]//ancestor::ul[1]//li"));
    }
}
