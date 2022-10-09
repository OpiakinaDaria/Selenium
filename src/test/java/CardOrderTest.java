import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class CardOrderTest {


    private WebDriver driver;


    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
    }


    @BeforeEach
    void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

        driver.get("http://localhost:9999/");
    }
       

    @AfterEach
    void tearsDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void ShouldCardOrder() {
        driver.get("http://localhost:7777");
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        inputs.get(0).sendKeys("Булдакова Дарья");
        inputs.get(1).sendKeys("+79113331122");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.className("Success_successBlock__2L3Cw")).getText().trim();
        Assertions.assertEquals(expected, actual);
    }
}
