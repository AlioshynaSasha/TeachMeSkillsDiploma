package tests.gui;

import baseEntities.BaseTest;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import configuration.ReadProperties;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import models.Project;
import models.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.projects.ProjectsPage;
import steps.projects.AddProjectStep;

import java.util.Objects;

@Epic("GUI Tests")
@Feature("Project Tests")
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

    @Test(description = "Successful project creation")
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

    @Test(description = "Failure to create a project")
    @Description("Creating a project without a name.")
    public void createProjectFailureTest() {
        SelenideElement errorField = addProjectStep.createProjectIncorrect().errorField;

        errorField.shouldBe(Condition.visible);
        errorField.shouldHave(Condition.text("Field Name is a required field."));
    }

    @Test(description = "Checking the field with the maximum value")
    @Description("Checking the maximum allowed value of the field.")
    public void maxLengthProjectNameFieldTest() {
        SelenideElement nameField = addProjectStep.addProjectPage.name;
        nameField.sendKeys(RandomStringUtils.randomAlphabetic(251));

        Assert.assertEquals(Objects.requireNonNull(nameField.getValue()).length(), 250);
    }

    @Test(description = "Checking the tooltip")
    @Description("Checking tooltips for adding table and editor.")
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
