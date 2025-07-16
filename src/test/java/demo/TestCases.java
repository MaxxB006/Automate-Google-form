package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;
    Wrappers wrapper ;
    LocalDate today = LocalDate.now();
    LocalDate sevenDaysAgo = today.minusDays(7);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    long epoch = System.currentTimeMillis() / 1000;
    private String url ="https://forms.gle/wjPkzeSEk1CM7KgGA";


    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */
    @Test
    public void testCase01() throws InterruptedException {
        //launching form
        //adding this for commit PR
        wrapper.openURL(url);
        Thread.sleep(2000);
        //filling form
        List<WebElement> inputbox = driver.findElements(By.xpath("//input[@type='text']"));
        wrapper.typeText(inputbox.get(0), "Crio Learner");
        WebElement autobox = driver.findElement(By.xpath("//textarea[@jsname='YPqjbf']"));;
        wrapper.typeText(autobox, "I want to be the best QA Engineer! " + epoch);
        WebElement radio = driver.findElement(By.xpath("(//div[@class='SG0AAe']/div[3]//div/div)[5]"));
        wrapper.clickElement(radio);
        List<WebElement> checkboxes = driver.findElements(By.xpath("//div[@class='eBFwI']"));
        wrapper.clickElement(checkboxes.get(0));
        wrapper.clickElement(checkboxes.get(1));
        wrapper.clickElement(checkboxes.get(3));
        WebElement dropElement = driver.findElement(By.xpath("//span[text()='Choose']"));
        wrapper.clickElement(dropElement);
        Thread.sleep(1000);
        wrapper.selectPronoun(By.xpath("//div[@jsname='V68bde']/div[@jsname='wQNmvb'][2]/span"));
        Thread.sleep(1000);
        WebElement date = driver.findElement(By.xpath("//input[@type='date']"));
        wrapper.setDate(date, sevenDaysAgo.format(formatter));
        wrapper.typeText(inputbox.get(1), "07");
        wrapper.typeText(inputbox.get(2), "30");
        // submitting form
        wrapper.clickSubmit(By.xpath("(//div[@role='button'])[1]"));
        //checking succes message
        wrapper.displayText(By.xpath("//div[@class='vHW8K']"));
    }

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */
    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wrapper = new Wrappers(driver);
    }

    @AfterTest
    public void endTest() {
        driver.close();
        driver.quit();

    }
}