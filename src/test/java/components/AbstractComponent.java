package components;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public abstract class AbstractComponent {
    protected WebDriverWait wait;
    protected WebDriver driver;
    protected Actions actions;

    public AbstractComponent(WebDriver driver, long timeOutInSeconds) {
        this.driver = driver;
        wait = new WebDriverWait(driver, timeOutInSeconds);
        actions = new Actions(driver);
    }

    public WebElement waitVisibleElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitClickableElement(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public List<WebElement> waitPresenceOfAllElements(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void scrollElementIntoView(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView()", element);
    }

    public void hover(WebElement element) {
        actions.moveToElement(element).build().perform();
    }

    public int getRandomIndex(int bound) {
        return new Random().nextInt(bound);
    }

    public String getElementText(WebElement element) {
        return element.getText();
    }
}
