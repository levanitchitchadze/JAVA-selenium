package com.example.switchtotest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.*;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.Alert;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MainPageTest {

    @Test
    public void SwitchWork() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver =new ChromeDriver();
        JavascriptExecutor js=((JavascriptExecutor) driver);
        driver.manage().window().maximize();
        driver.get("http://the-internet.herokuapp.com/iframe");


        driver.switchTo().frame("mce_0_ifr");
        try {
            js.executeScript("document.getElementsByTagName('P')[0].textContent = 'Here Goes';");
            js.executeScript("document.getElementsByTagName('P')[0].style.color = 'RED';");
        }catch (Exception ex){
            System.out.println("შეცდომა ჯავასკრიპტის კოდის შესრულების დროს ");
        }

        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//div[contains(@title ,'alignment')]/button[2]")).click();

        driver.manage().timeouts().implicitlyWait(100, SECONDS);
        driver.quit();

    }

    @Test
    public void main1() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver =new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/alerts");

        driver.findElement(By.xpath("//button[@id='alertButton']")).click();
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        driver.switchTo().alert().accept();
        driver.manage().timeouts().implicitlyWait(100, SECONDS);
        driver.quit();

    }

    @AfterMethod
    public void end(){

    }


}
