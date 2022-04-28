package lesson5;

import com.google.common.base.Supplier;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lesson6.BaseView;
import lesson6.MainPage;
import lesson7.CustomLogger;
import org.assertj.core.api.ListAssert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Story("Проверка основных функций сайта")
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
        driver = new EventFiringDecorator(new CustomLogger()).decorate(new ChromeDriver());
    }

    @Test
    @Feature("Проверка проигрывателя и настроек")

    void RPTest() {
        driver.get(RADIO_PARADISE_URL);
        List<WebElement> songs = driver.findElements(By.xpath("//*[@class='song-labels']"));

        new MainPage(driver)
                .playStop()
                .rpQuality()
                .rpViewSongInfo() //не понимаю, почему падает
                .rpViewWikiInfo(); //та же фигня

       Assertions.assertAll(
                () -> assertTrue(driver.findElement(By.id("play-button")).isDisplayed()),
                () -> assertTrue(driver.findElement(By.xpath("//button[contains(@title," +
                        "\"ultra\")]")).isDisplayed()),
                () -> assertThat(songs).doesNotContainNull());

    }

    @AfterEach
    void tearDown() {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry log: logEntries) {
            Allure.addAttachment("Элемент лога браузера", log.getMessage());
        }
        driver.quit();
    }
}
