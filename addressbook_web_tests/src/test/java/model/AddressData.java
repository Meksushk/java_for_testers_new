package model;

public record AddressData(String id, String firstname, String lastname, String mobile, String home, String work,
                          String secondary) {

    public AddressData(){
        this("", "","","", "", "", "");
    }

    public AddressData withId(String id) {
        return new AddressData(id, firstname,this.lastname,this.mobile, this.home, this.work, this.secondary);
    }

    public AddressData withFirstName(String first_name) {
        return new AddressData(this.id, first_name,this.lastname,this.mobile, this.home, this.work, this.secondary);
    }

    public AddressData withLastName(String last_name) {
        return new AddressData(this.id, this.firstname, last_name,this.mobile, this.home, this.work, this.secondary);
    }

//    public AddressData withPhoto(String photo) {
//        return new AddressData(this.id, this.firstname,this.lastname,photo,mobile);
//    }

    public AddressData withMobile(String mobile) {
        return new AddressData(this.id, this.firstname,this.lastname,mobile, this.home, this.work, this.secondary);
    }

    public AddressData withHome(String home) {
        return new AddressData(this.id, this.firstname, this.lastname, this.mobile, home, this.work, this.secondary);
    }

    public AddressData withWork(String work) {
        return new AddressData(this.id, this.firstname, this.lastname, this.mobile, this.home, work, this.secondary);
    }

    public AddressData withSecondary(String secondary) {
        return new AddressData(this.id, this.firstname, this.lastname, this.mobile, this.home, this.work, secondary);
    }

}
