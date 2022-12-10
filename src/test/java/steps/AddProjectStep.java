package steps;

import models.Project;
import pages.projects.AddProjectPage;
import pages.projects.ProjectsPage;

public class AddProjectStep {
    public AddProjectPage addProjectPage;

    public AddProjectStep() {
        addProjectPage = new AddProjectPage();
    }

    public ProjectsPage createProjectSuccessful(Project project) {
        createProject(project);

        return new ProjectsPage();
    }

    public AddProjectPage createProjectIncorrect(Project project) {
        createProject(project);

        return addProjectPage;
    }

    private void createProject(Project project) {
        addProjectPage.name.sendKeys(project.getName());
        addProjectPage.announcement.sendKeys(project.getAnnouncement());
        addProjectPage.types.get(project.getType()).click();

        if (project.isShowAnnouncement()) {
            addProjectPage.showAnon.click();
        }

        addProjectPage.addButton.click();
    }
}
