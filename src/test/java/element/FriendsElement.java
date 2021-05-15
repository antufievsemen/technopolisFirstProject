package element;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wrapper.FriendWrapper;

import java.util.ArrayList;
import java.util.List;

public class FriendsElement {
    private static final String JAVASCRIPT_SCRIPT = "return document.querySelector(\"#msg_layer\").shadowRoot";
    private static final String CSS_SELECTOR_MAIN_ELEMENT = "msg-app";
    private static final String CSS_SELECTOR_MESSAGE = "msg-chats-list-item";
    private WebElement rootElem;
    private final WebDriver driver;

    public FriendsElement(WebDriver driver) {
        this.driver = driver;
        findRootElement();
    }

    private void findRootElement() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement shadowDom = (WebElement) js.executeScript(JAVASCRIPT_SCRIPT);
        rootElem = shadowDom.findElement(By.cssSelector(CSS_SELECTOR_MAIN_ELEMENT));
    }

    public List<FriendWrapper> getFriendList() {
        List<WebElement> webElementList = rootElem.findElements(By.cssSelector(CSS_SELECTOR_MESSAGE));
        List<FriendWrapper> friendWrapperList = new ArrayList<>();
        for (WebElement elem: webElementList) {
            friendWrapperList.add(new FriendWrapper(elem));
        }
        return friendWrapperList;
    }
}
