package wrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ChatPickerWrapper {
    private static final String CSS_SELECTOR_CHAT_PICKER_TEXT = "msg-parsed-text";
    private final WebElement element;
    private final String fullName;

    public ChatPickerWrapper(WebElement webElement) {
        this.element = webElement;
        fullName = this.element.findElement(By.cssSelector(CSS_SELECTOR_CHAT_PICKER_TEXT)).getText();
    }

    public String getFullName() {
        return fullName;
    }

    public void choose() {
        element.click();
    }
}
