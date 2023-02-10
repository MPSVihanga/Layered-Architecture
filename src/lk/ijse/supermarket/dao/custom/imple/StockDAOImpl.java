package lk.ijse.supermarket.dao.custom.imple;

import lk.ijse.supermarket.dao.custom.StockDAO;
import lk.ijse.supermarket.dto.Stock;
import lk.ijse.supermarket.dao.CRUD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockDAOImpl implements StockDAO {
    @Override
    public ArrayList<Stock> getAll() throws SQLException, NullPointerException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Stock name) throws SQLException, ClassNotFoundException {
        return false;
    }
    @Override
    public boolean update(Stock updateStock) throws SQLException, ClassNotFoundException {
       return CRUD.execute("UPDATE Supermarket.product SET sellingDiscount = ?, sellingUnitPrice = ?  WHERE name=? ",
               updateStock.getDiscount(),updateStock.getUnitPrice(), updateStock.getPname());
    }

    @Override
    public ResultSet searchByID(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet autoIncrementID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet comboLoadProductNames() throws SQLException, ClassNotFoundException {
        return CRUD.execute("SELECT name FROM Supermarket.product");
    }

}
