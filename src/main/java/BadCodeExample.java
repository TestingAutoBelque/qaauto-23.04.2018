import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

import static java.lang.Thread.sleep;
//import static org.openqa.selenium.support.ui.ExpectedConditions.findElements;

public class BadCodeExample {

public static void main(String args[]) throws InterruptedException {
    //System.out.println("Hello World!!!");
    WebDriver webDriver = new FirefoxDriver();
    webDriver.get("https://www.google.com");

    WebElement searchField = webDriver.findElement(By.id("1st-ib"));
    searchField.sendKeys("Selenium");

    sleep ( 1000);
    WebElement searchButton = webDriver.findElement(By.xpath("//input[@type='button' and contains(@value, 'Google')]"));
    searchButton.click();
    searchField.sendKeys(Keys.ENTER);

    sleep(3000);

    /*List<WebElement> searchResults = webDriver.findElements(By.xpath("//*div[@class='srg']/div[@class='g']"));
    System.out.println(searchResults.size());

    for (WebElement searchResult : searchResults) {
        String searchResultText = searchResult.getText();
        if (searchResultText.contains("Selenium")) {
            System.out.println("SearcTerm Found!!");
            System.out.println(searchResult.getText());
        }
        System.out.println(searchResultText);
    }*/


    for (int i = 1; i<=10; i++) {

        WebElement searchResult = webDriver.findElement(By.xpath ("//div[@class='srg']/div["+i+"]"));
        String searchResultText = searchResult.getText();
        System.out.println(searchResult.getText());
        if (searchResultText.contains("Selenium")) {
            System.out.println("SearcTerm Found!!");
            System.out.println(searchResult.getText());
        }
        System.out.println(searchResultText);
    }


    sleep (5000);
    webDriver.close();



}

}


