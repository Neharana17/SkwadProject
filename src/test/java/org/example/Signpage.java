package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;

import java.util.List;

public class LaunchBrowser {


    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());
        driver.manage().window().maximize();
        driver.get("https://skwadlink.com/login");


//Sign Up page

        // Locate the input fields and submit button
       // WebElement signuppage= driver.findElement(By.className("signup"));
//        WebElement firstNameInput = driver.findElement(By.id("firstname"));
//        WebElement lastnameInput = driver.findElement(By.id("lastname"));
//        WebElement usernameInput = driver.findElement(By.id("username"));
//        WebElement email = driver.findElement(By.id("email"));
//        WebElement passwordInput = driver.findElement(By.id("password"));
//        WebElement confirmpassword = driver.findElement(By.id("cpassword"));
        WebElement Eyeicons= driver.findElement(By.xpath("//*[@id=\"signUpForm\"]/div/div[5]/img"));
//        WebElement Ceyeicons= driver.findElement(By.xpath("//*[@id=\"signUpForm\"]/div/div[6]/img"));
//        WebElement state= driver.findElement(By.name("state"));
//        WebElement Zipcode= driver.findElement(By.id("zipcode"));
//        WebElement termsAndConditionsLink = driver.findElement(By.id("exampleCheck1"));
//        WebElement signUpButton = driver.findElement(By.xpath("//*[@id=\"signUpForm\"]/div/div[10]/button"));
//
//
//
//
//
//        // Fill in the sign-up form
//      //  signuppage.click();
//        firstNameInput.sendKeys("John");
//        lastnameInput.sendKeys("Doe");
//        usernameInput.sendKeys("JACKSON12");
//        email.sendKeys("JohnDoe123@yopmail.com");
//        passwordInput.sendKeys("123456");
//        confirmpassword.sendKeys("123456");
//        Eyeicons.click();
//        Ceyeicons.click();
//
//        Select selectState = new Select(state);
//        selectState.selectByVisibleText("Arizona");
//        Zipcode.sendKeys("123456");
//        termsAndConditionsLink.click();
        //Fill Login page

        WebElement Enteremail = driver.findElement(By.id("email"));
        Enteremail.sendKeys("amelia@yopmail.com");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        Eyeicons.click();
        WebElement Login = driver.findElement(By.xpath("Log In"));








        // Click the sign-up button
    // signUpButton.click();
     Login.click();

       // driver.close();


    }
}
