package components.elements;

import components.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ReactionsPanel extends AbstractComponent {

    private By reactionLinks = By.xpath("//div[@data-tracker='reactions-tooltip']//following::a");
    private List<WebElement> availableReactions;
    private String chosenReaction;

    public ReactionsPanel(WebDriver driver) {
        super(driver, 10);
        availableReactions = waitPresenceOfAllElements(reactionLinks);
    }

    public ReactionsPanel clickOnRandomReaction() {
        int index = getRandomIndex(availableReactions.size()-1);
        System.out.println(availableReactions.size());
        System.out.println(index);
        chosenReaction = availableReactions.get(index).getText();
        availableReactions.get(index).click();
        return this;
    }

    public String getReactionText() {
        return chosenReaction;
    }

}
