

package org.example.User;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

public class Browsersetup {
    WebDriver driver;

    public Browsersetup() {
    }

    @BeforeMethod
    public void setUp() {
        this.driver = new ChromeDriver();
        this.driver.get("https://skwadlink.com/");
        System.out.println(this.driver.getCurrentUrl());
        System.out.println(this.driver.getTitle());
        this.driver.manage().window().maximize();
    }
}
