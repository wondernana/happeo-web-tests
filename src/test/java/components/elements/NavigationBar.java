package components.elements;

import components.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationBar extends AbstractComponent {

    By channelsButton = By.id("nav-channels-button");
    By pagesButton = By.id("nav-pages-button");

    public NavigationBar(WebDriver driver) {
        super(driver, 3);
    }


    public ChannelsSection openChannels() {
        waitClickableElement(channelsButton).click();
        return new ChannelsSection(driver);
    }

    public PagesSection openPages() {
        waitClickableElement(pagesButton).click();
        return new PagesSection(driver);
    }

}
