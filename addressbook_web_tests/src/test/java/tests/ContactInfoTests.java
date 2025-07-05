package tests;

import model.AddressData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
        if (app.hbm().getAddressCount() == 0){
            app.hbm().createAddress(new AddressData("", "user1", "user1", "123", "", "", ""));
        }
        var contacts = app.hbm().getAddressList();
        var expected = contacts.stream().collect(Collectors.toMap(AddressData::id, contact ->
            Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                    .filter(s -> s != null && ! "".equals(s))
                    .collect(Collectors.joining("\n"))
        ));
        var phones = app.address().getPhones();
        Assertions.assertEquals(expected, phones);
    }
}
