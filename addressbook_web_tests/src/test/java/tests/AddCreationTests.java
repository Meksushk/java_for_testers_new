package tests;

import model.AddData;
import model.GroupData;
import org.junit.jupiter.api.Test;

public class AddCreationTests extends TestBase {

    @Test
    public void CanCreateNewAdd() {
        app.add().createAdd(new AddData("user", "user", "123"));
    }

    @Test
    public void CanCreateEmptyAdd() {
        app.add().createAdd(new AddData());
    }

    @Test
    public void CanCreateAddWithNameOnly() {
        app.add().createAdd(new AddData().withFirstName("unknown"));
    }
}
