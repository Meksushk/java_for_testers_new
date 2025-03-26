import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void CanCreateNewGroup() {
        openGroupsPage();
        createGroup("group1");
    }

    @Test
    public void CanCreateEmptyGroup() {
        openGroupsPage();
        createGroup("");
    }
}
