package lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class RPViewSongInfo {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://radioparadise.com/player");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        List<WebElement> songs = driver.findElements(By.xpath("//*[@class='song-labels']"));
        songs.get(0).click();

        Thread.sleep(3000);

        driver.close();
    }
}
