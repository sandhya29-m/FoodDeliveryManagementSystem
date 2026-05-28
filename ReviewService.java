import java.util.Scanner;
import java.sql.*;
public class ReviewService {
    public void addReview(){
        Connection con=null;
        try {

            Scanner sc = new Scanner(System.in);
            con=DBConnection.getConnection();
            con.setAutoCommit(false);
            int uid = sc.nextInt();
            int rid = sc.nextInt();
            double rating = sc.nextDouble();
            sc.nextLine();
            String comments = sc.nextLine();
            String query = "INSERT INTO reviews(user_id,restaurant_id,rating,comments) " +
                    "VALUES" +
                    " (?,?,?,?)";
            PreparedStatement pstmt=con.prepareStatement(query);
            pstmt.setInt(1,uid);
            pstmt.setInt(2,rid);
            pstmt.setDouble(3,rating);
            pstmt.setString(4,comments);
            pstmt.executeUpdate();
            con.commit();
            System.out.println("Thanks for the review!");
        }
        catch (Exception e) {
            try{
                if(con!=null){
                    con.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();;
            }
        }
    }
}
