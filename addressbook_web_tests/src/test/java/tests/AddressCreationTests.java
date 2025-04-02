package tests;

import model.AddressData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class AddressCreationTests extends TestBase {

    public static List<AddressData> addressProvider() {
        var result = new ArrayList<AddressData>();
        for (var first_name : List.of("","user")){
            for (var last_name : List.of("","user")){
                for (var mobile : List.of("","123")){
                    result.add(new AddressData(first_name, last_name, mobile));
                }
            }
        }
        for (int i = 0; i < 5; i++){
            result.add(new AddressData(randomString(i * 10), randomString(i * 10), randomString(i * 10)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("addressProvider")
    public void CanCreateMultipleAddress(AddressData address) {
        int addressCount = app.address().getCount();
        app.address().createAddress(address);
        int newAddressCount = app.address().getCount();
        Assertions.assertEquals(addressCount + 1, newAddressCount);
    }

    public static List<AddressData> negativeAddressProvider() {
        var result = new ArrayList<AddressData>((List.of(new AddressData("user'","",""))));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeAddressProvider")
    public void CanNotCreateAddress(AddressData address) {
        int addressCount = app.address().getCount();
        app.address().createAddress(address);
        int newAddressCount = app.address().getCount();
        Assertions.assertEquals(addressCount, newAddressCount);
    }
}
