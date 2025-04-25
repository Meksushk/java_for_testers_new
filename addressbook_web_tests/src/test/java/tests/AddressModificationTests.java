package tests;

import model.AddressData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class AddressModificationTests extends TestBase {

    @Test
    void canModifyAddress() {
        if (app.address().getCount() == 0){
            app.address().createAddress(new AddressData("", "user1", "user1", "src/test/resources/images/png-ikonka.png", "user1"));
        }
        var oldAddresses = app.address().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldAddresses.size());
        var testData = new AddressData().withLastName("new name").withPhoto("src/test/resources/images/png-ikonka.png");
        app.address().modifyAddress(oldAddresses.get(index), testData);
        var newAddresses = app.address().getList();
        var expectedList = new ArrayList<>(oldAddresses);
        expectedList.set(index, testData.withId(oldAddresses.get(index).id()).withPhoto(""));
        Comparator<AddressData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newAddresses.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newAddresses, expectedList);
    }
}

