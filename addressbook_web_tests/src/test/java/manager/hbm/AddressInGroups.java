package manager.hbm;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "address_in_groups")
public class AddressInGroups {

    @Id
    public int id;

    public int group_id;

    public Date created = new Date();
    public Date modified = new Date();
    public Date deprecated = new Date();

    public AddressInGroups(){
    }

    public AddressInGroups(int id, int group_id){
            this.id = id;
            this.group_id = group_id;
    }
}
