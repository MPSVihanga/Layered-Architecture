package lk.ijse.supermarket.dao.custom;

import lk.ijse.supermarket.dao.CrudDAO;
import lk.ijse.supermarket.dto.PO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface PODAO extends CrudDAO<PO,String> {

    public ResultSet loadProductOnAction() throws SQLException, ClassNotFoundException;

    public ResultSet loadSupplierOnAction() throws SQLException, ClassNotFoundException;

    public ResultSet cmbloadProductQtyType(String pid) throws SQLException, ClassNotFoundException;

    public ResultSet btnCompleteOnAction(String productID) throws SQLException, ClassNotFoundException;
}
