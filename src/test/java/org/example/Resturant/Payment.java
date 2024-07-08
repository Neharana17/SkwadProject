package org.example.Resturant;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;

public class Payment extends BaseTest {


    @Test(priority = 3)
    public void testInvalidCardNumber() {
        // Fill out the payment form with an invalid card number
        WebElement cardnum = driver.findElement(By.id("cardNumber"));
        cardnum.sendKeys("1234567890123456");
        cardnum.clear();
        driver.findElement(By.id("cardExpiry")).sendKeys("12/25");
        driver.findElement(By.id("cardCvc")).sendKeys("123");
        driver.findElement(By.id("billingName")).sendKeys("John Doe");
        driver.findElement(By.name("billingName")).click();
        driver.findElement(By.xpath("//div[@class='SubmitButton-CheckmarkIcon']")).click();

//        // Click the pay button
//        WebElement payButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("payButton")));
//        payButton.click();

        // Wait for the error message
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Your card number is incorrect.']")));
        String messageText = errorMessage.getText();
        assertEquals(messageText, "Your card number is incorrect.");

    }

    @Test(priority = 2)
    public void testExpiredCard() {
        // Fill out the payment form with an expired card
        driver.findElement(By.id("cardNumber")).sendKeys("4000000000000069");
        driver.findElement(By.id("cardExpiry")).sendKeys("12/20");
        driver.findElement(By.id("cardCvc")).sendKeys("123");
        driver.findElement(By.id("billingName")).sendKeys("John Doe");
        driver.findElement(By.name("billingName")).click();
        driver.findElement(By.xpath("//div[@class='SubmitButton-CheckmarkIcon']")).click();


//        WebElement validateButton = driver.findElement(By.id("validateButton"));  // Replace with actual ID or locator
//        validateButton.click();

        // Wait for the error message to appear
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class, 'FieldError')]/span[@role='alert']")));
        String messageText = errorMessage.getText().trim();

        // Verify the error message text
        assertEquals(messageText, "Your card's expiration year is in the past.");

    }

    @Test(priority = 1)
    public void validCardNumber() {
        driver.findElement(By.id("cardNumber")).sendKeys("4242424242424242");
        driver.findElement(By.id("cardExpiry")).sendKeys("12/25");
        driver.findElement(By.id("cardCvc")).sendKeys("123");
        driver.findElement(By.id("billingName")).sendKeys("John Doe");
        driver.findElement(By.name("billingName")).click();
        driver.findElement(By.xpath("//div[@class='SubmitButton-CheckmarkIcon']")).click();


        // Wait for the processing spinner to disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".SubmitButton-Icon.SubmitButton-SpinnerIcon")));

        // Wait for the success message to appear
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Payment successful! Thank you for choosing our services !']")));
        String messageText = successMessage.getText();
        assertEquals(messageText, "Payment successful! Thank you for choosing our services !");
        System.out.println("Get msg:- " + messageText);
    }



}



