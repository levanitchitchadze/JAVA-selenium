package com.example.exceptions;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import java.util.concurrent.TimeUnit;


public class MainPageTest {

    WebDriver driver;

    @BeforeTest
    public void openBrowser()
    {
        WebDriverManager.chromedriver().setup();

        driver=new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void date_test()
    {
        driver.get("https://jqueryui.com/datepicker/");

        //ფრეიმზე გადასვლა თარიღის ველში შესვლა და თარიღის არჩევა
        try {
            driver.switchTo().frame(driver.findElement(By.cssSelector("iframe.demo-frame")));
            driver.findElement(By.cssSelector("#datepicker")).click();
            WebElement old_saved=driver.findElement(By.xpath("//*[@class='ui-icon ui-icon-circle-triangle-w']"));
            old_saved.click();
            driver.findElement(By.xpath("//a[@class='ui-state-default'][@data-date='30']")).click();


            //StaleElementReferenceException - გამოძახება
            try {
                driver.switchTo().defaultContent();
                old_saved.isDisplayed();
            }catch (StaleElementReferenceException SERE){
                System.out.println("შეცდომა, ძველი ელემენტის ძებნის დროს "+SERE.getClass().getSimpleName());
            }

        //NoSuchElementException - გამოძახება
        }catch (org.openqa.selenium.NoSuchElementException ex){
            System.out.println("შეცდომა "+ex);

        }


    }

    @Test(dependsOnMethods = {"date_test"})
    public void timer_alert(){
        driver.navigate().to("https://demoqa.com/alerts");
        driver.findElement(By.xpath("//button[@Id='timerAlertButton']")).click();

        //NoAlertPresentException - გამოძახება
        try {

            Alert at=driver.switchTo().alert();
            at.accept();


        }catch (NoAlertPresentException NAPE){
            System.out.println("შეცდომა მსგავსი ალერტი არ მოიძებნა "+NAPE.getClass().getSimpleName()+" 1");

        }
        //დროის ალერტზე დათანხმება
        try {
            new WebDriverWait(driver,6).until(ExpectedConditions.alertIsPresent());
            Alert at=driver.switchTo().alert();
            at.accept();

        }catch (NoAlertPresentException NAPE){
            System.out.println("შეცდომა მსგავსი ალერტი არ მოიძებნა "+NAPE.getClass().getSimpleName() +" 2");
        }

        // TimeOutException - გამოძახება
        try {
            new WebDriverWait(driver,1).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='invokeID']")));

        }catch (Exception ex){
            System.out.println("შეცდომა დრო გავიდა "+ex.getClass().getName());
        }

    }



    @Test(dependsOnMethods = {"timer_alert"})
    public void WebTables(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebTablesTest TAB=new WebTablesTest();


        System.out.println("Honda price is : " +TAB.printText(driver));

    }

    @Test(dependsOnMethods = {"WebTables"})
    public void quit(){

        driver.quit();
    }
}
