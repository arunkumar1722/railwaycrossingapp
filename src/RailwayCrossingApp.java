import java.util.Scanner;

public class RailwayCrossingApp {
    public RailwayCrossingApp() {
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Welcome to Railway Crossing App");
            System.out.println("1: End User App");
            System.out.println("2: Government App");
            System.out.println("3: Close App");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            switch(choice) {
                case 1:
                    PublicApp.getInstance().startPublicApp();
                    break;
                case 2:
                    GovernmentApp.getInstance().startGovernmentApp();
                    break;
                case 3:
                    System.out.println("Thank you for using the application");
                    break;
                default:
                    System.err.println("Invalid Choice");
            }
        } while(choice != 3);

    }
}
