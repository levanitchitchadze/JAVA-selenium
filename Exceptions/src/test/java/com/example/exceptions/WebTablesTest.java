package com.example.exceptions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.github.bonigarcia.wdm.WebDriverManager;


public class WebTablesTest {

    public String printText(WebDriver driver){
        MainPageTest p=new MainPageTest();

        driver.get("http://techcanvass.com/Examples/webtable.html");


        String hondaPrice=driver.findElement(By.xpath("//tr[last()]/td[last()]")).getText();

        return(hondaPrice);


    }


}
