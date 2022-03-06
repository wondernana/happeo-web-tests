package components.pages;

import components.AbstractComponent;
import components.elements.PostActionsMenu;
import components.elements.PostEditor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Channel extends AbstractComponent {

    private By posts = By.xpath("//div[starts-with(@id,'post-')]");
    private By actionsMenu = By.xpath("//button[@aria-label='Post actions menu']");

    public Channel(WebDriver driver) {
        super(driver, 15);
    }

    public PostActionsMenu clickOnRandomPost() {
        List<WebElement> menuButtons = waitPresenceOfAllElements(actionsMenu);
        menuButtons.get(getRandomIndex(menuButtons.size())-1).click();
        return new PostActionsMenu(driver);
    }

    public WebElement getPostByIndex(int index) {
        List<WebElement> postsList = waitPresenceOfAllElements(posts);
        return postsList.get(index);

    }

    public PostEditor startEditingNewPost() {
        return new PostEditor(driver);
    }

    public Channel waitForNewPostWithText(String text) {
        try {
            waitVisibleElement(By.xpath("//p[text() = '" + text + "']"));
            //without this wait test sometimes fails
            Thread.sleep(1000);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not find new post with text: " + text);
        }
        return this;

    }

}
