import base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.ContactPage;

import java.time.Duration;

public class ContactTests extends BaseTest {

    ContactPage contactPage = new ContactPage();

    @Test(description = "TC001 - Başarılı iletişim formu gönderimi")
    public void contactFormSuccess() {
        driver.get(url);

        contactPage.goToContactPage();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(contactPage.nameField));

        contactPage.enterName(dummyName);
        contactPage.enterEmail(dummyEmail);
        contactPage.enterSubject(dummySubject);
        contactPage.enterMessage(dummyMessage);
        contactPage.clickSubmit();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        wait.until(ExpectedConditions.visibilityOfElementLocated(contactPage.successMessage));
        String message = contactPage.getSuccessMessage();

        assertEquals(message.trim(), "Success! Your details have been submitted successfully.");
    }

    @Test(description = "TC002 - Email alanı boş bırakıldığında form gönderilememeli")
    public void contactFormWithoutEmail() {
        driver.get(url);
        contactPage.goToContactPage();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(contactPage.nameField));

        contactPage.enterName(dummyName);
        contactPage.enterSubject(dummySubject);
        contactPage.enterMessage(dummyMessage);

        String urlBeforeSubmit = driver.getCurrentUrl();

        contactPage.clickSubmit();

        sleep(2);

        String urlAfterSubmit = driver.getCurrentUrl();
        assertEquals(urlBeforeSubmit, urlAfterSubmit);
    }

    @Test(description = "TC003 - Geçersiz e-mail formatı ile form gönderilememeli")
    public void contactFormWithInvalidEmailFormat() {
        driver.get(url);
        contactPage.goToContactPage();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(contactPage.nameField));

        contactPage.enterName(dummyName);
        contactPage.enterEmail("abc.com");
        contactPage.enterSubject(dummySubject);
        contactPage.enterMessage(dummyMessage);

        String beforeSubmitURL = driver.getCurrentUrl();

        contactPage.clickSubmit();
        sleep(2);

        String afterSubmitURL = driver.getCurrentUrl();

        assertEquals(beforeSubmitURL, afterSubmitURL);
    }

    @Test(description = "TC004 - Tüm alanlar boş bırakıldığında form gönderilememeli")
    public void contactFormWithAllFieldsEmpty() {
        driver.get(url);
        contactPage.goToContactPage();

        String beforeSubmitURL = driver.getCurrentUrl();

        contactPage.clickSubmit();

        sleep(2);

        String afterSubmitURL = driver.getCurrentUrl();

        assertEquals(beforeSubmitURL, afterSubmitURL);
    }


}
