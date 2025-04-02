package tests;

import model.AddressData;
import org.junit.jupiter.api.Test;

public class AddressRemovalTests extends TestBase {

    @Test
    public void canRemoveAdd() {
        if (!app.address().isAddressPresent()){
            app.address().createAddress(new AddressData("user", "user", "user"));
        }
        app.address().removeAddress();
    }
}
