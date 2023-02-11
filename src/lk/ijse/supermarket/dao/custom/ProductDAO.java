package lk.ijse.supermarket.dao.custom;

import lk.ijse.supermarket.dao.CrudDAO;
import lk.ijse.supermarket.dto.ProductDTO;

import java.sql.SQLException;

public interface ProductDAO extends CrudDAO<ProductDTO,String> {

    public  boolean updateQty(ProductDTO updateQty) throws SQLException, ClassNotFoundException;


    public  boolean updatePOSQty(ProductDTO updateQty) throws SQLException, ClassNotFoundException;
}
