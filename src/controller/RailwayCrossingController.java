//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package controller;

import db.DB;
import model.RailwayCrossing;
import model.User;
import java.util.Map;

public class RailwayCrossingController {
    private static RailwayCrossingController controller;
    private DB db = DB.getInstance();

    private RailwayCrossingController() {
    }

    public static RailwayCrossingController getInstance() {
        if (controller == null) {
            controller = new RailwayCrossingController();
        }

        return controller;
    }

    public boolean loginUser(User user) {
        if (user.validate()) {
            User retrievedUser = (User)this.db.retrieve(user.getEmail());
            if (retrievedUser != null && retrievedUser.getUserType() == 2) {
                user.setName(retrievedUser.getName());
                if (retrievedUser.getEmail().equalsIgnoreCase(user.getEmail()) && retrievedUser.getPassword().equals(user.getPassword())) {
                    return true;
                }

                return false;
            }
        }

        return false;
    }

    public boolean addOrUpdateCrossing(RailwayCrossing crossing) {
        return this.db.set(crossing);
    }

    public boolean deleteCrossing(RailwayCrossing crossing) {
        return this.db.delete(crossing);
    }

    public Map<String, ?> fetchCrossings() {
        return this.db.retrieve(new RailwayCrossing());
    }

    public int getCrossingsCount() {
        return this.db.getCrossingsCount();
    }

    public void exportData() {
        this.db.exportData();
    }

    public void importData() {
        this.db.importData();
    }
}
