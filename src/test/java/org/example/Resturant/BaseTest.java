package org.example.Resturant;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class BaseTest {

    WebDriver driver;
    WebDriverWait wait;

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


        WebElement Email = driver.findElement(By.id("email"));
        Email.sendKeys("neha_rana@inclusionsoft.com");
        WebElement Password = driver.findElement(By.id("password"));
        Password.sendKeys("123456");
        WebElement LoginEyeicons = driver.findElement(By.id("passwordVisibility"));
        LoginEyeicons.click();
        WebElement Loginok = driver.findElement(By.xpath("//button[normalize-space()='Log In']"));
        Loginok.click();



        WebElement profile = driver.findElement(By.xpath("//img[@src='https://skwadlink.com/storage/app_image/admin_dashboard/Frame-admin.svg']"));
        profile.click();
        WebElement profileclick = driver.findElement(By.xpath("//a[normalize-space()='My Profile']"));
        profileclick.click();

        WebElement upgradeToProButton = driver.findElement(By.xpath("//a[normalize-space()='Upgrade to Pro']"));

        upgradeToProButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement upgradeToProModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("upgrade_to_pro_modal")));


        Assert.assertTrue(upgradeToProModal.isDisplayed(), "The 'Upgrade to Pro' modal did not display.");

        WebElement modalTitle = upgradeToProModal.findElement(By.className("modal-title"));
        Assert.assertTrue(modalTitle.isDisplayed(), "The modal title is not displayed.");
        assertEquals(modalTitle.getText(), "Upgrade To Pro", "The modal title text is incorrect.");

        WebElement Upgrade = driver.findElement(By.xpath("//a[normalize-space()='Upgrade Now']"));
        Upgrade.click();

    }
    @AfterMethod
    public void tearDown() {
        if (this.driver != null) {
            this.driver.quit();
        }

    }
}

