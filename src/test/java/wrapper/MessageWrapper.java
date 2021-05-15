package wrapper;

import element.ChatPickerElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MessageWrapper {
    private static final String CSS_SELECTOR_TEXT = "msg-parsed-text";
    private static final String CSS_SELECTOR_MESSAGE_MENU_OPTION = "main > msg-button:nth-child(3)";
    private static final String CSS_SELECTOR_MESSAGE_MENU_SEND = "main > msg-button:nth-child(2)";
    private static final String CSS_SELECTOR_MESSAGE_MENU_OPTION_DELETE = "msg-menu-item.delimiter";
    private static final String CSS_SELECTOR_DELETE_BUTTON_CONFIRM = "msg-button[data-tsid=confirm-primary]";
    private static final String CSS_SELECTOR_SEARCH_INPUT = "msg-input";
    private static final String CSS_SELECTOR_SEND_MESSAGE_CONFIRM = "msg-button[data-tsid=forward_btn]";
    private final WebElement element;
    private final WebElement shadowRoot;
    private final String text;
    private final Actions actions;

    public MessageWrapper(WebDriver driver, WebElement element, WebElement shadowRoot) {
        this.element = element;
        this.shadowRoot = shadowRoot;
        this.text = element.findElement(By.cssSelector(CSS_SELECTOR_TEXT)).getText();
        this.actions = new Actions(driver);
    }

    public String getText() {
        return text;
    }

    /* метод сделан
    *Антуфьев Семен
    * Старый метод, изменен на паттерн Chain of invocations
    * */
//    public void deleteLastMessage(WebDriver driver) {
//        Actions actions = new Actions(driver);
//        actions.moveToElement(this.element).click().perform();
//        actions.moveToElement(this.element.findElement(By.cssSelector(CSS_SELECTOR_MESSAGE_MENU_OPTION))).click().perform();
//        actions.moveToElement(this.shadowRoot.findElement(By.cssSelector(CSS_SELECTOR_MESSAGE_MENU_OPTION_DELETE))).click().perform();
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        actions.moveToElement(this.shadowRoot.findElement(By.cssSelector(CSS_SELECTOR_DELETE_BUTTON_CONFIRM))).click().perform();
//    }

    public MessageWrapper clickMessage() {
        actions.moveToElement(this.element).click().perform();
        return this;
    }

    public MessageWrapper clickMessageOptionBar() {
        actions.moveToElement(this.element.findElement(By.cssSelector(CSS_SELECTOR_MESSAGE_MENU_OPTION))).click().perform();
        return this;
    }

    public MessageWrapper clickMessageOptionDelete() {
        actions.moveToElement(this.shadowRoot.findElement(By.cssSelector(CSS_SELECTOR_MESSAGE_MENU_OPTION_DELETE))).click().perform();
        return this;
    }

    public MessageWrapper clickConfirmDeleteMessage() {
        actions.moveToElement(this.shadowRoot.findElement(By.cssSelector(CSS_SELECTOR_DELETE_BUTTON_CONFIRM))).click().perform();
        return this;
    }

    /* метод сделан
    Шестакова Алина
    * */
    public void sendMessage(String fullName, WebDriver driver) {
        Actions actions = new Actions(driver);
        WebElement helpElement = element;
        actions.moveToElement(helpElement).click().perform();
        helpElement = helpElement.findElement(By.cssSelector(CSS_SELECTOR_MESSAGE_MENU_SEND));
        actions.moveToElement(helpElement).click().perform();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        actions.moveToElement(shadowRoot.findElement(By.cssSelector(CSS_SELECTOR_SEARCH_INPUT))).sendKeys(fullName).perform();

        ChatPickerElement chatPickerElement = new ChatPickerElement(driver);
        List<ChatPickerWrapper> chatPickerWrapperList = chatPickerElement.getChatPickerList();
        for (ChatPickerWrapper chatPickerWrapper : chatPickerWrapperList) {
            if (chatPickerWrapper.getFullName().equals(fullName)) {
                chatPickerWrapper.choose();
                break;
            }
        }
        actions.moveToElement(shadowRoot.findElement(By.cssSelector(CSS_SELECTOR_SEND_MESSAGE_CONFIRM))).click().perform();
    }
}
