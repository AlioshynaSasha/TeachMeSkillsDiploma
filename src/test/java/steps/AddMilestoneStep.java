package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.AddMilestonePage;

import java.io.File;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$;

public class AddMilestoneStep {
    public AddMilestonePage addMilestonePage;

    public AddMilestoneStep() {
        addMilestonePage = new AddMilestonePage();
    }

    public void addFile(File file) {
        addMilestonePage.openFileUploaderModalButton.click();
        addMilestonePage.fileUploader.uploadFile(file);
        $(By.cssSelector(addMilestonePage.firstSelectedAttachLocator)).shouldBe(Condition.visible);
        addMilestonePage.attachmentButton.click();
    }

    public List<SelenideElement> getFilesFromDescription() {
        return addMilestonePage.descriptionField.$$(By.xpath(addMilestonePage.imageItemLocator)).shouldHave(size(1));
    }
}
