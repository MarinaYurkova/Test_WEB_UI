package lesson5;

import com.google.common.base.Supplier;
import io.github.bonigarcia.wdm.WebDriverManager;
import lesson6.BaseView;
import lesson6.MainPage;
import org.assertj.core.api.ListAssert;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RPTest {

    private final static String RADIO_PARADISE_URL = "https://radioparadise.com/player";
    private final static String WIKI = "a[.='wiki']";

    WebDriver driver;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
    }

    @Test
    void RPTest() {
        driver.get(RADIO_PARADISE_URL);
        List<WebElement> songs = driver.findElements(By.xpath("//*[@class='song-labels']"));

        new MainPage(driver)
                .playStop()
                .rpQuality()
                .rpViewSongInfo()
                .rpViewWikiInfo();

        Assertions.assertAll(
                () -> assertTrue(driver.findElement(By.id("play-button")).isDisplayed()),
                () -> assertTrue(driver.findElement(By.xpath("//button[contains(@title," +
                        "\"ultra\")]")).isDisplayed()),
                () -> assertThat(songs).doesNotContainNull());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
