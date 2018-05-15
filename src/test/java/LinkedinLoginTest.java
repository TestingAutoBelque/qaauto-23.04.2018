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
    public void successfulLoginTest() {

        String actualLoginPageTitle = webDriver.getTitle();

//        Assert.assertEquals("a", "b", "Probably 'a' is not equal to 'b'");
       // Assert.assertEquals(webDriver.getTitle(),
                Assert.assertEquals(actualLoginPageTitle,
                "LinkedIn: Log In or Sign Up",
                "Login page title is wrong");




        LinkedinLoginPage linkedinLoginPage = new LinkedinLoginPage();
        linkedinLoginPage.login("belovaolga394@gmail.com", "hellga_83_83");







        Assert.assertFalse(signInButton.isDisplayed(),
                "Sign In button is not Displayed");



        Assert.assertEquals(webDriver.getCurrentUrl(),
                "X",
                "Home page URL is wrong");

        String actualHomePageTitle = webDriver.getTitle();
        Assert.assertNotEquals(actualLoginPageTitle, actualHomePageTitle,
                "Page title did not change after Sign In");

        Assert.assertTrue(webDriver.getTitle().contains("LinkedIn"),
                "Login page title is wrong");
    }








//Hometask_Negative Login Tests

    @Test
    public void failedLoginTest() {



        WebElement emailField = webDriver.findElement(By. id("login-email"));
        WebElement passwordField = webDriver.findElement(By. id("login-password"));
        WebElement signInButton = webDriver.findElement(By. id("login-submit"));


        Assert.assertFalse(signInButton.isDisplayed(),
                "Sign In button is not Displayed");


    //1. Email and Password are both blank

        emailField.clear();
        passwordField.clear();

        Assert.assertNull(emailField,
                "Please enter your username or email!");

        Assert.assertNull(passwordField,
                "Please enter your password!");

        Assert.assertFalse(signInButton.isDisplayed(),
                "Sign In button is not Displayed");


    //2. Email - filled. Password - blank

        emailField.sendKeys("belovaolga394@gmail.com");
        //passwordField.sendKeys("");
        passwordField.clear();

        Assert.assertEquals(emailField, emailField.getText(),
                "Wrong username or email!");
        Assert.assertNull(passwordField,
                "Please enter your password!");

        Assert.assertFalse(signInButton.isDisplayed(),
                "Sign In button is not Displayed");

    //3. Email - blank, Password - filled

        emailField.clear();
        passwordField.sendKeys("hellga_83_83");

        Assert.assertNull(emailField,
                "Please enter your username or email!");
        Assert.assertEquals(passwordField, passwordField.getText(),
                "Wrong password!");

        Assert.assertFalse(signInButton.isDisplayed(),
                "Sign In button is not Displayed");



    //4. Email - incorrect, Password - incorrect

        emailField.sendKeys("893745jnfjdngj");
        passwordField.sendKeys("swegwgw");

        Assert.assertNotEquals(emailField, emailField.getText(), "Email is wrong! Please enter correct email");
        Assert.assertNotEquals(passwordField, passwordField.getText(), "Password is wrong! Please enter correct password");

        Assert.assertFalse(signInButton.isDisplayed(),
                "Sign In button is not Displayed");


    //5. Email - incorrect, Password - correct

        emailField.sendKeys("jhbibi808081111////");
        passwordField.sendKeys("hellga_83_83");

        Assert.assertNotEquals(emailField, emailField.getText(), "Email is wrong! Please enter correct email");
        Assert.assertEquals(passwordField, passwordField.getText(), "Password is wrong! Please enter correct password");

        Assert.assertFalse(signInButton.isDisplayed(),
                "Sign In button is not Displayed");

    //6. Email - correct, Password - incorrect


        emailField.sendKeys("belovaolga394@gmail.com");
        passwordField.sendKeys("0=0=jnjknjn");

        Assert.assertEquals(emailField, emailField.getText(), "Email is wrong! Please enter correct email");
        Assert.assertNotEquals(passwordField, passwordField.getText(), "Password is wrong! Please enter correct password");

        Assert.assertFalse(signInButton.isDisplayed(),
                "Sign In button is not Displayed");



    //7. Email - absent

        Assert.assertNull(emailField.findElement(By. id("login-email")));
        Assert.assertNotNull(passwordField.findElement(By.id("login-password")));

        Assert.assertFalse(signInButton.isDisplayed(),
                "Sign In button is not Displayed");


    //8. Password - absent

        Assert.assertNotNull(emailField.findElement(By. id("login-email")));
        Assert.assertNull(passwordField.findElement(By.id("login-password")));

        Assert.assertFalse(signInButton.isDisplayed(),
                "Sign In button is not Displayed");


    //9. Email - absent, Password - absent

        Assert.assertNull(emailField.findElement(By. id("login-email")));
        Assert.assertNull(passwordField.findElement(By.id("login-password")));

        Assert.assertFalse(signInButton.isDisplayed(),
                "Sign In button is not Displayed");

    //10. 'Submit' button is absent

        Assert.assertNotNull(emailField.findElement(By. id("login-email")));
        Assert.assertNotNull(passwordField.findElement(By.id("login-password")));

        Assert.assertNull(signInButton.findElement(By.id("login-submit")));


    //11. Email field is not available

        Assert.assertFalse(emailField.isDisplayed(),
                "Email field is not available");
        Assert.assertTrue(passwordField.isDisplayed(),
                "Password field is not available");


        Assert.assertFalse(signInButton.isDisplayed(),
                "Sign In button is not Displayed");


    //12. Password field is not available

        Assert.assertTrue(emailField.isDisplayed(),
                "Email field is not available");
        Assert.assertFalse(passwordField.isDisplayed(),
                "Password field is not available");

        Assert.assertFalse(signInButton.isDisplayed(),
                "Sign In button is not Displayed");

    //13. Email and Password fields are not available

        Assert.assertFalse(emailField.isDisplayed(),
                "Email field is not available");
        Assert.assertFalse(passwordField.isDisplayed(),
                "Password field is not available");

        Assert.assertFalse(signInButton.isDisplayed(),
                "Sign In button is not Displayed");

    //14. 'Submit' button is not available

        Assert.assertTrue(emailField.isDisplayed(),
                "Email field is not available");
        Assert.assertTrue(passwordField.isDisplayed(),
                "Password field is not available");

        Assert.assertFalse(signInButton.isDisplayed(),
                "Sign In button is not Displayed");

    //15. Email: chars quantity out of limitation range

    //16. Password: chars quantity out of limitation range

    //17. Email and Password: chars quantity out of limitation range
    }


    //lesson 5

    @Test
    public void negativeLoginTest() throws InterruptedException {



        String actualLoginPageTitle = webDriver.getTitle();

//        Assert.assertEquals("a", "b", "Probably 'a' is not equal to 'b'");
        // Assert.assertEquals(webDriver.getTitle(),
        Assert.assertEquals(actualLoginPageTitle,
                "LinkedIn: Войти или зарегистрироваться",
                "Login page title is wrong");



        WebElement emailField = webDriver.findElement(By. id("login-email"));
        WebElement passwordField = webDriver.findElement(By. id("login-password"));
        WebElement signInButton = webDriver.findElement(By. id("login-submit"));




        Assert.assertFalse(signInButton.isDisplayed(),
                "Sign In button is not Displayed");



        emailField.sendKeys("belovaolga394@gmail.com");
        passwordField.sendKeys("1");
        signInButton.click();

        sleep (3000);

        String currentPageUrl = webDriver.getCurrentUrl();
        String currentPageTitle = webDriver.getTitle();

        Assert.assertEquals (currentPageUrl, "Sign In to LinkedIn",/*make sure assert falls either it is passed anyway*/
                "login-submit URL is wrong");

        WebElement errorMessage = webDriver.findElement(By.xpath("//div[@role='alert']"));

        Assert.assertNotEquals(errorMessage.getText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "Wrong error message text displayed");/*notequals - falls*/

}


    @AfterMethod
    public void after() {
        webDriver.close();
    }



}


/*
@beforeClass - open-close Browser 1nce
@beforeMethod - each time for each test
@beforeTest
*/