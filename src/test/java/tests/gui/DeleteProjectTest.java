package tests.gui;

import baseEntities.BaseTest;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import configuration.ReadProperties;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import models.User;
import org.apache.http.protocol.HTTP;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.projects.ProjectsPage;
import steps.projects.DeleteProjectStep;
import utils.Endpoints;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;

@Epic("GUI Tests")
@Feature("Project Tests")
public class DeleteProjectTest extends BaseTest {
    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();

        loginStep.loginSuccessful(new User(ReadProperties.username(), ReadProperties.password()));
    }

    @Test(description = "Successful project deletion")
    public void deleteProjectTest() throws IOException {
        int projectId = given()
                .baseUri(ReadProperties.getUrl())
                .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                .auth().preemptive().basic(ReadProperties.username(), ReadProperties.password())
                .body(Files.readString(Paths.get("src/test/resources/createProjectData.json")))
                .when()
                .post(Endpoints.CREATE_PROJECT)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().get("id");

        DeleteProjectStep deleteProjectStep = open(ProjectsPage.pagePath, DeleteProjectStep.class);
        SelenideElement project = deleteProjectStep.getProjectById(projectId);

        deleteProjectStep.deleteProject(project);

        project.shouldNotBe(Condition.exist);
    }
}
