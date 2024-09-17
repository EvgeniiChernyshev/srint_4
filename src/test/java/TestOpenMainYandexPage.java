import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.MainPage;

public class TestOpenMainYandexPage {
    WebDriver driver;

    @Test
    public void testOpenMainYandexPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage mainPage = new MainPage(driver);
        mainPage.switchToAnotherTab();
        Assert.assertEquals("https://dzen.ru/?yredirect=true", mainPage.getCurrentUrl());
    }

//    @After
//    public void tearDown() {
//        driver.quit();
//    }

    @Before
    public void setup() {
        driver = new ChromeDriver();
    }
}
