package com.beautysalon.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;



public class WebTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testRegisterForm(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.get("http://localhost:8080/api/users/register");
        String title = driver.getTitle();
        WebElement username = driver.findElement(By.id("username"));

        username.clear();
        username.sendKeys("abcdefgh", Keys.ENTER);


        Assert.assertEquals(title, "Register form");
    }


    @Test
    public void cartTitle() {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.get("http://localhost:8080/api/cart");
        String title = driver.getTitle();

        Assert.assertEquals(title, "Shopping Cart - Beauty Salon");
    }

    @Test
    public void removeItemButtonCart(){
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.get("http://localhost:8080/api/products/all");
        WebElement addToCartButton  = driver.findElement(By.id("addToCartBtn"));
        addToCartButton.click();
        driver.get("http://localhost:8080/api/cart");

        WebElement removeButton = driver.findElement(By.id("removeBtn"));
        removeButton.click();
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
