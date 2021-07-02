package persistence;

import model.RentalHouse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RentalsPersistence extends Persistence{
    public List<RentalHouse> rentalsList(){
        List<RentalHouse> rentals = new ArrayList<>();
        try{
            String q = "select * from rentalhouse";
            Statement st = this.getConnection().createStatement();
            ResultSet rs = st.executeQuery(q);
            while(rs.next()){
                String city = rs.getString("city");
                String country = rs.getString("country");
                int price = rs.getInt("price");
                String housing_type = rs.getString("housing_type");
                int bedrooms = rs.getInt("bedrooms");
                int usable_surface = rs.getInt("usable_surface");
                int garden_surface = rs.getInt("garden_surface");
                int floors = rs.getInt("floors");
                String house_name = rs.getString("house_name");

                RentalHouse rentalHouse = new RentalHouse(city,country,price,housing_type,bedrooms,usable_surface,garden_surface,floors,house_name);
                rentals.add(rentalHouse);
            }
            st.close();

        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return rentals;
    }

    public RentalHouse findByPrice(int no){
        RentalHouse rentalHouse = null;
        try {
            String q = "select * from rentalhouse where price=?";
            PreparedStatement statement = this.getConnection().prepareStatement(q);
            statement.setInt(1, no);
            ResultSet rs = statement.executeQuery();
            rs.next();
            String city = rs.getString("city");
            String country = rs.getString("country");
            int price = rs.getInt("price");
            String housing_type = rs.getString("housing_type");
            int bedrooms = rs.getInt("bedrooms");
            int usable_surface = rs.getInt("usable_surface");
            int garden_surface = rs.getInt("garden_surface");
            int floors = rs.getInt("floors");
            String house_name = rs.getString("house_name");

            rentalHouse = new RentalHouse(city, country, price, housing_type, bedrooms, usable_surface, garden_surface, floors, house_name);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentalHouse;
    }

    public void addRental(RentalHouse r){
        try {
            String q = "INSERT INTO rentalhouse VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = null;
            statement = this.getConnection().prepareStatement(q);

            statement.setString(1, r.getCity());
            statement.setString(2, r.getCountry());
            statement.setInt(3, r.getPrice());
            statement.setString(4, r.getHousing_type());
            statement.setInt(5, r.getBedrooms());
            statement.setInt(6, r.getUsable_surface());
            statement.setInt(7, r.getGarden_surface());
            statement.setInt(8, r.getFloors());
            statement.setString(9, r.getHouse_name());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println(r.getHouse_name() + " was updated\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateRental(RentalHouse oldRentalHouse, RentalHouse newRentalHouse) {
        try {
            String q = "UPDATE rentalhouse SET city=?, country=?, price=?, housing_type=?, bedrooms=?, usable_surface=?, garden_surface=?, floors=?, house_name=?  WHERE house_name=?";
            PreparedStatement statement = this.getConnection().prepareStatement(q);

            statement.setString(1, newRentalHouse.getCity());
            statement.setString(2, newRentalHouse.getCountry());
            statement.setInt(3, newRentalHouse.getPrice());
            statement.setString(4, newRentalHouse.getHousing_type());
            statement.setInt(5, newRentalHouse.getBedrooms());
            statement.setInt(6, newRentalHouse.getUsable_surface());
            statement.setInt(7, newRentalHouse.getGarden_surface());
            statement.setInt(8, newRentalHouse.getFloors());
            statement.setString(9, newRentalHouse.getHouse_name());
            statement.setString(10, oldRentalHouse.getHouse_name());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                //System.out.println(oldRentalHouse.getHouse_name() + " was updated\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteRental(RentalHouse e) {
        try {
            String q = "delete from rentalhouse where house_name=?";
            PreparedStatement statement = this.getConnection().prepareStatement(q);
            statement.setString(1, e.getHouse_name());

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println(e.getHouse_name() + " deleted from employee table\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
