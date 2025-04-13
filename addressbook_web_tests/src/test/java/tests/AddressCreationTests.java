package tests;

import model.AddressData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AddressCreationTests extends TestBase {

    public static List<AddressData> addressProvider() {
        var result = new ArrayList<AddressData>();
        for (var first_name : List.of("","user")){
            for (var last_name : List.of("","user")){
                for (var mobile : List.of("","123")){
                    result.add(new AddressData("", first_name, last_name, mobile));
                }
            }
        }
        for (int i = 0; i < 5; i++){
            result.add(new AddressData("", randomString(i * 10), randomString(i * 10), randomString(i * 10)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("addressProvider")
    public void CanCreateMultipleAddress(AddressData address) {
        var oldAddresses = app.address().getList();
        app.address().createAddress(address);
        var newAddresses = app.address().getList();
        Comparator<AddressData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newAddresses.sort(compareById);
        var expectedList = new ArrayList<>(oldAddresses);
        expectedList.add(address.withId(newAddresses.get(newAddresses.size() - 1).id()).withMobile(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newAddresses, expectedList);
    }

    public static List<AddressData> negativeAddressProvider() {
        var result = new ArrayList<AddressData>((List.of(new AddressData("", "user'","",""))));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeAddressProvider")
    public void CanNotCreateAddress(AddressData address) {
        var oldAddresses = app.address().getList();
        app.address().createAddress(address);
        var newAddresses = app.address().getList();
        Assertions.assertEquals(newAddresses, oldAddresses);
    }
}
