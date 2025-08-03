package pages;

import base.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ContactPage extends BaseLibrary {

    private final By contactLink = By.xpath("//a[text()=' Contact us']");
    public  final By nameField = By.name("name");
    private final By emailField = By.name("email");
    private final By subjectField = By.name("subject");
    private final By messageField = By.name("message");
    private final By submitButton = By.name("submit");
    public  final By successMessage = By.xpath("//div[@class='status alert alert-success']");

    @Step("Contact us sayfasına girilir.")
    public void goToContactPage() {
        driver.findElement(contactLink).click();
    }

    @Step("İsim girilir: {name}")
    public void enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Email girilir: {email}")
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Konu girilir: {subject}")
    public void enterSubject(String subject) {
        driver.findElement(subjectField).sendKeys(subject);
    }

    @Step("Mesaj girilir: {message}")
    public void enterMessage(String message) {
        driver.findElement(messageField).sendKeys(message);
    }

    @Step("Gönder butonuna tıklanır.")
    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

    @Step("Başarı mesajı alınır.")
    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }
}
