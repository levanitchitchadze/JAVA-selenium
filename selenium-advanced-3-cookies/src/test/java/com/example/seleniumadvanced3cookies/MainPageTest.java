package com.example.seleniumadvanced3cookies;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.util.*;
import static org.testng.Assert.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPageTest {
    WebDriver driver;

    @BeforeClass
    public void BrowserOpes() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @Test
    public void LogIn() {
        driver.get("http://demo.guru99.com/test/cookie/selenium_aut.php");
        //გავდივარ პირველ ავტორიზაციას შემყავს იუზერი
        WebElement UserInput =driver.findElement(By.cssSelector("input.form-control[name='username']"));
        UserInput.sendKeys("Selenium");
        //შემყავს პაროლი
        WebElement PassWordInput =driver.findElement(By.cssSelector("input.form-control[name='password']"));
        PassWordInput.sendKeys("abc123");
        //ვაწვები ლოგინ ღილაკს
        WebElement LogInButton = driver.findElement(By.cssSelector("button[name='submit']"));
        LogInButton.click();
        //cookie-ებს ვინახავ სეთში
        Set<Cookie> cookie = driver.manage().getCookies();



        //ვხურავ ბრაუზერს და თავიდან ვხსნი ლინკს
        driver.close();
        driver =new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://demo.guru99.com/test/cookie/selenium_aut.php");

        //წინა ბრაუზერიდან შენახულ ქუქიებს ვამატებ ახალში ციკლის მეშვეობით სათითაოდ
        for(Cookie ck:cookie){
            driver.manage().addCookie(ck);
        }

        //ვარეფრეშებ გვერდს რომ აისახოს cookie-ები
        driver.navigate().refresh();

        //ვშლი ყველა cookie-ის
        driver.manage().deleteAllCookies();



    }

    @Test
    public void toolsMenu() {
        //driver.quit();
    }

}
