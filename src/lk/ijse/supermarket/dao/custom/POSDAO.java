package lk.ijse.supermarket.dao.custom;

import lk.ijse.supermarket.dao.CrudDAO;
import lk.ijse.supermarket.dto.POSDetails;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface POSDAO extends CrudDAO<POSDetails,String> {
    public  ResultSet loadProductOnAction() throws SQLException, ClassNotFoundException;
    public  ResultSet cmbLoadProductQtyCountOnAction(String pid) throws SQLException, ClassNotFoundException;
    public  ResultSet btnAddProductOnAction(String pid) throws SQLException, ClassNotFoundException;
    public  ResultSet btnCompleteOnAction(String pid) throws SQLException, ClassNotFoundException ;
}
