package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "addressbook")
public class AddressRecord {

    @Id
    public int id;

    public String firstname;

    public String lastname;

    public String mobile;

    public AddressRecord(){
    }

    public AddressRecord(int id, String firstname, String lastname, String mobile){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
    }
}
