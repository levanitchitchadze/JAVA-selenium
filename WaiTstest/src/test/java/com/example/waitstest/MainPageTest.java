package com.example.waitstest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.commands.ToString;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;

import javax.xml.xpath.XPath;

import static org.testng.Assert.*;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPageTest {



    @Test
    public static void setUpAll() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();

        try {
            driver.get("https://demoqa.com/progress-bar");
            driver.findElement(By.cssSelector("button#startStopButton")).click();



            new WebDriverWait(driver,200).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='progress-bar bg-success']")));
            System.out.println(driver.findElement(By.xpath("//div[@class='progress-bar bg-success']")).getText());

            driver.quit();

        }catch (Exception ex){
            System.out.println("შეცდომა პროგრეს ბარზე ");
            driver.quit();
        }
    }
}
