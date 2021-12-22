package com.example.fileuploadtest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.lang.*;


public class JSexecutor {
    WebDriver driver;
    JavascriptExecutor JS;
    Actions act;

    @BeforeMethod
    public void OpenBrowser(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();


    }

    @Test
    public void JSWork(){
        driver.get("http://webdriveruniversity.com/To-Do-List/index.html");

        //ელემენტი ჩავსვით PM ცვლადში
        WebElement PM=driver.findElement(By.xpath("//*[contains(text(),'Practice magic')]"));
        //შევქმენით Actions კლასი
        act=new Actions(driver);
        //კურსორი გადავიტანეთ ელემენტზე რომელიც PM ცვლადშია ჩაწერილი
        act.moveToElement(PM).perform();


        // გამოვიძახეთ JavascriptExecutor კლასი
        JS=(JavascriptExecutor) driver;

        //გამოვიძახე JavascriptExecutor კლასის მეთოდი გადავეცი ელემენტი 'Buy new robes' და დავაკლიკე ჯავსკრიპტის გამოყენებით
        JS.executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[contains(text(),'Buy new robes')]/span")));




        //გადავდივარ ახალ საიტზე
        driver.navigate().to("http://webdriveruniversity.com/Scrolling/index.html");


        //ჩამოვსქროლე მეოთხე ზონაში ელემენტის იდენტიფიკატორით
        JS.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//*[@id=\"zone4\"]")) );

        //კურსორი გადავიტანე მარცხენა უჯრაზე
        act.moveToElement(driver.findElement(By.xpath("//h1[@id='zone2-entries']"))).perform();

        //WE ცვლადს მივანიჭე ზონა ორის ელემენტი და გავუშვი JavascriptExecutor ში და შემდეგ დავპრინტე
        WebElement WE=driver.findElement(By.xpath("//*[@id='zone2-entries']"));
        String theTextIWant = JS.executeScript("return arguments[0].innerHTML;",WE).toString();
        System.out.println(theTextIWant);


    }

    @AfterMethod
    public void EndSession(){
        driver.quit();
        System.out.println("All processes were completed successfully");
    }

}
