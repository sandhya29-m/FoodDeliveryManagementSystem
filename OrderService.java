import java.sql.*;
import java.util.Scanner;
public class OrderService {
    public void placeOrder() {
        Connection con=null;
        try {
            con = DBConnection.getConnection();
            Scanner sc = new Scanner(System.in);
            con.setAutoCommit(false);
            System.out.println("Enter user id:");
            int uid = sc.nextInt();
            System.out.println("Enter dish id:");
            int did = sc.nextInt();
            System.out.println("Enter quantity:");
            int q = sc.nextInt();
            String query="SELECT price FROM MenuItems WHERE dish_id=?";
            PreparedStatement pstmt=con.prepareStatement(query);
            pstmt.setInt(1,did);
            ResultSet rs=pstmt.executeQuery();
            double price=0;
            if(rs.next()){
                price=rs.getDouble("price");
            }
            if(price==0){
                System.out.println("Invalid Dish ID!!");
                return;
            }
            double totalAmount=price*q;
            String insquery="INSERT INTO Orders(user_id,order_date,total_amount)" +
                    "VALUES" +
                    "(?,?,?)";
            PreparedStatement orderstmt=con.prepareStatement(insquery,Statement.RETURN_GENERATED_KEYS);

            orderstmt.setInt(1,uid);
            orderstmt.setDate(2,java.sql.Date.valueOf("2026-05-27"));
            orderstmt.setDouble(3,totalAmount);
            orderstmt.executeUpdate();
            ResultSet generatedKeys=orderstmt.getGeneratedKeys();
            if(generatedKeys.next()){
                int orderId=generatedKeys.getInt(1);

                String itemQuery =
                        "INSERT INTO OrderItems(order_id, dish_id, quantity, price) VALUES (?, ?, ?, ?)";

                PreparedStatement itemStmt = con.prepareStatement(itemQuery);

                itemStmt.setInt(1, orderId);
                itemStmt.setInt(2, did);
                itemStmt.setInt(3, q);
                itemStmt.setDouble(4, totalAmount);

                itemStmt.executeUpdate();

                con.commit();

                System.out.println("Order placed successfully!");
            }
        }
        catch(Exception e){
            try{
                if(con!=null){
                    con.rollback();
                }
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }
    public void viewCustomerOrder(){
        Connection con=null;
        try {
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter user id:");
            int uid=sc.nextInt();
            con = DBConnection.getConnection();
            String query = "SELECT Users.name,MenuItems.dish_name,OrderItems.quantity,Orders.status " +
                    "FROM OrderItems " +
                    "JOIN Orders ON Orders.order_id=OrderItems.order_id " +
                    "JOIN Users ON Users.user_id=Orders.user_id " +
                    "JOIN MenuItems ON MenuItems.dish_id=OrderItems.dish_id " +
                    "WHERE Users.user_id=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1,uid);

            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                System.out.println(
                        rs.getString("name")+" | "+rs.getString("dish_name")+" | "+ rs.getInt("quantity")+" | "+rs.getString("status")
                );
            }
        }

        catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
