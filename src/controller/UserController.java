//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package controller;

import db.DB;
import model.User;

public class UserController {
    private static UserController controller;
    DB db = DB.getInstance();

    private UserController() {
    }

    public static UserController getInstance() {
        if (controller == null) {
            controller = new UserController();
        }

        return controller;
    }

    public boolean registerUser(User user) {
        if (user.validate()) {
            return this.db.set(user);
        } else {
            System.err.println("Email and Password should be non empty");
            return false;
        }
    }

    public boolean loginUser(User user) {
        if (user.validate()) {
            User retrievedUser = (User)this.db.retrieve(user.getEmail());
            if (retrievedUser != null && retrievedUser.getUserType() == 1) {
                user.setName(retrievedUser.getName());
                if (retrievedUser.getEmail().equalsIgnoreCase(user.getEmail()) && retrievedUser.getPassword().equals(user.getPassword())) {
                    return true;
                }

                return false;
            }
        }

        return false;
    }

    public int getUserCount() {
        return this.db.getUserCount();
    }
}
