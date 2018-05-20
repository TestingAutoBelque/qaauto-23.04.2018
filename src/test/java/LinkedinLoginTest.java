import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

    @Test
    public void successfulLoginTest() throws InterruptedException {
        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        Assert.assertEquals(linkedinLoginPage.getCurrentTitle(),
                "LinkedIn: Войти или зарегистрироваться",
                "Login page title is wrong");

        sleep(3000);

        Assert.assertTrue(linkedinLoginPage.isSignInButtonDisplayed(),
                "Sign In button is not Displayed");


        linkedinLoginPage.login("belovaolga394@gmail.com", "hellga_83_83");
        LinkedinHomePage linkedinHomePage = new LinkedinHomePage(webDriver);


        sleep(3000);

        Assert.assertEquals(linkedinHomePage.getCurrentUrl(),
                "https://www.linkedin.com/feed/",
                "Home page URL is wrong");

        Assert.assertTrue(linkedinHomePage.getCurrentTitle().contains("LinkedIn"),
                "Home page title is wrong");
    }


    @Test
    public void negativeLoginTest() throws InterruptedException {

        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage(webDriver);
        //String actualLoginPageTitle = linkedinLoginPage.getCurrentTitle();

        Assert.assertEquals(linkedinLoginPage.getCurrentTitle(),
                "LinkedIn: Войти или зарегистрироваться",
                "Login page title is wrong");

        sleep(3000);

        Assert.assertTrue(linkedinLoginPage.isSignInButtonDisplayed(),
                "Sign In button is not Displayed");

        linkedinLoginPage.login("belovaolga394@gmail.com", "1");

        sleep (3000);

        String currentPageUrl = webDriver.getCurrentUrl();
        String currentPageTitle = webDriver.getTitle();

        Assert.assertEquals (currentPageUrl, "Sign In to LinkedIn",
              "login-submit URL is wrong");
        Assert.assertEquals(currentPageTitle, actualHomePageTitle,
                "");
        WebElement errorMessage = webDriver.findElement(By.xpath("//div[@role='alert']"));

        Assert.assertNotEquals(errorMessage.getText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "Wrong error message text displayed");

}


    @AfterMethod
    public void after() {
        webDriver.close();
    }



}

