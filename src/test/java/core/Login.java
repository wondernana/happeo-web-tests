package core;

import components.AbstractComponent;
import infrastructure.Environment;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Login extends AbstractComponent {

    private final String USER = "example@google.com";
    private final String PASS = "secret";


    private By signinButton = By.id("signin-btn");
    private By inputEmail = By.xpath("//input[@type='email']");
    private By inputPassword = By.xpath("//input[@type='password']");
    private By myStream = By.xpath("//*[@data-component-name='MyStream']");

    public Login(WebDriver driver){
        super(driver, 15);
    }

    public Login openPage() {
        driver.get(Environment.getURL());
        return this;
    }

    public void loginWith(LoginOption option) {
        switch (option) {
            case GOOGLE:
                googleLogin();
                break;

            case COMPANY:
                //TODO
                break;
        }
    }

    private void googleLogin() {
        waitVisibleElement(signinButton).click();
        waitClickableElement(inputEmail).sendKeys(USER + Keys.ENTER);
        waitClickableElement(inputPassword).sendKeys(PASS + Keys.ENTER);
        waitForHomePage();
    }

    private void waitForHomePage() {
        waitVisibleElement(myStream);
    }

    public enum LoginOption {
        GOOGLE,
        COMPANY
    }
}
