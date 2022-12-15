package steps.projects;

import models.Project;
import pages.projects.AddProjectPage;
import pages.projects.ProjectsPage;

public class AddProjectStep {
    public AddProjectPage addProjectPage;

    public AddProjectStep() {
        addProjectPage = new AddProjectPage();
    }

    public ProjectsPage createProjectSuccessful(Project project) {
        addProjectPage.name.sendKeys(project.getName());
        addProjectPage.announcement.sendKeys(project.getAnnouncement());
        addProjectPage.types.get(project.getType()).click();

        if (project.isShowAnnouncement()) {
            addProjectPage.showAnon.click();
        }

        addProjectPage.addButton.click();

        return new ProjectsPage();
    }

    public AddProjectPage createProjectIncorrect() {
        addProjectPage.addButton.click();

        return addProjectPage;
    }
}
