package lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class RPQuality {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://radioparadise.com/player");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.findElement(By.xpath("//button[@title='player settings']")).click();
        driver.findElement(By.xpath("//button[contains(@title,\"ultra\")]")).click();
        driver.findElement(By.xpath("//*[@class='cdk-global-overlay-wrapper']")).click();
        /* Последний шаг пришлось переделать иначе, чем в 1 уроке, т.к. элемент с кнопкой "ultra"
        появляется в новом окошке, и нажать на ту же кнопку, с которой туда зашла не получается,
        только кликнув на div'е за границами окна.
         */

        Thread.sleep(3000);

        driver.close();
    }
}
