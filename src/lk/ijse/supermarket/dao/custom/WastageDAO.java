package lk.ijse.supermarket.dao.custom;

import lk.ijse.supermarket.dao.CrudDAO;
import lk.ijse.supermarket.dto.WastageDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface WastageDAO extends CrudDAO<WastageDTO,String> {
    public ResultSet loadProductOnAction() throws SQLException, ClassNotFoundException ;

    public ResultSet btnAddOnAction(String pid) throws SQLException, ClassNotFoundException;
}
