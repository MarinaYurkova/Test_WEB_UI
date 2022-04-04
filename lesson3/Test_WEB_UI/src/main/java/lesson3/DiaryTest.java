package lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Struct;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class DiaryTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://diary.ru/");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

       /*
        WebElement loginButton = driver.findElement(By.xpath("//a[.='Вход']"));
        driver.findElement(By.id("loginform-username")).sendKeys("spartalex");
        driver.findElement(By.id("loginform-password")).sendKeys("123456");

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));
        driver.findElement(By.xpath("recaptcha-checkbox-border")).click();

        driver.switchTo().parentFrame();

        driver.findElement(By.id("login_btn")).click();
        */

        Cookie authCookie = new Cookie("_identity_", "a2189c7ce7994221be29f809542c11906"+
                "86265a7448f853c14131a5775ea7b8ea%3A2%3A%7Bi%3A0%3Bs%3A10%3A%22_identity_%22%3Bi%3A"+
                "1%3Bs%3A48%3A%22%5B3545507%2C%22E_QJqRaNdBrepyeVN7uNXi5Dz9tjGpfX%22%2C900%5D%22%3B%7D");
        driver.manage().addCookie(authCookie);
        driver.navigate().refresh();

        driver.findElement(By.xpath("//a[@title='Новая запись']")).click();
        String postName = "Post" + new Random().nextInt(1000);

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postTitle")));

        driver.findElement(By.id("postTitle")).sendKeys(postName);
        driver.switchTo().frame(driver.findElement(By.id("message_ifr")));
        driver.findElement(By.id("tinymce")).sendKeys("Our post test");
        driver.switchTo().parentFrame();

        driver.findElement(By.id("rewrite")).click();
        //driver.findElement(By.xpath("//a[.='Post821']")).click();
        //driver.findElement(By.xpath(String.format("//a[.='%s']", postName))).click();
        List<WebElement> posts = driver.findElements(By.xpath("//a[@class='title']"));
        posts.stream().filter(p -> p.getText().contains(postName)).findFirst().get().click();
        Thread.sleep(3000);

        driver.close();
    }

}