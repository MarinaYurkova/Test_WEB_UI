package lesson6;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MainPage extends BaseView {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "play-button")
    private WebElement playButton;

    @Step("Проверка включения проигрывателя")
    public MainPage playStop() {
        playButton.click();
        webDriverWait.until(d -> d.findElement(By.id("play-button")).getAttribute("title").equals("Pause"));
        playButton.click();
        return this;
    }

    @FindBy(xpath = "//button[@title='player settings']")
    private WebElement settingsButton;

    @FindBy(xpath = "//button[contains(@title,\"ultra\")]")
    private WebElement ultraSettingsButton;

    @FindBy(xpath = "//div[@class='control_wrapper mode-history']")
    private WebElement backGround;

    @Step("Изменение качества")
    public MainPage rpQuality() {
        settingsButton.click();
        ultraSettingsButton.click();
        backGround.click();
        webDriverWait.until(ExpectedConditions.invisibilityOf(ultraSettingsButton));
        return this;
    }

    @FindBy(xpath = "//*[@class='song-labels']")
    private List<WebElement> songs;

    @Step("Вызов инфо о песне")
    public MainPage rpViewSongInfo() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='song-labels']")));
        songs.get(0).click();
        return this;
    }

    @FindBy(xpath = "//a[.='wiki']")
    private WebElement wikiButton;

    @Step("Вызов страницы на Вики о группе")
    public MainPage rpViewWikiInfo() {
        songs.get(0).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[.='wiki']")));
        wikiButton.click();
        webDriverWait.until(ExpectedConditions.urlContains("/wiki"));
        driver.switchTo().window((String) new ArrayList(driver.getWindowHandles()).get(1));
        return this;
    }
}
