package model;

public class Client extends Person {
    private String firstName;
    private String lastName;
    private String email;
    private String status;
    private String houseName;
    private String city;
    private String housingType;
    private int price;

    public Client() {

    }

    public Client(String firstName, String lastName, String email, String houseName, String city, String housingType, int price) {
        super(firstName, lastName);
/*        this.firstName = firstName;
        this.lastName = lastName;*/
        this.email = email;
        //this.status = status;
        this.houseName = houseName;
        this.city = city;
        this.housingType = housingType;
        this.price = price;
    }

/*    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }*/

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                "lastName='" + lastName + '\'' +
                "email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", houseName='" + houseName + '\'' +
                ", city='" + city + '\'' +
                ", housingType='" + housingType + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Client)) {
            return false;
        }

        Client client = (Client) object;
        return this.getFirstName().equals(client.getFirstName()) && this.getLastName().equals(client.getLastName());
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHousingType() {
        return housingType;
    }

    public void setHousingType(String housingType) {
        this.housingType = housingType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
