package manager;

import model.AddressData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class AddressHelper extends HelperBase {

    public AddressHelper(ApplicationManager manager){
        super(manager);
    }

    public void createAddress(AddressData address) {
        openAddressPage();
        fillAddressForm(address);
        submitAddressCreation();
        openHomePage();
    }

    public void createAddress(AddressData address, GroupData group) {
        openAddressPage();
        fillAddressForm(address);
        selectGroup(group);
        submitAddressCreation();
        openHomePage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    public void removeAddress(AddressData address) {
        selectAddress(address);
        removeSelectedAddress();
    }

    public void modifyAddress(AddressData address, AddressData modifiedAddress) {
        openHomePage();
        selectAddress(address);
        fillAddressForm(modifiedAddress);
        submitAddressModification();
        openHomePage();
    }

    private void submitAddressModification() {
        click(By.name("update"));
    }

    public void openAddressPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.linkText("add new"));
        }
    }

    private void fillAddressForm(AddressData address) {
        type(By.name("firstname"), address.first_name());
        type(By.name("lastname"), address.last_name());
        attach(By.name("photo"),address.photo());
        type(By.name("mobile"), address.mobile());
    }

    private void submitAddressCreation() {
        click(By.name("submit"));
    }

    private void openHomePage() {
        click(By.linkText("home"));
    }

    private void selectAddress(AddressData address) {
        click(By.xpath(String.format("//a[contains(@href,'edit') and endsWith(@href, '%s')]", address.id())));
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

    public List<AddressData> getList() {
        openHomePage();
        var addresses = new ArrayList<AddressData>();
        var trs = manager.driver.findElements(By.name("entry"));
        for (var tr : trs){
            var last_td = tr.findElement(By.cssSelector("td:nth-child(2)"));
            var last_name = last_td.getText();
            var first_td = tr.findElement(By.cssSelector("td:nth-child(3)"));
            var first_name = first_td.getText();
            var id_td = tr.findElement(By.cssSelector("td.center"));
            var checkbox = id_td.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            addresses.add(new AddressData().withId(id).withFirstName(first_name).withLastName(last_name));
        }
        return addresses;
    }
}
