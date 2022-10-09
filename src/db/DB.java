

package db;

import model.RailwayCrossing;
import model.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class DB implements DAO {
    private LinkedHashMap<String, User> users = new LinkedHashMap();
    private LinkedHashMap<String, RailwayCrossing> crossings = new LinkedHashMap();
    private static DB db;

    private DB() {
        this.populateAdminUsers();
    }

    public static DB getInstance() {
        if (db == null) {
            db = new DB();
        }

        return db;
    }

    void populateAdminUsers() {
        User user1 = new User("George", "george@example.com", "george123", 2);
        User user2 = new User("Harry", "harry@example.com", "harry123", 2);
        this.set(user1);
        this.set(user2);
    }

    public boolean set(Object object) {
        if (object instanceof User) {
            User user = (User)object;
            this.users.put(user.getEmail(), user);
            return true;
        } else {
            RailwayCrossing crossing = (RailwayCrossing)object;
            this.crossings.put(crossing.getName(), crossing);
            return true;
        }
    }

    public boolean delete(Object object) {
        if (object instanceof User) {
            User user = (User)object;
            this.users.remove(user.getEmail());
            return true;
        } else {
            RailwayCrossing crossing = (RailwayCrossing)object;
            this.crossings.remove(crossing.getName());
            return true;
        }
    }

    public Map<String, ?> retrieve(Object object) {
        return object instanceof User ? this.users : this.crossings;
    }

    public Object retrieve(String key) {
        if (this.users.containsKey(key)) {
            return this.users.get(key);
        } else {
            return this.crossings.containsKey(key) ? this.crossings.get(key) : null;
        }
    }

    public int getUserCount() {
        return this.users.size();
    }

    public int getCrossingsCount() {
        return this.crossings.size();
    }

    public void exportData() {
        try {
            File file = new File("/Users/arunkumarm/Documents/railwaycrossingapp/users-data");
            if (!file.exists()) {
                file.mkdir();
                System.out.println("Directory Created by the name of " + file.getName());
            }

            Iterator var3 = this.users.keySet().iterator();

            while(var3.hasNext()) {
                String key = (String)var3.next();
                File userFile = new File("/Users/arunkumarm/Documents/railwaycrossingapp/users-data/", key + ".txt");
                FileOutputStream stream = new FileOutputStream(userFile);
                ObjectOutputStream objectStream = new ObjectOutputStream(stream);
                objectStream.writeObject(this.users.get(key));
                System.out.println("User: " + key + " Exported...");
            }

            System.out.println("EXPORT FINISHED :)");
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    public void importData() {
        try {
            File file = new File("/Users/arunkumarm/Documents/railwaycrossingapp/users-data");
            String[] files = file.list();
            String[] var6 = files;
            int var5 = files.length;

            for(int var4 = 0; var4 < var5; ++var4) {
                String fileName = var6[var4];
                File userFile = new File("/Users/arunkumarm/Documents/railwaycrossingapp/users-data/", fileName);
                FileInputStream stream = new FileInputStream(userFile);
                ObjectInputStream objectStream = new ObjectInputStream(stream);
                User user = (User)objectStream.readObject();
                this.users.put(user.getEmail(), user);
            }

            System.out.println("IMPORT FINISHED :)");
        } catch (Exception var11) {
            var11.printStackTrace();
        }

    }
}
