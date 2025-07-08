package tests;

import model.AddressData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;


public class AddressRemovalTests extends TestBase {

    @Test
    public void canRemoveAdd() {
        if (app.hbm().getAddressCount() == 0){
            app.hbm().createAddress(new AddressData("", "user1", "user1", "123", "", "", "","","","",""));
        }
        var oldAddresses = app.hbm().getAddressList();
        var rnd = new Random();
        var index = rnd.nextInt(oldAddresses.size());
        app.address().removeAddress(oldAddresses.get(index));
        var newAddresses = app.hbm().getAddressList();
        var expectedList = new ArrayList<>(oldAddresses);
        expectedList.remove(index);
        Assertions.assertEquals(newAddresses, expectedList);
    }
}
