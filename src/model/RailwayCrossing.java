//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package model;

import java.util.LinkedHashMap;

public class RailwayCrossing {
    String name;
    String address;
    boolean status;
    User personInCharge;
    LinkedHashMap<String, String> schedules;

    public RailwayCrossing() {
        this.name = "";
        this.address = "";
        this.status = true;
        this.schedules = new LinkedHashMap();
    }

    public RailwayCrossing(String name, String address, boolean status, User personInCharge, LinkedHashMap<String, String> schedules) {
        this.name = name;
        this.address = address;
        this.status = status;
        this.personInCharge = personInCharge;
        this.schedules = schedules;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return this.status ? "OPEN" : "CLOSE";
    }

    public void flipStatus() {
        this.status = !status;
    }

    public User getPersonInCharge() {
        return this.personInCharge;
    }

    public void setPersonInCharge(User personInCharge) {
        this.personInCharge = personInCharge;
    }

    public LinkedHashMap<String, String> getSchedules() {
        return this.schedules;
    }

    public void setSchedules(LinkedHashMap<String, String> schedules) {
        this.schedules = schedules;
    }

    public String toString() {
        String crossingStatus = this.status ? "OPEN" : "CLOSE";
        String railwayCrossingText = "~~~~~~~~~~~~~~~~~~~~~" + this.name + "~~~~~~~~~~~~~~~~~~~~\n" + "Crossing Name: " + this.name + "\n" + "Crossing Address: " + this.address + "\n" + "Crossing Status: " + crossingStatus + "\n" + "Crossing Schedule: " + this.schedules.toString() + "\n" + "Crossing Person InCharge: " + this.personInCharge.getName() + "\n" + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        return railwayCrossingText;
    }
}
