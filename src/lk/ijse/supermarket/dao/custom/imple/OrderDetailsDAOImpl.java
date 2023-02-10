package lk.ijse.supermarket.dao.custom.imple;

import lk.ijse.supermarket.dao.custom.OrderDetailsDAO;
import lk.ijse.supermarket.dto.Order;
import lk.ijse.supermarket.dao.CRUD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    @Override
    public boolean save(Order order) throws SQLException, ClassNotFoundException {
        return CRUD.execute("INSERT INTO Supermarket.orderDetails VALUES (?,?)",
                order.getOrderId(), order.getProductId());
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, NullPointerException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Order supplier) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ResultSet searchByID(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet autoIncrementID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
