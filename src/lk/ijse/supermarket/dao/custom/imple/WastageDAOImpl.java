package lk.ijse.supermarket.dao.custom.imple;

import lk.ijse.supermarket.dao.custom.WastageDAO;
import lk.ijse.supermarket.dto.WastageDTO;
import lk.ijse.supermarket.dao.CRUD;
import lk.ijse.supermarket.view.tm.ProductTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WastageDAOImpl implements WastageDAO {
    @Override
    public ArrayList<ProductTM> getAll() throws SQLException, NullPointerException, ClassNotFoundException {
        ResultSet rst = CRUD.execute("SELECT * FROM Supermarket.WastageTM");
        ArrayList<WastageDTO> array= new ArrayList<>();

        while (rst.next()){
            array.add(
                    new WastageDTO(
                            rst.getString(1),
                            rst.getString(5),
                            rst.getDate(2).toLocalDate(),
                            rst.getInt(4),
                            rst.getString(3)

                    )
            );
        }
        return array;
    }

    @Override
    public boolean save(WastageDTO wastage) throws SQLException, ClassNotFoundException {
        return CRUD.execute("INSERT INTO Supermarket.WastageTM VALUES (?,?,?,?,?)",
                wastage.getWastageId(), wastage.getDate(), wastage.getReason(),wastage.getQty(), wastage.getProductId());

    }

    @Override
    public boolean update(WastageDTO name) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ResultSet searchByID(String s) throws SQLException, ClassNotFoundException {
        return null;
    }
    @Override
    public ResultSet autoIncrementID() throws SQLException, ClassNotFoundException {
        return CRUD.execute("SELECT wastageId FROM Supermarket.WastageTM ORDER BY wastageId DESC LIMIT 1");
    }
@Override
    public ResultSet loadProductOnAction() throws SQLException, ClassNotFoundException {
        return CRUD.execute("SELECT * FROM Supermarket.product");
    }
    @Override
    public ResultSet btnAddOnAction(String pid) throws SQLException, ClassNotFoundException {
        return CRUD.execute("SELECT qty FROM Supermarket.product WHERE prdctId=?",pid);
    }

}
