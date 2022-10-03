
import controller.RailwayCrossingController;
import controller.UserController;
import model.RailwayCrossing;
import model.User;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class PublicApp {
    UserController controller = UserController.getInstance();
    RailwayCrossingController railController = RailwayCrossingController.getInstance();
    Scanner scanner;
    private static PublicApp app;

    public static PublicApp getInstance() {
        if (app == null) {
            app = new PublicApp();
        }

        return app;
    }

    private PublicApp() {
        this.scanner = new Scanner(System.in);
    }

    void register() {
        User user = new User();
        this.scanner.nextLine();
        System.out.println("Enter Name: ");
        user.setName(this.scanner.nextLine());
        System.out.println("Enter Email: ");
        user.setEmail(this.scanner.nextLine());
        System.out.println("Enter Password: ");
        user.setPassword(this.scanner.nextLine());
        user.setUserType(1);
        if (this.controller.registerUser(user)) {
            System.out.println(user.getName() + ", You have Registered Successfully..");
            System.out.println("Navigating to the Railway Crossing Application");
            this.home();
        } else {
            System.err.println("Something Went Wrong. Please Try Again");
        }

    }

    void login() {
        User user = new User();
        this.scanner.nextLine();
        System.out.println("Enter Email: ");
        user.setEmail(this.scanner.nextLine());
        System.out.println("Enter Password: ");
        user.setPassword(this.scanner.nextLine());
        user.setUserType(1);
        if (this.controller.loginUser(user)) {
            System.out.println(user.getName() + ", You have Logged In Successfully..");
            System.out.println("Navigating to the Railway Crossing Application");
            this.home();
        } else {
            System.err.println("Something Went Wrong. Please Try Again");
        }

    }

    void listCrossings() {
        Map<String, ?> crossings = this.railController.fetchCrossings();
        Iterator var3 = crossings.keySet().iterator();

        while(var3.hasNext()) {
            String key = (String)var3.next();
            System.out.println(crossings.get(key));
            System.out.println("-------------------------------");
        }

    }

    void home() {
        int choice;
        do {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Welcome to Railway Crossing Home");
            System.out.println("1: List Railway Crossings");
            System.out.println("2: Search Railway Crossings");
            System.out.println("3: Sort Railway Crossings by Schedule");
            System.out.println("4: Close Public Application");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            choice = this.scanner.nextInt();
            switch(choice) {
                case 1:
                    this.listCrossings();
                case 2:
                case 3:
                    break;
                case 4:
                    System.out.println("Thank You for using Railway Crossing App");
                    break;
                default:
                    System.err.println("Invalid Choice");
            }
        } while(choice != 4);

    }

    void startPublicApp() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Welcome User");
        System.out.println("We have " + this.controller.getUserCount() + " Users in the DataBase");
        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        int choice = this.scanner.nextInt();
        switch(choice) {
            case 1:
                this.register();
                break;
            case 2:
                this.login();
                break;
            default:
                System.err.println("Invalid Choice");
        }

    }
}
