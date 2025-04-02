package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (app.groups().getCount() == 0){
            app.groups().createGroup(new GroupData("group1", "group1", "group1"));
        }
        int groupCount = app.groups().getCount();
        app.groups().removeGroup();
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount - 1, newGroupCount);
    }

    @Test
    void canRemoveAllGroupsAtOnce() {
        if (app.groups().getCount() == 0){
            app.groups().createGroup(new GroupData("group1", "group1", "group1"));
        }
        app.groups().removeAllGroup();
        Assertions.assertEquals(0, app.groups().getCount());
    }
}
