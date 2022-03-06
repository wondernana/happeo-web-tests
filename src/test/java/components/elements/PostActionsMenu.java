package components.elements;

import components.AbstractComponent;
import components.pages.Post;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PostActionsMenu extends AbstractComponent {

    private By viewPostAction = By.xpath("//li[@data-tracker='View post']");

    public PostActionsMenu(WebDriver driver) {
        super(driver, 5);
    }

    public Post viewPost() {
        waitClickableElement(viewPostAction).click();
        return new Post(driver);
    }

}
