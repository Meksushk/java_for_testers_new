package model;

public record AddressData(String id, String first_name, String last_name, String mobile) {

    public AddressData(){
        this("", "","","");
    }

    public AddressData withId(String id) {
        return new AddressData(id, first_name,this.last_name,this.mobile);
    }

    public AddressData withFirstName(String first_name) {
        return new AddressData(this.id, first_name,this.last_name,this.mobile);
    }

    public AddressData withLastName(String last_name) {
        return new AddressData(this.id, this.first_name, last_name, this.mobile);
    }

    public AddressData withMobile(String mobile) {
        return new AddressData(this.id, this.first_name,this.last_name,mobile);
    }

}
