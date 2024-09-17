import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObject.MainPage;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;


@RunWith(Parameterized.class)
public class TestIsVisibleTextOnMainPage {
    private WebDriver driver;
    private final String questionText;
    private final String expectedAnswer;

    public TestIsVisibleTextOnMainPage(String questionText, String expectedAnswer) {
        this.questionText = questionText;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static List<Object[]> getCredentials() {
        return Arrays.asList(new Object[][] {
                {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        });
    }

    @Test
    public void textIsVisible() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCloseCookiesWindowUsingActions();

        List<WebElement> questions = mainPage.elementList();
        WebDriverWait wait = new WebDriverWait(driver, 5);

        for (WebElement question : questions) {
            if (question.getText().equals(questionText)) {
                question.click();
                WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@class='accordion__item' and contains(text(),'" + questionText + "')]/following-sibling::div"))
                );
                Assert.assertEquals(expectedAnswer, answer.getText());
                break;
            }
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Before
    public void setup() {
        driver = new ChromeDriver();
    }
}