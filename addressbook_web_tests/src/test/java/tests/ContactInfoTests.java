package tests;

import model.AddressData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
        if (app.hbm().getAddressCount() == 0){
            app.hbm().createAddress(new AddressData("", "user1", "user1", "123", "321", "456", "654","768","","",""));
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

    @Test
    void testEmails() {
        if (app.hbm().getAddressCount() == 0){
            app.hbm().createAddress(new AddressData("", "user1", "user1", "123", "", "", "","","email","email2","email3"));
        }
        var contacts = app.address().getList();
        var rnd = new Random();
        var index = rnd.nextInt(contacts.size());
        var contact = contacts.get(index);
        var emails = app.address().getEmails(contact);
        var raw = app.address().emailsFromEditForm(contact);
        var expected = Stream.of(raw.get("email"), raw.get("email2"), raw.get("email3"))
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, emails);
    }

    @Test
    void testAddress() {
        if (app.hbm().getAddressCount() == 0) {
            app.hbm().createAddress(new AddressData("", "user1", "user1", "address123", "", "", "","","","",""));
        }
        var contacts = app.address().getList();
        var rnd = new Random();
        var index = rnd.nextInt(contacts.size());
        var contact = contacts.get(index);
        var address = app.address().getAddress(contact);
        var expected = app.address().addressFromEditForm(contact);
        Assertions.assertEquals(expected, address);
    }
}
