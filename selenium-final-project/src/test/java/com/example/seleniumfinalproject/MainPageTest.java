package com.example.seleniumfinalproject;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.impl.Arguments;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.hc.core5.reactor.Command;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import javax.swing.*;
import java.io.IOException;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static org.testng.Assert.*;

import static com.codeborne.selenide.Selenide.*;

public class MainPageTest {

    WebDriver driver;
    JavascriptExecutor js;
    Actions ACT;
    WebDriverWait wait;

    @BeforeClass
    public void setUpAll() {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");



    }


    @Test
    public void TShirt() {
        //ACT ვანიჭებ Action კლასს
        ACT = new Actions(driver);
        //js ვანიჭებ JavascriptExecutor კლასს
        js = (JavascriptExecutor) driver;


        //screenshot ში ვაიმპორტირებ TakesScreenshot კლასს
        TakesScreenshot screenshot=(TakesScreenshot) driver;
        //ვიძახებ მეთოდს და ვიღებ სქრინს
        File scrFile = screenshot.getScreenshotAs(OutputType.FILE);
        //სქრინს ვინახავ target ფოლდერში
        try {
            FileHandler.copy(scrFile,new File("./ScreenShot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //region T-shirts

        //ვპოულობ Women ღილაკს და ვაკეთებ ჰოვერს
        WebElement WomenButton=driver.findElement(By.xpath("//a[contains(text(),'Women')]"));
        ACT.moveToElement(WomenButton).perform();

        //ვიღებ T-shirts ელემენტს და გადავდივარ
        WebElement ShirtButton =driver.findElement(By.xpath("//a[contains(text(),'T-shirts')]"));
        ShirtButton.click();


        //wait ში ვაინპორტირებ Explicit ვეითის კლასს
        wait = new WebDriverWait(driver,10);


        try {

            //ველოდები სურათის ჩატვირთვას რომ შემდეგ executeScript-ით ჩამოვსქროლო
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.product-container img")));
            js.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.cssSelector("div.product-container img")));


            //კურსორს ვატარებ სურათზე რომ გამოჩნდეს quick-view ღილაკი
            ACT.moveToElement(driver.findElement(By.cssSelector("div.product-container img"))).perform();
            driver.findElement(By.cssSelector("div.product-container a.quick-view")).click();

        }catch (TimeoutException ex){
            System.out.println("შეცდომა  "+ex.getClass().getSimpleName());
        }
        //endregion


        //region quick-view
        //გადავდივარ ფრეიმზე quick-view
        driver.switchTo().frame(driver.findElement(By.tagName("iframe")));

        //სურათის ელემენტებით ვქმნი ლისტს რომ სათითაოდ გადავატარო კურსორი და შევამოწმო თუ შეიცვლება დიდი სურათი
        List<WebElement> SmallImages=driver.findElements(By.xpath("//ul[@id='thumbs_list_frame']//a"));

        //ამ ციკლით კურსორს ვატარებ ყველა პატარა სურათზე და თან ვამოწმებწინა დამახსოვრებული
        // და შემდეგი ელემენტი თუ განსხვავდება ერთმანეთისგან
        for (int i=0;i<SmallImages.size();i++){


            WebElement BigImage=driver.findElement(By.cssSelector("img#bigpic"));

            ACT.moveToElement(SmallImages.get(i)).perform();

            WebElement BigImageLast=driver.findElement(By.cssSelector("img#bigpic"));

            if((BigImage.getAttribute("src").toString())!=(BigImageLast.getAttribute("src").toString())){
                System.out.println("დიდი სურათი შეიცვალა სურათი "+i+"-ით");
            }

        }



        Select DropDown =new Select(driver.findElement(By.cssSelector("select#group_1")));

        DropDown.selectByVisibleText("M");
        driver.findElement(By.cssSelector("input#quantity_wanted")).clear();
        driver.findElement(By.cssSelector("input#quantity_wanted")).sendKeys("2");

        WebElement AddToCart=driver.findElement(By.cssSelector("button.exclusive"));
        AddToCart.click();
        //endregion

        //region Go To Dresses
        //ვუბრუნდები მთავარ ფრეიმს
        driver.switchTo().parentFrame();

        //ველოდები ღილაკს რომ გავაგრძელო შოპინგი
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='button-container']/span[@title='Continue shopping']")));
        driver.findElement(By.xpath("//div[@class='button-container']/span[@title='Continue shopping']")).click();


        driver.findElement(By.xpath("//a[@title='My Store']")).click();

        //კურსორს ვატარებ Dress ღილაკზე რომ გამოჩნდეს დანარჩენი ელემენტებიც
        ACT.moveToElement(driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[2]/a"))).perform();
        driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/ul//a")).click();
        //endregion


    }

    @Test(priority = 2)
    public void CDresses() {

        //region Add dress to cart
        //თრაში ვსვავ ვეითინგს რომ დროის გასვლის შემდეგ მაინც გააგრძელოს კოდმა მუშაობა
        try {
            wait = new WebDriverWait(driver, 10);
            //ველოდები პროდუქტის კონტეინერში სურათების გახსნას
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.product-container img")));
        }catch (TimeoutException Ex){

        }
        //ვსქროლავ პროდუქტის კონტეინერის ერთ ერთ დივზე რომ გამოჩნდეს დანარჩენი ელემენტები
        js.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//*[@id='center_column']//div[@class='product-container']//div")));

        //ერთერთ ღილაკს ვსვავ ცვლადში MoreButton
        WebElement MoreButton =driver.findElement(By.xpath("//*[@id='center_column']//div[@class='product-container']//div[@class='button-container']/a[2]"));

        //კურსორს ვატარებ გამოტანილ სურათზე და შემდეგ ვაჭერ ღილაკს
        ACT.moveToElement(driver.findElement(By.xpath("//div[@id='center_column']//img[@class='replace-2x img-responsive']"))).perform();
        MoreButton.click();

        //ვაჭერ Add to cart ღილაკს
        WebElement AddToCart=driver.findElement(By.cssSelector("button.exclusive"));
        AddToCart.click();

        //ველოდები ContinueShopping ღილაკის გააქტიურებას
        WebElement ContinueShopping=driver.findElement(By.xpath("//div[@class='button-container']/span[@title='Continue shopping']"));
        wait.until(ExpectedConditions.elementToBeClickable(ContinueShopping));
        ContinueShopping.click();
        //endregion

        //region Check product

        //ვიღებ CartButton-ს და ვსქროლავ მასთან და გადავდივარ შემდეგ
        WebElement CartButton=driver.findElement(By.xpath("//a[@title='View my shopping cart']"));
        js.executeScript("arguments[0].scrollIntoView();",CartButton);
        CartButton.click();

        //ვაკეთებ ორ ლისტს პროდუქტის სახელებით და პროდუქტის აღწერებით
        List <WebElement> ProductName =driver.findElements(By.xpath("//tbody//p[@class='product-name']"));
        List <WebElement> ProductDesc =driver.findElements(By.xpath("//tbody//small"));

        //ვაკეთებ ორ ციკლს რომ ინფორმაცია წარმოვადგინო სასურველი ფორმით
        for (int i=0;i<ProductName.size();i++){
            System.out.print("\n");
            System.out.print(ProductName.get(i).getText()+ " -> | \t ");

            for (int j=0;j<ProductDesc.size();j++){
                System.out.print(ProductDesc.get(j).getText()+" | ");
            }
        }

        //ასევე ვსქროლავ ქვევით რომ ავიღო პროდუქტების მთლიანი ფასი შემდეგში შესადარებლად
        js.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.cssSelector("span#total_price")));
        String TotalAmountText=driver.findElement(By.cssSelector("span#total_price")).getText();

        //endregion


        //region registration
        //გადავდივარ შემდეგ ველზე და ვიწყებ რეგისტრაციის გავლას
        WebElement ProceedCheckout =driver.findElement(By.xpath("//a[@class='button btn btn-default standard-checkout button-medium']"));
        ProceedCheckout.click();


        //თავიდან შევქმენი რამდენიმე ცვლადი სადაც იქნებოდა მოთავსებული ძირითადი სარეგისტრაციო ინფორმაცია
        String FName="Levan";
        String LName="Tchicha";
        String Address="Tbilisi";
        String ZipCode="10001";
        String Mobile="1321421421";

        //ასევე შევქმენი ერთი ბულეან ტიპის ცვლადი
        boolean first =true;

        //ბულეან ტიპის ცვლად ვიყენებ while ციკლში იმ მიზნით თუ ჩემი არჩეული მეილი უკვე გამოყენებულია
        //პროგრამა თავიდან ცდის ახალი მეილით სანამ არ გაივლის პროცესს
        while (first){
            double RandNumber = Math.floor((Math.random() * 1000));


            driver.findElement(By.xpath("//input[@class='is_required validate account_input form-control']")).sendKeys(LName + RandNumber + "@gmail.com");
            driver.findElement(By.cssSelector("Button[class='btn btn-default button button-medium exclusive']")).click();

            try {
                driver.findElement(By.cssSelector("div#create_account_error il")).isDisplayed();
                first=true;
            }catch (NoSuchElementException NSEE){
                first=false;
            }
        }

        //სარეგისტრაციო გვერდზე ველოდები ელემენტების ჩატვირთვას და შემდეგ ვავსებ
        wait.until(ExpectedConditions.elementToBeClickable(By.id("id_gender1")));
        driver.findElement(By.id("id_gender1")).click();

        //სარეგისტრაციო ელემენტებს ვავსებ ზევით არსებულ ცვლადებში მოთავსებული ინფორმაციით P.S კლასით ძებნაც გამოყენებული მაქვს
        driver.findElement(By.id("customer_firstname")).sendKeys(FName);
        driver.findElement(By.id("customer_lastname")).sendKeys(LName);
        driver.findElement(By.id("passwd")).sendKeys(LName+FName+"@gmail.com");
        driver.findElement(By.className("form-control")).sendKeys(FName);
        driver.findElement(By.id("lastname")).sendKeys(LName);
        driver.findElement(By.id("address1")).sendKeys(Address);
        driver.findElement(By.id("city")).sendKeys(Address);
        driver.findElement(By.id("postcode")).sendKeys(ZipCode);
        driver.findElement(By.id("phone_mobile")).sendKeys(Mobile);


        //შტატის დროპდაუნში ვირჩევ New York -ს
        Select StateDropDown =new Select(driver.findElement(By.id("id_state")));
        StateDropDown.selectByVisibleText("New York");

        //და ბოლოს ვაჭერ რეგისტრაციის ღილაკს
        WebElement RegisterButton=driver.findElement(By.id("submitAccount"));
        RegisterButton.click();

        //ადრესის ველს ვტოვებ შეუვსებელს და პირდაპირ ვაჭერ ღილაკს processAddress
        driver.findElement(By.cssSelector("button[name='processAddress']")).click();

        //endregion


        //region Shipping

        //შიპინგში ვაჭერ ღილაკს რომ გამომიტანოს საიტმა შეცდომის შეტყობინება
        WebElement ProcessCarrier= driver.findElement(By.cssSelector("button[name='processCarrier']"));
        ProcessCarrier.click();

        //ველოდები ალერტის გამოსვლას და შემდეგ ვხურავ
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[class='fancybox-item fancybox-close']")));
        driver.findElement(By.cssSelector("a[class='fancybox-item fancybox-close']")).click();

        //ვეთანხმები პირობებს და თავიდან ვცდილობ გავიარო ფორმა
        driver.findElement(By.id("cgv")).click();
        ProcessCarrier.click();



        //endregion



        //region Payment
        //ვირჩევ ღილაკს pay by check
        driver.findElement(By.cssSelector("a.cheque")).click();

        //აქედან ვიღებ სრულ თანხას რომ შევადარო წინა დამახსოვრებულ თანხას
        WebElement TotalAmountPay=driver.findElement(By.xpath("//p[contains(text(),'The total amount of your order comes to')]"));

        //სრულ თანხებს ვადარებ ერთმანეთს და ვბეჭდავ პასუხს
        if (TotalAmountPay.getText().contains(TotalAmountText)){
            System.out.println("\n \n"+TotalAmountPay.getText()+"\t"+TotalAmountText);
            System.out.println("Amount is correct ");
        }else {
            System.out.println("Amount is incorrect ");

        }

        //ვირჩევ i confirm my order ღილაკს
        driver.findElement(By.cssSelector("button[class='button btn btn-default button-medium']")).click();

        //გადავდივარ customer service department -ის ლინკზე
        driver.findElement(By.xpath("//a[contains(text(),'customer service department.')]")).click();

        //endregion


        //region Customer Support

        //სამივე დროპ დაუნში ვირჩევ პირველ ინდექსიან წევრს
        Select ContactDrop= new Select(driver.findElement(By.cssSelector("select#id_contact")));
        ContactDrop.selectByIndex(1);

        Select OrderDrop=new Select(driver.findElement(By.cssSelector("select[name='id_order']")));
        OrderDrop.selectByIndex(1);

        Select ProductDrop=new Select(driver.findElement(By.cssSelector("select[name='id_product']")));
        ProductDrop.selectByIndex(1);


        //ტექსტ არეაში ვწერ ტექსტს js მხოლოდ სქროლისთვის დამჭირდა და გადავწყვიტე დავალების გამო ტექსტიც ამით დამემატებინა
        js.executeScript("document.getElementById(\"message\").value=\"იდიალური პროექტიაა 450 ქულიანი ;) :D :D \"");


        //ვიღებ ფაილის ასატვირთ პორმას
        WebElement UploadElement =driver.findElement(By.cssSelector("input#fileUpload"));
        //ასევე ფაილის path-ს რომელიც პროექტის რესურსებშია ატვირთული
        File FilePath=new File("src\\main\\resources\\test.png");


        //getAbsolutePath-ის გამოყენებით ვტვირთავ ფაილს
        UploadElement.sendKeys(FilePath.getAbsolutePath());

        // და ბოლოს ვგზავნი წერილს ასევე js executor-ით
        js.executeScript("document.getElementById(\"submitMessage\").click()");



        //endregion

    }

    @AfterTest
    public void BrowserClose(){
        driver.quit();
    }
}
