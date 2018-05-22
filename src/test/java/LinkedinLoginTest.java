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
                { "belovaolga394@gmail.com", "hellga_83_83" },
                { "BELOVAOLGA394@GMAIL.COM", "hellga_83_83" }
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
        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(webDriver);


        sleep(3000);

        Assert.assertEquals(linkedinHomePage.getCurrentUrl(),
                "https://www.linkedin.com/feed/",
                "Home page URL is wrong");

        Assert.assertTrue(linkedinHomePage.getCurrentTitle().contains("LinkedIn"),
                "Home page title is wrong");
    }


    @Test
    public void negativeReturnedToLoginSubmitTest() throws InterruptedException {

        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertTrue(linkedinLoginPage.isSignInButtonDisplayed(),
                "Sign In button is not Displayed");

        sleep (3000);

        linkedinLoginPage.login("belovaolga394@gmail.com", "1");
        LinkedinLoginSubmitPage linkedinLoginSubmitPage = new LinkedinLoginSubmitPage(webDriver);

        sleep (3000);

        Assert.assertTrue(linkedinLoginSubmitPage.isPageLoaded(),
                "Login-Submit page is not loaded.");
        Assert.assertEquals (linkedinLoginSubmitPage.getErrorMessageText(),"",
                "Error message text is incorrect");
    }

    @Test
    public void failLoginTest2() throws InterruptedException {

        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getCurrentTitle(),
                "LinkedIn: Войти или зарегистрироваться",
                "Login page title is wrong");
        Assert.assertTrue(linkedinLoginPage.isSignInButtonDisplayed(),
                "Sign In button is not Displayed");

        linkedinLoginPage.login("0", "hellga_83_83");

        sleep (3000);
        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(webDriver);

        Assert.assertEquals (linkedinHomePage.getCurrentUrl(), "Sign In to LinkedIn",
                "login-submit URL is wrong");
        Assert.assertTrue(linkedinHomePage.getCurrentTitle().contains("LinkedIn"),
                "Home page title is wrong");
    }


    @AfterMethod
    public void after() {
        webDriver.close();
    }



}

