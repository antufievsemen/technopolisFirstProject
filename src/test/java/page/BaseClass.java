package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {
    protected WebDriver driver;

    public WebDriver driverInit() {
        System.setProperty("webdriver.chrome.driver", "C:/configs/chromedriver_win32/chromedriver.exe");
        return new ChromeDriver();
    }

    public void driverDown(WebDriver driver) {
        driver.quit();
    }

}
