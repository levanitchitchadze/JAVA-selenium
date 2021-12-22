package com.example.fileuploadtest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;


public class MainPageTest {
    WebDriver driver;

    @BeforeClass
    public  void OpenBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/upload");
        driver.manage().window().maximize();

    }


    @Test(priority=1)
    public void Upload() {

        //ფორმის შენახვა Choose ელემენტში
        WebElement Choose=driver.findElement(By.cssSelector("input#file-upload"));
        //ფაილის ატვირთვა
        Choose.sendKeys("C:\\Users\\lenovo\\Desktop\\repositors\\selenium-homework\\FileUploadTest\\src\\test\\java\\source\\UPl.gif");
        //Upload ღილაკზე დაწკაპება
        driver.findElement(By.cssSelector("#file-submit")).click();

        //StaleElementReferenceException-ის გამოძახება
        try {
            Choose.click();

        }catch (StaleElementReferenceException SERE){
            System.out.println("შეცდომა "+SERE.getClass().getName());
        }



    }



    @AfterMethod
    public void EndSession() {
        driver.quit();
        System.out.println("File Upload Successful");
    }

}
