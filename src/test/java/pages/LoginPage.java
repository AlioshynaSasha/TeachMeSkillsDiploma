package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public SelenideElement emailInput = $(By.id("name"));
    public SelenideElement passwordInput = $(By.id("password"));
    public SelenideElement loginButton = $(By.id("button_primary"));
}
