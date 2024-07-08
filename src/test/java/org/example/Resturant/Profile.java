package org.example.Resturant;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLOutput;

public class Profile {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://skwadlink.com/restaurant/signup");
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());
        driver.manage().window().maximize();
        // Initialize WebDriver (Assuming ChromeDriver here)

        WebElement Loginpage = driver.findElement(By.xpath("//a[@href='https://skwadlink.com/restaurant/login']"));
        Loginpage.click();
    }
    @Test(priority = 1)
    public void Postive_Test() {
        WebElement Email = driver.findElement(By.id("email"));
        Email.sendKeys("pro_blue_moon@gmail.com");
        WebElement Password = driver.findElement(By.id("password"));
        Password.sendKeys("123456");
        WebElement LoginEyeicons = driver.findElement(By.id("passwordVisibility"));
        LoginEyeicons.click();
        WebElement Loginok = driver.findElement(By.xpath("//button[normalize-space()='Log In']"));
        Loginok.click();

        WebElement profile= driver.findElement(By.xpath("//img[@src='https://skwadlink.com/storage/app_image/admin_dashboard/Frame-admin.svg']"));
        profile.click();

        WebElement profileclick= driver.findElement(By.xpath("//a[normalize-space()='My Profile']"));
        profileclick.click();





        WebElement profileName = driver.findElement(By.xpath("//span[normalize-space()='Name']"));
        Assert.assertTrue(profileName.isDisplayed());
        String gettext="Pro_Restaurant P.";
        Assert.assertEquals(profileName.getText(), "Name");
        System.out.println("User Profile Name :- " +gettext);

        WebElement ProfileEmail = driver.findElement(By.xpath("//span[normalize-space()='Email']"));
        Assert.assertTrue(ProfileEmail.isDisplayed());
        String getemail= "pro_blue_moon@gmail.com";
        Assert.assertEquals(profileName.getText(), "Name");
        System.out.println(" User Email is :- " +getemail);

        //Verified User
        WebElement Verified = driver.findElement(By.xpath("//small[@class='account_varify']"));
        Assert.assertTrue(Verified.isDisplayed());
        String getverify= "Verified";
        Assert.assertEquals(profileName.getText(), "Name");
        System.out.println(" User is :- " +getverify);




        //click on view button
        WebElement view= driver.findElement(By.xpath("//a[normalize-space()='View']"));
        view.click();

        //cross View button
         WebElement ok= driver.findElement(By.xpath("(//button[@aria-label='Close'])[3]"));
         ok.click();
         

        // Click on user Reciept
        WebElement Reciept= driver.findElement(By.xpath("(//a[normalize-space()='Receipt invoices'])[1]"));
        Reciept.click();


    }

    @AfterMethod
    public void tearDown() {
        if (this.driver != null) {
            this.driver.quit();
        }

    }

}
