package element;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wrapper.MessageWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessagesElement {
    private static final String JAVASCRIPT_SCRIPT = "return document.querySelector(\"#msg_layer\").shadowRoot";
    private static final String CSS_SELECTOR_ELEMENT_MESSAGE = "msg-message-list";
    private static final String CSS_SELECTOR_MESSAGE = "msg-message";
    private WebElement rootElem;
    private WebElement shadowRoot;
    private final WebDriver driver;


    public MessagesElement(WebDriver driver) {
        this.driver = driver;
        findRootElement();
    }

    private void findRootElement() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        shadowRoot = (WebElement) js.executeScript(JAVASCRIPT_SCRIPT);
        this.rootElem = shadowRoot.findElement(By.cssSelector(CSS_SELECTOR_ELEMENT_MESSAGE));
    }

    public List<MessageWrapper> getListMessages() {
        List<WebElement> webElementList = rootElem.findElements(By.cssSelector(CSS_SELECTOR_MESSAGE));
        List<MessageWrapper> messageWrapperList = new ArrayList<>();
        for (WebElement elem : webElementList) {
            messageWrapperList.add(new MessageWrapper(this.driver, elem, shadowRoot));
        }
        Collections.reverse(messageWrapperList);
        return messageWrapperList;
    }


}
