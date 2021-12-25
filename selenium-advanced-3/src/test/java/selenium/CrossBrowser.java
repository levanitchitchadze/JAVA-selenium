package selenium;

import java.util.concurrent.TimeUnit;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.lang.*;


public class CrossBrowser {

    WebDriver driver;
    JavascriptExecutor JS;
    Actions act;

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) throws Exception{

        if(browser.equalsIgnoreCase("chrome")){
            driver = new HtmlUnitDriver(BrowserVersion.CHROME);
        }
        else if(browser.equalsIgnoreCase("Edge")){
            WebDriverManager.edgedriver().setup();
            driver = new HtmlUnitDriver(BrowserVersion.EDGE);
        }
        else{
            throw new Exception("Browser is not correct");
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


        @Test
        public void JSWork() {
            driver.get("http://webdriveruniversity.com/To-Do-List/index.html");

            //ელემენტი ჩავსვით PM ცვლადში
            WebElement PM = driver.findElement(By.xpath("//*[contains(text(),'Practice magic')]"));
            //შევქმენით Actions კლასი
            act = new Actions(driver);
            //კურსორი გადავიტანეთ ელემენტზე რომელიც PM ცვლადშია ჩაწერილი
            act.moveToElement(PM).perform();


            // გამოვიძახეთ JavascriptExecutor კლასი
            JS = (JavascriptExecutor) driver;

            //გამოვიძახე JavascriptExecutor კლასის მეთოდი გადავეცი ელემენტი 'Buy new robes' და დავაკლიკე ჯავსკრიპტის გამოყენებით
            JS.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[contains(text(),'Buy new robes')]/span")));

            System.out.println("Full Text : " + JS.executeScript("return document.documentElement.innerText;").toString() + "\n");
            System.out.println("Full Text : " + JS.executeScript("return document.documentElement.innerText;").toString() + "\n");


            //გადავდივარ ახალ საიტზე
            driver.navigate().to("http://webdriveruniversity.com/Scrolling/index.html");


            //ჩამოვსქროლე მეოთხე ზონაში ელემენტის იდენტიფიკატორით
            JS.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[@id=\"zone4\"]")));

            //კურსორი გადავიტანე მარცხენა უჯრაზე
            act.moveToElement(driver.findElement(By.xpath("//h1[@id='zone2-entries']"))).perform();

            //WE ცვლადს მივანიჭე ზონა ორის ელემენტი და გავუშვი JavascriptExecutor ში და შემდეგ დავპრინტე
            WebElement WE = driver.findElement(By.xpath("//*[@id='zone2-entries']"));
            String theTextIWant = JS.executeScript("return arguments[0].innerHTML;", WE).toString();
            System.out.println(theTextIWant);


        }
}

