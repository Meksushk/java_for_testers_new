package manager;

import model.AddressData;
import org.openqa.selenium.By;

public class AddressHelper extends HelperBase {

    public AddressHelper(ApplicationManager manager){
        super(manager);
    }

    public void createAddress(AddressData address) {
        openAddressPage();
        fillAddressForm(address);
        click(By.cssSelector("form:nth-child(2)"));
        submitAddressCreation();
        openHomePage();
    }

    public void removeAddress() {
        selectAddress();
        removeSelectedAddress();
    }

    public void openAddressPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.linkText("add new"));
        }
    }

    private void fillAddressForm(AddressData address) {
        type(By.name("firstname"), address.first_name());
        type(By.name("lastname"), address.last_name());
        type(By.name("mobile"), address.mobile());
    }

    private void submitAddressCreation() {
        click(By.name("submit"));
    }

    private void openHomePage() {
        click(By.linkText("home"));
    }

    private void selectAddress() {
        click(By.name("selected[]"));
    }

    private void removeSelectedAddress() {
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    public boolean isAddressPresent() {
        return manager.isElementPresent(By.name("selected[]"));
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }
}
