package lk.ijse.supermarket.dao;


import lk.ijse.supermarket.view.tm.ProductTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T , ID> extends SuperDAO{

    ArrayList<T> getAll() throws SQLException, NullPointerException, ClassNotFoundException;

    boolean save(T name) throws SQLException, ClassNotFoundException;

    boolean update(T name) throws SQLException, ClassNotFoundException ;

    ResultSet searchByID(ID id) throws SQLException, ClassNotFoundException ;

    ResultSet autoIncrementID() throws SQLException, ClassNotFoundException;

}
