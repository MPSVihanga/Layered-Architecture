package lk.ijse.supermarket.dao.custom.imple;

import lk.ijse.supermarket.dao.custom.PODetailsDAO;
import lk.ijse.supermarket.dto.PODetails;
import lk.ijse.supermarket.dao.CRUD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PODetailsDAOImpl implements PODetailsDAO {
@Override
    public  boolean save(PODetails poDetails) throws SQLException, ClassNotFoundException {

        return CRUD.execute("INSERT INTO Supermarket.poDetails VALUES (?,?)",
                poDetails.getPoDpoID(), poDetails.getPoDprdctID());
    }

    @Override
    public ArrayList<PODetails> getAll() throws SQLException, NullPointerException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(PODetails supplier) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ResultSet searchByID(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet autoIncrementID() throws SQLException, ClassNotFoundException {
        return null;
    }

}
