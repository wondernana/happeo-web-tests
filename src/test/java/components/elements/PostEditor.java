package components.elements;

import components.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PostEditor extends AbstractComponent {

    private final By postInput = By.xpath("//new-post//div[@class='fr-element fr-view']");
    private final By postOptionsButton = By.xpath("//button[@aria-label='Post options']");
    private final By announcementOption = By.xpath("//li[@data-tracker='Publish']");

    private String lastPostMessage;

    public PostEditor(WebDriver driver) {
        super(driver, 10);
    }

    public AnnouncementModal shareAsAnnouncement() {
        waitClickableElement(postOptionsButton).click();
        waitClickableElement(announcementOption).click();
        return new AnnouncementModal(driver);
    }

    public PostEditor typePost() {
        String postMessage = String.valueOf(System.currentTimeMillis());
        waitClickableElement(postInput).sendKeys(postMessage);
        setLastPostMessage(postMessage);
        return this;
    }

    public String getLastPostMessage() {
        return lastPostMessage;
    }

    protected void setLastPostMessage(String lastPostMessage) {
        this.lastPostMessage = lastPostMessage;
    }

}
