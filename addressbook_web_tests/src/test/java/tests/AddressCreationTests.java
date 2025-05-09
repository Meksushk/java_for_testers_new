package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.AddressData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static common.CommonFunctions.randomFile;

public class AddressCreationTests extends TestBase {

    public static List<AddressData> addressProvider() throws IOException {
        var result = new ArrayList<AddressData>();
        //for (var first_name : List.of("","user")){
        //    for (var last_name : List.of("","user")){
        //        for (var mobile : List.of("","123")){
        //            result.add(new AddressData("", first_name, last_name,"src/test/resources/images/png-ikonka.png", mobile));
        //        }
        //    }
        //}

        //for (int i = 0; i < 5; i++){
         //   result.add(new AddressData("", CommonFunctions.randomString(i * 10), CommonFunctions.randomString(i * 10),randomFile("src/test/resources/images"), CommonFunctions.randomString(i * 10)));
        //}
        var mapper = new ObjectMapper();
        var value = mapper.readValue(new File("addresses.json"),  new TypeReference<List<AddressData>>(){});
        result.addAll(value);
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
        expectedList.add(address.withId(newAddresses.get(newAddresses.size() - 1).id()).withPhoto("").withMobile(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newAddresses, expectedList);
    }

    public static List<AddressData> negativeAddressProvider() {
        var result = new ArrayList<AddressData>((List.of(new AddressData("", "user'","","src/test/resources/images/png-ikonka.png",""))));
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

    @Test
    void CanCreateAddressInGroups() {
        var address = new AddressData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));
        if (app.hbm().getGroupCount() == 0){
            app.hbm().createGroup(new GroupData("", "group1", "group1", "group1"));
        }
        var group = app.hbm().getGroupList().get(0);

        var oldRelated = app.hbm().getAddressesInGroup(group);
        app.address().createAddress(address, group);
        var newRelated = app.hbm().getAddressesInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }
}
