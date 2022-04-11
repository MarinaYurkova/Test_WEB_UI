package lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class RPPlayStop {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://radioparadise.com/player");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.findElement(By.id("play-button")).click();
        /*
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.   ...
        Здесь я не знаю, что именно написать: как только мы нажимаем кнопку play, ее
        title меняется с "Play" на "Pause", и я бы хотела указать, чтобы драйвер дождался,
        пока этот title не сменится.
        */

        Thread.sleep(5000);
        driver.findElement(By.id("play-button")).click();

        Thread.sleep(3000);

        driver.close();
    }
}
