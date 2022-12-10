package tests.gui;

import baseEntities.BaseTest;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import configuration.ReadProperties;
import models.Project;
import models.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.projects.ProjectsPage;
import steps.AddProjectStep;

import java.util.Objects;

public class CreateProjectTest extends BaseTest {
    private AddProjectStep addProjectStep;

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();

        loginStep.loginSuccessful(
                new User(ReadProperties.username(), ReadProperties.password())
        ).addProjectButton.click();
        addProjectStep = new AddProjectStep();
    }

    @Test
    public void createProjectSuccessTest() {
        ProjectsPage projectsPage = addProjectStep.createProjectSuccessful(
                new Project.Builder()
                        .withName("TestName")
                        .withAnnouncement("Test Announcement")
                        .withType(2)
                        .withShowAnon(true)
                        .build()
        );

        projectsPage.title.shouldBe(Condition.visible);
    }

    @Test
    public void createProjectFailureTest() {
        SelenideElement errorField = addProjectStep.createProjectIncorrect(new Project()).errorField;

        errorField.shouldBe(Condition.visible);
        errorField.shouldHave(Condition.text("Field Name is a required field."));
    }

    @Test
    public void maxLengthProjectNameFieldTest() {
        SelenideElement nameField = addProjectStep.addProjectPage.name;
        nameField.sendKeys(RandomStringUtils.randomAlphabetic(251));

        Assert.assertEquals(Objects.requireNonNull(nameField.getValue()).length(), 250);
    }

    @Test
    public void checkTooltipTest() {
        Assert.assertEquals(
                addProjectStep.addProjectPage.tableMarkdown.attr("tooltip-text"),
                "Add a table to this text field."
        );
        Assert.assertEquals(
                addProjectStep.addProjectPage.helpMarkdown.attr("tooltip-text"),
                "Open the editor formatting reference."
        );
    }
}
