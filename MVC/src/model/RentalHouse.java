package model;

public class RentalHouse {

    private String city;
    private String country;
    private String price;
    private String housing_type;
    private String bedrooms;
    private String usable_surface;
    private String garden_surface;
    private String floors;
    private String house_name;

    public RentalHouse() {
    }

    public RentalHouse(String city, String country, String price, String housing_type, String bedrooms, String usable_surface, String garden_surface, String floors, String house_name) {
        this.city = city;
        this.country = country;
        this.price = price;
        this.housing_type = housing_type;
        this.bedrooms = bedrooms;
        this.usable_surface = usable_surface;
        this.garden_surface = garden_surface;
        this.floors = floors;
        this.house_name = house_name;
    }

    @Override
    public String toString() {
        return "RentalHouse{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", price='" + price + '\'' +
                ", housing_type='" + housing_type + '\'' +
                ", bedrooms='" + bedrooms + '\'' +
                ", usable_surface='" + usable_surface + '\'' +
                ", garden_surface='" + garden_surface + '\'' +
                ", floors='" + floors + '\'' +
                ", house_name='" + house_name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof RentalHouse)) {
            return false;
        }
        RentalHouse rentalHouse = (RentalHouse) object;
        return this.getHouse_name().equals(rentalHouse.getHouse_name()) && this.getHousing_type().equals(rentalHouse.getHousing_type())
                && this.getCity().equals(rentalHouse.getCity());
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getHousing_type() {
        return housing_type;
    }

    public void setHousing_type(String housing_type) {
        this.housing_type = housing_type;
    }

    public String getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(String bedrooms) {
        this.bedrooms = bedrooms;
    }

    public String getUsable_surface() {
        return usable_surface;
    }

    public void setUsable_surface(String usable_surface) {
        this.usable_surface = usable_surface;
    }

    public String getGarden_surface() {
        return garden_surface;
    }

    public void setGarden_surface(String garden_surface) {
        this.garden_surface = garden_surface;
    }

    public String getFloors() {
        return floors;
    }

    public void setFloors(String floors) {
        this.floors = floors;
    }

    public String getHouse_name() {
        return house_name;
    }

    public void setHouse_name(String house_name) {
        this.house_name = house_name;
    }

}
