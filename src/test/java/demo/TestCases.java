package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;


public class TestCases {
    ChromeDriver driver;


    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void testCase01() throws InterruptedException {

        System.out.println("Start Test Case: 01");

        Wrappers wrap = new Wrappers(driver);

        wrap.openUrl("https://www.flipkart.com/");

        wrap.typeText(By.name("q"), "Washing Machine");

        wrap.click(By.xpath("//div[text()='Popularity']"));


        List<WebElement> products = wrap.getElements(By.xpath(
                "//a[@class='k7wcnx']//div[@class='MKiFS6' and number(text()) <= 4]"));
        System.out.println("Items which have rating less than 4.0: " + wrap.getCount(products));
        Thread.sleep(2000);
        System.out.println("End Test Case: 01");
    }

    @Test
    public void testCase02() throws InterruptedException {
        System.out.println("Start Test Case: 02");

        Wrappers wrap = new Wrappers(driver);

        wrap.typeText(By.name("q"), "iPhone");

        wrap.setFilter(By.xpath("//div[text()='Discount']//following-sibling::*[1]"),
                By.xpath("//div[text()='Discount']/ancestor::section//div[contains(text(),'10% or more')]"));

        List<WebElement> products = wrap.getElements(By.xpath(
                "//div[@class='HQe8jr']//span[contains(text(),'%')]"));



//        Print Title and Discount of products
        wrap.iphoneTitleAndDiscount(products);

        Thread.sleep(2000);
        System.out.println("End Test Case: 02");
    }

    @Test
    public void testCase03() throws InterruptedException {
        System.out.println();
        System.out.println("Start Test Case: 03");

        Wrappers wrap = new Wrappers(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wrap.typeText(By.name("q"), "Coffee Mug");

        //Click on Four Star and Above
        wrap.setFilter(By.xpath("//div[text()='Customer Ratings']//following-sibling::*[1]"),
                By.xpath("//div[text()='Customer Ratings']/ancestor::section//div[contains(text(),'4')]"));

        //Get all the product cards
        List<WebElement> products = wrap.getElements(By.xpath("//div[@class='RGLWAk']"));

        //Print Title and Image URL of the 5 items with highest number of reviews
        wrap.coffeeMugTitleAndImageURL(products);

        System.out.println("End Test Case: 03");
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}