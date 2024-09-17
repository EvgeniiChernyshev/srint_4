import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObject.OrderPage;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class TestScooterOrder {
    private WebDriver driver;
    private String name;
    private String surname;
    private String address;
    private String metro;
    private String phone;
    private String deliveryDate;
    private String rent;
    private String colour;
    private String comment;

    public TestScooterOrder(String name, String surname, String address, String metro,
                             String phone, String rent, String deliveryDate, String colour, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.deliveryDate = deliveryDate;
        this.rent = rent;
        this.colour = colour;
        this.comment = comment;

    }

    @Parameterized.Parameters
    public static List<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                {"Иван", "Иванов", " Проспект ленина 30", "Сокольники", "+79000000000", "19.09.2025", "сутки", "чёрный жемчуг",
                        "комментарий для курьера"},
                {"Мария", "Петрова", "улица Щербакова 12", "Чистые пруды", "+79053334422","25.10.2024", "четверо суток","серая безысходность"},

        });
    }
    @Test
    public void testScooterOrder() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        OrderPage orderPage = new OrderPage(driver);
        orderPage.makeOrder(name, surname, address, metro, phone,  deliveryDate, rent, colour, comment);
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

