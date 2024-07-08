
package org.example.User;

import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Signpage {
    WebDriver driver;
    private List<String> emailList;

    public Signpage() {
    }


    @BeforeMethod
    public void setUp() {
        this.driver = new ChromeDriver();
        this.driver.get("https://skwadlink.com/signup");
        System.out.println(this.driver.getCurrentUrl());
        System.out.println(this.driver.getTitle());
        this.driver.manage().window().maximize();

    }
        private void generateEmails () {
            String baseEmailPrefix = "user";
            String emailDomain = "@example.com";
            int numberOfEmails = 5;

            emailList = new ArrayList<>();

            for (int i = 0; i < numberOfEmails; i++) {
                String uniqueEmail = baseEmailPrefix + "+" + UUID.randomUUID().toString() + emailDomain;
                emailList.add(uniqueEmail);
            }

           // Print generated emails for debugging
            System.out.println("Generated Emails: " + emailList);
        }



        @Test
        public void testSignupWithMultipleEmails() {
            for (String email : emailList) {

                WebElement signUpPage = driver.findElement(By.className("signup"));
                signUpPage.click();
                WebElement firstNameInput = driver.findElement(By.id("firstname"));
                WebElement lastnameInput = driver.findElement(By.id("lastname"));
                WebElement usernameInput = driver.findElement(By.id("username"));
                WebElement email1 = driver.findElement(By.id("email"));
                WebElement passwordInput = driver.findElement(By.id("password"));
                WebElement confirmpassword = driver.findElement(By.id("cpassword"));
                WebElement eyeIcon = driver.findElement(By.xpath("//*[@id=\"signUpForm\"]/div/div[5]/img"));
                WebElement confirmEyeIcon = driver.findElement(By.xpath("//*[@id=\"signUpForm\"]/div/div[6]/img"));
                WebElement state = driver.findElement(By.name("state"));
                WebElement zipcode = driver.findElement(By.id("zipcode"));
                WebElement termsAndConditionsLink = driver.findElement(By.id("exampleCheck1"));
                WebElement signUpButton = driver.findElement(By.xpath("//*[@id=\"signUpForm\"]/div/div[10]/button"));
                firstNameInput.sendKeys("jhfhf " );
                lastnameInput.sendKeys("Doe12");
                usernameInput.sendKeys("JACKSON12345");
                email1.sendKeys(email);
                passwordInput.sendKeys("123456");
                confirmpassword.sendKeys("123456");
                eyeIcon.click();
                confirmEyeIcon.click();
                Select selectState = new Select(state);
                selectState.selectByVisibleText("Arizona");
                zipcode.sendKeys("123456");
                termsAndConditionsLink.click();
                signUpButton.click();

                // Add necessary validations/assertions here
                try {
                    Thread.sleep(3000); // Add appropriate wait/condition here
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

    @Test(priority = 1)
    public void signUpwithoutfirstname() {
        WebElement signUpPage = driver.findElement(By.className("signup"));
        signUpPage.click();
        WebElement firstNameInput = driver.findElement(By.id("firstname"));
        WebElement lastnameInput = driver.findElement(By.id("lastname"));
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement email = driver.findElement(By.id("email"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement confirmpassword = driver.findElement(By.id("cpassword"));
        WebElement eyeIcon = driver.findElement(By.xpath("//*[@id=\"signUpForm\"]/div/div[5]/img"));
        WebElement confirmEyeIcon = driver.findElement(By.xpath("//*[@id=\"signUpForm\"]/div/div[6]/img"));
        WebElement state = driver.findElement(By.name("state"));
        WebElement zipcode = driver.findElement(By.id("zipcode"));
        WebElement termsAndConditionsLink = driver.findElement(By.id("exampleCheck1"));
        WebElement signUpButton = driver.findElement(By.xpath("//*[@id=\"signUpForm\"]/div/div[10]/button"));
        firstNameInput.sendKeys(" " );
        lastnameInput.sendKeys("Doe12");
        usernameInput.sendKeys("JACKSON12345");
        email.sendKeys("JohnDoe12345@yopmail.com");
        passwordInput.sendKeys("123456");
        confirmpassword.sendKeys("123456");
        eyeIcon.click();
        confirmEyeIcon.click();
        Select selectState = new Select(state);
        selectState.selectByVisibleText("Arizona");
        zipcode.sendKeys("123456");
        termsAndConditionsLink.click();
        signUpButton.click();
        String expectedURL = "https://skwadlink.com/signup";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL);
        String expErrorMsg = "Please enter the first name.";
        String expectedTitle = "Skwad Link";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        System.out.println("expErrorMsg :" + expErrorMsg);
    }

    @Test(
            priority = 1
    )
    public void signUpalreadyexistEmail() {
        WebElement signUpPage = driver.findElement(By.className("signup"));
        signUpPage.click();
        WebElement firstNameInput = driver.findElement(By.id("firstname"));
        WebElement lastnameInput =driver.findElement(By.id("lastname"));
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement email = driver.findElement(By.id("email"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement confirmpassword = driver.findElement(By.id("cpassword"));
        WebElement eyeIcon = driver.findElement(By.xpath("//img[@class='hide_icon passwordImg']"));
        WebElement confirmEyeIcon = driver.findElement(By.xpath("//img[@class='hide_icon cpasswordImg']"));
        WebElement state = driver.findElement(By.name("state"));
        WebElement zipcode = driver.findElement(By.id("zipcode"));
        WebElement termsAndConditionsLink = driver.findElement(By.id("exampleCheck1"));
        WebElement signUpButton = driver.findElement(By.xpath("//button[normalize-space()='Sign Up']"));
        firstNameInput.sendKeys("haks ");
        lastnameInput.sendKeys("Doe12");
        usernameInput.sendKeys("JACKSON12345");
        email.sendKeys("amelia@yopmail.com");
        passwordInput.sendKeys("123456");
        confirmpassword.sendKeys("123456");
        eyeIcon.click();
        confirmEyeIcon.click();
        Select selectState = new Select(state);
        selectState.selectByVisibleText("Arizona");
        zipcode.sendKeys("123456");
        termsAndConditionsLink.click();
        signUpButton.click();
        String expectedErrorMsg = "The email has already been taken.";
        WebElement exp = driver.findElement(By.xpath("//li[normalize-space()='The email has already been taken.']"));
        String actualErrorMsg = exp.getText();
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
        System.out.println("expectedErrorMsg :" + expectedErrorMsg);
    }

    @Test(
            priority = 2
    )
    public void termsRedirectionTest() {
        WebElement termsLink = driver.findElement(By.xpath("//*[@id=\"signUpForm\"]/div/div[9]/label"));
        termsLink.click();
        Set<String> allWindows = driver.getWindowHandles();
        Iterator var3 = allWindows.iterator();

        String actualURL;
      {
            actualURL = (String)var3.next();
            driver.switchTo().window(actualURL);
        }

        String expectedURL = "https://skwadlink.com/terms-conditions";
        actualURL = driver.getCurrentUrl();
        System.out.println(actualURL);
        Assert.assertEquals(actualURL, expectedURL);
        String expectedTitle = "Skwad Link";
        String actualTitle = driver.getTitle();
        System.out.println(actualTitle);
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test(
            priority = 1
    )
    public void signUpPage() {
        WebElement signUpPage = driver.findElement(By.className("signup"));
        signUpPage.click();
        WebElement firstNameInput = driver.findElement(By.id("firstname"));
        WebElement lastnameInput = driver.findElement(By.id("lastname"));
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement email = driver.findElement(By.id("email"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement confirmpassword = driver.findElement(By.id("cpassword"));
        WebElement eyeIcon = driver.findElement(By.xpath("//*[@id=\"signUpForm\"]/div/div[5]/img"));
        WebElement confirmEyeIcon = driver.findElement(By.xpath("//*[@id=\"signUpForm\"]/div/div[6]/img"));
        WebElement state = driver.findElement(By.name("state"));
        WebElement zipcode = driver.findElement(By.id("zipcode"));
        WebElement termsAndConditionsLink =driver.findElement(By.id("exampleCheck1"));
        WebElement signUpButton = driver.findElement(By.xpath("//*[@id=\"signUpForm\"]/div/div[10]/button"));
        firstNameInput.sendKeys("John");
        lastnameInput.sendKeys("Doe12");
        usernameInput.sendKeys("JACKSON12345");
        email.sendKeys("JohnDoe12345@yopmail.com");
        passwordInput.sendKeys("123456");
        confirmpassword.sendKeys("123456");
        eyeIcon.click();
        confirmEyeIcon.click();
        Select selectState = new Select(state);
        selectState.selectByVisibleText("Arizona");
        zipcode.sendKeys("123456");
        termsAndConditionsLink.click();
        signUpButton.click();
    }

    @Test(priority = 2)
    public void loginRedirectionTest() {
        WebElement loginLink = driver.findElement(By.xpath("//*[@id=\"signUpForm\"]/div/div[11]/div/p/a"));
        loginLink.click();
        String expectedURL = "https://skwadlink.com/login";
        String actualURL = driver.getCurrentUrl();
        System.out.println(actualURL);
        Assert.assertEquals(actualURL, expectedURL);
        String expectedTitle = "Skwad Link";
        String actualTitle = driver.getTitle();
        System.out.println(actualTitle);
        Assert.assertEquals(actualTitle, expectedTitle);
    }


    @AfterMethod
    public void tearDown() {
        if (this.driver != null) {
            this.driver.quit();
        }

    }
}