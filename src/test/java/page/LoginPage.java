package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private static final String LOCATOR_USERNAME = ".//input[@id='field_email']";
    private static final String LOCATOR_PASSWORD = ".//input[@id='field_password']";
    private static final String LOCATOR_SIGN_IN = ".//*[@value='Войти в Одноклассники']";
    private static final String loginURL = "https://ok.ru/";
    private final WebDriver driver;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public UserPage doLogin(String username, String password) {
        driver.manage().window().maximize();
        driver.get(loginURL);
        driver.findElement(By.xpath(LOCATOR_USERNAME)).sendKeys(username);
        driver.findElement(By.xpath(LOCATOR_PASSWORD)).sendKeys(password);
        driver.findElement(By.xpath(LOCATOR_SIGN_IN)).click();
        return new UserPage(this.driver);
    }
}
