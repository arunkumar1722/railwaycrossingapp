
import controller.RailwayCrossingController;
import controller.UserController;
import model.RailwayCrossing;
import model.User;
import dao.UserDAO;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;

public class PublicApp {
    UserController controller = UserController.getInstance();
    RailwayCrossingController railController = RailwayCrossingController.getInstance();
    Scanner scanner;
    UserDAO dao;

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
        if (this.controller.registerUser(user) && dao.insert(user) > 0) {
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

        User loggedUser = dao.queryOne(user);
        if (this.controller.loginUser(user) && !loggedUser.getName().isEmpty()) {
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

    void sortCrossings() {
        Map<String, ?> crossings = this.railController.fetchCrossings();
        Map<String, ?> sortedCrossings = new TreeMap<>(crossings);
        Iterator var3 = sortedCrossings.keySet().iterator();

        while(var3.hasNext()) {
            String key = (String)var3.next();
            System.out.println(sortedCrossings.get(key));
            System.out.println("-------------------------------");
        }

    }

    void searchCrossing() {
        this.scanner.nextLine();
        System.out.println("Enter Railway Crossing Name: ");
        String crossingName = this.scanner.nextLine();
        RailwayCrossing retrievedCrossing = this.railController.fetchCrossing(crossingName);
        if (retrievedCrossing!=null) {
            System.out.println(retrievedCrossing);
        } else {
            System.err.println("Railway Crossing not found");
        }
    }

    void displayStatus() {
        this.scanner.nextLine();
        System.out.println("Enter Railway Crossing Name: ");
        String crossingName = this.scanner.nextLine();
        RailwayCrossing retrievedCrossing = this.railController.fetchCrossing(crossingName);
        if (retrievedCrossing!=null) {
            System.out.println("Railway Crossing Status: " + retrievedCrossing.getStatus());
        } else {
            System.err.println("Railway Crossing not found");
        }
    }

    void home() {
        int choice;
        do {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Welcome to Railway Crossing Home");
            System.out.println("1: List Railway Crossings");
            System.out.println("2: Display status of Railway Crossing");
            System.out.println("3: Search Railway Crossings");
            System.out.println("4: Sort Railway Crossings by Schedule");
            System.out.println("5: Close Public Application");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            choice = this.scanner.nextInt();
            switch(choice) {
                case 1:
                    this.listCrossings();
                    break;
                case 2:
                    this.displayStatus();
                    break;
                case 3:
                    this.searchCrossing();
                    break;
                case 4:
                    this.sortCrossings();
                    break;
                case 5:
                    System.out.println("Thank You for using Railway Crossing App");
                    break;
                default:
                    System.err.println("Invalid Choice");
            }
        } while(choice != 5);

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
