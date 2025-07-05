package manager;

import model.AddressData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void addInGroup(AddressData address, GroupData group) {
        openHomePage();
        selectContact(address);
        selectToGroup(group);
        submitAddTo();
        openHomePage();
    }

    public void delInGroup(AddressData address, GroupData group) {
        openHomePage();
        selectFromGroup(group);
        selectContact(address);
        removeFromGroup();
        openHomePage();
    }

    private void removeFromGroup() {
        click(By.name("remove"));
    }

    private void selectFromGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    private void submitAddTo() {
        click(By.name("add"));
    }

    private void selectContact(AddressData address) {
        click(By.xpath(String.format("//input[@name=\'selected[]\' and @value=\'%s\']",address.id())));
    }

    private void selectToGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
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
        type(By.name("firstname"), address.firstname());
        type(By.name("lastname"), address.lastname());
        //attach(By.name("photo"),address.photo());
        type(By.name("mobile"), address.mobile());
    }

    private void submitAddressCreation() {
        click(By.name("submit"));
    }

    private void openHomePage() {
        click(By.linkText("home"));
    }

    private void selectAddress(AddressData address) {
        click(By.xpath(String.format("//a[contains(@href,'edit') and contains(@href,\'%s\')]", address.id())));
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

    public String getPhones(AddressData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]",contact.id()))).getText();
    }

    public Map<String, String> getPhones() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }
}
