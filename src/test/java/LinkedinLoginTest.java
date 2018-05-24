import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Keyboard;
import org.testng.Assert;
import org.testng.annotations.*;

import static java.lang.Thread.sleep;

public class LinkedinLoginTest {
  WebDriver webDriver;


    @BeforeMethod
    public void before(){

        webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com/");
    }

    @DataProvider
    public Object[][] ValidDataProvider() {
        return new Object[][]{
                {"belovaolga394@gmail.com", "hellga_83_83" },
                {"BELOVAOLGA394@GMAIL.COM", "hellga_83_83" }
        };
    }


    @Test(dataProvider="ValidDataProvider")
    public void successfulLoginTest(String email, String password) throws InterruptedException {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getCurrentTitle(),
                "LinkedIn: Войти или зарегистрироваться",
                "Login page title is wrong");

        sleep(3000);

        Assert.assertTrue(linkedinLoginPage.isSignInButtonDisplayed(),
                "Sign In button is not Displayed");


        linkedinLoginPage.login(email, password);
        linkedinLoginPage.clickSignIn();
        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(webDriver);


        sleep(3000);

        Assert.assertEquals(linkedinHomePage.getCurrentUrl(),
                "https://www.linkedin.com/feed/",
                "Home page URL is wrong");

        Assert.assertTrue(linkedinHomePage.getCurrentTitle().contains("LinkedIn"),
                "Home page title is wrong");
    }


    @DataProvider
    public Object[][] inValidDataProvider() {
        return new Object[][]{
                {"belovaolga394gmail.com", "hellga_83_83" },
                {"belovaolga394@gmailcom", "hellga_83_83" },
                {"belovaolga394@gmail. com", "hellga_83_83"},
                {"belovaolga394@gmail. com", "HELLGA_83_83"},
                {"belovaolga394@gmail.com", "HELLGA_83_83"}
        };
    }
    @Test(dataProvider="inValidDataProvider")
    public void negativeReturnedToLoginSubmitTest(String email, String password) throws InterruptedException {

        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertTrue(linkedinLoginPage.isSignInButtonDisplayed(),
                "Sign In button is not Displayed");

        sleep (3000);

        linkedinLoginPage.login(email, password);
        linkedinLoginPage.clickSignIn();
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(webDriver);

        sleep (3000);

        Assert.assertTrue(linkedinLoginSubmitPage.isPageLoaded(),
                "Login-Submit page is not loaded.");
        Assert.assertEquals (linkedinLoginSubmitPage.getErrorMessageText(),
                "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.",
                "Error message text is incorrect");
    }


    @DataProvider
    public Object[][] cornerinValidDataProvider() {
        return new Object[][]{
                {"belovaolga394@gmail.com", "1" },
                {"0", "hellga_83_83" },
                {"0", "1"}
        };
    }
    @Test(dataProvider="cornerinValidDataProvider")
    public void negativeReturnedToLoginSubmitCornerMinTest(String email, String password) throws InterruptedException {

        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertTrue(linkedinLoginPage.isSignInButtonDisplayed(),
                "Sign In button is not Displayed");

        sleep (3000);
        linkedinLoginPage.login(email, password);
        linkedinLoginPage.clickSignIn();

        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(webDriver);

        sleep (3000);

        Assert.assertTrue(linkedinLoginSubmitPage.isPageLoaded(),
                "Login-Submit page is not loaded.");
        Assert.assertEquals (linkedinLoginSubmitPage.getErrorMessageText(),
                "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.",
                "Error message text is incorrect");

    }


    @DataProvider
    public Object[][] emptyinValidDataProvider() {
        return new Object[][]{
                {"", ""},
                {"", "hellga_83_83"},
                {"belovaolga394@gmail.com", ""}
        };
    }
    @Test(dataProvider="emptyinValidDataProvider")
    public void negativeReturnedToLoginSubmitEmptyTest(String email, String password) throws InterruptedException {

        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertTrue(linkedinLoginPage.isSignInButtonDisplayed(),
                "Sign In button is not Displayed");

        sleep (3000);

        linkedinLoginPage.login(email, password);


        Assert.assertFalse(linkedinLoginPage.isSignInButtonActive(),
                "Sign In button is not available");


        Assert.assertTrue(linkedinLoginPage.isLoginPageLoaded(),
                "Login page is not loaded.");

    }

    @AfterMethod
    public void after() {
        webDriver.close();
    }



}

