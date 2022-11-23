package dao;

import model.RailwayCrossing;
import model.User;
import dao.UserDAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RailwayCrossingDAO implements DAO<RailwayCrossing>{

    DB db = DB.getDB();
    UserDAO dao = new UserDAO();

    @Override
    public int insert(RailwayCrossing object) {
        System.out.println("Insert Railway Crossing data");
        String sql = "INSERT INTO RailwayCrossings(name, address, status, personInCharge) VALUES ('"+object.getName()+"', '"+object.getAddress()+"', '"+object.getStatus()+"', "+object.getPersonInCharge().getName()+")";
        return db.executeSQL(sql);
    }

    @Override
    public int update(RailwayCrossing object) {
        System.out.println("Update Railway Crossing status data");
        String sql = "UPDATE RailwayCrossings SET status ="+object.getStatus()+" WHERE name ='"+object.getName()+"'";
        return db.executeSQL(sql);
    }

    @Override
    public int delete(RailwayCrossing object) {
        System.out.println("Delete Railway Crossing data");
        String sql = "DELETE FROM RailwayCrossings WHERE name ='"+object.getName()+"'";
        return db.executeSQL(sql);
    }

    @Override
    public int count(){
        String sql = "SELECT COUNT(*) FROM RailwayCrossings";
        return db.executeSQL(sql);
    }

    @Override
    public List<RailwayCrossing> query() {
        List<RailwayCrossing> crossings = new ArrayList<RailwayCrossing>();
        String sql = "SELECT name, address, status, personInCharge FROM RailwayCrossings";
        ResultSet set = db.executeRetrieveQuery(sql);
        try{
            while(set.next()){
                RailwayCrossing crossing = new RailwayCrossing();
                crossing.setName(set.getString("name"));
                crossing.setAddress(set.getString("address"));
                crossing.setStatus(set.getInt("status"));
                User user = new User();
                user.setName(set.getString("personInCharge"));
                User loggedUser = this.dao.queryOne(user);
                if (!loggedUser.getName().isEmpty()) {
                    crossing.setPersonInCharge(loggedUser);
                }
                crossings.add(crossing);
            }

        }catch (Exception e){
            System.out.println("Something Went Wrong.."+e);
        }
        return crossings;
    }


    public RailwayCrossing queryOne(RailwayCrossing object) {

        RailwayCrossing railwayCrossing = new RailwayCrossing();

        String sql = "SELECT * from RailwayCrossings WHERE name = '"+object.getName()+"'";
        ResultSet set = db.executeRetrieveQuery(sql);

        try {
            if(set.next()) {
                railwayCrossing.setName(set.getString("name"));
                railwayCrossing.setAddress(set.getString("address"));
                railwayCrossing.setStatus(set.getInt("status"));
            }
        } catch (Exception e) {
            System.err.println("Something Went Wrong: "+e);
        }

        return railwayCrossing;

    }
}