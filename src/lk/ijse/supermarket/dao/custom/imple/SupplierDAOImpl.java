package lk.ijse.supermarket.dao.custom.imple;

import lk.ijse.supermarket.dao.custom.SupplierDAO;
import lk.ijse.supermarket.dto.Supplier;
import lk.ijse.supermarket.dao.CRUD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {

@Override
    public ArrayList<Supplier> getAll() throws SQLException, NullPointerException, ClassNotFoundException {
        ResultSet rst = CRUD.execute("SELECT * FROM Supermarket.supplier");
        ArrayList<Supplier> array= new ArrayList<>();

        while (rst.next()){
            array.add(
                    new Supplier(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getString(4),
                            rst.getString(5),
                            rst.getDate(6).toLocalDate(),
                            rst.getString(7),
                            rst.getString(8)
                    )
            );
        }
        return array;
    }

@Override
    public boolean save(Supplier supplier) throws SQLException, ClassNotFoundException {
        return CRUD.execute("INSERT INTO Supermarket.supplier VALUES (?,?,?,?,?,?,?,?)",
                supplier.getId(),supplier.getCompanyName(),supplier.getCompanyTel(),
                supplier.getSupplierName(),supplier.getSupplierTel(),supplier.getDate(),
                supplier.getAdderss(),supplier.getCompanyEmail());

    }

@Override
    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        return CRUD.execute("UPDATE Supermarket.supplier set companyName=? ,companyTel= ? ," +
                        " supplierName=? , supplierTel=? , dateRegisterd=? , " +
                        "address=? , companyEmail=?   where supId=?",
                supplier.getCompanyName(), supplier.getCompanyTel() , supplier.getSupplierName() ,
                supplier.getSupplierTel() , supplier.getDate() , supplier.getAdderss(),
                supplier.getCompanyEmail(),supplier.getId());
    }

@Override
    public ResultSet searchByID(String id) throws SQLException, ClassNotFoundException {
        return CRUD.execute("SELECT * from Supermarket.supplier where supId= ? ",id);
    }

@Override
    public ResultSet autoIncrementID() throws SQLException, ClassNotFoundException {
        return CRUD.execute("SELECT supId FROM Supermarket.supplier ORDER BY supId DESC LIMIT 1");
    }

}
