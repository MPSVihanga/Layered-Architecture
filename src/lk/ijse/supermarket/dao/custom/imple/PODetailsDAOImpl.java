package lk.ijse.supermarket.dao.custom.imple;

import lk.ijse.supermarket.dao.custom.PODetailsDAO;
import lk.ijse.supermarket.dto.PODetailsDTO;
import lk.ijse.supermarket.dao.CRUD;
import lk.ijse.supermarket.view.tm.ProductTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PODetailsDAOImpl implements PODetailsDAO {
@Override
    public  boolean save(PODetailsDTO poDetails) throws SQLException, ClassNotFoundException {

        return CRUD.execute("INSERT INTO Supermarket.poDetails VALUES (?,?)",
                poDetails.getPoDpoID(), poDetails.getPoDprdctID());
    }

    @Override
    public ArrayList<ProductTM> getAll() throws SQLException, NullPointerException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(PODetailsDTO supplier) throws SQLException, ClassNotFoundException {
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
