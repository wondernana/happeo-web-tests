package components.pages;

import components.AbstractComponent;
import components.elements.ReactionsPanel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Post extends AbstractComponent {

    private final By reactionsButton = By.xpath("//button[@aria-label='Reactions']");
    private final By addedReaction = By.xpath("//div[@data-tracker='reactions']//span");

    public Post(WebDriver driver) {
        super(driver, 10);
    }

    public ReactionsPanel hoverOverReactions() {
        WebElement reactions = waitClickableElement(reactionsButton);
        hover(reactions);
        return new ReactionsPanel(driver);
    }

    public String getAddedReactionText() {
       return getElementText(waitVisibleElement(addedReaction));
    }
}
