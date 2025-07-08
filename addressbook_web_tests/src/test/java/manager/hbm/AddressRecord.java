package manager.hbm;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

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

    public String mobile = "231";

    public String work = "321";

    public String fax = "231";

    public String email = "email@email.ru";

    public String email2 = "email2@email.eu";

    public String email3 = "email3@email.ru";

    public String homepage = "homepage";

    public String phone2;

    public AddressRecord(){
    }

    public AddressRecord(int id, String firstname, String lastname, String mobile){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
    }
}
