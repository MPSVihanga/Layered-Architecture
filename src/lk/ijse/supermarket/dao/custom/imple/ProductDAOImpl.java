package lk.ijse.supermarket.dao.custom.imple;

import lk.ijse.supermarket.dao.custom.ProductDAO;
import lk.ijse.supermarket.dto.ProductDTO;
import lk.ijse.supermarket.dao.CRUD;
import lk.ijse.supermarket.view.tm.ProductTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public ArrayList<ProductDTO> getAll() throws SQLException, NullPointerException, ClassNotFoundException {
        ResultSet rst = CRUD.execute("SELECT * FROM Supermarket.product");
        ArrayList<ProductTM> array= new ArrayList<>();

        while (rst.next()){
            array.add(
                    new ProductTM(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getString(4),
                            rst.getDate(5).toLocalDate(),
                            rst.getDate(6).toLocalDate(),
                            rst.getInt(7),
                            rst.getString(8),
                            rst.getDouble(9),
                            rst.getDouble(10)

                    )
            );
        }
        return array;
    }
    @Override
    public boolean save(ProductDTO product) throws SQLException, ClassNotFoundException {
        return CRUD.execute("INSERT INTO Supermarket.product VALUES (?,?,?,?,?,?,?,?,?,?)",
                product.getPid(), product.getPbName(), product.getPname(), product.getPcategory(),
                product.getMfdDate(), product.getExpDate(),product.getQty(),product.getQtyType(),product.getDiscount(),product.getPrice());

    }
    @Override
    public  boolean update(ProductDTO product) throws SQLException, ClassNotFoundException {
        return CRUD.execute("UPDATE Supermarket.product set brndName=? ,name= ? , category=? , mfdDate=? , expDate=?   where prdctId=?",
                product.getPbName(), product.getPname(), product.getPcategory(), product.getMfdDate(), product.getExpDate(), product.getPid());
    }

    @Override
    public ResultSet searchByID(String id) throws SQLException, ClassNotFoundException {
        return CRUD.execute("SELECT * from Supermarket.product where prdctId= ? ",id);
    }

    @Override
    public ResultSet autoIncrementID() throws SQLException, ClassNotFoundException {
        return CRUD.execute("SELECT prdctId FROM Supermarket.product ORDER BY prdctId DESC LIMIT 1");
    }

    @Override
    public  boolean updateQty(ProductDTO updateQty) throws SQLException, ClassNotFoundException {
        return CRUD.execute("UPDATE  Supermarket.product SET qty=? , qtyType=? WHERE prdctId=? ", updateQty.getQty(),updateQty.getQtyType(),updateQty.getPid());
    }

    @Override
    public boolean updatePOSQty(ProductDTO updateQty) throws SQLException, ClassNotFoundException {
        return CRUD.execute("UPDATE  Supermarket.product SET qty=?  WHERE prdctId=? ", updateQty.getQty(),updateQty.getPid());
    }




}
