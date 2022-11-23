package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.RailwayCrossing;
import model.User;

public class UserDAO implements DAO<User>{

    DB db = DB.getDB();

    @Override
    public int insert(User object) {
        String sql = "INSERT INTO Users(name, email, password, userType) VALUES ('"+object.getName()+"', '"+object.getEmail()+"', '"+object.getPassword()+"', "+object.getUserType()+")";
        return db.executeSQL(sql);
    }

    @Override
    public int update(User object) {
        System.out.println("Update User data");
        String sql = "UPDATE Users SET name ="+object.getName()+", email ='"+object.getEmail()+"', password ='"+object.getPassword()+"', userType ='"+object.getUserType()+"', WHERE name ='"+object.getName()+"'";
        return db.executeSQL(sql);
    }

    @Override
    public int delete(User object) {
        System.out.println("Delete User data");
        String sql = "DELETE FROM Users WHERE name ='"+object.getName()+"'";
        return db.executeSQL(sql);
    }

    @Override
    public List<User> query() {
        List<User> users = new ArrayList<User>();
        String sql = "SELECT name, email, password, userType FROM Users";
        ResultSet set = db.executeRetrieveQuery(sql);
        try{
            while(set.next()){
                User user = new User();
                user.setName(set.getString("name"));
                user.setEmail(set.getString("email"));
                user.setPassword(set.getString("password"));
                user.setUserType(set.getInt("userType"));
                users.add(user);
            }

        }catch (Exception e){
            System.out.println("Something Went Wrong.."+e);
        }
        return users;
    }

    @Override
    public int count(){
        String sql = "SELECT COUNT(*) FROM Users";
        return db.executeSQL(sql);
    }


    public User queryOne(User object) {

        User user = new User();

        String sql = "SELECT * from Users WHERE email = '"+object.getEmail()+"' AND password = '"+object.getPassword()+"'";
        ResultSet set = db.executeRetrieveQuery(sql);

        try {
            if(set.next()) {
                user.setName(set.getString("name"));
                user.setEmail(set.getString("email"));
                user.setPassword(set.getString("password"));
                user.setUserType(set.getInt("userType"));
            }
        } catch (Exception e) {
            System.err.println("Something Went Wrong: "+e);
        }

        return user;

    }
}