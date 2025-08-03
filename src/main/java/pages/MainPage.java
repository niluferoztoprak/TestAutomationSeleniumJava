package pages;

import base.BaseTest;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class MainPage extends BaseTest {

    @Step("Hesap Bilgisi Alınır.")
    public String getAccountInfo() {
        String text = driver.findElements(By.cssSelector("[class='link-text']")).get(0).getText();
        screenshot();
        return text;
    }
}
