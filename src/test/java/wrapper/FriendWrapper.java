package wrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FriendWrapper {
    private static final String CSS_SELECTOR_FRIEND_NAME = ".//msg-parsed-text";
    private final WebElement element;
    private final String fullName;

    public FriendWrapper(WebElement webElement) {
        this.element = webElement;
        fullName = element.findElement(By.xpath(CSS_SELECTOR_FRIEND_NAME)).getText();
    }

    public String getFullName() {
        return fullName;
    }

    public void choose() {
        element.click();
    }
}
