package com.example.seleniumcommands;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Wait;

public class MainPageTest {

    @Test
    public void start() {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        String url = "http://the-internet.herokuapp.com/dynamic_controls";
        driver.get(url);

        try {



                driver.findElement(By.cssSelector("#input-example button")).click();
                System.out.println("Hold down the button");
                System.out.println(driver.findElement(By.cssSelector("#input-example button")).getText());
            Thread.sleep(10000);
                String EText=driver.findElement(By.cssSelector("#message")).getText();
                String BText=driver.findElement(By.cssSelector("#input-example button")).getText();


            if(EText.equals("It's enabled!")|| BText.equals("Enable")) {


                System.out.println("enabled!");
                System.out.println("button changed to " +BText);

                driver.findElement(By.cssSelector("#input-example input")).sendKeys("Bootcamp");
                Thread.sleep(2000);
                driver.findElement(By.cssSelector("#input-example input")).clear();




            }else {


            }


            driver.navigate().to("http://the-internet.herokuapp.com/drag_and_drop");



            int ACordY= driver.findElement(By.cssSelector("#column-a")).getLocation().getY();
            int BCordY= driver.findElement(By.cssSelector("#column-b")).getLocation().getY();

            if(ACordY==BCordY) {
                System.out.println("Block A coordinate Y :" + ACordY);
                System.out.println("Block B coordinate Y :" + BCordY);
                System.out.println("coordinates are same ");

            }else {
                System.out.println("Block A coordinate Y :" + ACordY);
                System.out.println("Block B coordinate Y :" + BCordY);
                System.out.println("coordinates are not same ");

            }

            Thread.sleep(10000);
            driver.close();

        } catch (Exception ex) {
                System.out.println("Something went wrong");

        }


    }



}
