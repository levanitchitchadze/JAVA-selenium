package com.example.webelements;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
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



            driver.get("http://the-internet.herokuapp.com/add_remove_elements/");

            for (int i=0;i<3;i++){
                driver.findElement(By.cssSelector(".example button")).click();
            }

            System.out.println(driver.findElement(By.cssSelector("#elements :last-child")).getText());
            List <WebElement> items=driver.findElements(By.cssSelector("[class^='added']:last-child"));
            System.out.println(items.get(0).getText());
            System.out.println(driver.findElement(By.xpath("//button[contains(@class ,'manually')][last()][text()='Delete']")).getText());



            Thread.sleep(5000);

            driver.navigate().to("http://the-internet.herokuapp.com/challenging_dom");

            System.out.println(driver.findElement(By.xpath("//td[text()='Apeirian9']//preceding-sibling::td")).getText());
            System.out.println(driver.findElement(By.xpath("//td[text()='Apeirian9']//following-sibling::td")).getText());


            Thread.sleep(5000);
            driver.close();

        }catch (Exception Ex){
            System.out.println("Something went wrong ");
        }



    }

}
