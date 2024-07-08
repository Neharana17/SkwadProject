package org.example.User;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OpenShareLink {
    WebDriver driver;

    public OpenShareLink() {
    }

    @BeforeMethod
    public void setUp() {
        this.driver = new ChromeDriver();
        this.driver.get("https://skwadlink.com/");
        System.out.println(this.driver.getCurrentUrl());
        System.out.println(this.driver.getTitle());
        this.driver.manage().window().maximize();
        WebElement LoginPage = this.driver.findElement(By.xpath("//a[normalize-space()='Log In']"));
        LoginPage.click();
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
        }

    }

    @Test(
            priority = 2
    )
    public void CopyUrl() {
        WebElement myparties = this.driver.findElement(By.xpath("//a[normalize-space()='My Parties']"));
        myparties.click();
        WebElement sharelink = this.driver.findElement(By.xpath("//a[@href='https://skwadlink.com/share_receipt/71']"));
        sharelink.click();
        WebElement copylink = this.driver.findElement(By.xpath("//button[@id='search-button']"));
        copylink.click();
        WebElement inputElement = this.driver.findElement(By.id("search"));
        String linkUrl = inputElement.getAttribute("value");
        System.out.println("Link URL: " + linkUrl);
        ChromeOptions incognitoOptions = new ChromeOptions();
        incognitoOptions.addArguments(new String[]{"--incognito"});
        WebDriver incognitoDriver = new ChromeDriver(incognitoOptions);
        incognitoDriver.manage().window().maximize();
        incognitoDriver.get(linkUrl);
    }

    @Test(
            priority = 3
    )
    public void Paybill() {
        try {
            WebElement termCondition = this.driver.findElement(By.id("term\\&Condition"));
            termCondition.click();
            System.out.println("Clicked on 'term & Condition' checkbox.");
        } catch (Exception var2) {
            System.out.println("Element not found or unable to interact: " + var2.getMessage());
        }

    }

    @AfterMethod
    public void tearDown() {
        this.driver.quit();
    }
}
