package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AddMilestonePage {
    public final static String pagePath = "/index.php?/milestones/add/%d";
    public SelenideElement openFileUploaderModalButton = $(By.xpath("//a[contains(@onclick, 'addImage')]"));
    public final String firstSelectedAttachLocator = ".attachment-block.attachment-selected";
    public SelenideElement fileUploader = $(By.xpath("//*[@id='dialog-ident-attachmentNewDialogFile']/following::input[@type='file']"));
    public SelenideElement attachmentButton = $(By.id("attachmentNewSubmit"));
    public SelenideElement descriptionField = $(By.id("description_display"));
    public final String imageItemLocator = ".//*[contains(@class, 'attachment-list-item')]";
}
