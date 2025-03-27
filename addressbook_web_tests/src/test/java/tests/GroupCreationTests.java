package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void CanCreateNewGroup() {
        app.openGroupsPage();
        app.createGroup(new GroupData("group1", "group1", "group1"));
    }

    @Test
    public void CanCreateEmptyGroup() {
        app.openGroupsPage();
        app.createGroup(new GroupData());
    }

    @Test
    public void CanCreateGroupWithNameOnly() {
        app.openGroupsPage();
        app.createGroup(new GroupData().withName("some name"));
    }
}
