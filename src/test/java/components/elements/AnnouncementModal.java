package components.elements;

import components.AbstractComponent;
import components.pages.Channel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AnnouncementModal extends AbstractComponent {

    private final By expireDropdown = By.xpath("//div[contains(@class,'dropdown__control')]");
    private final By shareButton = By.xpath("//p[contains(text(), 'Share')]//ancestor::button");
    private final By customDateOption = By.xpath("//div[contains(@class,'dropdown__option') and contains(text(),'custom date')]");
    private final By dayPickerInput = By.xpath("//div[@class='DayPickerInput']//input");
    private final By confirmMessage = By.xpath("//h2[contains(text(), 'Are you sure')]");


    public AnnouncementModal(WebDriver driver) {
        super(driver, 5);
    }

    private void openDropDown() {
        waitClickableElement(expireDropdown).click();
    }

    public AnnouncementModal selectCustomExpireDateOption() {
        openDropDown();
        waitClickableElement(customDateOption).click();
        return this;
    }

    /* this is fairly ugly, I tried js executor here:
    js.executeScript("arguments[0].setAttribute('value', '"+date+"');", driver.findElement(dayPickerInput));
    but it refuses to work properly */

    public AnnouncementModal selectCustomDateWeekLater() {
        Calendar current = Calendar.getInstance();
        current.add(Calendar.DATE, 7);
        String weekLater = new SimpleDateFormat("dd/MM/yyyy").format(current.getTime());
        actions.moveToElement(driver.findElement(dayPickerInput)).doubleClick()
                .pause(2000).build().perform();
        for (String s : weekLater.split("")) {
            driver.findElement(dayPickerInput).sendKeys(s);
            driver.findElement(dayPickerInput).click();
        }
        return this;
    }

    public AnnouncementModal shareAnnouncement() {
        driver.findElement(shareButton).click();
        return this;
    }

    public Channel confirm() {
        waitVisibleElement(confirmMessage);
        driver.findElement(shareButton).click();
        return new Channel(driver);
    }
}
