package restaurantObjects;

public class User implements Serializable {

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String address;
    private String city;
    private String zipCode;
    private String streetNumber;
    private static final long serialVersionUID = 5177222050535318633L;

    public User(String userName, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName =  userName;
    }

    //public setters
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String passWord) {this.password = password;}
    public void setCity(String city) {this.city = city;}
    public void setzipCode(String zipCode) {this.zipCode = zipCode;}
    public void setStreetNumber(String streetNumber) {this.streetNumber = streetNumber;}

    public void setAddress(String city, String zipcode, String streetNumber) {
        this.address = city + "-" + zipcode + "-" + streetNumber;
    }

    //public getters
    public String getFirstName() {return this.firstName ;}
    public String getLastName() {return this.lastName;}
    public String getEmail() {return this.email;}
    public String getPassword() {return this.password;}
    public String getAddress() {return this.address;}
    public String getUserName() {return this.userName;}

}
