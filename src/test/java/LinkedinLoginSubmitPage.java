import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinLoginSubmitPage extends LinkedinBasePage {

    @FindBy(id="session_key-login")
    private WebElement emailField;

    @FindBy(id="session_password")
    private WebElement passwordField;

    @FindBy (xpath="//div[@role='alert']")
    private WebElement errorMessage;

    public LinkedinLoginSubmitPage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }


    public String getErrorMessageText(){
        return errorMessage.getText();
    }

    public boolean isPageLoaded(){
        return emailField.isDisplayed();
    }

}
