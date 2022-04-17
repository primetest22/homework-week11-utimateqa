package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "https://courses.ultimateqa.com/";

    @Before
    public  void setUp(){
        openBrowser(baseUrl);
    }
    @Test
    public void userShouldNavigateToLoginPageSuccessfully(){
        //find signin link and click
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        String expectedDisplay = "Welcome Back!";
        String actualDisplay = driver.findElement(By.className("page__heading")).getText();

        Assert.assertEquals("Not Matching", expectedDisplay, actualDisplay);
    }
    @Test
    public void verifyTheErrorMessage(){

        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();

        WebElement userName = driver.findElement(By.id("user[email]"));
        userName.sendKeys("123.com");

        WebElement password = driver.findElement(By.id("user[password]"));
        password.sendKeys("123");

        WebElement logBtn =
                driver.findElement(By.xpath("//body/main[@id='main-content']/div[1]/div[1]/article[1]/form[1]/div[4]/input[1]"));
        logBtn.click();

        String expectedDisplay = "Invalid email or password.";
        String actualDisplay =
                driver.findElement(By.xpath("//li[contains(text(),'Invalid email or password.')]")).getText();

        Assert.assertEquals("Not Matching",expectedDisplay, actualDisplay);


    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
