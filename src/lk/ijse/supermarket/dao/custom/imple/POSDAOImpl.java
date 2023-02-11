package lk.ijse.supermarket.dao.custom.imple;

import lk.ijse.supermarket.dao.custom.POSDAO;
import lk.ijse.supermarket.dto.POSDetailsDTO;
import lk.ijse.supermarket.dao.CRUD;
import lk.ijse.supermarket.view.tm.ProductTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class POSDAOImpl implements POSDAO {
    @Override
    public ArrayList<ProductTM> getAll() throws SQLException, NullPointerException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(POSDetailsDTO name) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(POSDetailsDTO name) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ResultSet searchByID(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet autoIncrementID() throws SQLException, ClassNotFoundException {
        return CRUD.execute("SELECT oId FROM Supermarket.orders ORDER BY oId DESC LIMIT 1");
    }
    @Override
    public  ResultSet loadProductOnAction() throws SQLException, ClassNotFoundException {
        return CRUD.execute("SELECT * FROM Supermarket.product");
    }
    @Override
    public ResultSet cmbLoadProductQtyCountOnAction(String pid) throws SQLException, ClassNotFoundException {
        return CRUD.execute("SELECT qty FROM Supermarket.product WHERE prdctId=?",pid);
    }
    @Override
    public  ResultSet btnAddProductOnAction(String pid) throws SQLException, ClassNotFoundException {
        return CRUD.execute("SELECT sellingDiscount,sellingUnitPrice FROM Supermarket.product WHERE prdctId=?",pid);
    }
    @Override
    public ResultSet btnCompleteOnAction(String pid) throws SQLException, ClassNotFoundException {
        return  CRUD.execute("SELECT qty FROM Supermarket.product WHERE prdctId=?", pid);
    }
}
