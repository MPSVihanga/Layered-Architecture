package lk.ijse.supermarket.dao.custom;

import lk.ijse.supermarket.dao.CrudDAO;
import lk.ijse.supermarket.dto.Stock;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface StockDAO extends CrudDAO<Stock,String> {
    public ResultSet comboLoadProductNames() throws SQLException, ClassNotFoundException;
}
