package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;

public class Headless {
    WebDriver driver;
    public Headless() {
        driver = new HtmlUnitDriver(true);
        driver.get("http://the-internet.herokuapp.com/upload");
    }




    @Test
    public void Upload() {

        //ფორმის შენახვა Choose ელემენტში
        WebElement Choose=driver.findElement(By.cssSelector("input#file-upload"));
        try {
            //ფაილის ატვირთვა
            File filePath = new File("test1.jpg");
            Choose.sendKeys(filePath.getAbsolutePath());
        }catch (org.openqa.selenium.InvalidArgumentException IAE){
            System.out.println("შეცდომა ფაილების ატვირთვის დროს "+IAE.getClass().getName());
        }

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
