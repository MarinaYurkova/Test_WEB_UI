package lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage extends BaseView {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "play-button")
    private WebElement playButton;

    public MainPage playStop() {
        playButton.click();
        //webDriverWait.until(d -> d.getTitle().equals("Pause"));
        //В этом месте в прошлом дз я писала: "как только мы нажимаем кнопку play, ее
        // title меняется с "Play" на "Pause", и я бы хотела указать, чтобы драйвер дождался,
        // пока этот title не сменится". Вы предложили такое решение, и я не понимаю, почему
        //на нем все заваливается.
        playButton.click();
        return this;
    }

    @FindBy(xpath = "//button[@title='player settings']")
    private WebElement settingsButton;

    @FindBy(xpath = "//button[contains(@title,\"ultra\")]")
    private WebElement ultraSettingsButton;

    @FindBy(xpath = "//*[@class='cdk-global-overlay-wrapper']")
    private WebElement wallPaper;

    public MainPage rpQuality() {
        settingsButton.click();
        ultraSettingsButton.click();
        wallPaper.click();
        return this;
    }

    @FindBy(xpath = "//*[@class='song-labels']")
    private List<WebElement> songs;

    @FindBy(xpath = "//a[.='wiki']")
    private WebElement wikiButton;

    public MainPage rpViewSongInfo() {
        songs.get(0).click();
        return this;
    }

    public MainPage rpViewWikiInfo() {
        songs.get(0).click();
        //webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("a[.='wiki']")));
        wikiButton.click();
        webDriverWait.until(ExpectedConditions.urlContains("/wiki"));
        return this;
    }
}
