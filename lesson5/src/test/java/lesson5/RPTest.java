package lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RPTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    private final static String RADIO_PARADISE_URL = "https://radioparadise.com/player";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get(RADIO_PARADISE_URL);
    }

    @Test
    void RPPlayStopTest() {
        driver.findElement(By.id("play-button")).click();
        webDriverWait.until(d -> d.getTitle().equals("Pause"));
        //В этом месте в прошлом дз я писала: "как только мы нажимаем кнопку play, ее
        // title меняется с "Play" на "Pause", и я бы хотела указать, чтобы драйвер дождался,
        // пока этот title не сменится". Вы предложили такое решение, и я не понимаю, почему
        //на нем все заваливается.
        driver.findElement(By.id("play-button")).click();

        Assertions.assertTrue(driver.findElement(By.id("play-button")).isDisplayed());
    }

    @Test
    void RPQualityTest() {
        driver.findElement(By.xpath("//button[@title='player settings']")).click();
        driver.findElement(By.xpath("//button[contains(@title,\"ultra\")]")).click();
        driver.findElement(By.xpath("//*[@class='cdk-global-overlay-wrapper']")).click();

        Assertions.assertTrue(driver.findElement(By.xpath("//button[contains(@title,"+
                "\"ultra\")]")).isDisplayed());
    }

    @Test
    void RPViewSongInfoTest() {
        List<WebElement> songs = driver.findElements(By.xpath("//*[@class='song-labels']"));
        songs.get(0).click();

        assertThat(songs).doesNotContainNull();
    }

    @Test
    void RPViewWikiInfoTest() {
        List<WebElement> songs = driver.findElements(By.xpath("//*[@class='song-labels']"));
        songs.get(0).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[.='wiki']")));

        driver.findElement(By.xpath("//a[.='wiki']")).click();
        webDriverWait.until(ExpectedConditions.urlContains("/wiki"));
        // Не могу понять, почему тут падает тест=(
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
