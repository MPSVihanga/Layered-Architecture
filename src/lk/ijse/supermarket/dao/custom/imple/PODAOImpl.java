package lk.ijse.supermarket.dao.custom.imple;

import lk.ijse.supermarket.dao.custom.PODAO;
import lk.ijse.supermarket.dto.PODTO;
import lk.ijse.supermarket.dao.CRUD;
import lk.ijse.supermarket.view.tm.ProductTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PODAOImpl implements PODAO {
    @Override
    public boolean save(PODTO po) throws SQLException, ClassNotFoundException {

        return CRUD.execute("INSERT INTO Supermarket.po VALUES (?,?,?)",
                po.getPoID(), po.getPoSupID(), po.getDate());


    }

    @Override
    public ArrayList<ProductTM> getAll() throws SQLException, NullPointerException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(PODTO supplier) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ResultSet searchByID(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet autoIncrementID() throws SQLException, ClassNotFoundException {
        return CRUD.execute("SELECT poId FROM Supermarket.po ORDER BY poId DESC LIMIT 1");
    }

    @Override
    public ResultSet loadProductOnAction() throws SQLException, ClassNotFoundException {
        return CRUD.execute("SELECT * FROM Supermarket.product");
    }

    @Override
    public  ResultSet loadSupplierOnAction() throws SQLException, ClassNotFoundException {
        return CRUD.execute("SELECT * FROM Supermarket.supplier");
    }

    @Override
    public  ResultSet cmbloadProductQtyType(String pid) throws SQLException, ClassNotFoundException {
        return CRUD.execute("SELECT qtyType FROM Supermarket.product WHERE prdctId=?", pid);
    }

    @Override
    public ResultSet btnCompleteOnAction(String productID) throws SQLException, ClassNotFoundException {
        return CRUD.execute("SELECT qty FROM Supermarket.product WHERE prdctId=?",productID);
    }


}
