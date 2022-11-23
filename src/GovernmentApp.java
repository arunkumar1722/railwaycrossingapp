

import model.RailwayCrossing;
import model.User;
import dao.UserDAO;
import dao.RailwayCrossingDAO;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GovernmentApp {
    Scanner scanner;
    UserDAO dao = new UserDAO();
    RailwayCrossingDAO rcDao = new RailwayCrossingDAO();

    private static GovernmentApp app;

    public static GovernmentApp getInstance() {
        if (app == null) {
            app = new GovernmentApp();
        }

        return app;
    }

    private GovernmentApp() {
        this.scanner = new Scanner(System.in);
    }

    void listCrossings() {
        List<RailwayCrossing> crossings = this.rcDao.query();

        for (Iterator<RailwayCrossing> iter = crossings.iterator(); iter.hasNext(); ) {
            RailwayCrossing crossing = iter.next();
            System.out.println(crossing);
            System.out.println("-------------------------------");
        }

    }

    void addCrossing() {
        this.scanner.nextLine();
        User user = new User();
        RailwayCrossing crossing = new RailwayCrossing();

        System.out.println("Enter Person InCharge Details");
        System.out.println("Enter Name: ");
        user.setName(this.scanner.nextLine());
        System.out.println("Enter Email: ");
        user.setEmail(this.scanner.nextLine());
        System.out.println("Enter Password: ");
        user.setPassword(this.scanner.nextLine());
        user.setUserType(3);

        if(this.dao.insert(user)>0){
            System.out.println("User Added");
        }else{
            System.out.println("Something went wrong.. user not added");
        }

        System.out.println("Enter Railway Crossing Details");
        System.out.println("Enter Crossing Name: ");
        crossing.setName(this.scanner.nextLine());
        System.out.println("Enter Crossing Address: ");
        crossing.setAddress(this.scanner.nextLine());
        System.out.println("Enter Crossing Schedule: ");
        String scheduleKey = this.scanner.nextLine();
        String scheduleValue = this.scanner.nextLine();
        crossing.getSchedules().put(scheduleKey, scheduleValue);
        crossing.setPersonInCharge(user);

        if (this.rcDao.insert(crossing) > 0) {
            System.out.println(crossing.getName() + " Added Successfully...");
        } else {
            System.err.println("Something Went Wrong. Please Try Again");
        }

    }

    void deleteCrossing() {
        this.scanner.nextLine();
        RailwayCrossing crossing = new RailwayCrossing();
        System.out.println("Enter Railway Crossing Name: ");
        crossing.setName(this.scanner.nextLine());
        RailwayCrossing retrievedCrossing = this.rcDao.queryOne(crossing);
        if (!retrievedCrossing.getName().isEmpty() && this.rcDao.delete(crossing) > 0) {
            System.out.println(retrievedCrossing.getName() + " Deleted Successfully...");
        } else {
            System.err.println("Railway Crossing not found");
        }
    }

    void searchCrossing() {
        this.scanner.nextLine();
        RailwayCrossing crossing = new RailwayCrossing();
        System.out.println("Enter Railway Crossing Name: ");
        crossing.setName(this.scanner.nextLine());
        RailwayCrossing retrievedCrossing = this.rcDao.queryOne(crossing);
        if (!retrievedCrossing.getName().isEmpty()) {
            System.out.println(retrievedCrossing);
        } else {
            System.err.println("Railway Crossing not found");
        }
    }

    void updateStatus() {
        this.scanner.nextLine();
        RailwayCrossing crossing = new RailwayCrossing();
        System.out.println("Enter Railway Crossing Name: ");
        crossing.setName(this.scanner.nextLine());
        RailwayCrossing retrievedCrossing = this.rcDao.queryOne(crossing);
        if (!retrievedCrossing.getName().isEmpty() && retrievedCrossing.flipStatus() != null && this.rcDao.update(retrievedCrossing) > 0) {
            System.out.println("Switched Railway Crossing Status to " + retrievedCrossing.getStatus());
            System.out.println(retrievedCrossing);
        } else {
            System.err.println("Railway Crossing not found");
        }
    }

    void login() {
        User user = new User();
        System.out.println("Enter Email: ");
        user.setEmail(this.scanner.nextLine());
        System.out.println("Enter Password: ");
        user.setPassword(this.scanner.nextLine());

        User loggedUser = this.dao.queryOne(user);
        if (!loggedUser.getName().isEmpty()) {
            System.out.println(user.getName() + ", You have Logged In Successfully..");
            System.out.println("Navigating to the Government Railway Crossing Application");
            this.home();
        } else {
            System.err.println("Something Went Wrong. Please Try Again");
        }

    }

    void home() {
        int choice;
        do {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Welcome to Government Railway Crossing Home");
            System.out.println("We have " + this.rcDao.count() + " Crossings in the DataBase");
            System.out.println("1: List Railway Crossings");
            System.out.println("2: Add Railway Crossing");
            System.out.println("3: Delete Railway Crossing");
            System.out.println("4: Search Railway Crossings");
            System.out.println("5: Update Status of Railway Crossing");
            System.out.println("6: Close Government Application");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            choice = this.scanner.nextInt();
            switch(choice) {
                case 1:
                    this.listCrossings();
                    break;
                case 2:
                    this.addCrossing();
                    break;
                case 3:
                    this.deleteCrossing();
                    break;
                case 4:
                    this.searchCrossing();
                    break;
                case 5:
                    this.updateStatus();
                    break;
                case 6:
                    System.out.println("Thank You for using Railway Crossing App");
                    break;
                default:
                    System.err.println("Invalid Choice");
            }
        } while(choice != 8);

    }

    void startGovernmentApp() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Welcome Admin User");
        System.out.println("Proceed to Login");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        this.login();
    }
}
