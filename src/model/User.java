//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package model;

import java.io.Serializable;

public class User implements Serializable {
    String name;
    String email;
    String password;
    int userType;

    public User() {
        this.name = "";
        this.email = "";
        this.password = "";
        this.userType = 0;
    }

    public User(String name, String email, String password, int userType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return this.userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String toString() {
        return "User [name=" + this.name + ", email=" + this.email + ", password=" + this.password + ", userType=" + this.userType + "]";
    }

    public boolean validate() {
        return !this.email.isEmpty() && !this.password.isEmpty();
    }
}
