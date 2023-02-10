package lk.ijse.supermarket.dao.custom;

import lk.ijse.supermarket.dao.CrudDAO;
import lk.ijse.supermarket.dto.Product;

import java.sql.SQLException;

public interface ProductDAO extends CrudDAO<Product,String> {

    public  boolean updateQty(Product updateQty) throws SQLException, ClassNotFoundException;


    public  boolean updatePOSQty(Product updateQty) throws SQLException, ClassNotFoundException;
}
