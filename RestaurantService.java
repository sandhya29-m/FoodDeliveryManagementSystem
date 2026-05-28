import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.PreparedStatement;
public class RestaurantService {
    public void viewRestaurants() {
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM Restaurants";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println(
                        rs.getInt("restaurant_id") + " | " +
                                rs.getString("restaurant_name") + " | " +
                                rs.getString("location") + " | " +
                                rs.getDouble("rating")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void viewMenuByRestaurant() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter restaurant id:");
            int id = sc.nextInt();
            Connection con = DBConnection.getConnection();
            String query = "SELECT * FROM MenuItems WHERE restaurant_id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                System.out.println(
                        rs.getString("dish_name") + " | " +
                                rs.getDouble("price") + " | " +
                                rs.getString("category")
                );
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}