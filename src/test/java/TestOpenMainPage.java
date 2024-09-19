import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.OrderPage;

public class TestOpenMainPage {
    private WebDriver driver;
    @Test
    public void openMainPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        OrderPage orderPage = new OrderPage(driver);
        orderPage.openMainPage();
        Assert.assertEquals("Привезём его прямо к вашей двери,\n" +
                "а когда накатаетесь — заберём" ,orderPage.getTextOnMainPage());
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
