package model;

public record AddressData(String id, String firstname, String lastname, String mobile) {

    public AddressData(){
        this("", "","","");
    }

    public AddressData withId(String id) {
        return new AddressData(id, firstname,this.lastname,this.mobile);
    }

    public AddressData withFirstName(String first_name) {
        return new AddressData(this.id, first_name,this.lastname,this.mobile);
    }

    public AddressData withLastName(String last_name) {
        return new AddressData(this.id, this.firstname, last_name,this.mobile);
    }

//    public AddressData withPhoto(String photo) {
//        return new AddressData(this.id, this.firstname,this.lastname,photo,mobile);
//    }

    public AddressData withMobile(String mobile) {
        return new AddressData(this.id, this.firstname,this.lastname,mobile);
    }

}
