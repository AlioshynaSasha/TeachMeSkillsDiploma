package steps.projects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.projects.ProjectsPage;

import static com.codeborne.selenide.Selenide.*;

public class DeleteProjectStep {
    public ProjectsPage projectsPage;

    public DeleteProjectStep() {
        projectsPage = new ProjectsPage();
    }

    public void deleteProject(SelenideElement project) {
        project.$(By.xpath(projectsPage.deleteProjectLocator)).click();
        SelenideElement dialogWindow = $(By.id("deleteDialog"));
        dialogWindow.$(By.name("deleteCheckbox")).click();
        dialogWindow.$(By.className("button-ok")).click();
    }

    public SelenideElement getProjectById(int projectId) {
        for (SelenideElement project : projectsPage.projects) {
            if (project.$$(By.xpath(String.format(projectsPage.getProjectLocator, projectId))).size() != 0) {
                return project;
            }
        }

        throw new RuntimeException(String.format("Project by this Id \"%d\" not found", projectId));
    }
}
