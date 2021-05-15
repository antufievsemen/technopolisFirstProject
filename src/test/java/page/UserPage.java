package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserPage {
    private static final String LOCATOR_MESSAGES = ".//div[@data-module='ToolbarManager']//li[@data-l='t,messages']";
    private final WebDriver driver;

    public UserPage(WebDriver driver) {
        this.driver = driver;
    }

    public MessagesPage getMessagesPage() {
        driver.findElement(By.xpath(LOCATOR_MESSAGES)).click();
        return new MessagesPage(driver);
    }
}
