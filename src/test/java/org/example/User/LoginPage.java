package org.example.User;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPage {
    WebDriver driver;

    public LoginPage() {
    }

    @BeforeMethod
    public void setUp() {
        this.driver = new ChromeDriver();
        this.driver.get("https://skwadlink.com/login");
        System.out.println(this.driver.getCurrentUrl());
        System.out.println(this.driver.getTitle());
        this.driver.manage().window().maximize();
    }

    @Test(
            priority = 1
    )
    public void LoginwithwithoutEmail() {
        WebElement Enteremail = this.driver.findElement(By.id("email"));
        Enteremail.sendKeys(new CharSequence[]{" "});
        WebElement password = this.driver.findElement(By.id("password"));
        password.sendKeys(new CharSequence[]{"123456"});
        WebElement LoginEyeicons = this.driver.findElement(By.id("passwordVisibility"));
        LoginEyeicons.click();
        WebElement Login = this.driver.findElement(By.xpath("//button[normalize-space()='Log In']"));
        Login.click();
        WebElement errorElement = this.driver.findElement(By.id("email-error"));
        String actualErrorMessage = errorElement.getText();
        String expectedErrorMessage = "Please enter the email.";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "The error message does not match the expected message.");
        System.out.println("Actual Error Message: " + actualErrorMessage);
    }

    @Test(priority = 2)
    public void LoginwithwithoutPassword() {
        WebElement Enteremail = this.driver.findElement(By.id("email"));
        Enteremail.sendKeys(new CharSequence[]{"amelia@yopmail.com"});
        WebElement password = this.driver.findElement(By.id("password"));
        password.sendKeys(new CharSequence[]{" "});
        WebElement Login = this.driver.findElement(By.xpath("//button[normalize-space()='Log In']"));
        Login.click();
        WebElement alertElement = this.driver.findElement(By.cssSelector(".alert.alert-danger"));
        String alertMessage = alertElement.getText();
        String expectedMessage = "The password field is required.";
        Assert.assertTrue(alertMessage.contains(expectedMessage), "Expected message not found in alert.");
        System.out.println("expErrorMsg :" + expectedMessage);
    }

    @Test(
            priority = 3
    )
    public void Invalidemailformat() {
        WebElement Enteremail = this.driver.findElement(By.id("email"));
        Enteremail.sendKeys(new CharSequence[]{"amelia#yopmail.com"});
        WebElement password = this.driver.findElement(By.id("password"));
        password.sendKeys(new CharSequence[]{"123456"});
        WebElement LoginEyeicons = this.driver.findElement(By.id("passwordVisibility"));
        LoginEyeicons.click();
        WebElement Login = this.driver.findElement(By.xpath("//button[normalize-space()='Log In']"));
        Login.click();
        String expectedErrorMsg = "Please enter valid email address.";
        WebElement exp = this.driver.findElement(By.xpath("//label[@id='email-error']"));
        String actualErrorMsg = exp.getText();
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
        System.out.println("expectedErrorMsg " + expectedErrorMsg);
        Enteremail.clear();
        password.clear();
    }

    @Test(
            priority = 4
    )
    public void InvaliduserNotFound() {
        WebElement Enteremail = this.driver.findElement(By.id("email"));
        Enteremail.sendKeys(new CharSequence[]{"amelia@yopmail.com"});
        WebElement password = this.driver.findElement(By.id("password"));
        password.sendKeys(new CharSequence[]{"123"});
        WebElement LoginEyeicons = this.driver.findElement(By.id("passwordVisibility"));
        LoginEyeicons.click();
        WebElement Login = this.driver.findElement(By.xpath("//button[normalize-space()='Log In']"));
        Login.click();
        String expectedErrorMsg = "The provided credentials do not match our records.";
        WebElement exp = this.driver.findElement(By.xpath("//div[@id='swal2-html-container']"));
        String actualErrorMsg = exp.getText();
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
        System.out.println("Expected result is equal to Actual Result");
        System.out.println("Expected error msg :" + expectedErrorMsg);
    }

    @Test(
            priority = 5
    )
    public void LoginpagewithvalidInput() {
        WebElement Enteremail = this.driver.findElement(By.id("email"));
        Enteremail.sendKeys(new CharSequence[]{"amelia@yopmail.com"});
        WebElement password = this.driver.findElement(By.id("password"));
        password.sendKeys(new CharSequence[]{"123456"});
        WebElement LoginEyeicons = this.driver.findElement(By.id("passwordVisibility"));
        LoginEyeicons.click();
        WebElement Login = this.driver.findElement(By.xpath("//button[normalize-space()='Log In']"));
        Login.click();
        String u = this.driver.getCurrentUrl();
        if (u.equalsIgnoreCase("https://skwadlink.com/user/dashboard")) {
            System.out.println("Test case passed");
        } else {
            System.out.println("Test case failed");
            System.out.println("User Sucessfully Login with valid username or Password");
        }

        System.out.println("User Sucessfully Login with valid username or Password");
    }

    @Test(
            priority = 6
    )
    public void forgotpassword() {
        WebElement ForgotPassword = this.driver.findElement(By.xpath("//a[normalize-space()='Forgot Your Password?']"));
        ForgotPassword.click();
        WebElement enteremail = this.driver.findElement(By.xpath("//input[@placeholder='Please enter your email']"));
        enteremail.sendKeys("amelia@yopmail.com");
        WebElement sendclick = this.driver.findElement(By.xpath("//input[@value='Send Verification Link']"));
        sendclick.click();
        System.out.println("Link Sent on Your Email Address");
    }

    @AfterMethod
    public void tearDown() {
        if (this.driver != null) {
            this.driver.quit();
        }

    }
}
