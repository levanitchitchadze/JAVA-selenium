package com.example.waitstest;

import java.util.*;

import com.codeborne.selenide.conditions.Value;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.Test;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.commands.ToString;
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

import javax.xml.xpath.XPath;

import static com.codeborne.selenide.Condition.*;
import static org.testng.Assert.*;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.*;


public class WaitsTest {

    @Test
    public static void dropdown(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();

        try {
            driver.get("http://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");

            Select dropdown = new Select(driver.findElement(By.xpath("//select[@id='dropdowm-menu-1']")));
            List<WebElement> items=dropdown.getOptions();

            for (int i=0;i<items.size();i++) {
                System.out.println(items.get(i).getText());
            }

            dropdown.selectByVisibleText("JAVA");




        }catch(Exception ex){
            System.out.println("შეცდომა სიიდან არჩევის დროს ");
        }

        try {

            for (int i=1;i<5;i++) {
                if((driver.findElement(By.xpath("//div[@id='checkboxes']/label["+i+"]/input"))).isSelected()){
                    System.out.println("TRUE");
                }else{
                    System.out.println("FALSE");
                    driver.findElement(By.xpath("//div[@id='checkboxes']/label["+i+"]/input")).click();
                }
            }

        }catch (Exception ex){
            System.out.println("შეცდომა ჩეკბოქსის შემოწმების დროს ");
        }



        try {

            List<WebElement> ritem=driver.findElements(By.xpath("//form[@id='radio-buttons']/input"));

            System.out.println();
            System.out.println();

            for (int i=0;i<ritem.size();i++){
                //System.out.println(ritem.get(i).getAttribute("value"));

                if((ritem.get(i).getAttribute("Value")).equals("yellow")){
                    driver.findElement(By.xpath("//input[@name='color']["+(i+1)+"]")).click();
                }
            }

        }catch (Exception ex){
            System.out.println("შეცდომა რადიობუთონის შემოწმების დროს ");
        }



        try {

            List<WebElement> radioitem=driver.findElements(By.xpath("//select[@id='fruit-selects']/option"));
            Select droporange=new Select(driver.findElement(By.xpath("//select[@id='fruit-selects']")));


            for(int i=0;i<droporange.getOptions().size();i++) {
                System.out.println(droporange.getOptions().get(i).getText()+" is open : "+droporange.getOptions().get(i).isEnabled());
                System.out.println();
            }



        }catch (Exception ex){
            System.out.println("შეცდომა რადიობუთონის შემოწმების დროს ");
        }

    }

}
