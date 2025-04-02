package model;

public record AddressData(String first_name, String last_name, String mobile) {

    public AddressData(){
        this("","","");
    }

    public AddressData withFirstName(String first_name) {
        return new AddressData(first_name,this.last_name,this.mobile);
    }

    public AddressData withLast_name(String last_name) {
        return new AddressData(this.first_name, last_name, this.mobile);
    }

    public AddressData withMobile(String mobile) {
        return new AddressData(this.first_name,this.last_name,mobile);
    }

}
