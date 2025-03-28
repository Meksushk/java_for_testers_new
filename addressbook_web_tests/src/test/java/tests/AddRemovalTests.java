package tests;

import model.AddData;
import model.GroupData;
import org.junit.jupiter.api.Test;

public class AddRemovalTests extends TestBase {

    @Test
    public void canRemoveAdd() {
        if (!app.add().isAddPresent()){
            app.add().createAdd(new AddData("user", "user", "user"));
        }
        app.add().removeAdd();
    }
}
