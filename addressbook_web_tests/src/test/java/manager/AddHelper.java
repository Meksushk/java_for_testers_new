package manager;

import model.AddData;
import org.openqa.selenium.By;

public class AddHelper extends HelperBase {

    public AddHelper(ApplicationManager manager){
        super(manager);
    }

    public void createAdd(AddData add) {
        openAddPage();
        fillAddForm(add);
        click(By.cssSelector("form:nth-child(2)"));
        submitAddCreation();
        openHomePage();
    }

    public void removeAdd() {
        selectAdd();
        removeSelectedAdd();
    }

    public void openAddPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.linkText("add new"));
        }
    }

    private void fillAddForm(AddData add) {
        type(By.name("firstname"), add.first_name());
        type(By.name("lastname"), add.last_name());
        type(By.name("mobile"), add.mobile());
    }

    private void submitAddCreation() {
        click(By.name("submit"));
    }

    private void openHomePage() {
        click(By.linkText("home page"));
    }

    private void selectAdd() {
        click(By.name("selected[]"));
    }

    private void removeSelectedAdd() {
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    public boolean isAddPresent() {
        return manager.isElementPresent(By.name("selected[]"));
    }
}
