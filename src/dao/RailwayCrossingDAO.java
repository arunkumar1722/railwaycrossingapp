package dao;

import model.RailwayCrossing;
import model.User;

import java.sql.ResultSet;
import java.util.List;

public class RailwayCrossingDAO implements DAO<RailwayCrossing>{

    DB db = DB.getDB();

    @Override
    public int insert(RailwayCrossing object) {
        String sql = "INSERT INTO RailwayCrossings(name, address, status, personInCharge) VALUES ('"+object.getName()+"', '"+object.getAddress()+"', '"+object.getStatus()+"', "+object.getPersonInCharge().getName()+")";
        return db.executeSQL(sql);
    }

    @Override
    public int update(RailwayCrossing object) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int delete(RailwayCrossing object) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<RailwayCrossing> query() {
        // TODO Auto-generated method stub
        return null;
    }


    public RailwayCrossing queryOne(RailwayCrossing object) {

        RailwayCrossing railwayCrossing = new RailwayCrossing();

        String sql = "SELECT * from RailwayCrossings WHERE name = '"+object.getName()+"'";
        ResultSet set = db.executeRetrieveQuery(sql);

        try {
            if(set.next()) {
                railwayCrossing.setName(set.getString("name"));
                railwayCrossing.setAddress(set.getString("address"));
            }
        } catch (Exception e) {
            System.err.println("Something Went Wrong: "+e);
        }

        return railwayCrossing;

    }
}