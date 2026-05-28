import java.util.Scanner;
import java.sql.Connection;
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        RestaurantService restaurantService = new RestaurantService();
        OrderService or=new OrderService();
        ReviewService rs=new ReviewService();
        int choice=0;
        while(choice!=6){
            System.out.println("1.View Restaurants");
            System.out.println("2.View Menu By Restaurant");
            System.out.println("3.Place order");
            System.out.println("4.View Customer Orders");
            System.out.println("5.Add Review");
            System.out.println("6.Exit");
            choice=sc.nextInt();
            switch (choice) {
                case 1:
                    restaurantService.viewRestaurants();
                    break;
                case 2:
                    restaurantService.viewMenuByRestaurant();
                    break;
                case 3:
                    or.placeOrder();
                    break;
                case 4:
                    or.viewCustomerOrder();
                    break;
                case 5:
                    rs.addReview();
                    break;
                case 6:
                    System.out.println("Thank you for using Food Delivery App!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        }
    }
}