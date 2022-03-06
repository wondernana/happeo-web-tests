package components.elements;

import components.AbstractComponent;
import components.pages.Channel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChannelsSection extends AbstractComponent {

    By searchBar = By.id("nav-channels-search");
    By searchResults = By.xpath("//*[contains(text(),'Search results')]");

    public ChannelsSection(WebDriver driver) {
        super(driver, 5);
    }

    public Channel searchAndOpenChannel(String channel) {
        waitVisibleElement(searchBar).sendKeys(channel);
        waitForResults();
        openChannelFromResults(channel);
        return new Channel(driver);
    }


    private void waitForResults() {
        waitVisibleElement(searchResults);
    }

    private void openChannelFromResults(String channel) {
        driver.findElement(By.partialLinkText(channel)).click();
    }
}
