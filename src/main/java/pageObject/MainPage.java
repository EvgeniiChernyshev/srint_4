package pageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class MainPage {
    private WebDriver driver;
    //Сколько это стоит? И как оплатить?
    private By price = By.xpath("//div[@id='accordion__heading-0']");
    //Хочу сразу несколько самокатов! Так можно?
    private By countScooterInOneOrder = By.id("accordion__heading-17");
    //Как рассчитывается время аренды?
    private By rentTime = By.id("accordion__heading-18");
    //Можно ли заказать самокат прямо на сегодня?
    private By todaysOrder = By.id("accordion__heading-19");
    //Можно ли продлить заказ или вернуть самокат раньше?
    private By continueOrderOrReturScooter = By.id("accordion__heading-20");
    //Вы привозите зарядку вместе с самокатом?
    private By chargeForScooter = By.id("accordion__heading-21");
    //Можно ли отменить заказ?
    private By canICancelOrder = By.id("accordion__heading-22");
    //Я жизу за МКАДом, привезёте?
    private By moveScooterToMe = By.xpath("//div[@id='accordion__heading-7']");

    //кнопка принять куки
    private By conferCookieButton = By.xpath("//button[@id='rcc-confirm-button']");

    private By list = By.xpath("//div[@class='accordion__item']/parent::div");

    private By mainYandexPage = By.xpath("//img[@alt='Yandex']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void switchToAnotherTab() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        clickElement(mainYandexPage);
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public void clickElement(By locator) {
        driver.findElement(locator).click();
    }

    public void checkTextOnPage(By locator, String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(ExpectedConditions.textToBePresentInElement(element, expectedText));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();

    }


    public void clickCloseCookiesWindowUsingActions() {
        WebElement element = driver.findElement(conferCookieButton);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    public List<WebElement> elementList() {
        return driver.findElements(list);

    }

}
