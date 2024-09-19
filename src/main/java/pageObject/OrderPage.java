package pageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class OrderPage {
    private WebDriver driver;
    //поле имя
    private By nameField = By.xpath("//input[@placeholder='* Имя']");
    // поле фамилия
    private By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    // поле адрес
    private By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    // выпадающий список метро
    private By metroList = By.xpath("//input[@placeholder='* Станция метро']");
    // поле телефон
    private By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    // кнопка далее
    private By nextButton = By.xpath("//button[contains(text(),'Далее')]");
    // календарь
    private By calendar = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    // выпадающий список срок аренды
    private By rentList = By.xpath("//div[@class='Dropdown-placeholder']");
    // чекбокс выбора цвета черный
    private By blackCheckBox = By.xpath("//input[@id='black']");
    // чекбокс выбора цвета серый
    private By greyCheckBox = By.xpath("//input[@id='grey']");
    // комментарий для курьера
    private By commentForDeliver = By.xpath("//input[@placeholder='Комментарий для курьера']");
    //кнопка заказать
    private By orderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //кнопка подтверждения заказа
    private By confirmButton = By.xpath("//button[contains(text(),'Да')]");
    //верхняя кнопка заказа
    private By topOrderButton = By.xpath("//button[@class='Button_Button__ra12g']");
    //кнопка заказать снизу
    private By middleOrderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //кнопка принятия кукисов
    private By conferCookieButton = By.xpath("//button[@id='rcc-confirm-button']");
    //кнопка "Да" при заказе
    private By buttonYes = By.xpath("//button[contains(text(),'Да')]");
    //локатор текста на странице
    private By textOnMainPage = By.xpath("//div[@class='Home_Header__iJKdX']//div[1]");
    //кнопка главной страницы заказа скутера
    private By ButtonScooter = By.xpath("//img[@alt='Scooter']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }



    public void makeOrder(String name, String surname, String address, String nameMetro, String phone, String rent,
                          String orderDate, String colour, String comment, String buttonType) {
        //принимаем куки
        clickElement(conferCookieButton);
        //нажимаем на кнопку заказать
        if (buttonType.equals("top")) {
            clickElement(topOrderButton);
        } else if (buttonType.equals("middle")) {
            clickElement(middleOrderButton);
        }
        //вводим имя
        input(nameField, name);
        //вводим фамилию
        input(surnameField, surname);
        //вводим адрес
        input(addressField, address);
        //кликаем на выбор станции метро
        clickElement(metroList);
        //выбираем станцию метро
        By metroName = getElementByClass("Order_Text__2broi" , nameMetro);
        clickElement(metroName);
        //вводим номер телефона
        input(phoneField, phone);
        //нажимаем кнопку далее
        clickElement(nextButton);
        //выбираем дату доставки
        input(calendar,orderDate);
        WebElement inputField = driver.findElement(calendar);
        inputField.sendKeys(Keys.ENTER);
        //кликаем на список выбора времени аренды
        clickElement(rentList);
        //выбираем на сколько хотим арендовать самокат
        By rentTime = getElementByClass("Dropdown-option", rent);
        clickElement(rentTime);
        //выбираем цвет
        By blackColour = getElementBylabel("Checkbox_Label__3wxSf", colour);
        clickElement(blackColour);
        //пишем комментарий для курьера
        input(commentForDeliver, comment);
        //кликаем на кнопку заказать
        clickElement(orderButton);
        //кливаем на кнопку да
        clickElement(buttonYes);
        //checkTextOnPage(orderText, "Заказ оформлен");
        
    }

    public void openMainPage() {
        //принимаем куки
        clickElement(conferCookieButton);
        //нажимаем на верхнюю кнопку заказать
        clickElement(orderButton);
        //нажимаем на кнопку самокат
        clickElement(ButtonScooter);

    }

    public String getTextOnMainPage() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(textOnMainPage));
        return driver.findElement(textOnMainPage).getText();
    }

    public void clickElement(By locator) {
        driver.findElement(locator).click();
    }

    private By getElementByClass(String className, String text) {
        return By.xpath("//div[contains(@class, '" + className + "') and text()='" + text + "']");
    }

    private By getElementBylabel(String className, String text) {
        return By.xpath("//label[contains(@class, '" + className + "') and text()='" + text + "']");
    }


    public List<WebElement> elementList() {
        return driver.findElements(metroList);

    }

    public void input(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }


}
