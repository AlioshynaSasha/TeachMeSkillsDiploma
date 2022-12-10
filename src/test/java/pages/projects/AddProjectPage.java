package pages.projects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AddProjectPage {
    public SelenideElement name = $(By.id("name"));
    public SelenideElement announcement = $(By.id("announcement"));
    public SelenideElement showAnon = $(By.id("show_announcement"));
    public SelenideElement tableMarkdown = $(By.xpath("//*[@class='icon-markdown-table']/parent::a"));
    public SelenideElement helpMarkdown = $(By.xpath("//*[@class='icon-markdown-help']/parent::a"));

    public List<SelenideElement> types = $$(By.xpath("//*[" +
            "@id='suite_mode_single'" +
            "or @id='suite_mode_single_baseline'" +
            "or @id='suite_mode_multi']")
    );
    public SelenideElement addButton = $(By.id("accept"));
    public SelenideElement errorField = $(By.xpath("//div[@class='message message-error']"));
}
