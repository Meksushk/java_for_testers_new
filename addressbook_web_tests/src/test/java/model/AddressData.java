package model;

public record AddressData(String id, String firstname, String lastname, String address, String mobile, String home, String work,
                          String secondary, String email, String email2, String email3) {

    public AddressData(){
        this("", "","","", "", "", "","","","","");
    }

    public AddressData withId(String id) {
        return new AddressData(id, firstname,this.lastname,this.address, this.mobile, this.home, this.work, this.secondary, this.email, this.email2, this.email3);
    }

    public AddressData withFirstName(String first_name) {
        return new AddressData(this.id, first_name,this.lastname,this.address, this.mobile, this.home, this.work, this.secondary, this.email, this.email2, this.email3);
    }

    public AddressData withLastName(String last_name) {
        return new AddressData(this.id, this.firstname, last_name,this.address, this.mobile, this.home, this.work, this.secondary, this.email, this.email2, this.email3);
    }

//    public AddressData withPhoto(String photo) {
//        return new AddressData(this.id, this.firstname,this.lastname,photo,mobile);
//    }

    public AddressData withMobile(String mobile) {
        return new AddressData(this.id, this.firstname,this.lastname,this.address, mobile, this.home, this.work, this.secondary, this.email, this.email2, this.email3);
    }

    public AddressData withHome(String home) {
        return new AddressData(this.id, this.firstname, this.lastname, this.address, this.mobile, home, this.work, this.secondary, this.email, this.email2, this.email3);
    }

    public AddressData withWork(String work) {
        return new AddressData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.home, work, this.secondary, this.email, this.email2, this.email3);
    }

    public AddressData withSecondary(String secondary) {
        return new AddressData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.home, this.work, secondary, this.email, this.email2, this.email3);
    }

    public AddressData withEmail(String email) {
        return new AddressData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.home, this.work, this.secondary, email, this.email2, this.email3);
    }

    public AddressData withEmail2(String email2) {
        return new AddressData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.home, this.work, this.secondary, this.email, email2, this.email3);
    }

    public AddressData withEmail3(String email3) {
        return new AddressData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.home, this.work, this.secondary, this.email, this.email2, email3);
    }

}
