package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage{
    public SelenideElement addProjectButton = $(By.id("sidebar-projects-add"));
}

