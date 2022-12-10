package pages.projects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProjectsPage {
    public final static String pagePath = "index.php?/admin/projects/overview";
    public final String deleteProjectLocator = ".//a[contains(@onclick, 'projects/delete/')]";
    public final String getProjectLocator = ".//a[contains(@href, 'projects/edit/%d')]";
    public SelenideElement title = $(By.className("page_title"));
    public List<SelenideElement> projects = $$(By.xpath("//tbody/tr[@class!='header']"));
}
