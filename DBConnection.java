import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
    private static final String URL="jdbc:mysql://localhost:3306/food_";
    private static final String USER="sandhya";
    private static final String PASSWORD="......";
    public static Connection getConnection(){
        try{
            Connection con=DriverManager.getConnection(URL,USER,PASSWORD);
            return con;
        }
        catch(SQLException e){
            System.out.println("Failed");
            e.printStackTrace();
            return null;
        }
    }
}
