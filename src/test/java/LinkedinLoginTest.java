import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Keyboard;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LinkedinLoginTest {
    @Test
    public void successfulLoginTest() {
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com/");
        String actualLoginPageTitle = webDriver.getTitle();

//        Assert.assertEquals("a", "b", "Probably 'a' is not equal to 'b'");
       // Assert.assertEquals(webDriver.getTitle(),
                Assert.assertEquals(actualLoginPageTitle,
                "LinkedIn: Log In or Sign Up",
                "Login page title is wrong");



        WebElement emailField = webDriver.findElement(By. id("login-email"));
        WebElement passwordField = webDriver.findElement(By. id("login-password"));
        WebElement signInButton = webDriver.findElement(By. id("login-submit"));


        Assert.assertFalse(signInButton.isDisplayed(),
                "Sign In button is not Displayed");



        emailField.sendKeys("belovaolga394@gmail.com");
        passwordField.sendKeys("hellga_83_83");
        signInButton.click();


        Assert.assertEquals(webDriver.getCurrentUrl(),
                "X",
                "Home page URL is wrong");

        String actualHomePageTitle = webDriver.getTitle();
        Assert.assertNotEquals(actualLoginPageTitle, actualHomePageTitle,
                "Page title did not change after Sign In");

        Assert.assertTrue(webDriver.getTitle().contains("LinkedIn"),
                "Login page title is wrong");


    }


}
