package org.example.Resturant;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RLoginPage {
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
    public void Nagtive_Test1(){
        //Without Email
        WebElement Email= driver.findElement(By.id("email"));
        Email.sendKeys(" ");
        WebElement Password= driver.findElement(By.id("password"));
        Password.sendKeys("123456");
        WebElement LoginEyeicons = driver.findElement(By.id("passwordVisibility"));
        LoginEyeicons.click();
        WebElement Loginok= driver.findElement(By.xpath("//button[normalize-space()='Log In']"));
        Loginok.click();

        WebElement errorElement = driver.findElement(By.id("email-error"));

        String actualErrorMessage = errorElement.getText();
        String expectedErrorMessage = "Please enter the email .";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

    }


    @Test(priority = 2)
    public void Nagtive_Test2(){
        // without Password
        WebElement Email= driver.findElement(By.id("email"));
        Email.sendKeys("pro_blue_moon@gmail.com");
        WebElement Password= driver.findElement(By.id("password"));
        Password.sendKeys(" ");
        WebElement LoginEyeicons = driver.findElement(By.id("passwordVisibility"));
        LoginEyeicons.click();
        WebElement Loginok= driver.findElement(By.xpath("//button[normalize-space()='Log In']"));
        Loginok.click();

        WebElement alertElement = driver.findElement(By.cssSelector(".alert.alert-danger"));

        // Get the text content of the alert message
        String alertMessage = alertElement.getText();

        // Expected alert message
        String expectedMessage = "The password field is required.";

        // Assert that the alert message contains the expected message
        Assert.assertTrue(alertMessage.contains(expectedMessage), "Expected message not found in alert.");
        System.out.println("expErrorMsg :" +expectedMessage);


    }
    @Test(priority = 3)
    public void Nagtive_Test3(){
        //Wrong Email
        WebElement Email= driver.findElement(By.id("email"));
        Email.sendKeys("moon@gmail.com");
        WebElement Password= driver.findElement(By.id("password"));
        Password.sendKeys("123456");
        WebElement LoginEyeicons = driver.findElement(By.id("passwordVisibility"));
        LoginEyeicons.click();
        WebElement Loginok= driver.findElement(By.xpath("//button[normalize-space()='Log In']"));
        Loginok.click();

        WebElement alert = driver.findElement(By.id("swal2-html-container"));

        // Check if the alert contains the expected message
        String alertText = alert.getText();
        if (alertText.equals("The provided credentials do not match our records.")) {
            System.out.println("Error alert is displayed with the expected message.");
        } else {
            System.out.println("Unexpected alert message: " + alertText);
        }
        System.out.println("Unexpected alert message: " + alertText);


    }
    @Test(priority = 4)
    public void Nagtive_Test4(){
        // Wrong Password
        WebElement Email= driver.findElement(By.id("email"));
        Email.sendKeys("pro_blue_moon@gmail.com");
        WebElement Password= driver.findElement(By.id("password"));
        Password.sendKeys("123456789 ");
        WebElement LoginEyeicons = driver.findElement(By.id("passwordVisibility"));
        LoginEyeicons.click();
        WebElement Loginok= driver.findElement(By.xpath("//button[normalize-space()='Log In']"));
        Loginok.click();

        WebElement alert = driver.findElement(By.id("swal2-html-container"));

        // Check if the alert contains the expected message
        String alertText = alert.getText();
        if (alertText.equals("The provided credentials do not match our records.")) {
            System.out.println("Error alert is displayed with the expected message.");
        } else {
            System.out.println("Unexpected alert message: " + alertText);
        }
        System.out.println("Unexpected alert message: " + alertText);



    }


    @Test(priority = 5)
    public void Nagtive_Test5(){
        // Without Email and Without Password
        WebElement Email= driver.findElement(By.id("email"));
        Email.sendKeys(" ");
        WebElement Password= driver.findElement(By.id("password"));
        Password.sendKeys(" ");
        WebElement LoginEyeicons = driver.findElement(By.id("passwordVisibility"));
        LoginEyeicons.click();
        WebElement Loginok= driver.findElement(By.xpath("//button[normalize-space()='Log In']"));
        Loginok.click();

    }

    @Test(priority = 6)
    public void forgotpassword(){
        WebElement ForgotPassword= driver.findElement(By.xpath("//a[normalize-space()='Forgot Your Password?']"));
        ForgotPassword.click();

        WebElement enteremail= driver.findElement(By.xpath("//input[@placeholder='Please enter your email']"));
        enteremail.sendKeys("amelia@yopmail.com");

        WebElement sendclick= driver.findElement(By.xpath("//input[@value='Send Verification Link']"));
        sendclick.click();
        System.out.println("Link Sent on Your Email Address");






    }
    @Test(priority = 7)
    public void Postive_Test(){
        WebElement Email= driver.findElement(By.id("email"));
        Email.sendKeys("pro_blue_moon@gmail.com");
        WebElement Password= driver.findElement(By.id("password"));
        Password.sendKeys("123456");
        WebElement LoginEyeicons = driver.findElement(By.id("passwordVisibility"));
        LoginEyeicons.click();
        WebElement Loginok= driver.findElement(By.xpath("//button[normalize-space()='Log In']"));
        Loginok.click();

        String u = driver.getCurrentUrl();
        if (u.equalsIgnoreCase("https://skwadlink.com/user/dashboard")) {
            System.out.println("Test case passed");
        } else {
            System.out.println("Test case failed");
        }
        System.out.println("User Sucessfully Login with valid username or Password");

    }




    @AfterMethod
    public void tearDown() {
        // Close the WebDriver
        if (driver != null) {
            driver.quit();
        }
    }

}
