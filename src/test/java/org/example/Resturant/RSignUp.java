package org.example.Resturant;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class RSignUp {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://skwadlink.com/restaurant/signup");
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());
        driver.manage().window().maximize();
        // Initialize WebDriver (Assuming ChromeDriver here)

    }

    @Test()
    public void chooseSubscriptionPlan() {
        WebElement premiumPlanRadioButton = driver.findElement(By.id("flexRadioDefault6"));
        WebElement yearlyPlanRadioButton = driver.findElement(By.id("plan_type5"));
        WebElement monthlyPlanRadioButton = driver.findElement(By.id("plan_type6"));

        // Select the Premium Plan radio button
        if (!premiumPlanRadioButton.isSelected()) {
            premiumPlanRadioButton.click();
        }

        if (!yearlyPlanRadioButton.isSelected() && !monthlyPlanRadioButton.isSelected()) {
            yearlyPlanRadioButton.click(); // Select Yearly Plan by default
        } else if (!yearlyPlanRadioButton.isSelected() && monthlyPlanRadioButton.isSelected()) {
            monthlyPlanRadioButton.click(); // Select Monthly Plan if Yearly Plan is not selected
        }

        if (premiumPlanRadioButton.isSelected()) {
            System.out.println("Premium Plan radio button is selected.");
        } else {
            System.out.println("Premium Plan radio button is not selected.");
        }

        // Verify that either Yearly Plan or Monthly Plan radio button is selected
        if (yearlyPlanRadioButton.isSelected()) {
            System.out.println("Yearly Plan radio button is selected.");
        } else if (monthlyPlanRadioButton.isSelected()) {
            System.out.println("Monthly Plan radio button is selected.");
        } else {
            System.out.println("Neither Yearly Plan nor Monthly Plan radio button is selected.");
        }
    }


    @Test(priority = 1)
    public void SignupPage_Nagtive() {
// Invalid Email Address

        WebElement firstNameInput = driver.findElement(By.id("firstname"));
        WebElement lastnameInput = driver.findElement(By.id("lastname"));

        WebElement email = driver.findElement(By.id("email"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement confirmpassword = driver.findElement(By.id("cpassword"));
        WebElement eyeIcon = driver.findElement(By.xpath("//img[@class='hide_icon passwordImg']"));
        WebElement confirmEyeIcon = driver.findElement(By.xpath("//img[@class='hide_icon cpasswordImg']"));
        WebElement state = driver.findElement(By.name("state"));
        WebElement zipcode = driver.findElement(By.id("zipcode"));
        WebElement resturant_name = driver.findElement(By.id("resturant_name"));
        WebElement address = driver.findElement(By.id("address"));
        WebElement termCondition = driver.findElement(By.id("exampleCheck1"));
        WebElement signup = driver.findElement(By.xpath("//button[normalize-space()='Sign Up']"));

        // Fill in the sign-up form
        firstNameInput.sendKeys("My Resturant");
        lastnameInput.sendKeys("Mark");

        email.sendKeys("resturant#yopmail.com");
        passwordInput.sendKeys("123456");
        confirmpassword.sendKeys("123456");
        eyeIcon.click();
        confirmEyeIcon.click();
        resturant_name.sendKeys("7STAR");
        address.sendKeys("123abc rode");

        Select selectState = new Select(state);
        selectState.selectByVisibleText("Arizona");
        zipcode.sendKeys("123456");
        termCondition.click();
        signup.click();


        String expErrorMsg = "Please enter valid email address.";
        String expectedTitle = "Skwad Link";  //Please enter the first name.
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        System.out.println("expErrorMsg :" + expErrorMsg);


    }

    @Test(priority = 2)
    public void SignupPage_Nagtive1() {
// Same Resturant name same zip code

        WebElement firstNameInput = driver.findElement(By.id("firstname"));
        WebElement lastnameInput = driver.findElement(By.id("lastname"));

        WebElement email = driver.findElement(By.id("email"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement confirmpassword = driver.findElement(By.id("cpassword"));
        WebElement eyeIcon = driver.findElement(By.xpath("//img[@class='hide_icon passwordImg']"));
        WebElement confirmEyeIcon = driver.findElement(By.xpath("//img[@class='hide_icon cpasswordImg']"));
        WebElement state = driver.findElement(By.name("state"));
        WebElement zipcode = driver.findElement(By.id("zipcode"));
        WebElement resturant_name = driver.findElement(By.id("resturant_name"));
        WebElement address = driver.findElement(By.id("address"));
        WebElement termCondition = driver.findElement(By.id("exampleCheck1"));
        WebElement signup = driver.findElement(By.xpath("//button[normalize-space()='Sign Up']"));

        // Fill in the sign-up form
        firstNameInput.sendKeys("My Resturant");
        lastnameInput.sendKeys("Mark");

        email.sendKeys("resturant74@yopmail.com");
        passwordInput.sendKeys("123456");
        confirmpassword.sendKeys("123456");
        eyeIcon.click();
        confirmEyeIcon.click();
        resturant_name.sendKeys("Pro_Restaurant P.");
        address.sendKeys("123abc rode");

        Select selectState = new Select(state);
        selectState.selectByVisibleText("Arizona");
        zipcode.sendKeys("123456");

        termCondition.click();
        signup.click();

        String expectedErrorMsg = "The restaurant name 'Pro_Restaurant P.' must be unique for the given zip code.";

        WebElement exp = driver.findElement(By.xpath("//section[@class='signUp_wrap']//li[1]"));
        String actualErrorMsg = exp.getText();

        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);

        System.out.println("Expected error msg :" + expectedErrorMsg);


    }

    @Test(priority = 3)
    public void SignupPage_Nagtive2() {
// Without Resturant name

        WebElement firstNameInput = driver.findElement(By.id("firstname"));
        WebElement lastnameInput = driver.findElement(By.id("lastname"));

        WebElement email = driver.findElement(By.id("email"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement confirmpassword = driver.findElement(By.id("cpassword"));
        WebElement eyeIcon = driver.findElement(By.xpath("//img[@class='hide_icon passwordImg']"));
        WebElement confirmEyeIcon = driver.findElement(By.xpath("//img[@class='hide_icon cpasswordImg']"));
        WebElement state = driver.findElement(By.name("state"));
        WebElement zipcode = driver.findElement(By.id("zipcode"));
        WebElement resturant_name = driver.findElement(By.id("resturant_name"));
        WebElement address = driver.findElement(By.id("address"));
        WebElement termCondition = driver.findElement(By.id("exampleCheck1"));
        WebElement signup = driver.findElement(By.xpath("//button[normalize-space()='Sign Up']"));

        // Fill in the sign-up form
        firstNameInput.sendKeys("My Resturant");
        lastnameInput.sendKeys("Mark");

        email.sendKeys("resturant74@yopmail.com");
        passwordInput.sendKeys("123456");
        confirmpassword.sendKeys("123456");
        eyeIcon.click();
        confirmEyeIcon.click();
        resturant_name.sendKeys(" ");
        address.sendKeys("123abc rode");

        Select selectState = new Select(state);
        selectState.selectByVisibleText("Arizona");
        zipcode.sendKeys("123456");

        termCondition.click();
        signup.click();


        WebElement alertElement = driver.findElement(By.cssSelector(".alert.alert-danger"));

        // Get the text content of the alert message
        String alertMessage = alertElement.getText();

        // Expected alert message
        String expectedMessage = "The restaurant name field is required.";

        // Assert that the alert message contains the expected message
        Assert.assertTrue(alertMessage.contains(expectedMessage), "Expected message not found in alert.");
        System.out.println("expErrorMsg :" + expectedMessage);


    }


    @Test(priority = 4)
    public void SignupPage_Postive() {


        WebElement firstNameInput = driver.findElement(By.id("firstname"));
        WebElement lastnameInput = driver.findElement(By.id("lastname"));

        WebElement email = driver.findElement(By.id("email"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement confirmpassword = driver.findElement(By.id("cpassword"));
        WebElement eyeIcon = driver.findElement(By.xpath("//img[@class='hide_icon passwordImg']"));
        WebElement confirmEyeIcon = driver.findElement(By.xpath("//img[@class='hide_icon cpasswordImg']"));
        WebElement state = driver.findElement(By.name("state"));
        WebElement zipcode = driver.findElement(By.id("zipcode"));
        WebElement resturant_name = driver.findElement(By.id("resturant_name"));
        WebElement address = driver.findElement(By.id("address"));
        WebElement termCondition = driver.findElement(By.id("exampleCheck1"));
        WebElement signup = driver.findElement(By.xpath("//button[normalize-space()='Sign Up']"));

        // Fill in the sign-up form
        firstNameInput.sendKeys("My Resturant");
        lastnameInput.sendKeys("Mark");

        email.sendKeys("resturant14@yopmail.com");
        passwordInput.sendKeys("123456");
        confirmpassword.sendKeys("123456");
        eyeIcon.click();
        confirmEyeIcon.click();
        resturant_name.sendKeys("5 Bite");
        address.sendKeys("xyz12 north poll");

        Select selectState = new Select(state);
        selectState.selectByVisibleText("Arizona");
        zipcode.sendKeys("123456");
        termCondition.click();
        signup.click();
        System.out.println("Please Varify the Email");


    }


    @AfterMethod
    public void tearDown() {
        // Close the WebDriver
        if (driver != null) {
            driver.quit();
        }
    }

    public static class Browsersetup {

        WebDriver driver;

        @BeforeMethod
        public void setUp() {
            driver = new ChromeDriver();
            driver.get("https://skwadlink.com/");
            System.out.println(driver.getCurrentUrl());
            System.out.println(driver.getTitle());
            driver.manage().window().maximize();
        }

    //    @Test()
    //    public void LoginUser() {
    //        WebElement LoginPage = driver.findElement(By.xpath("//a[normalize-space()='Log In']"));
    //        LoginPage.click();
    //        WebElement Enteremail = driver.findElement(By.id("email"));
    //        Enteremail.sendKeys("amelia@yopmail.com");
    //        WebElement password = driver.findElement(By.id("password"));
    //        password.sendKeys("123456");
    //        WebElement LoginEyeicons = driver.findElement(By.id("passwordVisibility"));
    //        LoginEyeicons.click();
    //        WebElement Login = driver.findElement(By.xpath("//button[normalize-space()='Log In']"));
    //        Login.click();
    //
    //        String u = driver.getCurrentUrl();
    //        if (u.equalsIgnoreCase("https://skwadlink.com/user/dashboard")) {
    //            System.out.println("Test case passed");
    //        } else {
    //            System.out.println("Test case failed");
    //        }

       // }
    }

    public static class Dashboard {
        WebDriver driver;

        @BeforeMethod()
        public void setUp() {
            driver = new ChromeDriver();
            driver.get("https://skwadlink.com");
            System.out.println(driver.getCurrentUrl());
            System.out.println(driver.getTitle());
            driver.manage().window().maximize();
            // Initialize WebDriver (Assuming ChromeDriver here)

        }

        @Test(priority = 1)
        public void LoginUser() throws InterruptedException, AWTException {
            WebElement LoginPage = driver.findElement(By.xpath("//a[normalize-space()='Log In']"));
            LoginPage.click();
            WebElement Enteremail = driver.findElement(By.id("email"));
            Enteremail.sendKeys("amelia@yopmail.com");
            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys("123456");
            WebElement LoginEyeicons = driver.findElement(By.id("passwordVisibility"));
            LoginEyeicons.click();
            WebElement Login = driver.findElement(By.xpath("//button[normalize-space()='Log In']"));
            Login.click();

            String u = driver.getCurrentUrl();
            if (u.equalsIgnoreCase("https://skwadlink.com/user/dashboard")) {
                System.out.println("Test case passed");
            } else {
                System.out.println("Test case failed");
            }



    //    @Test(priority = 1)
    //    public void CreateParty() throws InterruptedException, AWTException {




                WebElement MultipleRebiusment = driver.findElement(By.xpath("//a[normalize-space()='Multiple Reimbursements']"));
                MultipleRebiusment.click();

                //Fill Informaation
                WebElement Partyname = driver.findElement(By.id("partyname"));
                Partyname.sendKeys("BirthdayParty");
                WebElement Date = driver.findElement(By.id("whenparty"));
                Date.sendKeys("09-12-2024");

                WebElement Restuerentname = driver.findElement(By.id("whereparty"));
                Restuerentname.sendKeys("5 Star");

                WebElement Amount = driver.findElement(By.id("member_limit"));
                Amount.sendKeys("3");
               System.out.println("Added meal Sucessfully");

                JavascriptExecutor jse = (JavascriptExecutor) driver;
                jse.executeScript("window.scrollBy(0,100)");





           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            Actions actions = new Actions(driver);
            try {

                // Locate and click the "Add member/ Edit Member" link
                WebElement addMemberClick = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Add member/ Edit Member']")));
                addMemberClick.click();

                // Locate and click the "Add Creator" button
                WebElement addCreator = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='add_newCreator']")));
                addCreator.click();

                // Locate and click to open the dropdown
                WebElement selectUserDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@aria-expanded='true']//ul[@class='select2-selection__rendered']")));
                selectUserDropdown.click();

                // Wait for the dropdown to open and interact with the input field
                WebElement Selectuser = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='select2-search__field']")));
                Selectuser.sendKeys("final");

                Selectuser.sendKeys(Keys.ENTER);

                // Wait for the dropdown options to appear and select the desired option
                WebElement desiredOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(), 'final')]")));
                actions.moveToElement(desiredOption).click().perform();

                // Locate and set the contribution percentage
                WebElement contribution = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='form-control creatorPercentage new_Creator_per']")));
                contribution.sendKeys("20");

                // Locate and click the "Add" button
                WebElement clickAdd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='signUp_btn btn mt-3 mb-3 Add_Creator_btn']")));
                clickAdd.click();
            }

            catch (TimeoutException e) {
                System.out.println("Element was not found within the specified time: " + e.getMessage());
            } catch (NoSuchElementException e) {
                System.out.println("Element was not found in the DOM: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }


             Thread.sleep(3000);

            WebElement UploadImg = driver.findElement(By.xpath("//*[@id='CratePartyform']/div/div[7]/div[1]/label[2]"));
                Actions act = new Actions(driver);
                act.moveToElement(UploadImg).click().perform();

                Robot rb = new Robot();
                rb.delay(2000);

                StringSelection ss = new StringSelection("C:\\Users\\Neha Rana\\Downloads\\IMG_2620 (1).jpeg");
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

                rb.keyPress(KeyEvent.VK_CONTROL);
                rb.keyPress(KeyEvent.VK_V);

                rb.keyRelease(KeyEvent.VK_CONTROL);
                rb.keyRelease(KeyEvent.VK_V);

                rb.keyPress(KeyEvent.VK_ENTER);
                rb.keyRelease(KeyEvent.VK_ENTER);

                Thread.sleep(20000);

                driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);


                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));



            try {
                // Find the element by its name attribute
                WebElement uploadimage = driver.findElement(By.xpath(" //span[@class='reUpload_receipt']"));
                uploadimage.isDisplayed();

                // Check if the element is displayed
                if (uploadimage.isDisplayed()) {
                    assertTrue(true);
                    System.out.println("Receipt file uploaded");
                } else {
                    assertTrue(false);
                    System.out.println("Receipt file not uploaded");
                }
            } catch (Exception e) {
                e.printStackTrace();
          }

                JavascriptExecutor jse2 = (JavascriptExecutor) driver;
                jse2.executeScript("window.scrollBy(0,200)");
    //
                WebElement addmeal= driver.findElement(By.xpath("//button[normalize-space()='Add Meal']"));
                addmeal.click();

                WebElement enterQuanity= driver.findElement(By.xpath("//input[@placeholder='Enter quantity']"));
                enterQuanity.sendKeys("1");

                WebElement enterProduct= driver.findElement(By.xpath("//input[@placeholder='Enter product']"));
                enterProduct.sendKeys("Pizza");

                WebElement enterPrice= driver.findElement(By.xpath("//input[@placeholder='Enter price']"));
                enterPrice.sendKeys("25");


                WebElement add= driver.findElement(By.xpath("//button[@class='signUp_btn btn submit_add-QtyBtn']"));
                add.click();

    //
              Thread.sleep(1000);
    //
                WebElement GenrateLink = driver.findElement(By.xpath("//button[normalize-space()='Generate Link']"));
                GenrateLink.click();

                WebElement cancel = driver.findElement(By.xpath("//button[normalize-space()='Cancel']"));
                cancel.click();

    //          WebElement yes= driver.findElement(By.xpath("//button[normalize-space()='Yes']"));
    //          yes.click();
    //
    //          WebElement ok= driver.findElement(By.xpath("//button[normalize-space()='OK']"));
    //           ok .click();
    //            System.out.println("Created Party Scuessfully");

           }


        @AfterMethod
        public void tearDown() {
           // Close the WebDriver
            if (driver != null) {
                driver.quit();
            }
        }
    }

    public static class LoginPage {

        WebDriver driver;

        @BeforeMethod()
        public void setUp() {
            driver = new ChromeDriver();
            driver.get("https://skwadlink.com/login");
            System.out.println(driver.getCurrentUrl());
            System.out.println(driver.getTitle());
            driver.manage().window().maximize();


            // Initialize WebDriver (Assuming ChromeDriver here)

        }

        @Test(priority = 1)
        public void LoginwithwithoutEmail() {


            WebElement Enteremail = driver.findElement(By.id("email"));
            Enteremail.sendKeys(" ");
            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys("123456");
            WebElement LoginEyeicons = driver.findElement(By.id("passwordVisibility"));
            LoginEyeicons.click();
            WebElement Login = driver.findElement(By.xpath("//button[normalize-space()='Log In']"));
            Login.click();

            WebElement errorElement = driver.findElement(By.id("email-error"));

            String actualErrorMessage = errorElement.getText();
            String expectedErrorMessage = "Please enter the email.";
            Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "The error message does not match the expected message.");
            System.out.println("Actual Error Message: " + actualErrorMessage);


        }

        @Test(priority = 2)
        public void LoginwithwithoutPassword() {


            WebElement Enteremail = driver.findElement(By.id("email"));
            Enteremail.sendKeys("amelia@yopmail.com");
            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys(" ");
            ;
            WebElement Login = driver.findElement(By.xpath("//button[normalize-space()='Log In']"));
            Login.click();

    //
            WebElement alertElement = driver.findElement(By.cssSelector(".alert.alert-danger"));

            // Get the text content of the alert message
            String alertMessage = alertElement.getText();

            // Expected alert message
            String expectedMessage = "The password field is required.";

            // Assert that the alert message contains the expected message
            assertTrue(alertMessage.contains(expectedMessage), "Expected message not found in alert.");
            System.out.println("expErrorMsg :" +expectedMessage);




        }



        @Test(priority = 3)
        public void Invalidemailformat() {


            WebElement Enteremail = driver.findElement(By.id("email"));
            Enteremail.sendKeys("amelia#yopmail.com");
            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys("123456");
            WebElement LoginEyeicons = driver.findElement(By.id("passwordVisibility"));
            LoginEyeicons.click();
            WebElement Login = driver.findElement(By.xpath("//button[normalize-space()='Log In']"));
            Login.click();

            String expectedErrorMsg = "Please enter valid email address.";

            WebElement exp = driver.findElement(By.xpath("//label[@id='email-error']"));
            String actualErrorMsg = exp.getText();

            Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
            System.out.println("expectedErrorMsg " +expectedErrorMsg);

            Enteremail.clear();
            password.clear();

        }
        @Test(priority = 4)
        public void InvaliduserNotFound() {


            WebElement Enteremail = driver.findElement(By.id("email"));
            Enteremail.sendKeys("amelia@yopmail.com");
            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys("123");
            WebElement LoginEyeicons = driver.findElement(By.id("passwordVisibility"));
            LoginEyeicons.click();
            WebElement Login = driver.findElement(By.xpath("//button[normalize-space()='Log In']"));
            Login.click();

            String expectedErrorMsg = "The provided credentials do not match our records.";

            WebElement exp = driver.findElement(By.xpath("//div[@id='swal2-html-container']"));
            String actualErrorMsg = exp.getText();

            Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
            System.out.println("Expected result is equal to Actual Result");
            System.out.println("Expected error msg :" +expectedErrorMsg);



        }


        @Test(priority = 5)
        public void LoginpagewithvalidInput() {


            WebElement Enteremail = driver.findElement(By.id("email"));
            Enteremail.sendKeys("amelia@yopmail.com");
            WebElement password = driver.findElement(By.id("password"));
            password.sendKeys("123456");
            WebElement LoginEyeicons = driver.findElement(By.id("passwordVisibility"));
            LoginEyeicons.click();
            WebElement Login = driver.findElement(By.xpath("//button[normalize-space()='Log In']"));
            Login.click();

            String u = driver.getCurrentUrl();
            if (u.equalsIgnoreCase("https://skwadlink.com/user/dashboard")) {
                System.out.println("Test case passed");
            } else {
                System.out.println("Test case failed");
                System.out.println("User Sucessfully Login with valid username or Password");
            }
            System.out.println("User Sucessfully Login with valid username or Password");


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




        @AfterMethod
        public void tearDown() {
            // Close the WebDriver
            if (driver != null) {
                driver.quit();
            }
        }
    }

    public static class OpenShareLink {
        WebDriver driver;

        @BeforeMethod
        public void setUp() {


            driver = new ChromeDriver();
            driver.get("https://skwadlink.com/");
            System.out.println(driver.getCurrentUrl());
            System.out.println(driver.getTitle());
            driver.manage().window().maximize();
            // Initialize WebDriver (Assuming ChromeDriver here)

                WebElement LoginPage = driver.findElement(By.xpath("//a[normalize-space()='Log In']"));
                LoginPage.click();
                WebElement Enteremail = driver.findElement(By.id("email"));
                Enteremail.sendKeys("amelia@yopmail.com");
                WebElement password = driver.findElement(By.id("password"));
                password.sendKeys("123456");
                WebElement LoginEyeicons = driver.findElement(By.id("passwordVisibility"));
                LoginEyeicons.click();
                WebElement Login = driver.findElement(By.xpath("//button[normalize-space()='Log In']"));
                Login.click();

                String u = driver.getCurrentUrl();
                if (u.equalsIgnoreCase("https://skwadlink.com/user/dashboard")) {
                    System.out.println("Test case passed");
                } else {
                    System.out.println("Test case failed");
                }
            }

        @Test(priority = 2)
        public void CopyUrl() {

                WebElement myparties = driver.findElement(By.xpath("//a[normalize-space()='My Parties']"));
                myparties.click();


                WebElement sharelink = driver.findElement(By.xpath("//a[@href='https://skwadlink.com/share_receipt/71']"));
                sharelink.click();

                WebElement copylink = driver.findElement(By.xpath("//button[@id='search-button']"));
                copylink.click();

                WebElement inputElement = driver.findElement(By.id("search"));
                String linkUrl = inputElement.getAttribute("value");

                System.out.println("Link URL: " + linkUrl);


                ChromeOptions incognitoOptions = new ChromeOptions();
                incognitoOptions.addArguments("--incognito");
                WebDriver incognitoDriver = new ChromeDriver(incognitoOptions);
                incognitoDriver.manage().window().maximize();

                // Navigate to the copied link in the incognito window
                incognitoDriver.get(linkUrl);
                // incognitoDriver.quit();
            }


        @Test(priority = 3)
        public void Paybill() {

            try {

                WebElement termCondition = driver.findElement(By.id("term\\&Condition"));
                termCondition.click();

                System.out.println("Clicked on 'term & Condition' checkbox.");
            } catch (Exception e) {
                System.out.println("Element not found or unable to interact: " + e.getMessage());
            }

        }
        @AfterMethod
        public void tearDown() {
            // Close the WebDriver


                driver.quit();
            }
    }

    public static class PayBill extends Browsersetup {
        WebDriver driver;

        @BeforeMethod
        public void setUp() {
            driver = new ChromeDriver();
            driver.get("https://skwadlink.com/");
            System.out.println(driver.getCurrentUrl());
            System.out.println(driver.getTitle());
            driver.manage().window().maximize();
        }

        @Test()
        public void Paybill() throws IOException {

            driver.get("https://skwadlink.com/without-account/HPO4dZz8QufXdMHeFSrjn0Dj7Ir1luWi");


            WebElement email = driver.findElement(By.xpath("//input[@placeholder='Enter your Email']"));
            email.sendKeys("jack@yopmail.com");
            WebElement firstName = driver.findElement(By.xpath("//input[@placeholder='Enter your first name']"));
            firstName.sendKeys("Jack");
            WebElement LastName = driver.findElement(By.xpath("//input[@placeholder='Enter your last name']"));
            LastName.sendKeys("Doe");

            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollBy(0,100)");

            try {


                // Find all elements with the specified class name
                java.util.List<WebElement> addMyMealButtons = driver.findElements(By.className("add_my_meal"));

                // Iterate over the elements and perform actions
                for (WebElement button : addMyMealButtons) {

                    // Wait for the element to be clickable
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                    wait.until(ExpectedConditions.elementToBeClickable(button));

                    // Click the button using JavaScript to avoid interception
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);

                    // Optionally, add verification or assertion steps after clicking
                }

            } catch (Exception e) {
                System.err.println("Exception: " + e.getMessage());
            }
            WebElement addMealIcon = driver.findElement(By.xpath("//i[@class='fa-solid add_my_meal fa-plus']"));


            addMealIcon.click();
            WebElement individualLink = driver.findElement(By.xpath("//a[@class='dropdown-item' and text()='Individual']"));

            // Click on the <a> element
            individualLink.click();

            WebElement submit= driver.findElement(By.xpath("//button[normalize-space()='Confirm and continue']"));
            submit.click();


            String pageSource = driver.getPageSource();

    // Storing Page Source length in Int variable
            int pageSourceLength = pageSource.length();

    // Printing length of the Page Source on console
            System.out.println("Total length of the Pgae Source is : " + pageSourceLength);





            TakesScreenshot screenshot = (TakesScreenshot)driver;
    //Saving the screenshot in desired location
            File source = screenshot.getScreenshotAs(OutputType.FILE);
    //Path to the location to save screenshot
            FileUtils.copyFile(source, new File("C:\\Users\\Neha Rana\\IdeaProjects\\SeleniumSkwadProject\\target\\screenshot.png"));
            System.out.println("Screenshot is captured");

            WebElement backhome= driver.findElement(By.xpath("//a[normalize-space()='Back to Home']"));
            backhome.click();


        }
        @AfterMethod
        public void tearDown() {
            // Close the WebDriver


            driver.quit();
        }
        }

    public static class Signpage  {
        WebDriver driver;

        @BeforeMethod
        public void setUp() {
            driver = new ChromeDriver();
            driver.get("https://skwadlink.com/signup");
            System.out.println(driver.getCurrentUrl());
            System.out.println(driver.getTitle());
            driver.manage().window().maximize();
            // Initialize WebDriver (Assuming ChromeDriver here)

        }



        @Test(priority = 1)
        public void signUpwithoutfirstname() {
            // Locate the input fields and submit button
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

            // Fill in the sign-up form
            firstNameInput.sendKeys(" ");
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

             String expErrorMsg= "Please enter the first name.";
            String expectedTitle = "Skwad Link";  //Please enter the first name.
            String actualTitle = driver.getTitle();
            Assert.assertEquals(actualTitle, expectedTitle);
            System.out.println("expErrorMsg :"+ expErrorMsg);





        }
        @Test(priority = 1)
        public void signUpalreadyexistEmail() {
            // Locate the input fields and submit button
            WebElement signUpPage = driver.findElement(By.className("signup"));
            signUpPage.click();

            WebElement firstNameInput = driver.findElement(By.id("firstname"));
            WebElement lastnameInput = driver.findElement(By.id("lastname"));
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

            // Fill in the sign-up form
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
            System.out.println("expectedErrorMsg :" +expectedErrorMsg);
        }


        @Test(priority = 2)
        public void termsRedirectionTest()
        {
            WebElement termsLink = driver.findElement(By.xpath("//*[@id=\"signUpForm\"]/div/div[9]/label"));
            termsLink.click();

            Set<String> allWindows = driver.getWindowHandles();

            for(String handle : allWindows)
            {
                driver.switchTo().window(handle);
            }

            String expectedURL = "https://skwadlink.com/terms-conditions";
            String actualURL = driver.getCurrentUrl();
            System.out.println(actualURL);
            Assert.assertEquals(actualURL, expectedURL);

            String expectedTitle = "Skwad Link";//Terms and Conditions
            String actualTitle = driver.getTitle();
            System.out.println(actualTitle);
            Assert.assertEquals(actualTitle, expectedTitle);
        }
        @Test(priority = 1)
        public void signUpPage() {
            // Locate the input fields and submit button
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

            // Fill in the sign-up form
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
        public void loginRedirectionTest()
        {
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
            // Close the WebDriver
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
