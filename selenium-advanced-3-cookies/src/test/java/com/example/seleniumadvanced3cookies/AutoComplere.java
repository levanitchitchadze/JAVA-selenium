package com.example.seleniumadvanced3cookies;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class AutoComplere {
    WebDriver driver;

    @BeforeMethod
    public void OpenBrowser(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void GoogleSearch(){
        driver.get("https://www.google.com/");

        //ვწერ ტექსტს გუგლის საძიებო ველში და ვაჭერ ენთერ ღილაკს
        driver.findElement(By.cssSelector("input[name='q'][type='text']")).sendKeys("Automation");
        driver.findElement(By.cssSelector("input[name='q'][type='text']")).sendKeys(Keys.ENTER);

        //ვსქროლავ დოკუმენტის სიგრძეზე რომ გამოჩნდეს ბოლო ელემენტი
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        //ვინახავ ყველა a თაგს სადაც ლინკი ინახება
        List<WebElement> Links=driver.findElements(By.xpath("//div[@class='v7W49e']//a"));
        for (int i=0;i<Links.size();i++) {
            //გვიბეჭდავს ყველა ლინკს
            System.out.println(Links.get(i).getText());

        }

        //ბოლო ლინკზე გადასვლა
        Links.get(Links.size()-1).click();
    }

}
