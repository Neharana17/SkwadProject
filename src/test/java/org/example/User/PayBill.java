package org.example.User;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PayBill extends Browsersetup {
    WebDriver driver;

    public PayBill() {
    }

    @BeforeMethod
    public void setUp() {
        this.driver = new ChromeDriver();
        this.driver.get("https://skwadlink.com/");
        System.out.println(this.driver.getCurrentUrl());
        System.out.println(this.driver.getTitle());
        this.driver.manage().window().maximize();
    }

    @Test
    public void Paybill() throws IOException {
        this.driver.get("https://skwadlink.com/without-account/HPO4dZz8QufXdMHeFSrjn0Dj7Ir1luWi");
        WebElement email = this.driver.findElement(By.xpath("//input[@placeholder='Enter your Email']"));
        email.sendKeys("jack@yopmail.com");
        WebElement firstName = this.driver.findElement(By.xpath("//input[@placeholder='Enter your first name']"));
        firstName.sendKeys("Jack");
        WebElement LastName = this.driver.findElement(By.xpath("//input[@placeholder='Enter your last name']"));
        LastName.sendKeys("Doe");
        JavascriptExecutor jse = (JavascriptExecutor)this.driver;
        jse.executeScript("window.scrollBy(0,100)");

        WebElement button;
        try {
            List<WebElement> addMyMealButtons = this.driver.findElements(By.className("add_my_meal"));
            Iterator var6 = addMyMealButtons.iterator();

            while(var6.hasNext()) {
                button = (WebElement)var6.next();
                WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(5L));
                wait.until(ExpectedConditions.elementToBeClickable(button));
                ((JavascriptExecutor)this.driver).executeScript("arguments[0].click();");
            }
        } catch (Exception var13) {
            System.err.println("Exception: " + var13.getMessage());
        }

        WebElement addMealIcon = this.driver.findElement(By.xpath("//i[@class='fa-solid add_my_meal fa-plus']"));
        addMealIcon.click();
        WebElement individualLink = this.driver.findElement(By.xpath("//a[@class='dropdown-item' and text()='Individual']"));
        individualLink.click();
        button = this.driver.findElement(By.xpath("//button[normalize-space()='Confirm and continue']"));
        button.click();
        String pageSource = this.driver.getPageSource();
        int pageSourceLength = pageSource.length();
        System.out.println("Total length of the Pgae Source is : " + pageSourceLength);
        TakesScreenshot screenshot = (TakesScreenshot)this.driver;
        File source = (File)screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("C:\\Users\\Neha Rana\\IdeaProjects\\SeleniumSkwadProject\\target\\screenshot.png"));
        System.out.println("Screenshot is captured");
        WebElement backhome = this.driver.findElement(By.xpath("//a[normalize-space()='Back to Home']"));
        backhome.click();
    }

    @AfterMethod
    public void tearDown() {
        this.driver.quit();
    }
}
