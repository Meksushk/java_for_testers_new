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

    public String middlename = "middlename";

    public String lastname;

    public String nickname = "nickname";

    public String company = "company";

    public String title = "title";

    public String address = "address";

    public String home = "123";

    public String mobile;

    public String work = "321";

    public String fax = "231";

    public String email = "email@mail.ru";

    public String email2 = "email2@mail.ru";

    public String email3 = "email3@mail.ru";

    public String homepage = "homepage";

    public AddressRecord(){
    }

    public AddressRecord(int id, String firstname, String lastname, String mobile){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
    }
}
