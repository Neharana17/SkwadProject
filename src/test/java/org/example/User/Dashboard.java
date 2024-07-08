package org.example.User;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Dashboard {
    WebDriver driver;

    public Dashboard() {
    }

    @BeforeMethod
    public void setUp() {
        this.driver = new ChromeDriver();
        this.driver.get("https://skwadlink.com");
        System.out.println(this.driver.getCurrentUrl());
        System.out.println(this.driver.getTitle());
        this.driver.manage().window().maximize();
    }

    @Test(
            priority = 1
    )
    public void LoginUser() throws InterruptedException, AWTException {
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

        WebElement MultipleRebiusment = this.driver.findElement(By.xpath("//a[normalize-space()='Multiple Reimbursements']"));
        MultipleRebiusment.click();
        WebElement Partyname =  driver.findElement(By.id("partyname"));
        Partyname.sendKeys(new CharSequence[]{"BirthdayParty"});
        WebElement Date = driver.findElement(By.id("whenparty"));
        Date.sendKeys(new CharSequence[]{"09-12-2024"});
        WebElement Restuerentname = this.driver.findElement(By.id("whereparty"));
        Restuerentname.sendKeys(new CharSequence[]{"5 Star"});
        WebElement Amount = this.driver.findElement(By.id("member_limit"));
        Amount.sendKeys(new CharSequence[]{"3"});
        System.out.println("Added meal Sucessfully");
        JavascriptExecutor jse = (JavascriptExecutor)this.driver;
        jse.executeScript("window.scrollBy(0,100)", new Object[0]);
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10L));
        Actions actions = new Actions(this.driver);

        WebElement UploadImg;
        WebElement uploadimage;
        WebElement addmeal;
        WebElement enterQuanity;
        try {
            UploadImg = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Add member/ Edit Member']")));
            UploadImg.click();
            WebElement addCreator = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='add_newCreator']")));
            addCreator.click();
            WebElement selectUserDropdown = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@aria-expanded='true']//ul[@class='select2-selection__rendered']")));
            selectUserDropdown.click();
            WebElement Selectuser = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='select2-search__field']")));
            Selectuser.sendKeys(new CharSequence[]{"final"});
            Selectuser.sendKeys(new CharSequence[]{Keys.ENTER});
            uploadimage = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(), 'final')]")));
            actions.moveToElement(uploadimage).click().perform();
            addmeal = (WebElement)wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='form-control creatorPercentage new_Creator_per']")));
            addmeal.sendKeys(new CharSequence[]{"20"});
            enterQuanity = (WebElement)wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='signUp_btn btn mt-3 mb-3 Add_Creator_btn']")));
            enterQuanity.click();
        } catch (TimeoutException var28) {
            System.out.println("Element was not found within the specified time: " + var28.getMessage());
        } catch (NoSuchElementException var29) {
            System.out.println("Element was not found in the DOM: " + var29.getMessage());
        } catch (Exception var30) {
            System.out.println("An unexpected error occurred: " + var30.getMessage());
        }

        Thread.sleep(3000L);
        UploadImg = this.driver.findElement(By.xpath("//*[@id='CratePartyform']/div/div[7]/div[1]/label[2]"));
        Actions act = new Actions(this.driver);
        act.moveToElement(UploadImg).click().perform();
        Robot rb = new Robot();
        rb.delay(2000);
        StringSelection ss = new StringSelection("C:\\Users\\Neha Rana\\Downloads\\IMG_2620 (1).jpeg");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, (ClipboardOwner)null);
        rb.keyPress(17);
        rb.keyPress(86);
        rb.keyRelease(17);
        rb.keyRelease(86);
        rb.keyPress(10);
        rb.keyRelease(10);
        Thread.sleep(20000L);
        this.driver.manage().timeouts().implicitlyWait(50L, TimeUnit.SECONDS);
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20L));

        try {
            uploadimage = this.driver.findElement(By.xpath(" //span[@class='reUpload_receipt']"));
            uploadimage.isDisplayed();
            if (uploadimage.isDisplayed()) {
                Assert.assertTrue(true);
                System.out.println("Receipt file uploaded");
            } else {
                Assert.assertTrue(false);
                System.out.println("Receipt file not uploaded");
            }
        } catch (Exception var27) {
            var27.printStackTrace();
        }

        JavascriptExecutor jse2 = (JavascriptExecutor)driver;
        jse2.executeScript("window.scrollBy(0,200)", new Object[0]);
        addmeal = driver.findElement(By.xpath("//button[normalize-space()='Add Meal']"));
        addmeal.click();
        enterQuanity = driver.findElement(By.xpath("//input[@placeholder='Enter quantity']"));
        enterQuanity.sendKeys("1");
        WebElement enterProduct =driver.findElement(By.xpath("//input[@placeholder='Enter product']"));
        enterProduct.sendKeys("Pizza");
        WebElement enterPrice =driver.findElement(By.xpath("//input[@placeholder='Enter price']"));
        enterPrice.sendKeys("25");
        WebElement add = driver.findElement(By.xpath("//button[@class='signUp_btn btn submit_add-QtyBtn']"));
        add.click();
        Thread.sleep(1000L);
        WebElement GenrateLink = driver.findElement(By.xpath("//button[normalize-space()='Generate Link']"));
        GenrateLink.click();
        WebElement cancel = driver.findElement(By.xpath("//button[normalize-space()='Cancel']"));
        cancel.click();
    }

    @AfterMethod
    public void tearDown() {
        if (this.driver != null) {
            this.driver.quit();
        }

    }
}

