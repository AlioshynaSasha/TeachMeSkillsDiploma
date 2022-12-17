package tests.gui;

import baseEntities.BaseTest;
import configuration.ReadProperties;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import models.User;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddMilestonePage;
import steps.AddMilestoneStep;
import utils.Endpoints;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;

@Epic("GUI Tests")
@Feature("File uploader Tests")
public class FileUploaderTest extends BaseTest {
    private int projectId = 0;

    @BeforeMethod
    @Override
    public void setUp() {
        super.setUp();

        loginStep.loginSuccessful(new User(ReadProperties.username(), ReadProperties.password()));
    }

    @Test(description = "Checking the file uploader")
    public void uploadFileTest() throws IOException, URISyntaxException {
        projectId = given()
                .baseUri(ReadProperties.getUrl())
                .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                .auth().preemptive().basic(ReadProperties.username(), ReadProperties.password())
                .body(Files.readString(Paths.get("src/test/resources/createProjectData.json")))
                .when()
                .post(Endpoints.CREATE_PROJECT)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().get("id");

        AddMilestoneStep addMilestoneStep = open(
                String.format(AddMilestonePage.pagePath, projectId),
                AddMilestoneStep.class
        );

        addMilestoneStep.addFile(
                new File(Objects.requireNonNull(
                        FileUploaderTest.class.getClassLoader().getResource("testImage.jpeg")
                ).toURI())
        );

        Assert.assertEquals(addMilestoneStep.getFilesFromDescription().size(), 1);
    }

    @AfterMethod
    @Override
    public void tearDown() {
        super.tearDown();

        if (projectId != 0) {
            given()
                    .baseUri(ReadProperties.getUrl())
                    .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                    .auth().preemptive().basic(ReadProperties.username(), ReadProperties.password())
                    .pathParam("project_id", projectId)
                    .when()
                    .post(Endpoints.DELETE_PROJECT)
                    .then()
                    .statusCode(HttpStatus.SC_OK);
        }
    }
}
