package tests;

import model.AddressData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class AddressModificationTests extends TestBase {

    @Test
    void canModifyAddress() {
        if (app.hbm().getAddressCount() == 0){
            app.hbm().createAddress(new AddressData("", "user1", "user1", "123"));
        }
        var oldAddresses = app.hbm().getAddressList();
        var rnd = new Random();
        var index = rnd.nextInt(oldAddresses.size());
        var testData = new AddressData().withLastName("new name");
        app.address().modifyAddress(oldAddresses.get(index), testData);
        var newAddresses = app.hbm().getAddressList();
        var expectedList = new ArrayList<>(oldAddresses);
        expectedList.set(index, testData.withId(oldAddresses.get(index).id()));
        Comparator<AddressData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newAddresses.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newAddresses, expectedList);
    }

    @Test
    public void AddAddressInGroups() {
        if (app.hbm().getAddressCount() == 0){
            app.hbm().createAddress(new AddressData("", "user1", "user1", "123"));
        }
        if (app.hbm().getGroupCount() == 0){
            app.hbm().createGroup(new GroupData("", "group1", "group1", "group1"));
        }
//
        var listAddresses = app.hbm().getAddressList();
        int counter = 0;
        for (AddressData address : listAddresses) {
            int indexA = Integer.parseInt(address.id());
            if (counter == listAddresses.size() - 1) {
                app.hbm().createGroup(new GroupData("", "group1", "group1", "group1"));
            }
            var listGroups = app.hbm().getGroupList();
            for (GroupData group : listGroups) {
                int indexG = Integer.parseInt(group.id());
                if (!app.hbm().isAddressInGroup(indexA,indexG)){
                    var oldRelated = app.hbm().getAddressesInGroup(listGroups.get(listGroups.indexOf(group)));
                    app.address().addInGroup(address,group);
                    var newRelated = app.hbm().getAddressesInGroup(listGroups.get(listGroups.indexOf(group)));
                    Comparator<AddressData> compareById = (o1, o2) -> {
                       return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
                    };
                    newRelated.sort(compareById);
                    var expectedList = new ArrayList<>(oldRelated);
                    expectedList.add(listAddresses.get(listAddresses.indexOf(address)));
                    expectedList.sort(compareById);
                    Assertions.assertEquals(newRelated, expectedList);
                    break;
                }
            }
            counter ++;
        }
    }

    @Test
    public void DelAddressInGroups() {
        if (app.hbm().getAddressCount() == 0){
            app.hbm().createAddress(new AddressData("", "user1", "user1", "123"));
        }
        if (app.hbm().getGroupCount() == 0){
            app.hbm().createGroup(new GroupData("", "group1", "group1", "group1"));
        }

        var listAddresses = app.hbm().getAddressList();
        var rndA = new Random();
        var indexA = rndA.nextInt(listAddresses.size());

        var listGroups = app.hbm().getGroupList();
        var rndG = new Random();
        var indexG = rndG.nextInt(listGroups.size());

        if (app.hbm().getAddressInGroupsCount() == 0){
            app.address().addInGroup(listAddresses.get(indexA),listGroups.get(indexG));
        }

        var oldRelated = app.hbm().getAddressesInGroup(listGroups.get(indexG));
        app.address().delInGroup(listAddresses.get(indexA),listGroups.get(indexG));

        var newRelated = app.hbm().getAddressesInGroup(listGroups.get(indexG));
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.remove(listAddresses.get(indexA));
        Assertions.assertEquals(newRelated, expectedList);
    }
}

