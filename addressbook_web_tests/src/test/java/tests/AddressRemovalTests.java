package tests;

import model.AddressData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;


public class AddressRemovalTests extends TestBase {

    @Test
    public void canRemoveAdd() {
        if (!app.address().isAddressPresent()){
            app.address().createAddress(new AddressData("", "user", "user", "user"));
        }
        var oldAddresses = app.address().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldAddresses.size());
        app.address().removeAddress(oldAddresses.get(index));
        var newAddresses = app.address().getList();
        var expectedList = new ArrayList<>(oldAddresses);
        expectedList.remove(index);
        Assertions.assertEquals(newAddresses, expectedList);
    }
}
