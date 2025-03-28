package model;

public record AddData(String first_name, String last_name, String mobile) {

    public AddData(){
        this("","","");
    }

    public AddData withFirstName(String first_name) {
        return new AddData(first_name,this.last_name,this.mobile);
    }

    public AddData withLast_name(String last_name) {
        return new AddData(this.first_name, last_name, mobile);
    }

    public AddData withMobile(String mobile) {
        return new AddData(this.first_name,this.last_name,mobile);
    }

}
