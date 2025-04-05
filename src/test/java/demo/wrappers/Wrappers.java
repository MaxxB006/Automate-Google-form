package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    ChromeDriver driver;
    WebDriverWait wait ;
    Logger logger = Logger.getLogger(Wrappers.class.getName());

    public Wrappers(ChromeDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openURL(String url) {
        driver.get(url);
    }

    public void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void typeText(WebElement element, String s) {
        element.click();
        element.clear();
        element.sendKeys(s);
    }

    public void displayText(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebElement string = driver.findElement(locator);
        System.out.println(string.getText());
    }

    public void selectPronoun(By pro) {
        WebElement pr = driver.findElement(pro);
        pr.click();
        System.out.println("Pronoun Selected");   
    }

    public void setDate(WebElement date, String format) {
        date.sendKeys(format);
        System.out.println("Date Selected");  
    }

    public void clickSubmit(By xpath) {
        WebElement element = driver.findElement(xpath);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        element.click();
    }
}
