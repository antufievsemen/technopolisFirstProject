package element;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wrapper.ChatPickerWrapper;

import java.util.ArrayList;
import java.util.List;

public class ChatPickerElement {
    private static final String JAVASCRIPT_SCRIPT = "return document.querySelector(\"#msg_layer\").shadowRoot";
    private static final String CSS_SELECTOR_ELEMENT_CHAT_PICKER = "msg-app";
    private static final String CSS_SELECTOR_CHAT_PICKER = "msg-chat-picker-list-item";
    private WebElement rootElem;
    private final WebDriver driver;

    public ChatPickerElement(WebDriver driver) {
        this.driver = driver;
        findRootElement();
    }

    private void findRootElement() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement shadowDom = (WebElement) js.executeScript(JAVASCRIPT_SCRIPT);
        rootElem = shadowDom.findElement(By.cssSelector(CSS_SELECTOR_ELEMENT_CHAT_PICKER));
    }

    public List<ChatPickerWrapper> getChatPickerList() {
        List<WebElement> webElementList = rootElem.findElements(By.cssSelector(CSS_SELECTOR_CHAT_PICKER));
        List<ChatPickerWrapper> chatPickerWrappers = new ArrayList<>();
        for (WebElement elem: webElementList) {
            chatPickerWrappers.add(new ChatPickerWrapper(elem));
        }
        return chatPickerWrappers;
    }
}
